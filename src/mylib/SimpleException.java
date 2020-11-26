package mylib;

public class SimpleException extends Exception {
    // Конструктор
    public SimpleException(String message) {
        // Вызывать конструктор предка
        super(message);
    }
}
