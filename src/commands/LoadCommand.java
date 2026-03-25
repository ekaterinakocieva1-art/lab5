package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import managers.CollectionManager;
import models.Route;
import utility.LocalDateTimeAdapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;

public class LoadCommand implements Command{
    private CollectionManager manager;

    public LoadCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String... args) {
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("routes.json"))){
            byte [] bytes = bufferedInputStream.readAllBytes();
            String json = new String(bytes);
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).
                    setPrettyPrinting().
                    create();
            Type type = new TypeToken<HashSet<Route>>(){}.getType();
            HashSet<Route> routes = gson.fromJson(json, type);
            manager.load(routes);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "load";
    }

    @Override
    public String getDiscription() {
        return "";
    }
}
