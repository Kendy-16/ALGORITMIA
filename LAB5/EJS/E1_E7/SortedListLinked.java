public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {
    
    public void insertOrden(T x) {
        
        Node<T> newNode = new Node<>(x);
        
        if (isEmptyList()) {
            
            newNode.next = getFirst();
            super.insertFirst(x);
            return;
        }
        
        Node<T> current = getFirst();
        Node<T> prev = null;
        
        while (current != null && current.value.compareTo(x) < 0) {
            prev = current;
            current = current.next;
        }
        
        if (prev == null) {
            
            super.insertFirst(x);
            
        } else {
            
            newNode.next = current;
            prev.next = newNode;
        }
    }
}