// Excepcion de elemento duplicado
public class ItemDuplicated extends Exception {
    public ItemDuplicated(String message) {
        super(message);
    }

    public ItemDuplicated() {
        super("Error: el dato ya existe en el árbol.");
    }
}