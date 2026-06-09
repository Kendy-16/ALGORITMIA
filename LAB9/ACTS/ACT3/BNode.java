import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    protected ArrayList<E> keys;            // Lista donde se guardan las claves del nodo
    protected ArrayList<BNode<E>> childs;   // Lista donde se guardan las referencias a los hijos
    protected int count;                    // Cantidad real de claves almacenadas actualmente
    private static int nextId = 1;          // Generador automático de IDs para los nodos
    protected int idNode;                   // Identificador único del nodo

    // Constructor, n representa el orden del árbol.
    public BNode(int n) {

        keys = new ArrayList<>(n);
        childs = new ArrayList<>(n);

        count = 0;          // Inicialmente el nodo está vacío
        idNode = nextId++;  // Asigna un ID único al nodo

        for (int i = 0; i < n; i++) {   // Se llenan las listas con null para luego usar 
            keys.add(null);          // set(pos, valor) sin errores
            childs.add(null);
        }
    }

    public boolean nodeFull(int maxKeys) {  // Verifica si el nodo alcanzó 
        return count == maxKeys;            // el máximo número de claves permitidas
    }

    public boolean nodeEmpty() {
        return count == 0;          // Verifica si el nodo no contiene claves
    }

    public boolean searchNode(E key, int pos[]) {   
    //Busca una clave dentro del nodo. Si la encuentra da true. Si no la encuentra da false

        pos[0] = 0;           // Comenzamos desde la primera clave

        while ( pos[0] < count && key.compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;         // Avanza mientras la clave buscada sea mayor que la clave actual
        }

        if (pos[0] < count && key.compareTo(keys.get(pos[0])) == 0) {
            return true;      // Si estamos dentro del rango y la clave coincide, entonces existe

        }

        return false;
    }

    public int getIdNode() {
        return idNode;      // Devuelve el identificador del nodo
    }

    @Override
    public String toString() {      // Convierte el nodo en una cadena de texto.

        StringBuilder sb = new StringBuilder();
        sb.append("Nodo ").append(idNode).append(": (");

        for (int i = 0; i < count; i++) {       // Recorre todas las claves válidas

            sb.append(keys.get(i));
            if (i < count - 1) {    
                sb.append(", ");           // Agrega coma entre claves
            }
        }

        sb.append(")");
        return sb.toString();
    }
}