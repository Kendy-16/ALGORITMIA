// Nodo simple para lista enlazada
public class Node<E> {
    private E data;        // aquí se guarda el valor
    private Node<E> next;  // referencia al siguiente nodo
    
    // Constructor: crea un nodo con un dato
    public Node(E data) {
        this.data = data;
        this.next = null;   // al inicio no apunta a nadie
    }
    
    // Devuelve el dato del nodo
    public E getData() {
        return data;
    }
    
    // Devuelve el siguiente nodo
    public Node<E> getNext() {
        return next;
    }
    
    // Cambia la referencia al siguiente nodo
    public void setNext(Node<E> next) {
        this.next = next;
    }
}