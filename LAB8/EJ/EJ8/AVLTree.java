/**
 * Clase AVLTree - Árbol AVL para administrar códigos de productos.
 *
 * Funcionalidades:
 *   → insertar(codigo)  : Agrega un producto y reporta la rotación aplicada.
 *   → buscar(codigo)    : Verifica si un producto existe en el sistema.
 *   → eliminar(codigo)  : Elimina un producto que ya no está disponible.
 *   → inorden()         : Imprime los códigos en orden ascendente.
 *   → listarProductos() : Vista amigable del inventario ordenado.
 *
 * Responde las preguntas adicionales del enunciado:
 *   P1 → Cada inserción reporta qué rotación se aplicó.
 *   P2 → El método inorden() demuestra el orden ascendente (BST invariante).
 *   P3 → buscar() devuelve un mensaje claro cuando el código no existe.
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLTree {

    private AVLNode      root;
    private TipoRotacion ultimaRotacion; // Registra la última rotación aplicada

    public AVLTree() {
        this.root          = null;
        this.ultimaRotacion = TipoRotacion.NINGUNA;
    }

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

    /** Rotación simple a la derecha — caso LL. */
    private AVLNode rotarDerecha(AVLNode y) {
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
        AVLNode y  = x.right;
        AVLNode T2 = y.left;
        y.left  = x;
        x.right = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    /** Evalúa el desbalance y aplica la rotación correspondiente. */
    private AVLNode rebalancear(AVLNode n, int codigo) {
        int fb = factorBalance(n);

        // ── LL: subárbol izquierdo pesado, insertado a la izquierda
        if (fb > 1 && codigo < n.left.codigo) {
            ultimaRotacion = TipoRotacion.LL;
            return rotarDerecha(n);
        }
        // ── RR: subárbol derecho pesado, insertado a la derecha
        if (fb < -1 && codigo > n.right.codigo) {
            ultimaRotacion = TipoRotacion.RR;
            return rotarIzquierda(n);
        }
        // ── LR: subárbol izquierdo pesado, insertado a la derecha del hijo
        if (fb > 1 && codigo > n.left.codigo) {
            ultimaRotacion = TipoRotacion.LR;
            n.left = rotarIzquierda(n.left);
            return rotarDerecha(n);
        }
        // ── RL: subárbol derecho pesado, insertado a la izquierda del hijo
        if (fb < -1 && codigo < n.right.codigo) {
            ultimaRotacion = TipoRotacion.RL;
            n.right = rotarDerecha(n.right);
            return rotarIzquierda(n);
        }

        return n; // sin rotación
    }

    // =========================================================
    //  INSERTAR PRODUCTO
    // =========================================================

    /**
     * Inserta un nuevo código de producto en el árbol AVL.
     * Reporta automáticamente qué rotación se aplicó (P1).
     *
     * @param codigo Código numérico único del producto.
     */
    public void insertar(int codigo) {
        ultimaRotacion = TipoRotacion.NINGUNA; // reset antes de insertar
        root = insertarRec(root, codigo);

        System.out.printf("  [+] Código %-6d → %s%n",
                          codigo, ultimaRotacion.getDescripcion());
    }

    private AVLNode insertarRec(AVLNode n, int codigo) {
        if (n == null) return new AVLNode(codigo);

        if      (codigo < n.codigo) n.left  = insertarRec(n.left,  codigo);
        else if (codigo > n.codigo) n.right = insertarRec(n.right, codigo);
        else {
            System.out.printf("  [!] Código %d ya existe en el sistema.%n", codigo);
            return n;
        }

        actualizarAltura(n);
        return rebalancear(n, codigo);
    }

    // =========================================================
    //  BUSCAR PRODUCTO  (Pregunta adicional 3)
    // =========================================================

    /**
     * Busca un código de producto en el árbol AVL.
     *
     * P3 — ¿Qué sucede al buscar un producto que no existe?
     *   → El algoritmo recorre el árbol comparando claves hasta
     *     llegar a un nodo null, e informa que el producto
     *     no está registrado en el sistema. Nunca lanza excepción.
     *
     * @param codigo Código a buscar.
     * @return true si existe, false si no.
     */
    public boolean buscar(int codigo) {
        boolean encontrado = buscarRec(root, codigo);
        if (encontrado) {
            System.out.printf("  [✓] Producto %d ENCONTRADO en el inventario.%n", codigo);
        } else {
            System.out.printf("  [✗] Producto %d NO EXISTE en el sistema.%n", codigo);
            System.out.println("      → La búsqueda llegó a un nodo null sin encontrar el código.");
        }
        return encontrado;
    }

    private boolean buscarRec(AVLNode n, int codigo) {
        if (n == null)          return false;
        if (codigo == n.codigo) return true;
        if (codigo < n.codigo)  return buscarRec(n.left,  codigo);
        return                         buscarRec(n.right, codigo);
    }

    // =========================================================
    //  ELIMINAR PRODUCTO
    // =========================================================

    /**
     * Elimina un código de producto del árbol AVL.
     * Aplica rebalanceo post-eliminación si es necesario.
     *
     * @param codigo Código del producto a eliminar.
     */
    public void eliminar(int codigo) {
        if (!buscarRec(root, codigo)) {
            System.out.printf("  [!] Código %d no encontrado. Nada que eliminar.%n", codigo);
            return;
        }
        root = eliminarRec(root, codigo);
        System.out.printf("  [-] Código %-6d eliminado correctamente.%n", codigo);
    }

    private AVLNode eliminarRec(AVLNode n, int codigo) {
        if (n == null) return null;

        if      (codigo < n.codigo) n.left  = eliminarRec(n.left,  codigo);
        else if (codigo > n.codigo) n.right = eliminarRec(n.right, codigo);
        else {
            // Nodo encontrado
            if (n.left == null || n.right == null) {
                n = (n.left != null) ? n.left : n.right;
            } else {
                // Sucesor inorden (mínimo del subárbol derecho)
                AVLNode sucesor = minimoNodo(n.right);
                n.codigo = sucesor.codigo;
                n.right  = eliminarRec(n.right, sucesor.codigo);
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
    //  RECORRIDOS Y VISUALIZACIÓN
    // =========================================================

    /**
     * Imprime los códigos en orden ASCENDENTE usando recorrido inorden.
     *
     * P2 — ¿Por qué el recorrido inorden muestra los productos ordenados?
     *   → En un BST/AVL, para cualquier nodo N:
     *       - todos los nodos del subárbol IZQUIERDO tienen clave < N.
     *       - todos los nodos del subárbol DERECHO tienen clave > N.
     *   → El recorrido izquierda→raíz→derecha visita los nodos
     *     respetando esa invariante, produciendo siempre orden ascendente.
     */
    public void inorden() {
        System.out.print("  Inventario ordenado (inorden): ");
        inordenRec(root);
        System.out.println();
    }

    private void inordenRec(AVLNode n) {
        if (n == null) return;
        inordenRec(n.left);
        System.out.print(n.codigo + " ");
        inordenRec(n.right);
    }

    /** Vista de inventario con formato de tabla. */
    public void listarProductos() {
        System.out.println("  ┌────────────────────────────────┐");
        System.out.println("  │     INVENTARIO DE PRODUCTOS    │");
        System.out.println("  │     (ordenado por código)      │");
        System.out.println("  ├────────────────────────────────┤");
        System.out.print("  │  Códigos: ");
        inordenRec(root);
        System.out.println();
        System.out.printf("  │  Altura del árbol AVL: %-7d │%n", getAltura());
        System.out.println("  └────────────────────────────────┘");
    }

    // =========================================================
    //  CONSULTAS
    // =========================================================

    public int getAltura() { return altura(root); }
    public boolean esVacio() { return root == null; }
}
