// Excepcion de elemento inexistente
public class ItemNoFound extends Exception {
    public ItemNoFound(String message) {
        super(message);
    }

    public ItemNoFound() {
        super("Error: el dato no fue encontrado en el árbol.");
    }
}