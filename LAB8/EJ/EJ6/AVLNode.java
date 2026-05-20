/**
 * Clase que representa un nodo del árbol AVL.
 * Contiene la clave, referencias a hijos izquierdo/derecho y la altura del nodo.
 *
 *
 */
public class AVLNode {

    int key;
    AVLNode left;
    AVLNode right;
    int height;

    /**
     * Constructor del nodo.
     * @param key Valor entero que almacena el nodo.
     */
    public AVLNode(int key) {
        this.key    = key;
        this.height = 1; // Todo nodo nuevo es hoja → altura 1
        this.left   = null;
        this.right  = null;
    }

    @Override
    public String toString() {
        return "[" + key + " | h=" + height + "]";
    }
}
