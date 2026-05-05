// Interfaz de una pila (Stack - LIFO)
public interface Stack<E> {
    // Inserta un elemento en el tope
    void push(E x);
    
    // Elimina y retorna el elemento del tope
    E pop() throws ExceptionIsEmpty;
    
    // Retorna el elemento del tope sin eliminarlo
    E top() throws ExceptionIsEmpty;
    
    // Verifica si la pila está vacía
    boolean isEmpty();
}