// Interfaz de cola de prioridad
// E = tipo de dato, N = tipo de prioridad

public interface PriorityQueue<E, N> {

    // Inserta un elemento con su prioridad
    void enqueue(E x, N pr);
    
    // Elimina el elemento con mayor prioridad (inicio)
    E dequeue() throws ExceptionIsEmpty;
    
    // Devuelve el elemento con mayor prioridad
    E front() throws ExceptionIsEmpty;
    
    // Devuelve el elemento con menor prioridad
    E back() throws ExceptionIsEmpty;
    
    // Verifica si está vacía
    boolean isEmpty();
}