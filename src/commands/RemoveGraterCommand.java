package commands;

import managers.CollectionManager;

import models.Coordinates;
import models.Location;
import models.Route;
import utility.InteractiveInputReader;

public class RemoveGraterCommand extends Command{

    public RemoveGraterCommand(CollectionManager manager, InteractiveInputReader reader){
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
        int sizeBefore = manager.getList().size();
        manager.removeGreater(route);
        int removed = sizeBefore - manager.getList().size();
        System.out.println("Удалено элементов: " + removed);
    }

    @Override
    public void execute(String args){
        Route route = parseRoute(args);
        int sizeBefore = manager.getList().size();
        manager.removeGreater(route);
        int removed = sizeBefore - manager.getList().size();
        System.out.println("Удалено элементов: " + removed);
    }

    @Override
    public String getName(){
        return "remove_greater";
    }

    @Override
    public String getDiscription(){
        return "удаляет из коллекции все элементы, превышающие заданный";
    }
}
