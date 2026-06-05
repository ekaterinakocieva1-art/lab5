package utility;
import java.util.Scanner;
/* класс, который отвечает только за чтение данных от пользователя */
public class InteractiveInputReader {
    private Scanner scanner = new Scanner(System.in);
    private boolean isFromFile = false;

    // Метод для переключения на чтение из файла скрипта
    public void setScanner(Scanner newScanner) {
        this.scanner = newScanner;
        this.isFromFile = true;
    }

    // Метод для возврата на чтение из стандартной консоли
    public void resetScanner() {
        this.scanner = new Scanner(System.in);
        this.isFromFile = false;
    }
    public boolean isFromFile() {
        return this.isFromFile;
    }
    public Scanner getScanner() {
        return this.scanner;
    }


    public String readString(String prompt) {
        if (!isFromFile) {
            System.out.print(prompt);
        }
        if (!scanner.hasNextLine()) {
            if (isFromFile) throw new RuntimeException("Неожиданный конец файла скрипта!");
            return "";
        }
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        if (isFromFile) {
            if (!scanner.hasNextInt()) {
                if (scanner.hasNext()) scanner.next(); // сжигаем испорченную строчку
                // Вместо "return 0" мы выкидываем ошибку, чтобы сразу СТОПНУТЬ скрипт
                throw new RuntimeException("Ошибка чтения скрипта: ожидалось целое число.");
            }
            int value = scanner.nextInt();
            if (scanner.hasNextLine()) scanner.nextLine(); // чистим буфер
            return value;
        }
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.err.println("Ошибка: введите целое число");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
    public int readInteger(String prompt) {
        return readInt(prompt);
    }

    public double readDouble(String prompt) {
        if (isFromFile) {
            if (!scanner.hasNextDouble()) {
                if (scanner.hasNext()) scanner.next(); // сжигаем некорректный токен
                throw new RuntimeException("Ошибка чтения скрипта: ожидалось вещественное число (double).");
            }
            double value = scanner.nextDouble();
            if (scanner.hasNextLine()) scanner.nextLine(); // чистим буфер
            return value;
        }

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
        if (isFromFile) {
            if (!scanner.hasNextFloat()) {
                if (scanner.hasNext()) scanner.next(); // сжигаем некорректный токен
                throw new RuntimeException("Ошибка чтения скрипта: ожидалось число с плавающей точкой (float).");
            }
            Float value = scanner.nextFloat();
            if (scanner.hasNextLine()) scanner.nextLine(); // чистим буфер
            return value;
        }

        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            System.err.println("Ошибка: введите число");
            if (!isFromFile) System.out.print(prompt);
            scanner.next();
        }
        Float value = scanner.nextFloat();
        scanner.nextLine();
        return value;
    }

    public Long readLong(String prompt) {
        if (isFromFile) {
            if (!scanner.hasNextLong()) {
                if (scanner.hasNext()) scanner.next(); // сжигаем некорректный токен
                throw new RuntimeException("Ошибка чтения скрипта: ожидалось число типа Long.");
            }
            Long value = scanner.nextLong();
            if (scanner.hasNextLine()) scanner.nextLine(); // чистим буфер
            return value;
        }

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
