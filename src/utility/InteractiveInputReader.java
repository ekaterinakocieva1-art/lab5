package utility;
import java.util.Scanner;
/* класс, который отвечает только за чтение данных от пользователя */
public class InteractiveInputReader {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.err.println("Ошибка: введите целое число");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return value;
    }
    public int readInteger(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.err.println("Ошибка: введите целое число");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return value;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.err.println("Ошибка: введите число");
            System.out.print(prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
    public Float readFloat(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            System.err.println("Ошибка: введите число");
            System.out.print(prompt);
            scanner.next();
        }
        Float value = scanner.nextFloat();
        scanner.nextLine();
        return value;
    }
    public Long readLong(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.err.println("Ошибка: введите число");
            System.out.print(prompt);
            scanner.next();
        }
        Long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }
}
