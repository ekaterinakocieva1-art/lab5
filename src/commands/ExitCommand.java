package commands;

public class ExitCommand implements Command{
    @Override
    public void execute(String ... args){
        System.exit(0);
    }

    @Override
    public String getName(){
        return "exit";
    }

    @Override
    public String getDiscription(){
        return "завершить программу (без сохранения в файл)";
    }
}
