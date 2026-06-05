package commands;


import common.Response;
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
        /*
        for(Command cmd: commandInvoker.getCommands().values()){
            System.out.println(cmd.getName() + ":" + cmd.getDiscription());
        }

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            StringBuilder helpBuilder = new StringBuilder("Доступные команды:\n");
            for(Command cmd: commandInvoker.getCommands().values()){
                helpBuilder.append(cmd.getName())
                        .append(" : ")
                        .append(cmd.getDiscription())
                        .append("\n");
            }
            return new Response(false, helpBuilder.toString().trim(), null);
        }catch (Exception e){
            return new Response(false, "Ошибка при генерации справки: " + e.getMessage(), null);
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
