package commands;

import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAscendingCommand extends Command{

    public PrintAscendingCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute() {
        /*
        if(manager.getList().isEmpty()){
            System.out.println("Коллекция пуста");
        }else {
            //мы записывем наш HashSet в ArrayList, чтобы была сортировка по дистанции
            List<Route> sortedList = new ArrayList<>(manager.getList());
            Collections.sort(sortedList);
            for(Route r : sortedList){
                System.out.println(r);
            }
        }

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            if(manager.getList().isEmpty()){
                return new Response(true, "Коллекция пуста.", null);
            }
            List<Route> sortedList = new ArrayList<>(manager.getList());
            Collections.sort(sortedList);
            return new Response(true, "Элементы коллекции в порядке возрастания:", sortedList);
        }catch (Exception e){
            return new Response(false, "Ошибка сервера при сортировке коллекции: " + e.getMessage(), null);
        }
    }

    @Override
    public void execute(String args) {
        /*
        this.execute();

         */
    }

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public String getDiscription() {
        return "выводит элементы коллекции в порядке возрастания";
    }
}
