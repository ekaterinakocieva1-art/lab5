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
        commandInvoker.register(new AddCommand(collectionManager,scanner));
        commandInvoker.register(new ShowCommand(collectionManager));
        commandInvoker.register(new RemoveCommand(collectionManager,scanner));
        commandInvoker.register(new ExitCommand());
        commandInvoker.register(new ClearCommand(collectionManager));
        commandInvoker.execute("load");
        while(true){
            String commandName = scanner.nextLine();
            if(!commandName.isEmpty()){
                commandInvoker.execute(commandName);
            }




        }
    }
}
