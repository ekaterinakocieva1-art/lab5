package commands;

import managers.CollectionManager;

import java.util.Scanner;

public class InfoCommand implements Command {
    private CollectionManager manager;
    private Scanner scan;
    public InfoCommand(CollectionManager manager, Scanner scan){
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute(String ... args){
        System.out.println("info works");
    }

    @Override
    public String getName(){
        return "info";
    }

    @Override
    public String getDiscription(){
        return "выводит в стандартный поток вывода информацию о коллекции";
    }
}
