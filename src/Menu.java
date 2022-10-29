import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Menu {
    private static Scanner in = new Scanner(System.in);
    public static void startMenu(){mainMenu();}
    public static int checkPoint(){
        Scanner scan = new Scanner(System.in);
        int redPoint;
        try{
            redPoint=Integer.parseInt(scan.nextLine());
        }
        catch(NumberFormatException e){
            System.out.println("Некорректный ввод! Введите число.");
            redPoint=checkPoint();
        }
        return redPoint;
    }
    private static void mainMenu() {
        int point =-1;
        do{
            System.out.println("+==============================+");
            System.out.println("|.........Главное меню.........|");
            System.out.println("| [1]- Показать задание   -[1] |");
            System.out.println("| [2]- Создать матрицу    -[2] |");
            System.out.println("| [3]- Ввести строку      -[3] |");
            System.out.println("| [0]- Выход из программы -[0] |");
            System.out.println("+------------------------------+");

            point=checkPoint();

            switch (point) {
                case 1:
                    System.out.println(
                    "a. найти определитель матрицы 3х3;\n" +
                    "b. найти количество слов и предложений в заданном тексте.\n" +
                    "c. найти все возможные определители второго порядка из матрицы 3х3;\n" +
                    "d. транспонировать матрицу ." + "\n");
                    break;
                case 2:
                    matrixMenu();
                    break;
                case 3:
                    textMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный ввод пункта!");
            }
        }while (point!=0);
    }
    private static void matrixMenu(){
        int point =-1;
        byte[] buffer;
        Matrix matrix=new Matrix();
        do {
            System.out.println("+================================+");
            System.out.println("|.....Меню работы с матрицей.....|");
            System.out.println("| [1]- Ввод матрицы         -[1] |");
            System.out.println("| [2]- Вывод матрицы        -[2] |");
            System.out.println("| [3]- Подсчёт детерминанта -[3] |");
            System.out.println("| [4]- Подсчёт миноров      -[4] |");
            System.out.println("| [5]- Транспонирование     -[5] |");
            System.out.println("| [6]- Запись в файл        -[6] |");
            System.out.println("| [7]- Чтение из файла       [7] |");
            System.out.println("| [0]- Выход в главное меню -[0] |");
            System.out.println("+--------------------------------+");
            point = checkPoint();
            try{
                switch (point) {
                    case 1:
                        System.out.println("Введите количество строк");             //проверка на отрицательные значения
                        matrix.rows = Integer.parseInt(in.nextLine());
                        System.out.println("Введите количество столбцов");          //проверка на отрицательные значения
                        matrix.columns = Integer.parseInt(in.nextLine());
                        System.out.println("Введите элементы матрицы");
                        if (matrix.rows != matrix.columns) {
                            throw new Exception("Матрица не является квадратной. Повторите ввод.");
                        }
                        matrix.init();
                        break;
                    case 2:
                        matrix.emptyCheck();
                        matrix.output();
                        break;
                    case 3:
                        matrix.emptyCheck();
                        System.out.println(matrix.getDeterminant());
                        break;
                    case 4:
                        matrix.emptyCheck();
                        matrix.getMinors();
                        break;
                    case 5:
                        matrix.emptyCheck();
                        matrix.transpose();
                        matrix.output();
                        break;
                    case 6:
                        FileOutputStream fileOut = new FileOutputStream("matrix.txt");
                        buffer = matrix.toString().getBytes();
                        fileOut.write(buffer, 0, buffer.length);
                        fileOut.close();
                        break;
                    case 7:
                        FileInputStream fileIn = new FileInputStream("matrix.txt");
                        buffer = new byte[fileIn.available()];
                        fileIn.read(buffer, 0, fileIn.available());
                        String str = new String(buffer, StandardCharsets.UTF_8);
                        System.out.println(str);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Неверный ввод пункта!");
                }
            }
            catch (IOException e)                {System.out.println("ошибка при работе с файлом");}
            catch(NumberFormatException e)       {System.out.println("Нельзя вводить текст, повторите заново");}
            catch (NegativeArraySizeException e) {System.out.println("Нельзя вводить отрицательный размер матрицы, повторите заново");}
            catch (NoSuchElementException e)     {System.out.println("Нельзя вводить нечисловые элементы, повторите заново");}
            catch (Exception e)                  {System.out.println(e.getMessage());}
        }while (point!=0);
    }
    private static void textMenu(){
        Text1 txt=new Text1("");
        byte[] buffer;
        int point =-1;
        do{
            System.out.println("+==========================================+");
            System.out.println("|..........Меню работы с текстом...........|");
            System.out.println("| [1]- Ввод текста                    -[1] |");
            System.out.println("| [2]- Подсчёт количества предложений -[2] |");
            System.out.println("| [3]- Подсчёт количества слов        -[3] |");
            System.out.println("| [4]- Запись в файл                  -[4] |");
            System.out.println("| [5]- Чтение строки из файла         -[5] |");
            System.out.println("| [0]- Выход в главное меню           -[0] |");
            System.out.println("+------------------------------------------+");

            point =checkPoint();
            try {
                switch (point) {
                    case 1:
                        System.out.println("ввод текста");
                        Scanner scan = new Scanner(System.in);
                        String text = scan.nextLine();
                        txt.getLines(text);
                        break;
                    case 2:
                        if (txt.nothing()) {
                            throw new Exception("Отсутствует текст");
                        }
                        System.out.println("Количество предложений в тексте");
                        System.out.println(txt.getSentenceCounter());
                    break;
                    case 3:
                        if (txt.nothing()) {
                            throw new Exception("Отсутствует текст");
                        }
                        System.out.println("Количество слов в тексте ");
                        System.out.println(txt.getWordCounter());
                    break;
                    case 4:
                        FileOutputStream fileOut = new FileOutputStream("file.txt");
                        buffer = txt.text.getBytes();
                        fileOut.write(buffer, 0, buffer.length);
                        fileOut.close();
                    break;
                    case 5:
                        FileInputStream fileIn = new FileInputStream("file.txt");
                        buffer = new byte[fileIn.available()];
                        fileIn.read(buffer, 0, fileIn.available());
                        String str = new String(buffer, StandardCharsets.UTF_8);
                        System.out.println(str);
                    break;
                    case 0:
                        return;
                    default:
                        System.out.println("Неверный ввод пункта!");
                }
            }
            catch (IOException e) {System.out.println("ошибка при работе с файлом");}
            catch (Exception e)   {System.out.println(e.getMessage());}
        }while (point!=0);
    }
}