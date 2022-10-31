import java.util.Scanner;
/*
public class Menu {
    private static Scanner in = new Scanner(System.in);

    public static void startMenu() {
        mainMenu();
    }

    public static int checkPoint() {
        Scanner scan = new Scanner(System.in);
        int redPoint;
        try {
            redPoint = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод! Введите число");
            redPoint = checkPoint();
        }
        return redPoint;
    }

    private static void mainMenu() {

        int point = -1;
        do {
            System.out.println("Главное меню ");
            System.out.println("1 - Показать задание");
            System.out.println("2 - Создать матрицу");
            System.out.println("3 - Ввести строку");
            System.out.println("0 - Выход из программы");
            point = checkPoint();

            switch (point) {
                case 1:
                    System.out.println("Task");
                    break;
                case 2:
                    matrixMenu();
                    break;
                case 3:
                    textMenu();
                    break;
                default:
                    System.out.println("Неверный ввод пункта!");
            }

        } while (point != 0);
    }


    private static void matrixMenu() {
        System.out.println("Меню работы с матрицей");
        System.out.println("1 - Ввод матрицы");
        System.out.println("2 - Вывод матрицы");
        System.out.println("3 - Подсчёт детерминанта");
        System.out.println("4 - Подсчёт миноров");
        System.out.println("5 - Транспонирование исходной матрицы");
        System.out.println("0 - Выход в главное меню");
    }

    private static void textMenu() {
        System.out.println("Меню работы с текстом");
        System.out.println("1 - Ввод текста");
        System.out.println("2 - подсчёт количества предложений в тексте");
        System.out.println("3 - подсчёт количества слов в тексте");
        System.out.println("0 - Выход в главное меню");
    }
}
*/