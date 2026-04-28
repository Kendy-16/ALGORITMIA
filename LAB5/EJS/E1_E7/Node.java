public class Node<T> {

    T value;        // dato almacenado
    Node<T> next;   // referencia al siguiente nodo

    public Node(T value) {
        this.value = value;
        this.next = null; // al inicio no apunta a nada
    }
}