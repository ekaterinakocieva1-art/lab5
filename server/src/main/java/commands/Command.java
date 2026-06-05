package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import common.Response;
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
    public abstract Response execute(Object payLoad);
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
    public String readName(InteractiveInputReader reader, Route route, boolean nullable){
        while(true){
            try{
                String name = reader.readString("Введите имя: ");
                if(nullable && name.isEmpty())return null;
                route.setName(name);
                return name;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Float readCoordinatesX(InteractiveInputReader reader, Coordinates coordinates,boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату X: ");
                if(nullable && input.isEmpty())return null;
                Float x = Float.parseFloat(input);
                coordinates.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Long readCoordinatesY(InteractiveInputReader reader, Coordinates coordinates, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату Y: ");
                if(nullable && input.isEmpty())return null;
                Long y = Long.parseLong(input);
                coordinates.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Integer readLocationXTo(InteractiveInputReader reader, Location to, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату X точки прибытия: ");
                if(nullable && input.isEmpty())return null;
                Integer x = Integer.parseInt(input);
                to.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationYTo(InteractiveInputReader reader, Location to, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату Y точки прибытия: ");
                if(nullable && input.isEmpty())return null;
                Double y = Double.parseDouble(input);
                to.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationZTo(InteractiveInputReader reader, Location to, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату Z точки прибытия: ");
                if(nullable && input.isEmpty())return null;
                double z = Double.parseDouble(input);
                to.setZ(z);
                return z;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Integer readLocationXFrom(InteractiveInputReader reader, Location from, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату X точки отправления: ");
                if(nullable && input.isEmpty())return null;
                Integer x = Integer.parseInt(input);
                from.setX(x);
                return x;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationYFrom(InteractiveInputReader reader, Location from, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату Y точки отправления: ");
                if(nullable && input.isEmpty())return null;
                Double y = Double.parseDouble(input);
                from.setY(y);
                return y;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Double  readLocationZFrom(InteractiveInputReader reader, Location from, boolean nullable){
        while(true){
            try{
                String input = reader.readString("Введите координату Z точки отправления: ");
                if(nullable && input.isEmpty())return null;
                double z = Double.parseDouble(input);
                from.setZ(z);
                return z;
            }catch (IllegalArgumentException e){
                System.err.println("Ошибка: " + e.getMessage());
                System.err.println("Попробуй еще раз");
            }
        }
    }
    public Long readDistance(InteractiveInputReader reader, Route route, boolean nullable) {
        while (true) {
            try {
                String input = reader.readString("Введите distance: ");
                if(nullable && input.isEmpty())return null;
                long distance = Long.parseLong(input);
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
