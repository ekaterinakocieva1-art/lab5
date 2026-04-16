package commands;

import exeptions.ScriptExecutingError;
import managers.CollectionManager;
import managers.CommandInvoker;
import utility.InteractiveInputReader;

import java.io.File;
import java.util.*;

public class ExecuteScriptCommand extends Command{
    private CommandInvoker commandInvoker;
    private Deque<String> stack = new ArrayDeque<>();
    private Set<String> active = new HashSet<>();

    private void start(String fileName){
        if(active.contains(fileName)){
            throw new ScriptExecutingError("Обнаружена циклическая зависимость");
        }
        stack.push(fileName);
        active.add(fileName);
    }
    private void exit(){
        active.remove(stack.pop());
    }
    public ExecuteScriptCommand(CommandInvoker commandInvoker, InteractiveInputReader reader, CollectionManager manager) {
        super(reader, manager);
        this.commandInvoker = commandInvoker;

    }
    private String readFileName(){
        while (true) {
            try {
                String fileName = reader.readString("Введите название файла: ");

                return fileName;
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ещё раз");
            }
        }
    }

    @Override
    public void execute() {
        execute(readFileName());
    }

    @Override
    public void execute(String args) {
        try{
            start(args);
            try(Scanner scanner = new Scanner(new File(args))) {
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(" ", 2);
                if(line.length == 1){
                    String commandName = line[0];
                    if(!commandName.isEmpty()){
                        commandInvoker.execute(commandName);
                    }
                } else if (line.length > 1) {
                    String commandName = line[0];
                    String argue = line[1];
                    if(!commandName.isEmpty()){
                        commandInvoker.execute(commandName, argue);
                    }
                }
            }
            }catch (Exception e){
                throw new ScriptExecutingError("Ошибка выполнения скрипта");
            }
        }finally {
            exit();
        }


        System.out.println("выполняется скрипт");
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDiscription() {
        return "считывает и исполняет скрипт из указанного файла";
    }
}
