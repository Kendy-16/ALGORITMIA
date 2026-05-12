import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// ═══════════════════════════════════════════════════════════════
//  EJERCICIO 02 – areaBST() · drawBST() · sameArea()
// ═══════════════════════════════════════════════════════════════
public class Ejercicio02 {

    static class ItemDuplicated extends Exception {
        public ItemDuplicated(String msg) { super(msg); }
    }

    static class LinkedBST<E extends Comparable<E>> {

        class Node {
            E data; Node left, right;
            Node(E d) { this(d, null, null); }
            Node(E d, Node l, Node r) { data = d; left = l; right = r; }
        }

        private Node root;
        public LinkedBST() { root = null; }

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
        //  a) areaBST()
        //     Área = (nodos hoja) × (altura del árbol).
        //     Un solo recorrido BFS calcula ambos valores.
        //     IMPLEMENTACIÓN ITERATIVA.
        // ════════════════════════════════════════════════════════
        public int areaBST() {
            if (root == null) return 0;

            int leaves = 0;
            int treeHeight = -1;

            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                treeHeight++;               // subimos un nivel en cada iteración
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    Node n = q.poll();
                    if (n.left == null && n.right == null) leaves++;
                    if (n.left  != null) q.offer(n.left);
                    if (n.right != null) q.offer(n.right);
                }
            }
            return leaves * treeHeight;
        }

        // ════════════════════════════════════════════════════════
        //  b) drawBST()
        //     Muestra el árbol nivel por nivel indicando las aristas
        //     (L = hijo izquierdo, R = hijo derecho) y además
        //     imprime el toString() in-order.
        // ════════════════════════════════════════════════════════
        public void drawBST() {
            System.out.println("┌─────────────────────────────────────┐");
            System.out.println("│           DIBUJO DEL BST            │");
            System.out.println("└─────────────────────────────────────┘");

            if (root == null) { System.out.println("  (árbol vacío)"); return; }

            System.out.println("In-order : " + toString());
            System.out.println("─────────────────────────────────────");

            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            int level = 0;

            while (!q.isEmpty()) {
                int sz = q.size();
                System.out.printf("Nivel %d: ", level);
                StringBuilder edges = new StringBuilder("         ");

                for (int i = 0; i < sz; i++) {
                    Node n = q.poll();
                    System.out.printf("[%s]", n.data);

                    if (n.left != null) {
                        edges.append(String.format("(%s)→L(%s)  ", n.data, n.left.data));
                        q.offer(n.left);
                    }
                    if (n.right != null) {
                        edges.append(String.format("(%s)→R(%s)  ", n.data, n.right.data));
                        q.offer(n.right);
                    }
                    if (i < sz - 1) System.out.print(" - ");
                }
                System.out.println();
                if (edges.toString().trim().length() > 0)
                    System.out.println(edges);
                level++;
            }
            System.out.println("─────────────────────────────────────");
            System.out.printf("Hojas: %d  |  Altura: %d  |  Área: %d%n",
                    countLeaves(), treeHeight(), areaBST());
        }

        // auxiliares privados usados en drawBST
        private int countLeaves() {
            int c = 0; Stack<Node> st = new Stack<>();
            if (root != null) st.push(root);
            while (!st.isEmpty()) {
                Node n = st.pop();
                if (n.left == null && n.right == null) c++;
                if (n.right != null) st.push(n.right);
                if (n.left  != null) st.push(n.left);
            }
            return c;
        }
        private int treeHeight() {
            if (root == null) return -1;
            Queue<Node> q = new LinkedList<>(); q.offer(root); int h = -1;
            while (!q.isEmpty()) { h++; int sz=q.size();
                for (int i=0;i<sz;i++){Node n=q.poll();if(n.left!=null)q.offer(n.left);if(n.right!=null)q.offer(n.right);}}
            return h;
        }
    }

    // ════════════════════════════════════════════════════════════
    //  c) sameArea()  – método ESTÁTICO en clase Prueba/Ejercicio02
    //     Retorna true si dos árboles BST distintos tienen la
    //     misma área  (hojas × altura).
    // ════════════════════════════════════════════════════════════
    public static <E extends Comparable<E>> boolean sameArea(
            LinkedBST<E> t1, LinkedBST<E> t2) {
        return t1.areaBST() == t2.areaBST();
    }

    // ── MAIN – pruebas ──────────────────────────────────────────
    public static void main(String[] args) throws Exception {

        // ── Árbol 1 ─────────────────────────────────────────────
        //         50
        //       /    \
        //      30     70
        //     /  \   /  \
        //    20  40 60   80
        LinkedBST<Integer> bst1 = new LinkedBST<>();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) bst1.insert(v);

        // b) drawBST
        bst1.drawBST();
        System.out.println();

        // a) areaBST
        System.out.println("a) areaBST(bst1) = " + bst1.areaBST());
        // Hojas: 20, 40, 60, 80  → 4 hojas × altura 2 = 8

        // ── Árbol 2 ─────────────────────────────────────────────
        //      10
        //     /  \
        //    5    15
        //   / \
        //  3   7
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        for (int v : new int[]{10, 5, 15, 3, 7}) bst2.insert(v);

        bst2.drawBST();
        System.out.println();
        System.out.println("a) areaBST(bst2) = " + bst2.areaBST());
        // Hojas: 3, 7, 15 → 3 hojas × altura 2 = 6

        // c) sameArea
        System.out.println("\nc) sameArea(bst1, bst2) = " + sameArea(bst1, bst2));  // false

        // Árbol 3 con la misma área que bst1
        LinkedBST<Integer> bst3 = new LinkedBST<>();
        for (int v : new int[]{100, 50, 150, 25, 75, 125, 175, 10}) bst3.insert(v);
        System.out.println("   areaBST(bst3)         = " + bst3.areaBST());
        System.out.println("   sameArea(bst1, bst3)  = " + sameArea(bst1, bst3));
    }
}