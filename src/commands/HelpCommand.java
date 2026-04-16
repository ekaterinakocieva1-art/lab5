package commands;


import managers.CollectionManager;
import managers.CommandInvoker;
import utility.InteractiveInputReader;

public class HelpCommand extends Command{
    private CommandInvoker commandInvoker;


    public HelpCommand(CommandInvoker commandInvoker,CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
        this.commandInvoker = commandInvoker;

    }

    @Override
    public void execute(String args) {

    }

    @Override
    public void execute(){
        for(Command cmd: commandInvoker.getCommands().values()){
            System.out.println(cmd.getName() + ":" + cmd.getDiscription());
        }
    }

    @Override
    public String getName(){
        return "help";
    }
    @Override
    public String getDiscription(){
        return " выводит справку по доступным командам";
    }
}
