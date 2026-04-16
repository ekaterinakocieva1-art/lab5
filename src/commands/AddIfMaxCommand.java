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
import java.util.Collections;

public class AddIfMaxCommand extends Command{

    public AddIfMaxCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute() {
        Route route = new Route();
        Coordinates coordinates = new Coordinates();
        Location to = new Location();
        Location from = new Location();

        readName(reader, route);
        readDistance(reader, route);

        readCoordinatesX(reader, coordinates);
        readCoordinatesY(reader, coordinates);
        route.setCoordinates(coordinates);

        readLocationXFrom(reader, from);
        readLocationYFrom(reader, from);
        readLocationZFrom(reader, from);
        route.setFrom(from);

        readLocationXTo(reader, to);
        readLocationYTo(reader, to);
        readLocationZTo(reader, to);
        route.setTo(to);
        if(manager.getList().isEmpty()){
            manager.add(route);
            System.out.println("Коллекция была пуста, элемент добавлен.");
        }else {
            Route maxRoute = Collections.max(manager.getList());
            if(route.compareTo(maxRoute) > 0){
                manager.add(route);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("Элемент не добавлен: он меньше или равен максимальному.");
            }
        }
    }

    @Override
    public void execute(String args){
        Route route = parseRoute(args);
        if(manager.getList().isEmpty()){
            manager.add(route);
            System.out.println("Коллекция была пуста, элемент добавлен.");
        }else {
            Route maxRoute = Collections.max(manager.getList());
            if(route.compareTo(maxRoute) > 0){
                manager.add(route);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("Элемент не добавлен: он меньше или равен максимальному.");
            }
        }
    }

    @Override
    public String getName(){
        return "add_if_max";
    }

    @Override
    public String getDiscription(){
        return "добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
