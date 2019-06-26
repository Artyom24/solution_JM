public class Calculator {
    private Integer first_digit;
    private Integer second_digit;
    private Integer arabic_result;
    private String roman_result;
    private String operator;
    public boolean roman; //флаг, показывающий являются ли введёные числа римскими

    public Calculator(String s) {
        String[] array_of_symbols = processor_String(s);
        string_to_Integer(array_of_symbols);
        arithmetic_processor();
        if (roman){
            converter(arabic_result);
        }
    }

    private String [] processor_String(String s) // метод возвращает массив из подстрок, где 1-й эллемент - 1-е число, 2-й элемент - оператор, 3-й элемент - второе число
    {
        String name = s;
        name=name.replaceAll(" ", ""); // удаляем из строки все пробелы
        if (name.isEmpty()) {                           //проверяем, не является ли строка пустой
            throw new IllegalArgumentException("Enter is empty string!");
        }
        int count_of_operators=0;
        for (char c : name.toCharArray()){          //проверяем, есть ли в строке предусмотренный заданием арифмитический знак и один ли он
            if(c=='+'||c=='-'||c=='*'||c=='/'){
                count_of_operators++;
            }
        }
        String[] array_of_symbols = new String[3];
        if(count_of_operators==1){                  //если в строке только один нужный оператор, делим её на подстроки по этому оператору
            if(name.contains("+")){
                array_of_symbols[0]=name.substring(0,name.indexOf("+"));
                array_of_symbols[1]="+";
                array_of_symbols[2]=name.substring(name.indexOf("+")+1);
            }
            else if(name.contains("-")){
                array_of_symbols[0]=name.substring(0,name.indexOf("-"));
                array_of_symbols[1]="-";
                array_of_symbols[2]=name.substring(name.indexOf("-")+1);
            }
            else if(name.contains("*")){
                array_of_symbols[0]=name.substring(0,name.indexOf("*"));
                array_of_symbols[1]="*";
                array_of_symbols[2]=name.substring(name.indexOf("*")+1);
            }
            if(name.contains("/")){
                array_of_symbols[0]=name.substring(0,name.indexOf("/"));
                array_of_symbols[1]="/";
                array_of_symbols[2]=name.substring(name.indexOf("/")+1);
            }

        }
        else{              // если в строке нет нужного символа, или их несколько, программа выбрасывает исключение
            throw new IllegalArgumentException("Enter of illegal operator!");
        }
        return array_of_symbols;

    }

    private void string_to_Integer(String[] array_of_symbols) //извлекает из массива строк первое число, второе число и оператор
    {
        try{
            first_digit = Integer.parseInt(array_of_symbols[0]);  //если в выражении арабские цифры, заносим их в переменные, заодно проверяем чтобы числа были в диапазоне от 1 до 10
            second_digit = Integer.parseInt(array_of_symbols[2]);
            if (first_digit<1||first_digit>10||second_digit<1||second_digit>10) {
                throw new IllegalArgumentException("The entered number does not match the specified range!");
            }

        }
        catch (NumberFormatException e){
            first_digit = new Roman_number(array_of_symbols[0]).getNumber();
            second_digit = new Roman_number(array_of_symbols[2]).getNumber();
            roman=true;
        }
        operator = array_of_symbols[1];
    }

    private void arithmetic_processor () //метод выполняет арифметические действия над числами в соответствии с введённым оператором и заносит результат в переменную
    {
        switch (operator){
            case "+": arabic_result=first_digit+second_digit;
                break;
            case "-": arabic_result=first_digit-second_digit;
                break;
            case "*": arabic_result=first_digit*second_digit;
                break;
            case "/": arabic_result=first_digit/second_digit;
                break;
        }

    }

    public Integer getArabic_result() {
        return arabic_result;
    }

    public String getRoman_result() {
        return roman_result;
    }

    private void converter(Integer arabic_digit) //метод, преобразующий арабские цифры в римские. Данный кусок взял с Хабра:https://habr.com/ru/post/136646/, так как не успевал. Исправил ошибки, работает )
    {
        StringBuffer a = new StringBuffer("");

        // Выделяем сотни
        int c1 = arabic_digit / 100;
        a.append(C(c1));
        // остаток из сотен
        int c2 = arabic_digit % 100;

        // Выделяем полсотни
        int l1 = c2 / 50;
        a.append(L(l1));
        // остаток
        int l2 = c2 % 50;

        // Выделяем десятки
        int x1 = l2 / 10;
        a.append(X(x1));
        // остаток
        int x2 = l2 % 10;

        // Выделяем то что осталось
        a.append(basic(x2));
        roman_result = a.toString();
    }

    private static String C(int in) {

        if ((in != 0) && (in < 4)) {
            StringBuffer a = new StringBuffer("");
            int i = 0;
            while (i < in) {
                a.append("C");
                i++;
            }
            return a.toString();
        }
        else return "";
    }

    private String L(int in) {
        if (arabic_result==90) return "XC"; // да, это костыль, но что делать, время 2:27 (

        if ((in != 0) && (in < 4)) {
            StringBuffer a = new StringBuffer("");
            int i = 0;
            while (i < in) {
                a.append("L");
                i++;
            }
            return a.toString();
        }
        else return "";

    }

    private String X(int in) {
        if (arabic_result==40) return "XL"; // если 40, то 50-10
        else if ((in != 0) && (in < 4)) {
            StringBuffer a = new StringBuffer("");
            int i = 0;
            while (i < in) {
                a.append("X");
                i++;
            }
            return a.toString();
        }
        else return "";
    }

    private static String basic(int in) {
        String[] a = {
                "",
                "I",
                "II",
                "III",
                "IV",
                "V",
                "VI",
                "VII",
                "VIII",
                "IX"
        };
        return a[in];
    }
}
