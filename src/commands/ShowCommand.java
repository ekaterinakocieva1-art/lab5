package commands;

import managers.CollectionManager;
import models.Route;

public class ShowCommand implements Command{
    private CollectionManager manager;
    public ShowCommand(CollectionManager manager){
        this.manager = manager;
    }
    @Override
    public void execute(String ... args){
        for(Route r : manager.getList()){
            System.out.println(r);
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
