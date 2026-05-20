public class DequeLink<E> implements Deque<E> {
    
    private Node<E> first; // primer nodo
    private Node<E> last;  // último nodo
    
    // Constructor: inicia la estructura vacía
    public DequeLink() {
        first = null;
        last = null;
    }
    
    @Override
    public void addFirst(E x) {
        Node<E> newNode = new Node<>(x);
        
        // Si está vacío, ambos apuntan al mismo nodo
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            // El nuevo nodo apunta al antiguo primero
            newNode.setNext(first);
            first = newNode;
        }
    }
    
    @Override
    public void addLast(E x) {
        Node<E> newNode = new Node<>(x);
        
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            // El último apunta al nuevo nodo
            last.setNext(newNode);
            last = newNode;
        }
    }
    
    @Override
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Estructura vacía");
        }
        
        E aux = first.getData();
        first = first.getNext(); // avanza el inicio
        
        // Si queda vacío, también se actualiza last
        if (first == null) {
            last = null;
        }
        
        return aux;
    }
    
    @Override
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Estructura vacía");
        }
        
        E aux = last.getData();
        
        // Caso: solo hay un elemento
        if (first == last) {
            first = null;
            last = null;
            return aux;
        }
        
        // Recorrer hasta el penúltimo nodo
        Node<E> current = first;
        while (current.getNext() != last) {
            current = current.getNext();
        }
        
        current.setNext(null);
        last = current;
        
        return aux;
    }
    
    @Override
    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Estructura vacía");
        }
        return first.getData();
    }
    
    @Override
    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Estructura vacía");
        }
        return last.getData();
    }
    
    @Override
    public boolean isEmpty() {
        return first == null;
    }
    
    @Override
    public String toString() {
        String s = "[";
        Node<E> current = first;
        
        // Recorrer la lista desde el inicio
        while (current != null) {
            s += current.getData();
            if (current.getNext() != null) {
                s += ", ";
            }
            current = current.getNext();
        }
        
        return s + "]";
    }
}