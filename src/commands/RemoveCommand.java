package commands;

import managers.CollectionManager;

import java.util.Scanner;

public class RemoveCommand implements Command{
    private CollectionManager manager;
    private Scanner scan;

    public RemoveCommand(CollectionManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }
    @Override
    public void execute(String ... args){
        System.out.println("Введите id:");
        int id;
        while (true) {
            try {
                String idInput = scan.nextLine().trim();
                if (!idInput.isEmpty()) {
                    id = Integer.parseInt(idInput);
                    if (manager.findId(id) != null) break;
                    else{
                        System.out.println("Ошибка: маршрут не найден. Введите другой ID:");
                        continue;
                    }

                }
                System.out.println("id не может быть пустым");



            } catch (NumberFormatException e) {
                System.out.println("Ошибка: ID должен быть числом!");

            }
        }
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
