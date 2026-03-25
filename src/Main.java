//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import commands.*;
import managers.CollectionManager;
import managers.CommandInvoker;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CommandInvoker commandInvoker = new CommandInvoker();
        CollectionManager collectionManager = new CollectionManager();
        commandInvoker.register(new InfoCommand(collectionManager, scanner));
        commandInvoker.register(new UpdateCommand(collectionManager, scanner));
        commandInvoker.register(new HelpCommand(commandInvoker));
        commandInvoker.register(new SaveCommand(collectionManager));
        commandInvoker.register(new LoadCommand(collectionManager));
        commandInvoker.execute("load");
        while(true){
            String commandName = scanner.nextLine();
            if (commandName.equals("update")){
                try {
                    System.out.println("Введите id");
                    String id = scanner.next();
                    commandInvoker.execute(commandName, id);
                }
                catch (IllegalArgumentException exc){
                    System.out.println("Введен неверный id");
                }

            } else if (commandName.equals("info")) {
                commandInvoker.execute(commandName);
            } else if (commandName.equals("help")) {
                commandInvoker.execute(commandName);
            } else if (commandName.equals("save")){
                commandInvoker.execute(commandName);
            }


        }
    }
}
