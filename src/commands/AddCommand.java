package commands;

public class AddCommand implements Command{
    @Override
    public void execute(String ... args){
        System.out.println("add works");
    }

    @Override
    public String getName(){
        return "add";
    }

    @Override
    public String getDiscription(){
        return "добавить новый элемент в коллекцию";
    }
}
