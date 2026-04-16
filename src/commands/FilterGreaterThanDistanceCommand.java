package commands;

import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;



public class FilterGreaterThanDistanceCommand extends Command {


    public FilterGreaterThanDistanceCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute() {
        Route route = new Route();
        var result = manager.filterGreaterThanDistance(readDistance(reader,route));
        if(result.isEmpty()){
            System.out.println("Ничего не найдено");
        }else{
            for(Route r : result){
                System.out.println(r);
            }
        }
    }

    @Override
    public void execute(String args) {
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