package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.CollectionManager;
import utility.LocalDateTimeAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;

public class SaveCommand implements Command {
    private CollectionManager manager;

    public SaveCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String ... args){
        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("routes.json"))){
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).
                    setPrettyPrinting().
                    create();
            String json = gson.toJson(manager.getList());
            writer.write(json);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName(){
        return "save";
    }

    @Override
    public String getDiscription(){
        return "сохранить коллекцию в файл";
    }
}
