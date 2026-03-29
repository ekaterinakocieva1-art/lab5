package commands;


import managers.CommandInvoker;

public class HelpCommand implements Command{
    private CommandInvoker commandInvoker;


    public HelpCommand(CommandInvoker invoker){
        this.commandInvoker = invoker;
    }

    @Override
    public void execute(String ... args){
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
