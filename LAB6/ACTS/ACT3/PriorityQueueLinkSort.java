// Implementación de cola de prioridad con lista enlazada (mayor primero)
public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    
    // Clase interna que guarda dato + prioridad
    class Entry {
        E data;
        N priority;
        
        Entry(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }
    
    private Node<Entry> first; // nodo con mayor prioridad
    private Node<Entry> last;  // nodo con menor prioridad
    
    public PriorityQueueLinkSort() {
        first = null;
        last = null;
    }
    
    @Override
    public void enqueue(E x, N pr) {
        Entry newEntry = new Entry(x, pr);
        Node<Entry> newNode = new Node<>(newEntry);
        
        // Caso 1: cola vacía
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        
        // Caso 2: insertar al inicio si tiene mayor prioridad
        if (pr.compareTo(first.getData().priority) > 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }
        
        // Caso 3: buscar posición correcta en la lista
        Node<Entry> current = first;
        
        // Avanza mientras la siguiente prioridad sea mayor o igual
        while (current.getNext() != null && pr.compareTo(current.getNext().getData().priority) <= 0) {
            current = current.getNext();
        }
        
        // Insertar el nuevo nodo en su posición
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        
        // Si se insertó al final, actualizar last
        if (newNode.getNext() == null) {
            last = newNode;
        }
    }
    
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        
        // Elimina el primero (mayor prioridad)
        E aux = first.getData().data;
        first = first.getNext();
        
        // Si queda vacía, actualizar last
        if (first == null) {
            last = null;
        }
        
        return aux;
    }
    
    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        
        return first.getData().data;
    }
    
    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        
        return last.getData().data;
    }
    
    @Override
    public boolean isEmpty() {
        return first == null;
    }
    
    @Override
    public String toString() {
        String s = "[";
        Node<Entry> current = first;
        
        // Recorre toda la lista mostrando dato y prioridad
        while (current != null) {
            s += current.getData().data + "(" + current.getData().priority + ")";
            if (current.getNext() != null) {
                s += ", ";
            }
            current = current.getNext();
        }
        
        return s + "]";
    }
}