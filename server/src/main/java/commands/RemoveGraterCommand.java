package commands;

import common.Response;
import managers.CollectionManager;

import models.Route;
import utility.InteractiveInputReader;

public class RemoveGraterCommand extends Command{

    public RemoveGraterCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute() {
        /*
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
        int sizeBefore = manager.getList().size();
        manager.removeGreater(route);
        int removed = sizeBefore - manager.getList().size();
        System.out.println("Удалено элементов: " + removed);

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try {
            if (payLoad == null) {
                return new Response(false, "Ошибка сервера: отсутствует объект для сравнения.", null);
            }
            Route route = (Route) payLoad;
            int oldSize = manager.getList().size();
            manager.removeGreater(route);
            int removed = oldSize - manager.getList().size();
            String message = "Удалено элементов, превышающих заданный:" + removed;
            return new Response(true, message, null);
        }catch (Exception e){
            return new Response(false, "Ошибка сервера при удалении больших элементов: " + e.getMessage(), null);
        }
    }

    @Override
    public void execute(String args){
        /*
        Route route = parseRoute(args);
        int sizeBefore = manager.getList().size();
        manager.removeGreater(route);
        int removed = sizeBefore - manager.getList().size();
        System.out.println("Удалено элементов: " + removed);

         */
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
