import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// ═══════════════════════════════════════════════════════════════
//  EJERCICIO 01 – destroyNodes · countAllNodes · countNodes
//                height(x)    · amplitude(nivel)
// ═══════════════════════════════════════════════════════════════
public class Ejercicio01 {

    // ── Excepciones ─────────────────────────────────────────────
    static class ExceptionIsEmpty extends Exception {
        public ExceptionIsEmpty(String msg) { super(msg); }
    }
    static class ItemDuplicated extends Exception {
        public ItemDuplicated(String msg) { super(msg); }
    }
    static class ItemNotFound extends Exception {
        public ItemNotFound(String msg) { super(msg); }
    }

    // ── LinkedBST ───────────────────────────────────────────────
    static class LinkedBST<E extends Comparable<E>> {

        class Node {
            E data; Node left, right;
            Node(E d) { this(d, null, null); }
            Node(E d, Node l, Node r) { data = d; left = l; right = r; }
        }

        private Node root;
        public LinkedBST() { root = null; }

        // ── insert (base) ────────────────────────────────────────
        public void insert(E data) throws ItemDuplicated {
            if (root == null) { root = new Node(data); return; }
            Node cur = root;
            while (true) {
                int cmp = data.compareTo(cur.data);
                if (cmp == 0) throw new ItemDuplicated("Duplicado: " + data);
                if (cmp < 0) {
                    if (cur.left  == null) { cur.left  = new Node(data); return; }
                    cur = cur.left;
                } else {
                    if (cur.right == null) { cur.right = new Node(data); return; }
                    cur = cur.right;
                }
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Stack<Node> st = new Stack<>(); Node cur = root;
            while (cur != null || !st.isEmpty()) {
                while (cur != null) { st.push(cur); cur = cur.left; }
                cur = st.pop();
                if (sb.length() > 1) sb.append(", ");
                sb.append(cur.data);
                cur = cur.right;
            }
            return sb.append("]").toString();
        }

        // ════════════════════════════════════════════════════════
        //  a) destroyNodes()
        //     Elimina todos los nodos. Lanza ExceptionIsEmpty si
        //     el árbol ya está vacío.
        // ════════════════════════════════════════════════════════
        public void destroyNodes() throws ExceptionIsEmpty {
            if (root == null)
                throw new ExceptionIsEmpty("El árbol ya estaba vacío");
            root = null;   // el GC libera todos los nodos
        }

        // ════════════════════════════════════════════════════════
        //  b) countAllNodes()
        //     Retorna el número TOTAL de nodos (iterativo con pila).
        // ════════════════════════════════════════════════════════
        public int countAllNodes() {
            if (root == null) return 0;
            int count = 0;
            Stack<Node> st = new Stack<>();
            st.push(root);
            while (!st.isEmpty()) {
                Node n = st.pop();
                count++;
                if (n.right != null) st.push(n.right);
                if (n.left  != null) st.push(n.left);
            }
            return count;
        }

        // ════════════════════════════════════════════════════════
        //  c) countNodes()
        //     Retorna el número de nodos NO-HOJA (tienen al menos
        //     un hijo). Implementación iterativa con pila.
        // ════════════════════════════════════════════════════════
        public int countNodes() {
            if (root == null) return 0;
            int count = 0;
            Stack<Node> st = new Stack<>();
            st.push(root);
            while (!st.isEmpty()) {
                Node n = st.pop();
                if (n.left != null || n.right != null) count++;  // no es hoja
                if (n.right != null) st.push(n.right);
                if (n.left  != null) st.push(n.left);
            }
            return count;
        }

        // ════════════════════════════════════════════════════════
        //  d) height(x)
        //     Retorna la altura del subárbol cuya raíz tiene dato x.
        //     Retorna -1 si no existe. COMPLETAMENTE ITERATIVO.
        //
        //     Paso 1 – Busca el nodo con BST search (O log n).
        //     Paso 2 – Calcula la altura con BFS por niveles.
        // ════════════════════════════════════════════════════════
        public int height(E x) {
            // Paso 1: localizar el nodo
            Node target = root;
            while (target != null) {
                int cmp = x.compareTo(target.data);
                if      (cmp == 0) break;
                else if (cmp <  0) target = target.left;
                else               target = target.right;
            }
            if (target == null) return -1;   // no existe

            // Paso 2: altura con BFS (nivel por nivel)
            Queue<Node> q = new LinkedList<>();
            q.offer(target);
            int h = -1;
            while (!q.isEmpty()) {
                h++;
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    Node n = q.poll();
                    if (n.left  != null) q.offer(n.left);
                    if (n.right != null) q.offer(n.right);
                }
            }
            return h;
        }

        // ════════════════════════════════════════════════════════
        //  e) amplitude(nivel)
        //     Retorna cuántos nodos existen en ese nivel del árbol.
        //     Usa BFS por niveles (mismo concepto que height).
        //     Retorna 0 si el nivel no existe.
        // ════════════════════════════════════════════════════════
        public int amplitude(int nivel) {
            if (root == null || nivel < 0) return 0;
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            int current = 0;
            while (!q.isEmpty()) {
                int sz = q.size();
                if (current == nivel) return sz;   // encontramos el nivel
                for (int i = 0; i < sz; i++) {
                    Node n = q.poll();
                    if (n.left  != null) q.offer(n.left);
                    if (n.right != null) q.offer(n.right);
                }
                current++;
            }
            return 0;
        }
    }

    // ── MAIN – pruebas ──────────────────────────────────────────
    public static void main(String[] args) throws Exception {

        LinkedBST<Integer> bst = new LinkedBST<>();
        //         50
        //       /    \
        //      30     70
        //     /  \   /  \
        //    20  40 60   80
        //   /  \   \
        //  10  25   45
        int[] vals = {50, 30, 70, 20, 40, 60, 80, 10, 25, 45};
        for (int v : vals) bst.insert(v);

        System.out.println("Árbol (in-order): " + bst);
        System.out.println();

        // b) countAllNodes
        System.out.println("b) countAllNodes() = " + bst.countAllNodes());  // 10

        // c) countNodes (no-hoja)
        System.out.println("c) countNodes()    = " + bst.countNodes());     // 6

        // d) height
        System.out.println("d) height(50)      = " + bst.height(50));       // 3
        System.out.println("   height(30)      = " + bst.height(30));       // 2
        System.out.println("   height(20)      = " + bst.height(20));       // 1
        System.out.println("   height(10)      = " + bst.height(10));       // 0  (hoja)
        System.out.println("   height(99)      = " + bst.height(99));       // -1 (no existe)

        // e) amplitude
        System.out.println("e) amplitude(0)    = " + bst.amplitude(0));     // 1
        System.out.println("   amplitude(1)    = " + bst.amplitude(1));     // 2
        System.out.println("   amplitude(2)    = " + bst.amplitude(2));     // 4
        System.out.println("   amplitude(3)    = " + bst.amplitude(3));     // 3
        System.out.println("   amplitude(9)    = " + bst.amplitude(9));     // 0 (no existe)

        // a) destroyNodes
        System.out.println("\na) destroyNodes:");
        System.out.println("   Antes : " + bst);
        bst.destroyNodes();
        System.out.println("   Después: " + bst);ZS
        // Intentar de nuevo → lanza ExceptionIsEmpty
        try { bst.destroyNodes(); }
        catch (ExceptionIsEmpty e) { System.out.println("   Excepción OK → " + e.getMessage()); }
    }
}