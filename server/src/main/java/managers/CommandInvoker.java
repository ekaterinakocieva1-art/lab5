package managers;

import com.google.gson.JsonSyntaxException;
import commands.Command;
import common.Request;
import common.Response;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class CommandInvoker {
    private final Map<String, Command> commands= new HashMap<>();
    private Response savedResponse = null;

    public void register(Command command){
        commands.put(command.getName().toLowerCase(), command);
    }

    public void execute(String name,String args) {
        try {
            String lowerName = name.toLowerCase();
            if (commands.containsKey(lowerName)) {
                commands.get(lowerName).execute(args);
                System.out.println("Команда " + name + " успешно завершена");
            } else {
                System.out.println("неизвестная команда");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка при парсинге");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            ;
        }


    }
    public void execute(String name){
        try {
            String lowerName = name.toLowerCase();
            if(commands.containsKey(lowerName)){
                commands.get(lowerName).execute();
                System.out.println("Команда " + name + " успешно завершена");
            }else{
                System.out.println("неизвестная команда");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());;
        }
    }
    public Response execute(Request request){
        try {
            String lowerName = request.getCommandName().toLowerCase();
            if(commands.containsKey(lowerName)){
                Object payload = request.getPayload();
                if (payload instanceof String && ((String) payload).trim().startsWith("{")) {
                    try {
                        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                                .registerTypeAdapter(java.time.LocalDateTime.class, new utility.LocalDateTimeAdapter())
                                .create();
                        payload = gson.fromJson((String) payload, models.Route.class);
                    } catch (com.google.gson.JsonSyntaxException e) {
                        return new Response(false, "Ошибка парсинга JSON в скрипте: " + e.getMessage(), null);
                    }
                }

                Response response = commands.get(lowerName).execute(payload);
                System.out.println("Команда " + lowerName + " успешно завершена");
                return response;
            } else {
                System.out.println("неизвестная команда");
                return new Response(false, "Неизвестная команда: " + lowerName, null);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return new Response(false, "Ошибка на сервере: " + e.getMessage(), null);
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

}
