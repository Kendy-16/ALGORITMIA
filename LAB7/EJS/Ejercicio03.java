import java.util.Stack;

// ═══════════════════════════════════════════════════════════════
//  EJERCICIO 03 – parenthesize()
//  Representación entre paréntesis con sangría de un árbol BST.
//
//  Formato (ejemplo con raíz = 50):
//    50 (
//      30 (
//        20
//        40
//      )
//      70 (
//        60
//        80
//      )
//    )
//
//  Regla: si un nodo tiene hijos  → "dato ("  luego hijos sangrados
//                                    luego ")"
//         si es hoja              → solo "dato"
//
//  IMPLEMENTACIÓN 100 % ITERATIVA (usa pila de objetos).
// ═══════════════════════════════════════════════════════════════
public class Ejercicio03 {

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

        // ════════════════════════════════════════════════════════
        //  parenthesize()
        //
        //  Estrategia iterativa con pila de Object[]:
        //    [0] Node    – nodo a procesar  (null si es cierre)
        //    [1] int     – nivel de sangría (2 espacios por nivel)
        //    [2] boolean – true = imprimir ")" de cierre
        //
        //  Al encontrar un nodo con hijos se apilan en orden:
        //    1. Entrada de cierre  ")"          (queda al fondo)
        //    2. Hijo derecho                     (se procesa segundo)
        //    3. Hijo izquierdo                   (se procesa primero)
        //  Gracias a LIFO, izquierdo sale antes que derecho,
        //  y el ")" sale al final, cuando ambos hijos ya se imprimieron.
        // ════════════════════════════════════════════════════════
        public void parenthesize() {
            if (root == null) { System.out.println("(árbol vacío)"); return; }

            Stack<Object[]> stack = new Stack<>();
            stack.push(new Object[]{ root, 0, false });

            while (!stack.isEmpty()) {
                Object[] entry  = stack.pop();
                Node     node   = (Node)    entry[0];
                int      indent = (int)     entry[1];
                boolean  close  = (boolean) entry[2];

                String pad = "  ".repeat(indent);

                if (close) {
                    // ── imprimir paréntesis de cierre ────────────
                    System.out.println(pad + ")");

                } else {
                    boolean hasChildren = (node.left != null || node.right != null);

                    if (hasChildren) {
                        // ── nodo interno: abrir paréntesis ───────
                        System.out.println(pad + node.data + " (");

                        // Apilar: cierre → derecho → izquierdo
                        // (LIFO: izquierdo se procesa primero)
                        stack.push(new Object[]{ null,       indent,     true  });
                        if (node.right != null)
                            stack.push(new Object[]{ node.right, indent + 1, false });
                        if (node.left  != null)
                            stack.push(new Object[]{ node.left,  indent + 1, false });
                    } else {
                        // ── nodo hoja: sin paréntesis ─────────────
                        System.out.println(pad + node.data);
                    }
                }
            }
        }
    }

    // ── MAIN – pruebas ──────────────────────────────────────────
    public static void main(String[] args) throws Exception {

        // Árbol 1: completo de 7 nodos
        //         50
        //       /    \
        //      30     70
        //     /  \   /  \
        //    20  40 60   80
        System.out.println("══ Árbol 1 (completo) ══════════════════");
        LinkedBST<Integer> bst1 = new LinkedBST<>();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) bst1.insert(v);
        bst1.parenthesize();

        // Árbol 2: desequilibrado a la derecha
        //   10
        //     \
        //      20
        //        \
        //         30
        System.out.println("\n══ Árbol 2 (cadena derecha) ════════════");
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        for (int v : new int[]{10, 20, 30}) bst2.insert(v);
        bst2.parenthesize();

        // Árbol 3: solo un nodo (raíz hoja)
        System.out.println("\n══ Árbol 3 (solo raíz) ════════════════");
        LinkedBST<Integer> bst3 = new LinkedBST<>();
        bst3.insert(42);
        bst3.parenthesize();

        // Árbol 4: similar al ejemplo de la figura 7.13 (con enteros)
        //               40
        //             /    \
        //           20      60
        //          /  \    /  \
        //         10  30  50  70
        //                    /
        //                   65
        System.out.println("\n══ Árbol 4 (varios niveles) ════════════");
        LinkedBST<Integer> bst4 = new LinkedBST<>();
        for (int v : new int[]{40, 20, 60, 10, 30, 50, 70, 65}) bst4.insert(v);
        bst4.parenthesize();
    }
}