package commands;

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
        System.out.println(manager.getInfo());
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
