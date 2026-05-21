/**
 * Clase AVLTree - Ejercicio 3.
 *
 * Implementa el método de ELIMINACIÓN AVL redefinido con soporte completo:
 *
 *   Casos BST clásicos manejados:
 *     • Caso 0 hijos (nodo hoja)   → eliminación directa.
 *     • Caso 1 hijo                → reemplazar el nodo con su hijo.
 *     • Caso 2 hijos               → reemplazar con sucesor inorden
 *                                    (mínimo del subárbol derecho) O
 *                                    antecesor inorden (máximo del
 *                                    subárbol izquierdo).
 *
 *   Post-eliminación:
 *     • Actualización del factor de balance en cada nodo del camino.
 *     • Aplicación de rotaciones (LL, RR, LR, RL) si |FB| > 1.
 *     • Registro de cada operación en TablaPruebas.
 *
 *  */
public class AVLTree {

    // ─── Estado interno ────────────────────────────────────────────────────
    private AVLNode       root;
    private TablaPruebas  tabla;

    // Variables temporales: se resetean antes de cada eliminación
    private int    sucesorUsado;        // Sucesor/antecesor inorden (-1 si no aplica)
    private int    nodoDesbalanceado;   // Nodo donde se detectó desequilibrio (-1 = ninguno)
    private String rotacionAplicada;    // Tipo de rotación ejecutada
    private String casoBST;            // Descripción del caso BST

    // ─── Constructor ───────────────────────────────────────────────────────

    public AVLTree(TablaPruebas tabla) {
        this.root   = null;
        this.tabla  = tabla;
    }

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
    //  ROTACIONES  (con registro en tabla)
    // =========================================================

