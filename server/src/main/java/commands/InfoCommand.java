package commands;

import common.Response;
import managers.CollectionManager;
import utility.InteractiveInputReader;


public class InfoCommand extends Command {

    public InfoCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        /*
        System.out.println(manager.getInfo());

         */
    }

    @Override
    public Response execute(Object payLoad) {

        try{
            String infoMessage = manager.getInfo();
            return new Response(true, infoMessage, null);
        }catch (Exception e){
            return new Response(false,"Ошибка сервера при получении информации о коллекции: " + e.getMessage(), null);
        }
    }

    @Override
    public String getName(){
        return "info";
    }

    @Override
    public String getDiscription(){
        return "выводит в стандартный поток вывода информацию о коллекции";
    }
}
