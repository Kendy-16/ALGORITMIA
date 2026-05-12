import java.util.Stack;

// ═══════════════════════════════════════════════════════════════
//  EJERCICIO 04 – isValidBST()
//  Verifica si la estructura actual cumple la propiedad BST:
//    · Todos los nodos del subárbol izquierdo < raíz
//    · Todos los nodos del subárbol derecho   > raíz
//
//  Restricciones:
//    · Sin reconstruir el árbol.
//    · Usar recorridos en profundidad (DFS).
//
//  Estrategia: un árbol cumple la propiedad BST si y solo si
//  su recorrido IN-ORDER entrega los valores en orden
//  ESTRICTAMENTE CRECIENTE.
//  Se implementa con DFS in-order iterativo (pila).
// ═══════════════════════════════════════════════════════════════
public class Ejercicio04 {

    static class ItemDuplicated extends Exception {
        public ItemDuplicated(String msg) { super(msg); }
    }

    static class LinkedBST<E extends Comparable<E>> {

        class Node {
            E data; Node left, right;
            Node(E d) { this(d, null, null); }
            Node(E d, Node l, Node r) { data = d; left = l; right = r; }
        }

        Node root;                           // package-visible para tests manuales
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
        //  isValidBST()
        //
        //  Recorrido DFS in-order iterativo:
        //    derecha      → raíz → izquierda   (NO: es in-order normal)
        //    izquierda    → raíz → derecha
        //
        //  Invariante: cada valor visitado debe ser MAYOR que el
        //  anterior (prev). Si encontramos uno ≤ prev → NO es BST.
        //
        //  Retorna: true  si la propiedad BST se cumple en todos
        //                 los nodos.
        //           false en caso contrario.
        // ════════════════════════════════════════════════════════
        public boolean isValidBST() {
            Stack<Node> stack   = new Stack<>();
            Node        current = root;
            E           prev    = null;          // último valor visitado

            while (current != null || !stack.isEmpty()) {
                // Bajar por la rama izquierda
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                // Visitar el nodo
                current = stack.pop();

                // Verificar orden estrictamente creciente
                if (prev != null && current.data.compareTo(prev) <= 0)
                    return false;    // violación: igual o menor que el anterior

                prev    = current.data;
                current = current.right;    // continuar por la derecha
            }
            return true;
        }
    }

    // ── MAIN – pruebas ──────────────────────────────────────────
    public static void main(String[] args) throws Exception {

        // ── Caso 1: BST válido ──────────────────────────────────
        //         50
        //       /    \
        //      30     70
        //     /  \   /  \
        //    20  40 60   80
        LinkedBST<Integer> bst1 = new LinkedBST<>();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) bst1.insert(v);
        System.out.println("Caso 1 – BST correcto");
        System.out.println("  In-order  : " + bst1);
        System.out.println("  isValidBST: " + bst1.isValidBST());   // true
        System.out.println();

        // ── Caso 2: árbol con propiedad violada manualmente ─────
        //  Construimos un árbol aparentemente correcto visualmente
        //  pero con un nodo fuera de lugar:
        //
        //         50
        //       /    \
        //      30     70
        //     /  \
        //    20  90    ← 90 está en subárbol izquierdo de 50, violación
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        bst2.root = bst2.new Node(50);
        bst2.root.left  = bst2.new Node(30);
        bst2.root.right = bst2.new Node(70);
        bst2.root.left.left  = bst2.new Node(20);
        bst2.root.left.right = bst2.new Node(90);   // ← 90 > 50, viola BST
        System.out.println("Caso 2 – árbol NO válido (90 en subárbol izq de 50)");
        System.out.println("  In-order  : " + bst2);
        System.out.println("  isValidBST: " + bst2.isValidBST());   // false
        System.out.println();

        // ── Caso 3: árbol con duplicados ────────────────────────
        //         50
        //       /    \
        //      30     50    ← duplicado, BST exige estrictamente mayor
        LinkedBST<Integer> bst3 = new LinkedBST<>();
        bst3.root = bst3.new Node(50);
        bst3.root.left  = bst3.new Node(30);
        bst3.root.right = bst3.new Node(50);   // duplicado
        System.out.println("Caso 3 – árbol con duplicado (50 en raíz y subárbol derecho)");
        System.out.println("  In-order  : " + bst3);
        System.out.println("  isValidBST: " + bst3.isValidBST());   // false
        System.out.println();

        // ── Caso 4: árbol vacío ─────────────────────────────────
        LinkedBST<Integer> bst4 = new LinkedBST<>();
        System.out.println("Caso 4 – árbol vacío");
        System.out.println("  isValidBST: " + bst4.isValidBST());   // true (vacío es válido)
        System.out.println();

        // ── Caso 5: un solo nodo ─────────────────────────────────
        LinkedBST<Integer> bst5 = new LinkedBST<>();
        bst5.insert(42);
        System.out.println("Caso 5 – un solo nodo (42)");
        System.out.println("  isValidBST: " + bst5.isValidBST());   // true
    }
}