    /** Rotación simple a la derecha — caso LL. */
    private AVLNode rotarDerecha(AVLNode y) {
        System.out.printf("    [ROT-LL]  Rotación DERECHA sobre nodo %-4d (FB=%+d)%n",
                          y.key, factorBalance(y));
        AVLNode x  = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left  = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    /** Rotación simple a la izquierda — caso RR. */
    private AVLNode rotarIzquierda(AVLNode x) {
        System.out.printf("    [ROT-RR]  Rotación IZQUIERDA sobre nodo %-4d (FB=%+d)%n",
                          x.key, factorBalance(x));
        AVLNode y  = x.right;
        AVLNode T2 = y.left;
        y.left  = x;
        x.right = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    /**
     * Evalúa el factor de balance del nodo tras la eliminación y aplica
     * la rotación correspondiente. Registra el nodo desbalanceado y el
     * tipo de rotación para la tabla de pruebas.
     */
    private AVLNode rebalancearPostEliminacion(AVLNode n) {
        int fb = factorBalance(n);

        // ── LL: subárbol izquierdo demasiado alto
        if (fb > 1 && factorBalance(n.left) >= 0) {
            if (nodoDesbalanceado == -1) nodoDesbalanceado = n.key;
            rotacionAplicada = "LL (rotación derecha)";
            return rotarDerecha(n);
        }

        // ── LR: hijo izquierdo inclina hacia la derecha
        if (fb > 1 && factorBalance(n.left) < 0) {
            if (nodoDesbalanceado == -1) nodoDesbalanceado = n.key;
            rotacionAplicada = "LR (doble: izq + der)";
            System.out.printf("    [ROT-LR]  Rotación doble LR sobre nodo %d%n", n.key);
            n.left = rotarIzquierda(n.left);
            return rotarDerecha(n);
        }

        // ── RR: subárbol derecho demasiado alto
        if (fb < -1 && factorBalance(n.right) <= 0) {
            if (nodoDesbalanceado == -1) nodoDesbalanceado = n.key;
            rotacionAplicada = "RR (rotación izquierda)";
            return rotarIzquierda(n);
        }

        // ── RL: hijo derecho inclina hacia la izquierda
        if (fb < -1 && factorBalance(n.right) > 0) {
            if (nodoDesbalanceado == -1) nodoDesbalanceado = n.key;
            rotacionAplicada = "RL (doble: der + izq)";
            System.out.printf("    [ROT-RL]  Rotación doble RL sobre nodo %d%n", n.key);
            n.right = rotarDerecha(n.right);
            return rotarIzquierda(n);
        }

        return n; // ya balanceado
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
        return rebalancearInsercion(n, key);
    }

    private AVLNode rebalancearInsercion(AVLNode n, int key) {
        int fb = factorBalance(n);
        if (fb > 1  && key < n.left.key)  return rotarDerecha(n);
        if (fb < -1 && key > n.right.key) return rotarIzquierda(n);
        if (fb > 1  && key > n.left.key)  { n.left  = rotarIzquierda(n.left);  return rotarDerecha(n);  }
        if (fb < -1 && key < n.right.key) { n.right = rotarDerecha(n.right);   return rotarIzquierda(n); }
        return n;
    }

    // =========================================================
    //  ELIMINACIÓN AVL  ← EJERCICIO 3
    // =========================================================

    /**
     * Método público de eliminación AVL.
     *
     * Sigue los tres casos BST clásicos y luego rebalancea el camino
     * de retorno aplicando rotaciones LL, RR, LR o RL según corresponda.
     * Registra cada operación en la TablaPruebas.
     *
     * Clave del nodo a eliminar.
     */
    public void eliminar(int key) {

        // Verificar existencia antes de proceder
        if (!contiene(root, key)) {
            System.out.printf("  [!] Clave %d no encontrada en el árbol.%n", key);
            return;
        }

        // Reset de variables de auditoría
        sucesorUsado      = -1;
        nodoDesbalanceado = -1;
        rotacionAplicada  = "Ninguna";
        casoBST           = determinarCasoBST(root, key);

        System.out.printf("  [-] Eliminando clave: %-4d | %s%n", key, casoBST);

        root = eliminarRec(root, key);

        System.out.printf("      Sucesor usado: %-6s | Nodo desbalan.: %-7s | Rotación: %s%n",
                          (sucesorUsado == -1 ? "N/A" : sucesorUsado),
                          (nodoDesbalanceado == -1 ? "Ninguno" : nodoDesbalanceado),
                          rotacionAplicada);

        // Registrar en la tabla de documentación
        tabla.registrar(new ResultadoEliminacion(
                key, casoBST, sucesorUsado, nodoDesbalanceado, rotacionAplicada));
    }

    /**
     * Núcleo recursivo de la eliminación AVL.
     *
     * 1. Búsqueda del nodo (igual que BST).
     * 2. Aplicación del caso correspondiente (0, 1 o 2 hijos).
     * 3. Actualización de altura y rebalanceo en el camino de vuelta.
     */
    private AVLNode eliminarRec(AVLNode n, int key) {
        if (n == null) return null;

        // ── Paso 1: Búsqueda del nodo ──────────────────────────────────────
        if (key < n.key) {
            n.left  = eliminarRec(n.left,  key);

        } else if (key > n.key) {
            n.right = eliminarRec(n.right, key);

        } else {
            // ── Paso 2: Nodo encontrado → aplicar caso BST ─────────────────

            if (n.left == null && n.right == null) {
                // CASO 0 HIJOS: nodo hoja → retornar null
                return null;

            } else if (n.left == null || n.right == null) {
                // CASO 1 HIJO: reemplazar con el hijo existente
                n = (n.left != null) ? n.left : n.right;

            } else {
                // CASO 2 HIJOS: reemplazar con sucesor inorden
                // (mínimo del subárbol derecho)
                AVLNode sucesor = minimoNodo(n.right);
                sucesorUsado = sucesor.key;
                System.out.printf("    [SUC] Sucesor inorden de %d → %d%n", key, sucesor.key);
                n.key   = sucesor.key;
                n.right = eliminarRec(n.right, sucesor.key);
            }
        }

        // Nodo hoja eliminado (caso 0 hijos ya retornó null)
        if (n == null) return null;

        // ── Paso 3: Actualizar altura y rebalancear ────────────────────────
        actualizarAltura(n);
        return rebalancearPostEliminacion(n);
    }

    // =========================================================
    //  AUXILIARES DE ELIMINACIÓN
    // =========================================================

    /** Retorna el nodo con la clave más pequeña del subárbol. */
    private AVLNode minimoNodo(AVLNode n) {
        while (n.left != null) n = n.left;
        return n;
    }

    /** Retorna el nodo con la clave más grande del subárbol. */
    @SuppressWarnings("unused")
    private AVLNode maximoNodo(AVLNode n) {
        while (n.right != null) n = n.right;
        return n;
    }

    /** Verifica si una clave existe en el árbol. */
    private boolean contiene(AVLNode n, int key) {
        if (n == null)       return false;
        if (key == n.key)    return true;
        if (key < n.key)     return contiene(n.left,  key);
        return                      contiene(n.right, key);
    }

    /**
     * Inspecciona el nodo antes de eliminarlo y retorna el caso BST
     * como cadena descriptiva para la tabla de documentación.
     */
    private String determinarCasoBST(AVLNode n, int key) {
        if (n == null) return "No encontrado";
        if (key < n.key) return determinarCasoBST(n.left,  key);
        if (key > n.key) return determinarCasoBST(n.right, key);

        // Nodo localizado
        boolean tieneIzq = (n.left  != null);
        boolean tieneDer = (n.right != null);

        if (!tieneIzq && !tieneDer) return "Caso 0 hijos (hoja)";
        if (!tieneIzq || !tieneDer) return "Caso 1 hijo";
        return "Caso 2 hijos (sucesor=" + minimoNodo(n.right).key + ")";
    }

    // =========================================================
    //  RECORRIDOS Y VISUALIZACIÓN
    // =========================================================

    /** Recorrido inorden: muestra claves en orden ascendente. */
    public void inorden() {
        System.out.print("  Inorden:  ");
        inordenRec(root);
        System.out.println();
    }

    private void inordenRec(AVLNode n) {
        if (n == null) return;
        inordenRec(n.left);
        System.out.print(n.key + " ");
        inordenRec(n.right);
    }

    /** Recorrido preorden: raíz → izquierda → derecha. */
    public void preorden() {
        System.out.print("  Preorden: ");
        preordenRec(root);
        System.out.println();
    }

    private void preordenRec(AVLNode n) {
        if (n == null) return;
        System.out.print(n.key + " ");
        preordenRec(n.left);
        preordenRec(n.right);
    }

    /** BFS recursivo por niveles. */
    public void bfs() {
        int h = altura(root);
        System.out.print("  BFS:      ");
        for (int lvl = 0; lvl < h; lvl++) visitarNivel(root, lvl);
        System.out.println();
    }

    private void visitarNivel(AVLNode n, int nivel) {
        if (n == null) return;
        if (nivel == 0) System.out.print(n.key + " ");
        else { visitarNivel(n.left, nivel - 1); visitarNivel(n.right, nivel - 1); }
    }

    /** Imprime el estado completo del árbol con sus factores de balance. */
    public void imprimirEstado(String etapa) {
        System.out.printf("  ── Estado tras %-30s ──%n", etapa);
        System.out.printf("  Altura: %d | Raíz: %s%n",
                          altura(root), (root == null ? "vacío" : root.key));
        inorden();
        preorden();
        bfs();
        imprimirFactoresBalance(root, "  FB: ");
        System.out.println();
    }

    /** Muestra el factor de balance de cada nodo (inorden). */
    private void imprimirFactoresBalance(AVLNode n, String prefix) {
        if (n == null) return;
        // Recorrer inorden para mostrar FB en orden de clave
        imprimirFactoresBalance(n.left, prefix);
        System.out.printf("  Nodo %-4d → FB = %+d%n", n.key, factorBalance(n));
        imprimirFactoresBalance(n.right, prefix);
    }

    // ─── Consultas ─────────────────────────────────────────────────────────
    public int     getAltura() { return altura(root); }
    public boolean esVacio()   { return root == null; }
}
