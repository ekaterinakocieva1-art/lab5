import common.RouteReader;
import common.Request;
import common.Response;
import exeptions.CommandAbortException;
import exeptions.ScriptStopException;
import models.Route;
import utility.InteractiveInputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MainC {
    // Стек для отслеживания запущенных скриптов и предотвращения бесконечной рекурсии
    private static final Deque<String> scriptStack = new ArrayDeque<>();
    public static void main(String[] args) {

        ClientNetwork network = new ClientNetwork("127.0.0.1", 1234);
        Scanner scan = new Scanner(System.in);
        InteractiveInputReader reader = new InteractiveInputReader();
        RouteReader routeReader = new RouteReader();
        System.out.println("Клиентское приложение запущено. Введите команду");
        while (true) {
            System.out.print("> ");
            if (!scan.hasNextLine()) {
                System.out.println("Завершение работы клиента");
                break;
            }

            String input = scan.nextLine().trim();
            if (input.isEmpty()) continue;
            if (input.equals("exit")) break;

            try {
                processCommand(input, scan, reader, routeReader, network);
            } catch (ScriptStopException e) {
                System.out.println("Выполнение скрипта остановлено.");
            } catch (java.util.NoSuchElementException e) {
                break;
            }catch (CommandAbortException e){
                System.out.println("Выполнение скрипта остановлено пользователем");
            }
        }
    }
    private static void processCommand(String input, Scanner scan, InteractiveInputReader reader, RouteReader routeReader, ClientNetwork network) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0].toLowerCase();
        String arg = (parts.length > 1) ? parts[1] : null;
        if (commandName.equals("execute_script")) {
            if (arg == null || arg.isEmpty()) {
                System.out.print("Введите название файла скрипта: ");
                arg = scan.nextLine().trim();
                if (arg.isEmpty()) return;
            }
            Request request = new Request(commandName, arg);
            sendRequestToServer(request, network, reader);
            executeScript(arg, scan, reader, routeReader, network);
            return;
        }
        if (commandName.equals("update")) {
            if (arg == null || arg.isEmpty()) {
                System.out.print("Введите id для команды update: ");
                arg = scan.nextLine().trim();
                if (arg.isEmpty()) return;
            }
            Request request = parseInputToRequest(commandName, arg, scan, reader, routeReader);
            if (request != null) {
                sendRequestToServer(request, network, reader);
            }
            return;
        }
        Request request = parseInputToRequest(commandName, arg, scan, reader, routeReader);
        if (request != null) {
            sendRequestToServer(request, network, reader);
        }
    }
    private static Request parseInputToRequest(String commandName, String arg, Scanner scan, InteractiveInputReader reader, RouteReader routeReader){
        switch (commandName){
            case "add":
            case "add_if_max":
            case "remove_greater":
            case "remove_lower":
                if (arg != null && arg.trim().startsWith("{")) {
                    return new Request(commandName, arg.trim());
                }
                return new Request(commandName, routeReader.buildRoute(reader));

            case "update":
                if (arg == null || arg.isEmpty()){
                    System.out.print("Введите id для команды update: ");
                    return null;
                }
                if (arg.trim().startsWith("{")) {
                    return new Request(commandName, arg.trim());
                }
                try {
                    int id = Integer.parseInt(arg);
                    Route updateElement = routeReader.buildRoute(reader);
                    updateElement.setId(id);
                    return new Request(commandName, updateElement);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: ID должен быть целым числом.");
                    return null;
                }

            case "remove_by_id":
            case "execute_script":
            case "filter_greater_than_distance":
                if (arg == null || arg.isEmpty()) {
                    System.out.print("Введите аргумент для команды " + commandName + ": ");
                    arg = scan.nextLine().trim();
                }
                return new Request(commandName, arg);

            case "help":
            case "info":
            case "show":
            case "save":
            case "clear":
            case "average_of_distance":
            case "print_ascending":
                return new Request(commandName, null);

            default:
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
                return null;
        }
    }
    private static void executeScript(String fileName, Scanner consoleScan, InteractiveInputReader reader, RouteReader routeReader, ClientNetwork network) {
        if (scriptStack.contains(fileName)) {
            throw new ScriptStopException("Обнаружена циклическая зависимость скриптов! Файл '" + fileName + "' уже запущен.");
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("Ошибка: Файл скрипта '" + fileName + "' не найден.");
            return;
        }
        Scanner previousScanner = reader.getScanner();

        try (Scanner fileScanner = new Scanner(file)) {
            scriptStack.push(fileName);
            System.out.println("Начало выполнения скрипта: " + fileName);
            reader.setScanner(fileScanner);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                System.out.println("Исполнение команды из скрипта: " + line);
                processCommand(line, fileScanner, reader, routeReader, network);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при открытии файла: " + e.getMessage());
        } catch (ScriptStopException e) {
            System.err.println("Выполнение текущего скрипта прервано: " + e.getMessage());
        } finally {
            if (!scriptStack.isEmpty() && scriptStack.peek().equals(fileName)) {
                scriptStack.pop();
            }
            if (scriptStack.isEmpty()) {
                reader.resetScanner();
            } else {
                reader.setScanner(previousScanner);
            }
            System.out.println("Выполнение скрипта " + fileName + " завершено.");
        }
    }

    private static void sendRequestToServer(Request request, ClientNetwork network, InteractiveInputReader reader) {
        boolean processed = false;
        while (!processed) {
            try {
                network.connect();
                network.sendRequest(request);
                Response response = network.receiveResponse();

                System.out.println(response.getMessage());
                if (response.getPayload() != null) {
                    System.out.println(response.getPayload());
                }
                processed = true;
                network.disconnect();
                if (reader.isFromFile() && !response.isSuccess()) {
                        throw new ScriptStopException(response.getMessage());
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Сервер временно недоступен. Повторная попытка через 2 секунды");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

}