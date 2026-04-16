package commands;

import managers.CollectionManager;
import models.Route;
import utility.InteractiveInputReader;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        if(manager.getList().isEmpty()){
            System.out.println("Коллекция пуста");
        }else {
            for(Route r : manager.getList()){
                System.out.println(r);
            }
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
