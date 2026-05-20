// Excepción personalizada para cuando la estructura está vacía
public class ExceptionIsEmpty extends Exception {
    
    // Constructor que recibe un mensaje de error
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}