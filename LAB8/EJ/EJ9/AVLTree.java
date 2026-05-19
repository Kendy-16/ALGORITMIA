/**
 * Clase AVLTree - Árbol AVL para la cola de turnos de una clínica.
 *
 * Administra los turnos de atención de pacientes garantizando:
 *   → Inserción O(log n): registro rápido de nuevos turnos.
 *   → Búsqueda  O(log n): localización inmediata de un turno.
 *   → Eliminación O(log n): retiro del turno una vez atendido el paciente.
 *   → Árbol siempre balanceado gracias a las rotaciones AVL.
 *
 * Responde las preguntas adicionales:
 *   P1 → Expone cuál nodo queda como raíz después de insertar 30.
 *   P2 → Compara complejidad de búsqueda en lista vs AVL.
 *   P3 → Identifica el caso BST que ocurre al eliminar el turno 10.
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
        System.out.println("    [ROT-DER] Rotación derecha sobre turno " + y.turno);
        return x;
    }

    private AVLNode rotarIzquierda(AVLNode x) {
        AVLNode y  = x.right;
        AVLNode T2 = y.left;
        y.left  = x;
        x.right = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        System.out.println("    [ROT-IZQ] Rotación izquierda sobre turno " + x.turno);
        return y;
    }

    private AVLNode rebalancear(AVLNode n, int turno) {
        int fb = factorBalance(n);
        if (fb > 1 && turno < n.left.turno)  return rotarDerecha(n);
        if (fb < -1 && turno > n.right.turno) return rotarIzquierda(n);
        if (fb > 1 && turno > n.left.turno)  { n.left  = rotarIzquierda(n.left);  return rotarDerecha(n); }
        if (fb < -1 && turno < n.right.turno) { n.right = rotarDerecha(n.right);   return rotarIzquierda(n); }
        return n;
    }

    private AVLNode rebalancearEliminacion(AVLNode n) {
        int fb = factorBalance(n);
        if (fb > 1) {
            if (factorBalance(n.left) >= 0) return rotarDerecha(n);
            n.left = rotarIzquierda(n.left);
            return rotarDerecha(n);
        }
        if (fb < -1) {
            if (factorBalance(n.right) <= 0) return rotarIzquierda(n);
            n.right = rotarDerecha(n.right);
            return rotarIzquierda(n);
        }
        return n;
    }

    // =========================================================
    //  INSERTAR TURNO
    // =========================================================

    /**
     * Registra un nuevo turno de paciente en el sistema.
     * @param turno Número de turno a registrar.
     */
    public void insertarTurno(int turno) {
        root = insertarRec(root, turno);
        System.out.printf("  [+] Turno %-4d registrado. Raíz actual: %d%n",
                          turno, root.turno);
    }

    private AVLNode insertarRec(AVLNode n, int turno) {
        if (n == null) return new AVLNode(turno);
        if      (turno < n.turno) n.left  = insertarRec(n.left,  turno);
        else if (turno > n.turno) n.right = insertarRec(n.right, turno);
        else {
            System.out.printf("  [!] El turno %d ya está registrado.%n", turno);
            return n;
        }
        actualizarAltura(n);
        return rebalancear(n, turno);
    }

    // =========================================================
    //  BUSCAR TURNO
    // =========================================================

    /**
     * Busca un turno en el sistema.
     *
     * P2 — Diferencia entre buscar en lista vs AVL:
     *   → Lista enlazada/arreglo: O(n) en el peor caso.
     *     Se recorre elemento por elemento hasta encontrarlo.
     *   → Árbol AVL: O(log n) garantizado.
     *     Cada comparación descarta la mitad del árbol,
     *     como una búsqueda binaria pero en estructura dinámica.
     *   Ejemplo: 1.000 turnos → lista hasta 1000 pasos,
     *            AVL máximo ~10 pasos (log₂ 1000 ≈ 10).
     *
     * @param turno Número de turno a buscar.
     * @return true si el turno está registrado, false si no.
     */
    public boolean buscarTurno(int turno) {
        boolean encontrado = buscarRec(root, turno);
        if (encontrado)
            System.out.printf("  [✓] Turno %d ENCONTRADO - paciente en espera.%n", turno);
        else
            System.out.printf("  [✗] Turno %d NO está en el sistema.%n", turno);
        return encontrado;
    }

    private boolean buscarRec(AVLNode n, int turno) {
        if (n == null)         return false;
        if (turno == n.turno)  return true;
        if (turno < n.turno)   return buscarRec(n.left,  turno);
        return                        buscarRec(n.right, turno);
    }

    // =========================================================
    //  ELIMINAR TURNO (paciente atendido)
    // =========================================================

    /**
     * Elimina un turno una vez que el paciente fue atendido.
     *
     * P3 — Caso BST al eliminar turno 10:
     *   Depende de los hijos del nodo 10:
     *   • Si es HOJA         → Caso 0 hijos: se elimina directamente.
     *   • Si tiene UN hijo   → Caso 1 hijo:  se reemplaza por ese hijo.
     *   • Si tiene DOS hijos → Caso 2 hijos: se busca el sucesor inorden
     *     (menor valor del subárbol derecho) y reemplaza la clave 10.
     *   En todos los casos, el árbol se rebalancea con rotaciones AVL.
     *
     * @param turno Número de turno del paciente atendido.
     */
    public void eliminarTurno(int turno) {
        if (!buscarRec(root, turno)) {
            System.out.printf("  [!] Turno %d no encontrado en el sistema.%n", turno);
            return;
        }

        // Determinar el caso BST antes de eliminar
        String casoBST = determinarCasoBST(root, turno);
        root = eliminarRec(root, turno);
        System.out.printf("  [-] Turno %-4d eliminado. Caso BST: %s%n", turno, casoBST);
        if (root != null)
            System.out.printf("      Nueva raíz: %d%n", root.turno);
    }

    /** Identifica el caso de eliminación BST para el turno dado. */
    private String determinarCasoBST(AVLNode n, int turno) {
        if (n == null) return "Nodo no encontrado";
        if (turno < n.turno) return determinarCasoBST(n.left,  turno);
        if (turno > n.turno) return determinarCasoBST(n.right, turno);

        // Nodo encontrado
        if (n.left == null && n.right == null) return "CASO 0 HIJOS (hoja - eliminación directa)";
        if (n.left == null || n.right == null) return "CASO 1 HIJO  (reemplazar con el hijo existente)";
        return "CASO 2 HIJOS (reemplazar con sucesor inorden: "
               + minimoNodo(n.right).turno + ")";
    }

    private AVLNode eliminarRec(AVLNode n, int turno) {
        if (n == null) return null;

        if      (turno < n.turno) n.left  = eliminarRec(n.left,  turno);
        else if (turno > n.turno) n.right = eliminarRec(n.right, turno);
        else {
            if (n.left == null || n.right == null) {
                n = (n.left != null) ? n.left : n.right;
            } else {
                AVLNode sucesor = minimoNodo(n.right);
                n.turno  = sucesor.turno;
                n.right  = eliminarRec(n.right, sucesor.turno);
            }
        }

        if (n == null) return null;
        actualizarAltura(n);
        return rebalancearEliminacion(n);
    }

    private AVLNode minimoNodo(AVLNode n) {
        while (n.left != null) n = n.left;
        return n;
    }

    // =========================================================
    //  RAÍZ ACTUAL  (P1)
    // =========================================================

    /**
     * Devuelve el turno del nodo raíz actual.
     * Usado para responder P1: ¿Qué nodo quedó como raíz
     * después de insertar 30?
     */
    public int getRaiz() {
        return (root != null) ? root.turno : -1;
    }

    // =========================================================
    //  RECORRIDOS
    // =========================================================

    /** Inorden: muestra los turnos en orden ascendente. */
    public void inorden() {
        System.out.print("  Turnos en espera (orden): ");
        inordenRec(root);
        System.out.println();
    }

    private void inordenRec(AVLNode n) {
        if (n == null) return;
        inordenRec(n.left);
        System.out.print(n.turno + " ");
        inordenRec(n.right);
    }

    /** Imprime el estado del sistema de turnos. */
    public void mostrarEstado() {
        System.out.println("  ┌──────────────────────────────────────┐");
        System.out.println("  │     SISTEMA DE TURNOS - CLÍNICA      │");
        System.out.println("  ├──────────────────────────────────────┤");
        System.out.printf ("  │  Raíz actual:   %-21d│%n", getRaiz());
        System.out.printf ("  │  Altura AVL:    %-21d│%n", getAltura());
        System.out.print  ("  │  Cola de espera: ");
        inordenRec(root);
        System.out.println();
        System.out.println("  └──────────────────────────────────────┘");
    }

    // =========================================================
    //  CONSULTAS
    // =========================================================

    public int  getAltura() { return altura(root); }
    public boolean esVacio() { return root == null; }
}
