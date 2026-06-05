package commands;

import common.Response;
import managers.CollectionManager;
import utility.InteractiveInputReader;

public class ExitCommand extends Command{
    public ExitCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        /*
        System.exit(0);

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            return new Response(true, "Завершение работы клиента", null);
        }catch (Exception e){
            return new Response(false, "Ошибка при выполнении команды exit: " + e.getMessage(), null);
        }
    }

    @Override
    public String getName(){
        return "exit";
    }

    @Override
    public String getDiscription(){
        return "завершить программу (без сохранения в файл)";
    }
}
