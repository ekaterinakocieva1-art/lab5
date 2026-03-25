package commands;

public class ShowCommand implements Command{
    @Override
    public void execute(String ... args){
        System.out.println("show works");
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
