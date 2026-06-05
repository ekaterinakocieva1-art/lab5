package commands;

import common.Response;
import managers.CollectionManager;
import utility.InteractiveInputReader;

public class AverageOfDistanceCommand extends Command{

    public AverageOfDistanceCommand(CollectionManager manager, InteractiveInputReader reader){
        super(reader, manager);

    }

    @Override
    public void execute() {
        /*
        try {
            double average = manager.averageOfDistance();
            System.out.printf("Среднее значение дистанции для всех маршрутов: %.2f%n",average);
        }catch (RuntimeException e){
            System.out.println("Ошибка:" + e.getMessage());
        }

         */
    }

    @Override
    public Response execute(Object payLoad) {
        try{
            if(manager.getList().isEmpty()){
                return new Response(true, "Коллекция пуста. Среднее значение: 0.0", null);
            }
            double average = manager.averageOfDistance();
            String message = String.format("Среднее значение дистанции для всех маршрутов: %.2f", average);
            return new Response(true, message, null);
        }catch (RuntimeException e){
            return new Response(false, "Ошибка" + e.getMessage(), null);
        }catch (Exception e){
            return new Response(false, "Ошибка при вычислении среднего значения: " + e.getMessage(), null);
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
