// Nodo para lista doblemente enlazada genérica
// Tiene puntero al siguiente y al anterior (para navegar en ambas direcciones)
public class NodoDoble<T> {

    T dato;
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}