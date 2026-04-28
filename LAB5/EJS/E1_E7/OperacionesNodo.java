public class OperacionesNodo {

    // EJERCICIO 3
    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        
        Node<T> nuevo = new Node<>(valor);
        
        if (head == null) {
            return nuevo;
        }
        
        Node<T> current = head;
        
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = nuevo;
        return head;
    }

    // EJERCICIO 4
    public static <T> int contarNodos(Node<T> head) {
        
        int count = 0;
        Node<T> current = head;
        
        while (current != null) {
            
            count++;
            current = current.next;
        }
        
        return count;
    }
}