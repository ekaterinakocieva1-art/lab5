package commands;

import common.Response;
import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

import java.util.stream.Collectors;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        /*
        if(manager.getList().isEmpty()){
            System.out.println("Коллекция пуста");
        }else {
            for(Route r : manager.getList()){
                System.out.println(r);
            }
        }

         */

    }

    @Override
    public Response execute(Object payLoad) {
        try {
            if (manager.getList().isEmpty()) {
                return new Response(true, "Коллекция пуста.", null);
            }
            String allRoutesString = manager.getList().stream().sorted((r1, r2) -> Integer.compare(r1.getId(), r2.getId()))
                    .map(Route::toString).collect(Collectors.joining("\n"));
            return new Response(true, "Элементы коллекции:", allRoutesString);
        }catch (Exception e){
            return new Response(false, "Ошибка сервера при выводе коллекции: " + e.getMessage(), null);
        }
    }

    @Override
    public String getName(){
        return "show";
    }

    @Override
    public String getDiscription(){
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
