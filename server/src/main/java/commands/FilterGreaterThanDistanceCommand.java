package commands;

import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

import java.util.HashSet;
import java.util.stream.Collectors;


public class FilterGreaterThanDistanceCommand extends Command {


    public FilterGreaterThanDistanceCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute() {
        /*
        Route route = new Route();
        var result = manager.filterGreaterThanDistance(readDistance(reader,route, false));
        if(result.isEmpty()){
            System.out.println("Ничего не найдено");
        }else{
            for(Route r : result){
                System.out.println(r);
            }
        }

         */
    }

    @Override
    public Response execute(Object payLoad) {
        if(payLoad == null){
            return new Response(false, "Ошибка сервера: отсутствует аргумент distance.", null);
        }
        try{
            long distance = Long.parseLong(payLoad.toString().trim());
            HashSet<Route> filteredRoutes = manager.filterGreaterThanDistance(distance);
            if(filteredRoutes.isEmpty()){
                return new Response(true, "Маршруты с distance больше " + distance + " не найдены.", null);
            }
            String result = filteredRoutes.stream().map(Route::toString).collect(Collectors.joining("\n"));
            return new Response(true, "Найденные элементы (distance > " + distance + "):", result);
        }catch (ClassCastException e){
            return new Response(false, "Ошибка сервера: аргумент distance должен быть числом.", null);
        }catch (Exception e){
            return new Response(false, "Ошибка при выполнении фильтрации: " + e.getMessage(), null);
        }
    }

    @Override
    public void execute(String args) {
        /*
        long distance;
        try{

            if (!args.isEmpty()) {
                distance = Long.parseLong(args);
                var result = manager.filterGreaterThanDistance(distance);
                if(result.isEmpty()){
                    System.out.println("Ничего не найдено");
                }else{
                    for(Route r : result){
                        System.out.println(r);
                    }
                }
            }else {
                System.out.println("distance не может быть пустым");
            }

        }catch (Exception e){
            System.out.println("Ошибка: distance должен быть числом!");
        }

         */
    }

    @Override
    public String getName() {
        return "filter_greater_than_distance";
    }

    @Override
    public String getDiscription() {
        return "выводит элементы, значение поля distance которых больше заданного";
    }
}