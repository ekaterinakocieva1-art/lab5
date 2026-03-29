package commands;

import managers.CollectionManager;
import models.Coordinates;
import models.Location;
import models.Route;

import java.util.Scanner;

public class AddCommand implements Command{
    private CollectionManager manager;
    private Scanner scan;

    public AddCommand(CollectionManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute(String ... args){

        Route r = new Route();
        while(true){
            System.out.println("Название маршрута");
            String name = scan.nextLine().trim();
            if (!name.isEmpty()) {
                r.setName(name);
                break;
            }
            System.out.println("Ошибка: имя не может быть пустым!");

        }

        Coordinates coordinates = new Coordinates();
        while (true) {
            try {
                System.out.println("Координаты x:");

                String x = scan.nextLine().trim();
                if (x.isEmpty()) {
                    System.out.println("Ошибка: Поле X не может быть null!");
                    continue;
                }
                coordinates.setX(Float.parseFloat(x));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: X должен быть целым числом!");
            }
        }
        while (true) {
            try {
                System.out.println("Координаты y:");

                String y = scan.nextLine().trim();
                if (y.isEmpty()) {
                    System.out.println("Ошибка: Поле Y не может быть null!");
                    continue;
                }
                coordinates.setY(Long.parseLong(y));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Y должен быть целым числом!");
            }
        }
        r.setCoordinates(coordinates);
        while (true) {
            try {
                System.out.println("Дистанция");
                long dist = Long.parseLong(scan.nextLine().trim());
                if (dist > 1) {
                    r.setDistance(dist);
                    break;
                }
                System.out.println("Ошибка: дистанция должна быть больше 1!");
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка: Дистанция должна быть целым числом");
            }

        }
        Integer xF = null;
        Double yF = null;
        double zF = 0;
        System.out.println("Место отправления");
        while (true) {
            try {
                System.out.print("X: ");
                String xFrom = scan.nextLine().trim();
                if (xFrom.isEmpty()) {
                    System.out.println("Ошибка: Поле X не может быть null!");
                    continue;
                }
                xF = Integer.parseInt(xFrom);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: X должен быть целым числом!");
            }
        }
        while (true) {
            try {
                System.out.print("Y: ");
                String yFrom = scan.nextLine().trim();
                if (yFrom.isEmpty()) {
                    System.out.println("Ошибка: Поле Y не может быть null!");
                    continue;
                }
                yF = Double.parseDouble(yFrom);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Y должен быть числом!");
            }
        }
        while (true) {
            try {
                System.out.print("Z: ");
                String zFrom = scan.nextLine().trim();
                zF = Double.parseDouble(zFrom);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Z должен быть числом!");
            }
        }
        r.setFrom(new Location(xF, yF, zF));

        Integer xT = null;
        Double yT = null;
        double zT = 0;
        System.out.println("Место прибытия");
        while (true) {
            try {
                System.out.print("X: ");
                String xTo = scan.nextLine().trim();
                if (xTo.isEmpty()) {
                    System.out.println("Ошибка: Поле X не может быть null!");
                    continue;
                }
                xT = Integer.parseInt(xTo);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: X должен быть целым числом!");
            }
        }
        while (true) {
            try {
                System.out.print("Y: ");
                String yTo = scan.nextLine().trim();
                if (yTo.isEmpty()) {
                    System.out.println("Ошибка: Поле Y не может быть null!");
                    continue;
                }
                yT = Double.parseDouble(yTo);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Y должен быть числом!");
            }
        }
        while (true) {
            try {
                System.out.print("Z: ");
                String zTo = scan.nextLine().trim();
                zT = Double.parseDouble(zTo);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Z должен быть числом!");
            }
        }
        r.setTo(new Location(xT, yT, zT));
        manager.add(r);
        System.out.println("Путь успешно добавлен");
        System.out.println(r);
    }



    @Override
    public String getName(){
        return "add";
    }

    @Override
    public String getDiscription(){
        return "добавить новый элемент в коллекцию";
    }
}
