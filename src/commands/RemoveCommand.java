package commands;

public class RemoveCommand implements Command{
    @Override
    public void execute(String ... args){
        System.out.println("remove_by_id works");
    }

    @Override
    public String getName(){
        return "remove_by_id";
    }

    @Override
    public String getDiscription(){
        return "удалить элемент из коллекции по его id";
    }
}
