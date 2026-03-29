package commands;

import managers.CollectionManager;

public class ClearCommand implements Command{
    private CollectionManager manager;
    public ClearCommand(CollectionManager manager){
        this.manager = manager;
    }
    @Override
    public void execute(String ... args){
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
