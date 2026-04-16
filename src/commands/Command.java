package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import managers.CollectionManager;
import models.Coordinates;
import models.Location;
import models.Route;
import utility.InteractiveInputReader;
import utility.LocalDateTimeAdapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public abstract class Command {
    CollectionManager manager;
    InteractiveInputReader reader;

    public Command(InteractiveInputReader reader, CollectionManager manager) {
        this.reader = reader;
        this.manager = manager;
    }

    public abstract void execute();
    public abstract void execute(String args);
    public abstract String getName();
    public abstract String getDiscription();

    public Integer readId(InteractiveInputReader reader){
        while(true){
            try{
                Integer id = reader.readInteger("Введите id: ");
                if (manager.findId(id) != null) return id;
                else{
                    System.out.println("Ошибка: маршрут не найден. Введите другой ID:");

                }
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public String readName(InteractiveInputReader reader, Route route){
        while(true){
            try{
                String name = reader.readString("Введите имя: ");
                route.setName(name);
                return name;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Float readCoordinatesX(InteractiveInputReader reader, Coordinates coordinates){
        while(true){
            try{
                Float x = reader.readFloat("Введите координату X: ");
                coordinates.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Long readCoordinatesY(InteractiveInputReader reader, Coordinates coordinates){
        while(true){
            try{
                Long y = reader.readLong("Введите координату Y: ");
                coordinates.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Integer readLocationXTo(InteractiveInputReader reader, Location to){
        while(true){
            try{
                Integer x = reader.readInteger("Введите координату X точки прибытия: ");
                to.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationYTo(InteractiveInputReader reader, Location to){
        while(true){
            try{
                Double y = reader.readDouble("Введите координату Y точки прибытия: ");
                to.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public double  readLocationZTo(InteractiveInputReader reader, Location to){
        while(true){
            try{
                double z = reader.readDouble("Введите координату Z точки прибытия: ");
                to.setZ(z);
                return z;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Integer readLocationXFrom(InteractiveInputReader reader, Location from){
        while(true){
            try{
                Integer x = reader.readInteger("Введите координату X точки отправления: ");
                from.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationYFrom(InteractiveInputReader reader, Location from){
        while(true){
            try{
                Double y = reader.readDouble("Введите координату Y точки отправления: ");
                from.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public double  readLocationZFrom(InteractiveInputReader reader, Location from){
        while(true){
            try{
                double z = reader.readDouble("Введите координату Z точки отправления: ");
                from.setZ(z);
                return z;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public long readDistance(InteractiveInputReader reader, Route route) {
        while (true) {
            try {
                long distance = reader.readLong("Введите distance: ");
                route.setDistance(distance);
                return distance;
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ещё раз");
            }
        }
    }
    public Route parseRoute(String json){
        Gson gson = new GsonBuilder().
                registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).
                setPrettyPrinting().
                create();
        Type type = new TypeToken<Route>(){}.getType();
        return gson.fromJson(json, type);

    }
}
