package common;

import exeptions.CommandAbortException;
import models.Coordinates;
import models.Location;
import models.Route;
import utility.InteractiveInputReader;

public class RouteReader {
    public Route buildRoute(InteractiveInputReader reader) {
        Route route = new Route();
        Coordinates coordinates = new Coordinates();
        Location to = new Location();
        Location from = new Location();

        readName(reader, route, false);
        readDistance(reader, route, false);

        readCoordinatesX(reader, coordinates, false);
        readCoordinatesY(reader, coordinates, false);
        route.setCoordinates(coordinates);

        readLocationXFrom(reader, from, false);
        readLocationYFrom(reader, from, false);
        readLocationZFrom(reader, from, false);
        route.setFrom(from);

        readLocationXTo(reader, to, false);
        readLocationYTo(reader, to, false);
        readLocationZTo(reader, to, false);
        route.setTo(to);
        return route;
    }
    public String readName(InteractiveInputReader reader, Route route, boolean nullable){
        while(true){
            try{
                String name = reader.readString("Введите имя: ");
                if(name.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
                if(input.equals("exit")){
                    throw new CommandAbortException("Выполнение команды прервано пользователем.");
                }
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
}
