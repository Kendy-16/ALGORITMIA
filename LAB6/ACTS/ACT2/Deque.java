// Insertar y eliminar por ambos extremos
public interface Deque<E> {
    
    void addFirst(E x);   // insertar al inicio
    void addLast(E x);    // insertar al final
    
    E removeFirst() throws ExceptionIsEmpty; // eliminar del inicio
    E removeLast() throws ExceptionIsEmpty;  // eliminar del final
    
    E getFirst() throws ExceptionIsEmpty; // ver primer elemento
    E getLast() throws ExceptionIsEmpty;  // ver último elemento
    
    boolean isEmpty(); // verificar si está vacío
}