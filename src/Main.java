import java.util.Scanner;

import mylib.Parser;
import mylib.Dictionary;
import mylib.SimpleException;


public class Main {

    public static void main(String[] args) throws SimpleException {

        System.out.println("Введите что посчитать: ");
        String userInput = "";

        while (userInput.equals("")) {
            // Scanner считывает ввод пользователя, пока не будет что-то передано
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
        }

        // Передать выражение для парсинга
        Parser parser = new Parser(userInput);

        // Если был введен оператор отличный от + - / *
        if(parser.getOperator().equals("Invalid operator") || parser.getOperands().length > 2) {
            //Кинуть исключение
            throw new SimpleException("Введен не валидный оператор");
        }

        System.out.println("Результат: ");

        // Если калькулятор работает в режиме счета арабских чисел
        if (parser.modeOfCalc().equals("arabic")) {
            // Если введенные арабские числа не соответствую диапазону от 1 до 10
            if (Integer.parseInt(parser.leftOperand()) < 1 || Integer.parseInt(parser.leftOperand()) > 10 ||
                    Integer.parseInt(parser.rightOperand()) < 1 || Integer.parseInt(parser.rightOperand()) > 10) {
                // Кинуть исключение
                throw new SimpleException("Цифры только от 1 до 10");
            }

            // Получить арабские операнды
            int l_operand = Integer.parseInt(parser.leftOperand());
            int r_operand = Integer.parseInt(parser.rightOperand());

            // Получить результат арабскими числами
            switch (parser.getOperator()) {
                case "\\+":
                    System.out.println(l_operand + r_operand);
                    break;
                case "\\*":
                    System.out.println(l_operand * r_operand);
                    break;
                case "/":
                    System.out.println(l_operand / r_operand);
                    break;
                case "-":
                    System.out.println(l_operand - r_operand);
                    break;
            }
        // Если калькулятор работает в режиме счета римских чисел
        } else if (parser.modeOfCalc().equals("rome")) {
            // Отправить римские операнды на перевод в арабские
            Dictionary translate = new Dictionary(parser.leftOperand(), parser.rightOperand());
            translate.RomeToArabic();

            // Получить арабские числа римских операндов
            int l_operand = Integer.parseInt(translate.leftOperand);
            int r_operand = Integer.parseInt(translate.rightOperand);

            // Получить результат римскими числами
            switch (parser.getOperator()) {
                case "\\+":
                    translate.ArabicToRome(l_operand + r_operand);
                    break;
                case "\\*":
                    translate.ArabicToRome(l_operand * r_operand);
                    break;
                case "/":
                    translate.ArabicToRome(l_operand / r_operand);
                    break;
                case "-":
                    translate.ArabicToRome(l_operand - r_operand);
                    break;
            }
        }
    }
}
