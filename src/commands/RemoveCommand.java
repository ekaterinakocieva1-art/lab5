package commands;

import managers.CollectionManager;
import utility.InteractiveInputReader;

import java.util.Scanner;

public class RemoveCommand extends Command{

    public RemoveCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);
    }

    @Override
    public void execute(String args) {
        int id;
        try{

            if (!args.isEmpty()) {
                id = Integer.parseInt(args);
                if (manager.findId(id) == null){
                    System.out.println("Ошибка: маршрут не найден");
                }else {
                    manager.remove(id);
                    System.out.println("Маршрут удален");
                }

            }else {
                System.out.println("id не может быть пустым");
            }

        }catch (Exception e){
            System.out.println("Ошибка: ID должен быть числом!");
        }

    }

    @Override
    public void execute(){
        int id = readId(reader);
        manager.remove(id);
        System.out.println("Маршрут удален");
    }

    @Override
    public String getName(){
        return "remove_by_id";
    }

    @Override
    public String getDiscription(){
        return "удалить элемент из коллекции по его id";
    }
}
