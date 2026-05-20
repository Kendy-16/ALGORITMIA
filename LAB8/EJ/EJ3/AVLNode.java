/**
 * Clase AVLNode - Nodo del árbol AVL.
 *
 * Almacena la clave entera del nodo, referencias a sus hijos
 * izquierdo y derecho, y la altura necesaria para el cálculo
 * del factor de balance AVL.
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLNode {

    int     key;     // Clave del nodo
    AVLNode left;    // Hijo izquierdo
    AVLNode right;   // Hijo derecho
    int     height;  // Altura del nodo en el árbol

    /**
     * Constructor: crea un nodo hoja con altura inicial 1.
     * @param key Valor entero que almacena el nodo.
     */
    public AVLNode(int key) {
        this.key    = key;
        this.height = 1;
        this.left   = null;
        this.right  = null;
    }

    @Override
    public String toString() {
        return "Nodo[" + key + " | h=" + height + "]";
    }
}
