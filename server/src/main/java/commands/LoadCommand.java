package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;
import utility.LocalDateTimeAdapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;

public class LoadCommand extends Command{

    public LoadCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute() {
        String filePath = System.getenv("LAB_FILE_NAME");
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Ошибка инициализации: Переменная окружения 'LAB_FILE_NAME' не установлена!");
            return;
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] bytes = bis.readAllBytes();
            String json = new String(bytes);

            com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new utility.LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

            java.lang.reflect.Type type = new TypeToken<HashSet<Route>>(){}.getType();
            HashSet<Route> routes = gson.fromJson(json, type);

            if (routes == null) {
                System.err.println("Предупреждение при старте: Файл пуст или содержит некорректный JSON.");
                return;
            }
            manager.load(routes);
            System.out.println("Коллекция успешно загружена при старте сервера. Элементов: " + routes.size());
        } catch (IOException e) {
            System.err.println("Ошибка при старте сервера: не удалось прочитать файл: " + e.getMessage());
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("Ошибка при старте сервера: синтаксическая ошибка в JSON-файле.");
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка при старте сервера: " + e.getMessage());
        }
    }

    @Override
    public Response execute(Object payLoad) {
        String filePath = System.getenv("LAB_FILE_NAME");
        if(filePath == null || filePath.isEmpty()){
            return new Response(false,"Ошибка сервера: Переменная окружения 'LAB_FILE_NAME' не установлена!", null);
        }
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))){
            byte [] bytes = bis.readAllBytes();
            String json = new String(bytes);
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).
                    setPrettyPrinting().
                    create();
            Type type = new TypeToken<HashSet<Route>>(){}.getType();
            HashSet<Route> routes = gson.fromJson(json, type);
            if(routes == null){
                return new Response(false, "Ошибка: Файл пуст или содержит некорректный JSON.", null);
            }
            manager.load(routes);
            return new Response(true, "Коллекция успешно загружена на сервере из файла.", null);
        }catch (IOException e){
            return new Response(false, "Ошибка сервера при чтении файла: " + e.getMessage(), null);
        }catch (com.google.gson.JsonSyntaxException e){
            return new Response(false,"Ошибка сервера: синтаксическая ошибка в JSON-файле.", null);
        }catch (Exception e){
            return new Response(false, "Непредвиденная ошибка сервера при загрузке: " + e.getMessage(), null);
        }
    }

    @Override
    public String getName() {
        return "load";
    }

    @Override
    public String getDiscription() {
        return "загрузить коллекцию из файла";
    }
}
