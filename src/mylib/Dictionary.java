package mylib;

import java.util.regex.Pattern;

public class Dictionary {
    // Поля класса
    String[] arabic = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    String str = String.join("|", arabic);
    public String leftOperand;
    public String rightOperand;

    // Конструктор
    public Dictionary(String leftOperand, String rightOperand) {
        this.leftOperand = leftOperand.toUpperCase();
        this.rightOperand = rightOperand.toUpperCase();
    }
    // --------------------------------------------------------------------------

    public void RomeToArabic() throws SimpleException {
        // перевод левого операнда в арабское число
        for (int i = 0; i < arabic.length; i++) {
            if (leftOperand.equals(rome[i])) {
                leftOperand = arabic[i];
            }
        }

        // перевод правого операнда в арабское число
        for (int i = 0; i < arabic.length; i++) {
            if (rightOperand.equals(rome[i])) {
                rightOperand = arabic[i];
            }
        }

        // Если введено римское число более X
        if (!Pattern.matches(str, leftOperand) || !Pattern.matches(str, rightOperand)) {
            // Кинуть исключение
            throw new SimpleException("Числа только от I до Х");
        }
    }
    // --------------------------------------------------------------------------

    public void ArabicToRome(int result) throws SimpleException {
        // Вывод результата римскими цифрами
        if (result == 0) {
            // Кинуть исключение если результат 0
            throw new SimpleException("'ноль', но римлянами он не предусмотрен");
        } else if (result == 100) {
            System.out.println("C");
        } else if (result > 90) {
            System.out.println("XC" + rome[result - 91]);
        } else if (result == 90) {
            System.out.println("XC");
        } else if (result > 80) {
            System.out.println("LXXX" + rome[result - 81]);
        } else if (result == 80) {
            System.out.println("LXXX");
        } else if (result > 70) {
            System.out.println("LXX" + rome[result - 71]);
        } else if (result == 70) {
            System.out.println("LXX");
        } else if (result > 60) {
            System.out.println("LX" + rome[result - 61]);
        } else if (result == 60) {
            System.out.println("LX");
        } else if (result > 50) {
            System.out.println("L" + rome[result - 51]);
        } else if (result == 50) {
            System.out.println("L");
        } else if (result > 40) {
            System.out.println("XL" + rome[result - 41]);
        } else if (result == 40) {
            System.out.println("XL");
        } else if (result > 30) {
            System.out.println("XXX" + rome[result - 31]);
        } else if (result > 20) {
            System.out.println("XX" + rome[result - 21]);
        } else if (result > 10) {
            System.out.println("X" + rome[result - 11]);
        } else {
            System.out.println(rome[result - 1]);
        }
    }
    // --------------------------------------------------------------------------
}
