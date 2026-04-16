package commands;
import managers.CollectionManager;
import models.Coordinates;
import models.Location;
import models.Route;
import utility.InteractiveInputReader;
import java.util.Scanner;


public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {
        try{
            Route route = parseRoute(args);
            if(manager.findId(route.getId()) != null){
                manager.update(route.getId(), route);
            }else{
                System.out.println("Ошибка: маршрут с таким ID не найден");
            }
        }catch (Exception e){
            System.out.println("Ошибка при десериализации JSON: " + e.getMessage());
        }
    }

    @Override
    public void execute() {
        Integer id = readId(reader);
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

        manager.update(id,route);
    }



    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDiscription() {
        return "обновляет значение элемента коллекции, id которого равен заданному";
    }
}


