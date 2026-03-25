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
        commands.get(name).execute(args);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
