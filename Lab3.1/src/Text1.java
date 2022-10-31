
public class Text1 {
    private  String text;

    public Text1(String text)
    {                                                                               // конструктор
        this.text = text;
    }
/*
    public String getLines(String text) {                                                       // готовая строка
        this.text=text;
        return text;
    }
*/
    public boolean nothing() {                                                   // проверка на пустую строку
        return text.isEmpty();
    }

    public int getSentenceCounter() {
        int counter;
        counter=0;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?') {
                counter++;
            }
        }
        return counter;
    }
    public int getWordCounter() {
        int counter;
        int flag=0;
        counter=0;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i)== ' ' || text.charAt(i)==  '.' || text.charAt(i) == '\t')
            {
                flag = 0;
            }
            else if(flag ==0) {
                flag = 1;
                counter++;
            }
        }
        return counter;
    }
    public String getText() {
        return this.text;
    }
}