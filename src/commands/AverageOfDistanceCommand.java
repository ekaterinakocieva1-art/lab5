package commands;

import managers.CollectionManager;
import utility.InteractiveInputReader;

public class AverageOfDistanceCommand extends Command{

    public AverageOfDistanceCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);

    }

    @Override
    public void execute() {
        try {
            double average = manager.averageOfDistance();
            System.out.printf("Среднее значение дистанции для всех маршрутов: %.2f%n",average);
        }catch (RuntimeException e){
            System.out.println("Ошибка:" + e.getMessage());
        }
    }

    @Override
    public void execute(String args){

    }

    @Override
    public String getName(){
        return "average_of_distance";
    }

    @Override
    public String getDiscription(){
        return "выводит среднее значение поля distance для всех элементов коллекции";
    }
}
