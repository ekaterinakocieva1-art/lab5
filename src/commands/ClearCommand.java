package commands;

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
        manager.clear();
        System.out.println("Очищаем коллекцию");
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
