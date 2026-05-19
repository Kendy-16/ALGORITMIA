/**
 * Clase AVLNode - Nodo del árbol AVL para el registro de productos.
 *
 * Cada nodo almacena el código numérico único de un producto,
 * las referencias a los hijos izquierdo y derecho, y la altura
 * del nodo dentro del árbol (necesaria para el balanceo AVL).
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLNode {

    int     codigo;   // Código numérico único del producto
    AVLNode left;     // Hijo izquierdo
    AVLNode right;    // Hijo derecho
    int     height;   // Altura del nodo en el árbol

    /**
     * Constructor: crea un nodo hoja con altura inicial 1.
     * @param codigo Código del producto a almacenar.
     */
    public AVLNode(int codigo) {
        this.codigo = codigo;
        this.height = 1;
        this.left   = null;
        this.right  = null;
    }

    @Override
    public String toString() {
        return "Producto[" + codigo + " | h=" + height + "]";
    }
}
