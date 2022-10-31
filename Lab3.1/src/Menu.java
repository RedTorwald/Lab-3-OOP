import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Menu {
    private static Scanner in = new Scanner(System.in);
    private static Matrix matrix=null;
    private static Text1 txt=null;
    static public String path="";
    static public String name="";
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
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Неверный ввод пункта!");
            }
        }while (point!=0);
    }
    private static void matrixMenu(){
        int point =-1;
        byte[] buffer;

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
                        int rows = Integer.parseInt(in.nextLine());
                        System.out.println("Введите количество столбцов");          //проверка на отрицательные значения
                        int columns = Integer.parseInt(in.nextLine());
                        matrix = new Matrix(rows, columns);

                        if (matrix.getRows() != matrix.getColumns()) {
                            throw new Exception("Матрица не является квадратной. Повторите ввод.");
                        }
                        break;
                    case 2:
                        if (matrix==null){
                            throw new Exception("Отсутствует матрица");
                        }
                        else{
                            matrix.output();
                        }
                        break;
                    case 3:
                        if (matrix==null){
                            throw new Exception("Отсутствует матрица");
                        }
                        else{
                            System.out.println(matrix.getDeterminant());
                        }
                        break;
                    case 4:
                        if (matrix==null){
                            throw new Exception("Отсутствует матрица");
                        }
                        else{
                            matrix.getMinors();
                        }
                        break;
                    case 5:
                        if (matrix==null){
                            throw new Exception("Отсутствует матрица");
                        }
                        else{
                            matrix.transpose();
                            matrix.output();
                        }
                        break;
                    case 6:
                        if (matrix==null){
                            throw new Exception("Отсутствует матрица");
                        }
                        else{
                            FileOutputStream fileOut = new FileOutputStream("matrix.txt");
                            buffer = matrix.toString().getBytes();
                            fileOut.write(buffer, 0, buffer.length);
                            fileOut.close();
                        }
                        break;
                    case 7:
                        FileInputStream fileIn = new FileInputStream("matrix.txt");
                        buffer = new byte[fileIn.available()];
                        fileIn.read(buffer, 0, fileIn.available());
                        String str = new String(buffer, StandardCharsets.UTF_8);
                        System.out.println(str);
                        break;
                    case 0:
                        System.out.println("Переход в главное меню");
                        break;
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
                        txt=new Text1(text);
                        break;
                    case 2:
                        if (txt ==null) {
                            throw new Exception("Отсутствует текст");
                        }
                        else
                        {
                            System.out.println("Количество предложений в тексте");
                            System.out.println(txt.getSentenceCounter());
                        }

                    break;
                    case 3:
                        if (txt ==null) {
                            throw new Exception("Отсутствует текст");
                        }
                        else
                        {
                            System.out.println("Количество слов в тексте ");
                            System.out.println(txt.getWordCounter());
                        }

                    break;
                    case 4:
                        if (txt ==null) {
                            throw new Exception("Отсутствует текст");
                        }
                        else {
                            inputMenu();
                        }
                    break;
                    case 5:
                        if (path.isEmpty() || name.isEmpty())
                        {
                            FileInputStream fileIn = new FileInputStream("file.txt");
                            buffer = new byte[fileIn.available()];
                            fileIn.read(buffer, 0, fileIn.available());
                            String str = new String(buffer, StandardCharsets.UTF_8);
                            txt=new Text1(str);
                            System.out.println(str);
                        }
                        else
                        {
                            String way=path + name;
                            FileInputStream fileIn = new FileInputStream(way);
                            buffer = new byte[fileIn.available()];
                            fileIn.read(buffer, 0, fileIn.available());
                            String str = new String(buffer, StandardCharsets.UTF_8);
                            txt=new Text1(str);
                            System.out.println(str);
                        }
                    break;
                    case 0:
                        System.out.println("Переход в главное меню");
                        break;
                    default:
                        System.out.println("Неверный ввод пункта!");
                }
            }
            catch (IOException e) {System.out.println("ошибка при работе с файлом");}
            catch (Exception e)   {System.out.println(e.getMessage());}
        }while (point!=0);

    }

    private static void inputMenu(){
        byte[] buffer;
        int point1 =-1;

        System.out.println("+==========================================+");
        System.out.println("|..........Меню ввода в файл...............|");
        System.out.println("| [1]- Ввод пути                      -[1] |");
        System.out.println("| [2]- Стандартный путь               -[2] |");
        System.out.println("| [0]- Выход в главное меню           -[0] |");
        System.out.println("+------------------------------------------+");

        point1 =checkPoint();
        try {
            switch (point1) {
                case 1:
                    System.out.println("Введите путь");
                    Scanner scan = new Scanner(System.in);
                    path = scan.nextLine();
                    File file = new File(path);
                    file.mkdir();
                    System.out.println("Введите имя файла");
                    name = scan.nextLine();
                    String way=path + name;
                    FileOutputStream fileOut = new FileOutputStream(way);
                    buffer = txt.getText().getBytes();
                    fileOut.write(buffer, 0, buffer.length);
                    fileOut.close();
                    break;
                case 2:
                    FileOutputStream fileOut1 = new FileOutputStream("file.txt");
                    buffer = txt.getText().getBytes();
                    fileOut1.write(buffer, 0, buffer.length);
                    fileOut1.close();
                    break;
                case 0:
                    System.out.println("Переход в главное меню");
                    break;
                default:
                    System.out.println("Неверный ввод пункта!");
            }
        }
        catch (IOException e) {System.out.println("ошибка при работе с файлом");}
        catch (Exception e)   {System.out.println(e.getMessage());}
    }

}