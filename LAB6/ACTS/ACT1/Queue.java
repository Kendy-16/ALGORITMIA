// Interfaz de cola FIFO
public interface Queue<E> {
    
    void enqueue(E x);                    // insertar
    E dequeue() throws ExceptionIsEmpty;  // eliminar frente
    E front() throws ExceptionIsEmpty;    // ver frente
    boolean isEmpty();                    // verificar vacío
}