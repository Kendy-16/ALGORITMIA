// Excepcion de estructura vacía
public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }

    public ExceptionIsEmpty() {
        super("Error: la estructura está vacía.");
    }
}