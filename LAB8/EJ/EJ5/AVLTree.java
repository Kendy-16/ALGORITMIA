/**
 * Clase AVLTree - Ejercicio 5.
 *
 * Implementa el recorrido por amplitud (BFS / recorrido por niveles)
 * de forma RECURSIVA en la clase AVLTree, cumpliendo el enunciado:
 *
 *   → Método principal : recorridoPorAmplitud()
 *       Recorre los niveles 0 .. altura-1 del árbol.
 *
 *   → Método auxiliar  : visitarNivel(nodo, nivel)
 *       Desciende recursivamente hasta el nivel indicado e imprime
 *       los nodos que pertenecen a ese nivel.
 *
 * Resultado esperado para la secuencia 50,30,70,20,40,60,80,10,25,65:
 *   50  30  70  20  40  60  80  10  25  65
 *
 * 
 */
public class AVLTree {

    private AVLNode root;

    // =========================================================
    //  UTILIDADES INTERNAS
    // =========================================================

    private int altura(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    private void actualizarAltura(AVLNode n) {
        n.height = 1 + Math.max(altura(n.left), altura(n.right));
    }

    private int factorBalance(AVLNode n) {
        return (n == null) ? 0 : altura(n.left) - altura(n.right);
    }

    // =========================================================
    //  ROTACIONES
    // =========================================================

    private AVLNode rotarDerecha(AVLNode y) {
        AVLNode x  = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left  = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private AVLNode rotarIzquierda(AVLNode x) {
        AVLNode y  = x.right;
        AVLNode T2 = y.left;
        y.left  = x;
        x.right = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    private AVLNode balancear(AVLNode n, int key) {
        int fb = factorBalance(n);

        if (fb > 1  && key < n.left.key)  return rotarDerecha(n);           // LL
        if (fb < -1 && key > n.right.key) return rotarIzquierda(n);         // RR
        if (fb > 1  && key > n.left.key)  { n.left  = rotarIzquierda(n.left);  return rotarDerecha(n);  } // LR
        if (fb < -1 && key < n.right.key) { n.right = rotarDerecha(n.right);   return rotarIzquierda(n); } // RL

        return n;
    }

    // =========================================================
    //  INSERCIÓN
    // =========================================================

    public void insertar(int key) {
        root = insertarRec(root, key);
    }

    private AVLNode insertarRec(AVLNode n, int key) {
        if (n == null) return new AVLNode(key);

        if      (key < n.key) n.left  = insertarRec(n.left,  key);
        else if (key > n.key) n.right = insertarRec(n.right, key);
        else                  return n; // duplicado

        actualizarAltura(n);
        return balancear(n, key);
    }

    // =========================================================
    //  RECORRIDO POR AMPLITUD RECURSIVO  ← EJERCICIO 5
    // =========================================================

    /**
     * Método PRINCIPAL del recorrido por amplitud recursivo.
     *
     * Itera desde el nivel 0 (raíz) hasta el nivel (altura - 1),
     * llamando al método auxiliar visitarNivel() en cada paso.
     * No utiliza cola ni estructura iterativa adicional.
     */
    public void recorridoPorAmplitud() {
        int h = altura(root);
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│     RECORRIDO POR AMPLITUD / BFS RECURSIVO  │");
        System.out.println("├──────────┬──────────────────────────────────┤");

        for (int nivel = 0; nivel < h; nivel++) {
            System.out.printf("│ Nivel %2d │ ", nivel);
            visitarNivel(root, nivel);
            System.out.println();
        }

        System.out.println("├──────────┴──────────────────────────────────┤");
        System.out.print("│ Secuencia completa: ");
        visitarSecuencia(root, h);
        System.out.println();
        System.out.println("└─────────────────────────────────────────────┘");
    }

    /**
     * Método AUXILIAR recursivo.
     *
     * Desciende por el árbol hasta alcanzar el nivel indicado.
     * Cuando nivel == 0, imprime la clave del nodo actual.
     *
     * @param nodo  Nodo en el que se encuentra la recursión.
     * @param nivel Número de niveles restantes por bajar.
     */
    private void visitarNivel(AVLNode nodo, int nivel) {
        if (nodo == null) return;

        if (nivel == 0) {
            System.out.print(nodo.key + " ");
        } else {
            visitarNivel(nodo.left,  nivel - 1);
            visitarNivel(nodo.right, nivel - 1);
        }
    }

    /** Versión limpia para imprimir la secuencia completa en una línea. */
    private void visitarSecuencia(AVLNode raiz, int h) {
        for (int nivel = 0; nivel < h; nivel++) {
            visitarNivel(raiz, nivel);
        }
    }

    // =========================================================
    //  INFORMACIÓN DEL ÁRBOL
    // =========================================================

    public int getAltura() { return altura(root); }

    /** Inorden para verificar que el BST/AVL mantiene el orden. */
    public void inorden() {
        System.out.print("Inorden (orden ascendente): ");
        inordenRec(root);
        System.out.println();
    }

    private void inordenRec(AVLNode n) {
        if (n == null) return;
        inordenRec(n.left);
        System.out.print(n.key + " ");
        inordenRec(n.right);
    }
}
