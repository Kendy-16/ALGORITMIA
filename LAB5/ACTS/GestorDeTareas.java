// Clase que gestiona tareas usando la lista enlazada
public class GestorDeTareas<T extends Comparable<T>> {

    private ListLinked<T> lista; // lista interna

    public GestorDeTareas() {
        lista = new ListLinked<T>();
    }

    // Agrega tarea al final
    public void agregarTarea(T tarea) {
        lista.insertLast(tarea);
    }

    // Elimina tarea si existe
    public boolean eliminarTarea(T tarea) {
        return lista.removeNode(tarea);
    }

    // Busca tarea
    public T buscarTarea(T tarea) {
        return lista.search(tarea);
    }

    // Imprime todas las tareas
    public void imprimirTareas() {
        lista.print();
    }

    // Cuenta tareas
    public int contarTareas() {
        return lista.length();
    }

    // Invierte la lista
    public void invertirTareas() {
        lista.reverse();
    }

    public ListLinked<T> obtenerLista() {
        return lista;
    }

    // Busca la tarea con mayor prioridad
    public T obtenerTareaPrioritaria() {
    
        // Si la lista está vacía, no hay tareas
        if (lista.isEmptyList()) {
            return null;
        }
        
        // Empezamos desde el primer nodo
        Node<T> current = lista.getFirst();
        
        // Tomamos el primero como referencia inicial
        T mejor = current.value;
        
        // Recorremos toda la lista
        while (current != null){
            
            // Si la tarea actual tiene mayor prioridad
            if (current.value.compareTo(mejor) < 0) {
                mejor = current.value;
            }
            
            // Avanzamos al siguiente nodo
            current = current.next;
        }
        
        // Devolvemos la mejor tarea encontrada
        return mejor;
    }
}