/**
 * Clase AVLNode - Nodo del árbol AVL para el sistema de turnos clínicos.
 *
 * Cada nodo almacena el número de turno único de un paciente,
 * junto con las referencias a sus hijos y su altura en el árbol.
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLNode {

    int     turno;   // Número de turno del paciente (clave única)
    AVLNode left;    // Hijo izquierdo
    AVLNode right;   // Hijo derecho
    int     height;  // Altura del nodo

    /**
     * Constructor: crea un nodo hoja con altura inicial 1.
     * @param turno Número de turno del paciente.
     */
    public AVLNode(int turno) {
        this.turno  = turno;
        this.height = 1;
        this.left   = null;
        this.right  = null;
    }

    @Override
    public String toString() {
        return "Turno[" + turno + " | h=" + height + "]";
    }
}
