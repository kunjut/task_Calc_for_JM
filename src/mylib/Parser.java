package mylib;

import java.util.regex.Pattern;

public class Parser {
    // Поля класса
    String userInput;

    // Конструктор
    public Parser(String userInput) {
        this.userInput = userInput;
    }
    //-------------------------------------------------------------------------------------

    // Получить оператор используя регулярки
    public String getOperator() {
        String operator = "";

        if (Pattern.matches("(\\s?)+\\d+\\s?\\+\\s?.+", userInput)) {
            operator = "\\+";
        } else if (Pattern.matches("(\\s?)+.+\\s?\\*\\s?.+", userInput)) {
            operator = "\\*";
        } else if (Pattern.matches("(\\s?)+.+\\s?/\\s?.+", userInput)) {
            operator = "/";
        } else if (Pattern.matches("(\\s?)+.+\\s?-\\s?.+", userInput)) {
            operator = "-";
        } else {
            operator = "Invalid operator";
        }

        return operator;
    }
    //-------------------------------------------------------------------------------------

    // Получить массив операндов
    public String[] getOperands() {
        String[] str = {};

        // Если был введен валидный оператор
        if (getOperator() == "\\+" || getOperator() == "\\*" || getOperator() == "/" || getOperator() == "-") {
            // Разделить строку в массив, разделителем будет валидный оператор
            str = userInput.split(getOperator());
        }

        // Подрезка пробелов у элементов массива
        for (int i = 0; i < str.length; i++) {
                str[i] = str[i].trim();
        }

        return str;
    }
    //-------------------------------------------------------------------------------------

    public String leftOperand() { // геттер для левого операнда
        return getOperands()[0];
    }

    //-------------------------------------------------------------------------------------

    public String rightOperand() { // геттер для правого операнда
        return getOperands()[1];
    }

    //-------------------------------------------------------------------------------------

    public String modeOfCalc() throws SimpleException {
        // Переключатель режима калькулятора
        String mode = "";

        String arab_pat = "\\d+";
        String rome_pat = "(?i)(i*|iv|vi*|i*x*|x*i+|l+.*|c+.*|d+.*|m+.*)";

        // Если был введен валидный оператор
        if (getOperator() == "\\+" || getOperator() == "\\*" || getOperator() == "/" || getOperator() == "-") {

            // Проверка шаблоном римского или арабского операнда
            boolean check_lo_arab = Pattern.matches(arab_pat, leftOperand());
            boolean check_ro_arab = Pattern.matches(arab_pat, rightOperand());
            boolean check_lo_rome = Pattern.matches(rome_pat, leftOperand());
            boolean check_ro_rome = Pattern.matches(rome_pat, rightOperand());

            // Переключение режима относительно вида операндов
            if (check_lo_arab && check_ro_arab) {
                mode = "arabic";            // режим для арабских чисел
            } else if (check_lo_rome && check_ro_rome) {
                mode = "rome";              // режим для римских чисел
            } else if (check_lo_arab && check_ro_rome || check_lo_rome && check_ro_arab) {
                // Кинуть исключение при вводе смешанных чисел
                throw new SimpleException("Одновременно только арабские или римские числа");
            } else {
                // Кинуть исключение при вводе неподходящих чисел
                throw new SimpleException("Введены неподходящие числа");
            }
        }
        return mode;
    }
    //-------------------------------------------------------------------------------------
}
