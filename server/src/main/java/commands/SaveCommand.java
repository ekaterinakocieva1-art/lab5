package commands;

import common.Response;
import managers.CollectionManager;
import utility.InteractiveInputReader;


public class SaveCommand extends Command {


    public SaveCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {
    }

    @Override
    public void execute(){

        manager.save();

    }

    @Override
    public Response execute(Object payLoad) {
        try{
            manager.save();
            return new Response(true, "Коллекция успешно сохранена в файл на сервере.", null);
        }catch (Exception e){
            return new Response(false, "Ошибка сервера при сохранении файла: " + e.getMessage(), null);
        }
    }

    @Override
    public String getName(){
        return "save";
    }

    @Override
    public String getDiscription(){
        return "сохранить коллекцию в файл";
    }
}
