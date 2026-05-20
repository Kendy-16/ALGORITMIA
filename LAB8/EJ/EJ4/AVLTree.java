/**
 * Clase AVLTree - Árbol AVL autobalanceado.
 *
 * Implementa:
 *   - Inserción con rotaciones (LL, RR, LR, RL).
 *   - Recorrido por amplitud RECURSIVO (BFS recursivo).
 *   - Recorrido inorden  (para comparación).
 *   - Recorrido preorden (para comparación).
 *
 * El recorrido por amplitud recursivo utiliza dos métodos:
 *   1. recorridoAmplitud()  → recorre niveles 0..altura-1.
 *   2. imprimirNivel(nodo, nivel) → método auxiliar recursivo que
 *      imprime los nodos de un nivel específico.
 *
 *
 */
public class AVLTree {

    private AVLNode root;

    // =========================================================
    //  UTILIDADES DE ALTURA Y BALANCE
    // =========================================================

    /** Devuelve la altura de un nodo (0 si es null). */
    private int altura(AVLNode nodo) {
        return (nodo == null) ? 0 : nodo.height;
    }

    /** Actualiza la altura de un nodo según sus hijos. */
    private void actualizarAltura(AVLNode nodo) {
        nodo.height = 1 + Math.max(altura(nodo.left), altura(nodo.right));
    }

    /** Factor de balance: altura(izq) - altura(der). */
    private int balance(AVLNode nodo) {
        return (nodo == null) ? 0 : altura(nodo.left) - altura(nodo.right);
    }

    // =========================================================
    //  ROTACIONES
    // =========================================================

    /** Rotación simple a la derecha (caso LL). */
    private AVLNode rotarDerecha(AVLNode y) {
        AVLNode x  = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left  = T2;

        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    /** Rotación simple a la izquierda (caso RR). */
    private AVLNode rotarIzquierda(AVLNode x) {
        AVLNode y  = x.right;
        AVLNode T2 = y.left;

        y.left  = x;
        x.right = T2;

        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    /** Aplica la rotación necesaria para rebalancear el nodo. */
    private AVLNode rebalancear(AVLNode nodo, int key) {
        int fb = balance(nodo);

        // LL
        if (fb > 1 && key < nodo.left.key)
            return rotarDerecha(nodo);

        // RR
        if (fb < -1 && key > nodo.right.key)
            return rotarIzquierda(nodo);

        // LR
        if (fb > 1 && key > nodo.left.key) {
            nodo.left = rotarIzquierda(nodo.left);
            return rotarDerecha(nodo);
        }

        // RL
        if (fb < -1 && key < nodo.right.key) {
            nodo.right = rotarDerecha(nodo.right);
            return rotarIzquierda(nodo);
        }

        return nodo; // ya está balanceado
    }

    // =========================================================
    //  INSERCIÓN
    // =========================================================

    public void insertar(int key) {
        root = insertarRec(root, key);
    }

    private AVLNode insertarRec(AVLNode nodo, int key) {
        // Caso base: posición encontrada
        if (nodo == null) return new AVLNode(key);

        if (key < nodo.key)
            nodo.left  = insertarRec(nodo.left,  key);
        else if (key > nodo.key)
            nodo.right = insertarRec(nodo.right, key);
        else
            return nodo; // duplicado, no se inserta

        actualizarAltura(nodo);
        return rebalancear(nodo, key);
    }

    // =========================================================
    //  RECORRIDO POR AMPLITUD RECURSIVO  ← EJERCICIO 4
    // =========================================================

    /**
     * Recorrido por amplitud (BFS) implementado de forma RECURSIVA.
     * Utiliza la altura del árbol para iterar nivel a nivel,
     * invocando el método auxiliar imprimirNivel() en cada iteración.
     */
    public void recorridoAmplitud() {
        int h = altura(root);
        System.out.print("BFS recursivo: ");
        for (int nivel = 0; nivel < h; nivel++) {
            imprimirNivel(root, nivel);
        }
        System.out.println();
    }

    /**
     * Método auxiliar recursivo: imprime los nodos del árbol que
     * pertenecen exactamente al 'nivel' indicado (0 = raíz).
     *
     * @param nodo  Nodo actual de la recursión.
     * @param nivel Nivel restante por bajar (0 → imprimir este nodo).
     */
    private void imprimirNivel(AVLNode nodo, int nivel) {
        if (nodo == null) return;

        if (nivel == 0) {
            System.out.print(nodo.key + " ");
        } else {
            imprimirNivel(nodo.left,  nivel - 1);
            imprimirNivel(nodo.right, nivel - 1);
        }
    }

    // =========================================================
    //  RECORRIDOS CLÁSICOS  (comparación)
    // =========================================================

    /** Recorrido inorden: izquierda → raíz → derecha. */
    public void inorden() {
        System.out.print("Inorden:       ");
        inordenRec(root);
        System.out.println();
    }

    private void inordenRec(AVLNode nodo) {
        if (nodo == null) return;
        inordenRec(nodo.left);
        System.out.print(nodo.key + " ");
        inordenRec(nodo.right);
    }

    /** Recorrido preorden: raíz → izquierda → derecha. */
    public void preorden() {
        System.out.print("Preorden:      ");
        preordenRec(root);
        System.out.println();
    }

    private void preordenRec(AVLNode nodo) {
        if (nodo == null) return;
        System.out.print(nodo.key + " ");
        preordenRec(nodo.left);
        preordenRec(nodo.right);
    }

    // =========================================================
    //  UTILIDAD: altura pública
    // =========================================================

    public int getAltura() {
        return altura(root);
    }
}
