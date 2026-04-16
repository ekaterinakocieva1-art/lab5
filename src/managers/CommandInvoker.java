package managers;

import com.google.gson.JsonSyntaxException;
import commands.Command;

import java.util.Map;
import java.util.HashMap;
public class CommandInvoker {
    private final Map<String, Command> commands= new HashMap<>();


    public void register(Command command){
        commands.put(command.getName(), command);
    }

    public void execute(String name,String args){
        try {
            if (commands.containsKey(name)) {
                commands.get(name).execute(args);
                System.out.println("Команда " + name + " успешно завершена");
            } else {
                System.out.println("неизвестная команда");
            }
        }catch (JsonSyntaxException e){
            System.out.println("Ошибка при парсинге");
        }
         catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());;
        }


    }
    public void execute(String name){
        try {
            if(commands.containsKey(name)){
                commands.get(name).execute();
                System.out.println("Команда " + name + " успешно завершена");
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
