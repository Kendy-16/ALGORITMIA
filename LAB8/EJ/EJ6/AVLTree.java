/**
 * Clase AVLTree - Ejercicio 6.
 *
 * Incluye el método preOrder() (recorrido en preorden) además de
 * los recorridos inorden y por amplitud, con el fin de comparar
 * resultados sobre múltiples árboles AVL.
 *
 * Preorden: raíz → subárbol izquierdo → subárbol derecho.
 * Propiedad: el primer elemento es siempre la raíz del árbol
 * (o subárbol), útil para reconstruir / clonar la estructura.
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLTree {

    private AVLNode root;

    // =========================================================
    //  UTILIDADES
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
        if (fb > 1  && key < n.left.key)  return rotarDerecha(n);
        if (fb < -1 && key > n.right.key) return rotarIzquierda(n);
        if (fb > 1  && key > n.left.key)  { n.left  = rotarIzquierda(n.left);  return rotarDerecha(n);  }
        if (fb < -1 && key < n.right.key) { n.right = rotarDerecha(n.right);   return rotarIzquierda(n); }
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
        else                  return n;
        actualizarAltura(n);
        return balancear(n, key);
    }

    // =========================================================
    //  RECORRIDO PREORDEN  ← EJERCICIO 6
    // =========================================================

    /**
     * Recorrido en PREORDEN: raíz → izquierda → derecha.
     * Implementación recursiva.
     *
     * Característica clave: el primer valor impreso es siempre
     * la raíz del árbol (o del subárbol actual).
     */
    public void preOrder() {
        System.out.print("PreOrder:  ");
        preOrderRec(root);
        System.out.println();
    }

    /** Versión con etiqueta personalizada para mayor claridad en pruebas. */
    public void preOrder(String etiqueta) {
        System.out.print(etiqueta + ": ");
        preOrderRec(root);
        System.out.println();
    }

    /**
     * Método auxiliar recursivo del preorden.
     * 1. Visita el nodo actual.
     * 2. Recorre el subárbol izquierdo.
     * 3. Recorre el subárbol derecho.
     */
    private void preOrderRec(AVLNode n) {
        if (n == null) return;
        System.out.print(n.key + " ");   // Visitar raíz
        preOrderRec(n.left);             // Subárbol izquierdo
        preOrderRec(n.right);            // Subárbol derecho
    }

    // =========================================================
    //  OTROS RECORRIDOS (comparación)
    // =========================================================

    public void inOrder() {
        System.out.print("InOrder:   ");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(AVLNode n) {
        if (n == null) return;
        inOrderRec(n.left);
        System.out.print(n.key + " ");
        inOrderRec(n.right);
    }

    public void bfsRecursivo() {
        int h = altura(root);
        System.out.print("BFS:       ");
        for (int lvl = 0; lvl < h; lvl++) visitarNivel(root, lvl);
        System.out.println();
    }

    private void visitarNivel(AVLNode n, int nivel) {
        if (n == null) return;
        if (nivel == 0) System.out.print(n.key + " ");
        else {
            visitarNivel(n.left,  nivel - 1);
            visitarNivel(n.right, nivel - 1);
        }
    }

    // =========================================================
    //  CONSULTAS
    // =========================================================

    public int getAltura() { return altura(root); }
    public boolean esVacio() { return root == null; }
}
