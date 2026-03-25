package commands;

public class ClearCommand implements Command{
    @Override
    public void execute(String ... args){
        System.out.println("clear works");
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
