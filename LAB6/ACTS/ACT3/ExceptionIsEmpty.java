// Excepción personalizada que se usa cuando la cola está vacía
public class ExceptionIsEmpty extends Exception {

    // Constructor que recibe el mensaje de error
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}