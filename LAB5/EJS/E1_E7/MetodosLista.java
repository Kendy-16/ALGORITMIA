public class MetodosLista {

    // EJERCICIO 1
    public static <T> boolean buscarElemento(ListLinked<T> lista, T valor) {
        
        Node<T> current = lista.getFirst();
        
        while (current != null) {
            
            if (current.value.equals(valor)) {
                return true;
            }
            
            current = current.next;
        }
        
        return false;
    }

    // EJERCICIO 2
    public static <T> ListLinked<T> invertirLista(ListLinked<T> lista) {
        
        ListLinked<T> nueva = new ListLinked<>();
        
        Node<T> current = lista.getFirst();
        
        while (current != null) {
            
            nueva.insertFirst(current.value);
            current = current.next;
        }
        
        return nueva;
    }

    // EJERCICIO 5
    public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {

        Node<T> c1 = lista1.getFirst();
        Node<T> c2 = lista2.getFirst();
        
        while (c1 != null && c2 != null) {
            
            if (!c1.value.equals(c2.value)) {
                return false;
            }
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return c1 == null && c2 == null;
    }

    // EJERCICIO 6
    public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
        
        ListLinked<T> nueva = new ListLinked<>();
        
        Node<T> current = lista1.getFirst();
        
        while (current != null) {
            
            nueva.insertLast(current.value);
            current = current.next;
        }
        
        current = lista2.getFirst();
        
        while (current != null) {
            
            nueva.insertLast(current.value);
            current = current.next;
        }
        
        return nueva;
    }
}