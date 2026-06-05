package commands;
import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;


public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {
        /*
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

         */
    }

    @Override
    public void execute() {
        /*
        Integer id = readId(reader);
        Route route = manager.findId(id).copy();
        Coordinates coordinates = new Coordinates();
        Location to = new Location();
        Location from = new Location();

        readName(reader, route, true);
        readDistance(reader, route, true);

        readCoordinatesX(reader, coordinates, true);
        readCoordinatesY(reader, coordinates, true);
        route.setCoordinates(coordinates);

        readLocationXFrom(reader, from, true);
        readLocationYFrom(reader, from,true);
        readLocationZFrom(reader, from,true);
        route.setFrom(from);

        readLocationXTo(reader, to, true);
        readLocationYTo(reader, to, true);
        readLocationZTo(reader, to, true);
        route.setTo(to);

        manager.update(id,route);

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try {
            if (payLoad == null) {
                return new Response(false, "Ошибка сервера: отсутствуют данные для обновления.", null);
            }
            Route route = (Route) payLoad;
            int id = route.getId();
            if (manager.findId(id) == null) {
                return new Response(false, "Ошибка: маршрут с ID " + id + " не найден в коллекции.", null);
            }
            manager.update(id, route);
            return new Response(true, "Маршрут с ID " + id + " успешно обновлен.", null);
        }catch (ClassCastException e){
            return new Response(false, "Ошибка сервера: неверный формат данных.", null);
        }catch (Exception e){
            return new Response(false, "Ошибка сервера при обновлении маршрута: " + e.getMessage(), null);
        }
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


