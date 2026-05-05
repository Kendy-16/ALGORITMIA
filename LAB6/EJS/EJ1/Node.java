// Nodo simple para lista enlazada
public class Node<E> {
    private E data;        // dato almacenado
    private Node<E> next;  // referencia al siguiente nodo
    
    public Node(E data) {
        this.data = data;
        this.next = null;
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