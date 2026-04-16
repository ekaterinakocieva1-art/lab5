package commands;

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
    public String getName(){
        return "save";
    }

    @Override
    public String getDiscription(){
        return "сохранить коллекцию в файл";
    }
}
