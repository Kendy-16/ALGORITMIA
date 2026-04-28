public class ListLinked<T> {
    private Node<T> first; // primer nodo

    public ListLinked() {
        first = null; // lista vacía
    }

    public Node<T> getFirst() {
        return first;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    // Inserta al inicio
    public void insertFirst(T x) {
        
        Node<T> newNode = new Node<T>(x);
        newNode.next = first;
        first = newNode;
    }

    // Inserta al final
    public void insertLast(T x) {
        
        Node<T> newNode = new Node<T>(x);
        if (isEmptyList()) {
            first = newNode;
            return;
        }
        
        Node<T> current = first;
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = newNode;
    }

    // Imprime lista
    public void print() {
        
        Node<T> current = first;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}