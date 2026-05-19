/**
 * Clase AVLTree - Ejercicio 7.
 *
 * Implementación COMPLETA del árbol AVL con:
 *   → Inserción con las 4 rotaciones (LL, RR, LR, RL).
 *   → Eliminación con las 4 rotaciones cuando sea necesario.
 *   → Registro de rotaciones aplicadas en cada operación.
 *   → Todos los recorridos para verificación.
 *
 * La eliminación AVL:
 *   1. Busca el nodo como en un BST normal.
 *   2. Caso 0/1 hijo: sustitución directa.
 *   3. Caso 2 hijos: reemplaza con el sucesor inorden (mínimo del subárbol derecho).
 *   4. Rebalanceo en el camino de regreso a la raíz.
 *
 * @author Ingeniería de Sistemas
 * @version 1.0
 */
public class AVLTree {

    private AVLNode root;
    private int contadorRotaciones = 0;

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
    //  ROTACIONES CON LOG
    // =========================================================

    private AVLNode rotarDerecha(AVLNode y) {
        contadorRotaciones++;
        System.out.println("    [ROT] Rotación DERECHA sobre nodo " + y.key);
        AVLNode x  = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left  = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private AVLNode rotarIzquierda(AVLNode x) {
        contadorRotaciones++;
        System.out.println("    [ROT] Rotación IZQUIERDA sobre nodo " + x.key);
        AVLNode y  = x.right;
        AVLNode T2 = y.left;
        y.left  = x;
        x.right = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    // =========================================================
    //  REBALANCEO GENÉRICO (inserción y eliminación)
    // =========================================================

    /**
     * Rebalanceo para INSERCIÓN (usa la clave insertada como guía).
     */
    private AVLNode rebalancearInsercion(AVLNode n, int key) {
        int fb = factorBalance(n);

        if (fb > 1 && key < n.left.key) {
            System.out.println("    [DES] Desbalance LL en nodo " + n.key);
            return rotarDerecha(n);
        }
        if (fb < -1 && key > n.right.key) {
            System.out.println("    [DES] Desbalance RR en nodo " + n.key);
            return rotarIzquierda(n);
        }
        if (fb > 1 && key > n.left.key) {
            System.out.println("    [DES] Desbalance LR en nodo " + n.key);
            n.left = rotarIzquierda(n.left);
            return rotarDerecha(n);
        }
        if (fb < -1 && key < n.right.key) {
            System.out.println("    [DES] Desbalance RL en nodo " + n.key);
            n.right = rotarDerecha(n.right);
            return rotarIzquierda(n);
        }
        return n;
    }

    /**
     * Rebalanceo para ELIMINACIÓN (evalúa solo el factor de balance,
     * ya que no hay una clave "guía" confiable).
     */
    private AVLNode rebalancearEliminacion(AVLNode n) {
        int fb = factorBalance(n);

        // Desbalance izquierdo
        if (fb > 1) {
            if (factorBalance(n.left) >= 0) {
                System.out.println("    [DES-EL] LL en nodo " + n.key);
                return rotarDerecha(n);
            } else {
                System.out.println("    [DES-EL] LR en nodo " + n.key);
                n.left = rotarIzquierda(n.left);
                return rotarDerecha(n);
            }
        }

        // Desbalance derecho
        if (fb < -1) {
            if (factorBalance(n.right) <= 0) {
                System.out.println("    [DES-EL] RR en nodo " + n.key);
                return rotarIzquierda(n);
            } else {
                System.out.println("    [DES-EL] RL en nodo " + n.key);
                n.right = rotarDerecha(n.right);
                return rotarIzquierda(n);
            }
        }

        return n;
    }

    // =========================================================
    //  INSERCIÓN
    // =========================================================

    public void insertar(int key) {
        System.out.println("  Insertando: " + key);
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

    // =========================================================
    //  ELIMINACIÓN  ← EJERCICIO 7
    // =========================================================

    public void eliminar(int key) {
        if (!contiene(root, key)) {
            System.out.println("  Eliminar " + key + ": clave NO encontrada.");
            return;
        }
        System.out.println("  Eliminando: " + key);
        root = eliminarRec(root, key);
    }

    private AVLNode eliminarRec(AVLNode n, int key) {
        if (n == null) return null;

        // 1. Búsqueda del nodo a eliminar
        if (key < n.key) {
            n.left  = eliminarRec(n.left,  key);
        } else if (key > n.key) {
            n.right = eliminarRec(n.right, key);
        } else {
            // Nodo encontrado
            if (n.left == null || n.right == null) {
                // Caso 0 o 1 hijo
                AVLNode hijo = (n.left != null) ? n.left : n.right;
                if (hijo == null) {
                    // Sin hijos (hoja)
                    return null;
                } else {
                    // Un hijo: sustituir
                    n = hijo;
                }
            } else {
                // Caso 2 hijos: sucesor inorden (mínimo del subárbol derecho)
                AVLNode sucesor = minimoNodo(n.right);
                System.out.println("    [SUCESOR] Reemplazando " + key + " con " + sucesor.key);
                n.key   = sucesor.key;
                n.right = eliminarRec(n.right, sucesor.key);
            }
        }

        // 2. Actualizar altura y rebalancear
        actualizarAltura(n);
        return rebalancearEliminacion(n);
    }

    /** Devuelve el nodo con la menor clave en el subárbol dado. */
    private AVLNode minimoNodo(AVLNode n) {
        AVLNode actual = n;
        while (actual.left != null) actual = actual.left;
        return actual;
    }

    /** Verifica si una clave existe en el árbol. */
    private boolean contiene(AVLNode n, int key) {
        if (n == null)      return false;
        if (key == n.key)   return true;
        if (key < n.key)    return contiene(n.left,  key);
        return                     contiene(n.right, key);
    }

    // =========================================================
    //  RECORRIDOS
    // =========================================================

    public void preOrder() {
        System.out.print("  PreOrder: ");
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(AVLNode n) {
        if (n == null) return;
        System.out.print(n.key + " ");
        preOrderRec(n.left);
        preOrderRec(n.right);
    }

    public void inOrder() {
        System.out.print("  InOrder:  ");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(AVLNode n) {
        if (n == null) return;
        inOrderRec(n.left);
        System.out.print(n.key + " ");
        inOrderRec(n.right);
    }

    public void bfs() {
        int h = altura(root);
        System.out.print("  BFS:      ");
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
    //  ESTADO
    // =========================================================

    public int getAltura() { return altura(root); }
    public int getContadorRotaciones() { return contadorRotaciones; }
    public void resetContadorRotaciones() { contadorRotaciones = 0; }

    public void imprimirEstado(String etapa) {
        System.out.println("  ── Estado tras " + etapa + " ──");
        System.out.println("  Altura: " + getAltura());
        inOrder();
        preOrder();
        bfs();
        System.out.println("  Rotaciones totales hasta ahora: " + contadorRotaciones);
    }
}
