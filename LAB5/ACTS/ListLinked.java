public class ListLinked<T> {

    private Node<T> first; // primer nodo

    public ListLinked() {
        first = null; // lista inicial vacía
    }

    public Node<T> getFirst() {
        return first; // devuelve el primer nodo
    }

    public boolean isEmptyList() {
        return first == null;
    }

    // Inserta al inicio
    public void insertFirst(T x) {
        Node<T> newNode = new Node<T>(x);
        
        newNode.next = first; // enlaza con el anterior primero
        first = newNode;      // ahora es el primero
    }

    // Inserta al final
    public void insertLast(T x) {
        
        Node<T> newNode = new Node<T>(x);
        
        if (isEmptyList()) {
            first = newNode; // si está vacía
            return;
        }

        Node<T> current = first;
        
        // recorrer hasta el último nodo
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = newNode; // enlazar nuevo nodo
    }

    // Elimina un nodo con valor x
    public boolean removeNode(T x) {
        
        if (isEmptyList()) {
            return false;
        }
        
        // si el primero es el que se elimina
        if (first.value.equals(x)) {
            first = first.next;
            return true;
        }
        
        Node<T> current = first;
        
        // buscar el nodo a eliminar
        while (current.next != null) {
            
            if (current.next.value.equals(x)) {
            
                current.next = current.next.next; // saltar nodo
                return true;
            }
            
            current = current.next;
        }
        
        return false;
    }

    // Busca un valor
    public T search(T x) {
        
        Node<T> current = first;
        
        while (current != null) {
        
            if (current.value.equals(x)) {
                return current.value;
            }
            
            current = current.next;
        }
        
        return null;
    }

    // Cuenta nodos
    public int length() {
        
        int count = 0;
        
        Node<T> current = first;
        
        while (current != null) {
            
            count++;
            current = current.next;
        }

        return count;
    }

    // Imprime todos los valores
    public void print() {

        Node<T> current = first;
        
        while (current != null) {
        
            System.out.println(current.value);
            current = current.next;
        }
    }

    // Invierte el orden de la lista
    public void reverse() {
        
        Node<T> prev = null;
        Node<T> current = first;
        Node<T> next = null;
        
        while (current != null) {
            
            next = current.next;
            current.next = prev; // invierte enlace
            
            prev = current;
            current = next;
        }
        
        first = prev; // nuevo primer nodo
    }
}