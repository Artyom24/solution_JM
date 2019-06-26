import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task_JM {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter numbers from 1 to 10 (Arabic or Roman) and the operator (*, /, -, +)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression = reader.readLine();
        Calculator calculator_JM = new Calculator(expression);
        if(calculator_JM.roman){
            System.out.println(calculator_JM.getRoman_result());
        }
        else{
            System.out.println(calculator_JM.getArabic_result());
        }

    }
}
/* Даже если вы не возьмёте меня на обучение, всё равно спасибо за интересную задачу  ) */