package commands;
import common.Response;
import exeptions.ValidationException;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

public  class AddCommand extends Command{

    public AddCommand(CollectionManager manager, InteractiveInputReader reader) {
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

        manager.add(route);

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try {
            if (payLoad == null) {
                return new Response(false, "Ошибка: Данные для добавления маршрута не получены.", null);
            }
            Route route = (Route) payLoad;
            manager.add(route);
            return new Response(true, "Маршрут добавлен", null);
        } catch (ValidationException e) {
            return new Response(false, "Ошибка валидации на сервере: " + e.getMessage(), null);
        }catch (Exception e){
            return new Response(false,e.getMessage(), null);
        }
    }

    @Override
    public void execute(String args) {
        /*
        Route route = parseRoute(args);
        manager.add(route);
         */
    }


    @Override
    public String getName(){
        return "add";
    }

    @Override
    public String getDiscription(){
        return "добавить новый элемент в коллекцию";
    }
}
