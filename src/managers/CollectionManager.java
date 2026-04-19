package managers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exeptions.RouteNotFoundException;
import exeptions.ValidationException;
import models.Route;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class CollectionManager {
    public HashSet<Route> list = new HashSet<>();
    private LocalDateTime lastInitTime;
    private String fileName;
    private int counter = 1;
    public CollectionManager(){
    }

    public CollectionManager(String fileName) {
        this.fileName = fileName;
        this.lastInitTime = LocalDateTime.now();
    }

    // метод поиска маршрута по его id
    public Route findId(int id){
        for (Route r : list) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
    public void add(Route route){
        route.setCreationDate(LocalDateTime.now());
        route.setId(counter);
        if(!route.validate()){
            throw new ValidationException("некорректный маршрут");
        }
        ++counter;
        list.add(route);
    }

    public void update(int id, Route newRoute){
        Route oldRoute = findId(id);
        if (oldRoute != null) {
            list.remove(oldRoute);
            newRoute.setId(id);
            newRoute.setCreationDate(LocalDateTime.now());
            list.add(newRoute);
        } else {
            throw new RouteNotFoundException("Маршрут с таким ID не найден");
        }

    }
    public void remove(int id){
        Route route = findId(id);
        if(route == null){
            throw new RouteNotFoundException("Incorrect id, route not found");
        }
        list.remove(route);
    }
    public void clear(){
        counter = 1;
        list.clear();
    }
    public void addIfMax(Route route) {
        if (list.isEmpty()) {
            route.setId(++counter);
            list.add(route);
        } else {
            Route last = null;
            for (Route r : list) {
                last = r;
            }
            if (route.compareTo(last) > 0) {
                route.setId(++counter);
                list.add(route);
            }
        }
    }
    public void removeGreater(Route route) {
        List<Route> routesDelete = new ArrayList<>();
        for(Route r : list) {
            if (r.compareTo(route) > 0) {
                routesDelete.add(r);
            }
        }
        list.removeAll(routesDelete);
    }
    public void removeLower(Route route){
        List<Route> routesToDelete = new ArrayList<>();
        for(Route r : list){
            if (r.compareTo(route)< 0){
                routesToDelete.add(r);
            }
        }
        list.removeAll(routesToDelete);
    }
    public double averageOfDistance(){
        if(list.isEmpty()){
            throw new RuntimeException("Коллекция пустая");
        }
        double sum = 0;
        for(Route route: list){
            sum += route.getDistance();
        }
        double average = sum / list.size();
        return average;
    }


    public HashSet<Route> filterGreaterThanDistance(long maxDist){
        HashSet<Route> routes= new HashSet<>();
        for(Route r : list){
            if(r.getDistance() > maxDist){
                routes.add(r);
            }
        }
        return routes;
    }

    public HashSet<Route> getList() {
        return list;
    }
    public void load(HashSet<Route> routes){
        if (routes == null){
            System.out.println("Ошибка: Передана пустая коллекция");
            return;
        }
        File file = new java.io.File(this.fileName);
        if(file.exists() && !file.canRead()){
            System.err.println("Ошибка: Нет прав на чтение файла " + fileName);
            return;
        }
        for(Route r : routes){
            if(counter < r.getId()){
                counter = r.getId();
            }
        }
        counter++;
        list = routes;
    }
    public void save() {
        if (this.fileName == null) {
            System.err.println("Ошибка: Путь к файлу не определен!");
            return;
        }

        java.io.File file = new java.io.File(this.fileName);

        // 1. Проверка папки
        java.io.File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            System.err.println("Ошибка: Папка не существует: " + parentDir.getAbsolutePath());
            return;
        }

        // 2. Проверка прав
        if (file.exists() && !file.canWrite()) {
            System.err.println("Ошибка: Нет прав на запись в файл!");
            return;
        }

        // 3. Запись
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new utility.LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

            // Превращаем список из менеджера в JSON
            String json = gson.toJson(this.getList());
            writer.write(json);

            System.out.println("Коллекция успешно сохранена в: " + file.getName());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    public String getInfo() {
        return "Сведения о коллекции:\n" +
                "  Тип: " + list.getClass().getSimpleName() + "\n" +
                "  Тип элементов: " + Route.class.getSimpleName() + "\n" +
                "  Дата инициализации: " + lastInitTime + "\n" +
                "  Количество элементов: " + list.size() + "\n" +
                "  Путь к файлу: " + (fileName != null ? fileName : "не задан");
    }
}
