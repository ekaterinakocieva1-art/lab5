package commands;

import common.Response;
import exeptions.RouteNotFoundException;
import managers.CollectionManager;
import utility.InteractiveInputReader;

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

        }catch (Exception e) {
            System.out.println("Ошибка: ID должен быть числом!");
        }

    }

    @Override
    public void execute(){
        /*
        int id = readId(reader);
        manager.remove(id);
        System.out.println("Маршрут удален");

         */
    }

    @Override
    public Response execute(Object payLoad) {
        if (payLoad == null || payLoad.toString().isBlank()) {
            return new Response(false, "Ошибка сервера: отсутствует аргумент ID.", null);
        }
        try {
            int id;
            if (payLoad instanceof Double) {
                id = ((Double) payLoad).intValue();
            } else if (payLoad instanceof Long) {
                id = ((Long) payLoad).intValue();
            } else if (payLoad instanceof Integer) {
                id = (Integer) payLoad;
            } else {
                String cleanStr = payLoad.toString().trim();
                if (cleanStr.contains(".")) {
                    id = (int) Double.parseDouble(cleanStr);
                } else {
                    id = Integer.parseInt(cleanStr);
                }
            }

            manager.remove(id);
            return new Response(true, "Маршрут с ID " + id + " успешно удален.", null);

        } catch (NumberFormatException e) {
            return new Response(false, "Ошибка сервера: не удалось распознать ID как число. Ввод: " + payLoad, null);
        } catch (exeptions.RouteNotFoundException e) {
            return new Response(false, e.getMessage(), null);
        } catch (Exception e) {
            return new Response(false, "Ошибка сервера при удалении маршрута: " + e.getMessage(), null);
        }
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
