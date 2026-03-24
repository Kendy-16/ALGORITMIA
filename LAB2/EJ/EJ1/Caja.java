public class Caja<T> {

    private String color;
    private T contenido;

    // Constructor (asigna color)
    public Caja(String color) {
        this.color = color;
    }

    // Guardar objeto dentro de la caja
    public void guardar(T objeto) {
        this.contenido = objeto;
    }

    // Obtener objeto guardado
    public T obtener() {
        return contenido;
    }

    // Obtener color
    public String getColor() {
        return color;
    }
}
