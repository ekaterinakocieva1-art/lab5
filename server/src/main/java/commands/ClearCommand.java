package commands;

import common.Response;
import managers.CollectionManager;
import utility.InteractiveInputReader;

public class ClearCommand extends Command{
    public ClearCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        /*
        manager.clear();
        System.out.println("Очищаем коллекцию");

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            manager.clear();
            return new Response(true, "Коллекция успешно очищена", null);
        }catch (Exception e){
            return new Response(false, "Ошибка при очистке коллекции: " + e.getMessage(), null);
        }
    }


    @Override
    public String getName(){
        return "clear";
    }

    @Override
    public String getDiscription(){
        return "очистить коллекцию";
    }
}
