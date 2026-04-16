package commands;

import managers.CollectionManager;
import utility.InteractiveInputReader;

public class ExitCommand extends Command{
    public ExitCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
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
