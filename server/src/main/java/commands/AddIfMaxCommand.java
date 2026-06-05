package commands;

import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

public class AddIfMaxCommand extends Command{

    public AddIfMaxCommand(CollectionManager manager, InteractiveInputReader reader){
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

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            if(payLoad == null) {
                return new Response(false, "Ошибка: Данные для сравнения не получены.", null);
            }
            Route route = (Route) payLoad;
            int oldSize = manager.getList().size();
            manager.addIfMax(route);
            if(manager.getList().size() > oldSize){
                return new Response(true, "Маршрут успешно добавлен.", null);
            }else{
                return new Response(true, "Маршрут не добавлен, так как он не превышает максимальный элемент коллекции.", null);
            }
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды add_if_max: "+ e.getMessage(), null);
        }
    }

    @Override
    public void execute(String args){
        /*
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
         */
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
