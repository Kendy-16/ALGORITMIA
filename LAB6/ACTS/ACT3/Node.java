// Nodo simple para la lista enlazada
public class Node<E> {
    private E data;        // dato que guarda el nodo
    private Node<E> next;  // referencia al siguiente nodo
    
    public Node(E data) {
        this.data = data;
        this.next = null; // inicialmente no apunta a nadie
    }
    
    public E getData() {
        return data;
    }
    
    public Node<E> getNext() {
        return next;
    }
    
    public void setNext(Node<E> next) {
        this.next = next;
    }
}