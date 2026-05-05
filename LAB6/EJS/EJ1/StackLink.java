public class StackLink<E> implements Stack<E> {
    
    private Node<E> top; // apunta al nodo del tope
    
    public StackLink() {
        top = null; // inicia vacía
    }
    
    @Override
    public void push(E x) {
        // Crear nuevo nodo
        Node<E> newNode = new Node<>(x);
        
        // El nuevo nodo apunta al antiguo tope
        newNode.setNext(top);
        
        // Ahora el nuevo nodo es el tope
        top = newNode;
    }
    
    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        }
        
        // Guardar el valor del tope
        E aux = top.getData();
        
        // Mover el tope al siguiente nodo
        top = top.getNext();
        
        return aux;
    }
    
    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        }
        
        // Retorna el valor del tope sin eliminarlo
        return top.getData();
    }
    
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public String toString() {
        String s = "[";
        Node<E> current = top;
        
        // Recorre desde el tope hacia abajo
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