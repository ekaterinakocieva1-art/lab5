package managers;

import commands.Command;

import java.util.Map;
import java.util.HashMap;
public class CommandInvoker {
    private final Map<String, Command> commands= new HashMap<>();


    public void register(Command command){
        commands.put(command.getName(), command);
    }

    public void execute(String name,String... args){
        try {
            if(commands.containsKey(name)){
                commands.get(name).execute(args);
            }else{
                System.out.println("неизвестная команда");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());;
        }


    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
