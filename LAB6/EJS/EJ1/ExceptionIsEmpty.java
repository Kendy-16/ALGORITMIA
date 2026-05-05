// Excepción para cuando se intenta operar con la pila vacía
public class ExceptionIsEmpty extends Exception {

    // Constructor que recibe el mensaje de error
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}