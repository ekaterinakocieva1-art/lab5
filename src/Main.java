//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import commands.*;
import managers.CollectionManager;
import managers.CommandInvoker;
import utility.InteractiveInputReader;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String fileName = System.getenv("LAB_FILE_NAME");
        if (fileName == null || fileName.isBlank()) {
            System.err.println("Предупреждение: переменная LAB_FILE_NAME не задана, используем файл по умолчанию");
            fileName = "routes.json";
        }
        Scanner scanner = new Scanner(System.in);

        CommandInvoker commandInvoker = new CommandInvoker();
        CollectionManager collectionManager = new CollectionManager(fileName);
        InteractiveInputReader reader = new InteractiveInputReader();

        commandInvoker.register(new InfoCommand(collectionManager,reader));
        commandInvoker.register(new UpdateCommand(collectionManager, reader));
        commandInvoker.register(new HelpCommand(commandInvoker,collectionManager,reader));
        commandInvoker.register(new SaveCommand(collectionManager,reader));
        commandInvoker.register(new LoadCommand(collectionManager,reader));
        commandInvoker.register(new AddCommand(collectionManager, reader));
        commandInvoker.register(new ShowCommand(collectionManager,reader));
        commandInvoker.register(new RemoveCommand(collectionManager,reader));
        commandInvoker.register(new ExitCommand(collectionManager,reader));
        commandInvoker.register(new ClearCommand(collectionManager,reader));
        commandInvoker.register(new ExecuteScriptCommand(commandInvoker,reader, collectionManager));
        commandInvoker.register(new AddIfMaxCommand(collectionManager, reader));
        commandInvoker.register(new AverageOfDistanceCommand(collectionManager,reader));
        commandInvoker.register(new FilterGreaterThanDistanceCommand(collectionManager, reader));
        commandInvoker.register(new PrintAscendingCommand(collectionManager,reader));
        commandInvoker.register(new RemoveGraterCommand(collectionManager, reader));
        commandInvoker.register(new RemoveLowerCommand(collectionManager, reader));
        commandInvoker.execute("load");
        while(true){
            String[] line = scanner.nextLine().split(" ", 2);
            if(line.length == 1){
                String commandName = line[0];
                if(!commandName.isEmpty()){
                    commandInvoker.execute(commandName);
                }
            } else if (line.length > 1) {
                String commandName = line[0];
                String argue = line[1];
                if(!commandName.isEmpty()){
                    commandInvoker.execute(commandName, argue);
                }
            }

        }
    }
}
