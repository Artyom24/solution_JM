public class Roman_number {
    private Integer number;

    public Roman_number(String number_string) {
        number = converter(number_string);
    }

    private Integer converter (String number) //превращает римские цифры в арабские (индийские), проверяет, чтобы оба числа были римскими (в соответствии с условиями задачи)
    {
        Integer arabic_numeral = 0;
        switch (number){
            case "I" : arabic_numeral = 1;
                break;
            case "II" : arabic_numeral = 2;
                break;
            case "III" : arabic_numeral = 3;
                break;
            case "IV" : arabic_numeral = 4;
                break;
            case "V" : arabic_numeral = 5;
                break;
            case "VI" : arabic_numeral = 6;
                break;
            case "VII" : arabic_numeral = 7;
                break;
            case "VIII" : arabic_numeral = 8;
                break;
            case "IX" : arabic_numeral = 9;
                break;
            case "X" : arabic_numeral = 10;
                 break;
            default:
                throw new IllegalArgumentException("Enter of illegal number!");
        }
        return arabic_numeral;
    }

    public Integer getNumber() {
        return number;
    }
}

