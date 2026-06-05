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
import java.util.HashSet;
import java.util.stream.Collectors;

public class CollectionManager {
    public HashSet<Route> list = new HashSet<>();
    private LocalDateTime lastInitTime;
    private String fileName;
    private int counter = 1;

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
    }

    public CollectionManager(String fileName) {
        this.fileName = fileName;
        this.lastInitTime = LocalDateTime.now();
    }
    public Route findId(int id) {
        return list.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Route route) {
        route.setCreationDate(LocalDateTime.now());
        route.setId(counter++); // Сначала присваиваем, потом увеличиваем
        if (!route.validate()) {
            throw new ValidationException("Некорректный маршрут");
        }
        list.add(route);
    }

    public void update(int id, Route newRoute) {
        Route oldRoute = findId(id);
        if (oldRoute != null) {
            if (!newRoute.validate()) {
                throw new ValidationException("Некорректные новые данные для маршрута");
            }
            list.remove(oldRoute);
            newRoute.setId(id);
            newRoute.setCreationDate(oldRoute.getCreationDate());
            list.add(newRoute);
        } else {
            throw new RouteNotFoundException("Маршрут с таким ID не найден");
        }
    }

    public void remove(int id) {
        Route route = findId(id);
        if (route == null) {
            throw new RouteNotFoundException("Маршрут с таким ID не найден");
        }
        list.remove(route);
    }

    public void clear() {
        counter = 1;
        list.clear();
    }

    public void addIfMax(Route route) {
        if (!route.validate()) {
            throw new ValidationException("Некорректный маршрут");
        }
        Route maxRoute = list.stream().max(Route::compareTo).orElse(null);
        if (maxRoute == null || route.compareTo(maxRoute) > 0) {
            route.setCreationDate(LocalDateTime.now());
            route.setId(counter++); // Единый стиль генерации ID
            list.add(route);
        } else {
            throw new ValidationException("Элемент меньше максимального в коллекции");
        }
    }

    public void removeGreater(Route route) {
        list.removeIf(r -> r.compareTo(route) > 0);
    }

    public void removeLower(Route route) {

        list.removeIf(r -> r.compareTo(route) < 0);
    }

    public double averageOfDistance() {
        if (list.isEmpty()) {
            throw new RuntimeException("Коллекция пустая");
        }
        return list.stream()
                .mapToDouble(Route::getDistance)
                .average()
                .orElse(0.0);
    }

    public HashSet<Route> filterGreaterThanDistance(long maxDist) {
        return list.stream()
                .filter(r -> r.getDistance() > maxDist)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public HashSet<Route> getList() {
        return list;
    }

    public void load(HashSet<Route> routes) {
        if (routes == null) {
            System.out.println("Предупреждение: Передана пустая коллекция или файл не найден");
            this.counter = 1;
            return;
        }

        if (this.fileName != null) {
            File file = new File(this.fileName);
            if (file.exists() && !file.canRead()) {
                System.err.println("Ошибка: Нет прав на чтение файла " + fileName);
                return;
            }
        }

        list = routes;

        int maxId = list.stream()
                .mapToInt(Route::getId)
                .max()
                .orElse(0);
        this.counter = maxId + 1;
    }

    public void save() {
        if (this.fileName == null) {
            System.err.println("Ошибка: Путь к файлу не определен!");
            return;
        }

        File file = new File(this.fileName);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            System.err.println("Ошибка: Папка не существует: " + parentDir.getAbsolutePath());
            return;
        }

        if (file.exists() && !file.canWrite()) {
            System.err.println("Ошибка: Нет прав на запись в файл!");
            return;
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new utility.LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

            String json = gson.toJson(this.getList());
            writer.write(json);
            System.out.println("Коллекция успешно сохранена в файл.");
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
