import java.util.LinkedList;
import java.util.Stack;

// ═══════════════════════════════════════════════════════════════
//  EJERCICIO 05 – Gestión de productos en inventario
//
//  Cada nodo del BST representa un PRODUCTO identificado por
//  su código (entero). La propiedad de orden del BST se respeta.
//
//  Métodos implementados:
//    · insert            – inserta productos respetando el orden BST
//    · searchRange(min,max) – retorna productos con código en [min,max]
//    · countLeaves()     – número de productos en nodos hoja
//    · printDescending() – muestra productos en orden descendente
// ═══════════════════════════════════════════════════════════════
public class Ejercicio05 {

    static class ItemDuplicated extends Exception {
        public ItemDuplicated(String msg) { super(msg); }
    }

    // ── Clase Producto ──────────────────────────────────────────
    static class Producto implements Comparable<Producto> {
        int    codigo;
        String nombre;
        double precio;

        public Producto(int codigo, String nombre, double precio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public int compareTo(Producto otro) {
            return Integer.compare(this.codigo, otro.codigo);
        }

        @Override
        public String toString() {
            return String.format("Cod:%-4d %-20s S/.%.2f", codigo, nombre, precio);
        }
    }

    // ── LinkedBST genérico ──────────────────────────────────────
    static class LinkedBST<E extends Comparable<E>> {

        class Node {
            E data; Node left, right;
            Node(E d) { this(d, null, null); }
            Node(E d, Node l, Node r) { data = d; left = l; right = r; }
        }

        private Node root;
        public LinkedBST() { root = null; }

        // ════════════════════════════════════════════════════════
        //  insert – respeta la propiedad de orden del BST
        // ════════════════════════════════════════════════════════
        public void insert(E data) throws ItemDuplicated {
            if (root == null) { root = new Node(data); return; }
            Node cur = root;
            while (true) {
                int cmp = data.compareTo(cur.data);
                if (cmp == 0) throw new ItemDuplicated("Código ya existe: " + data);
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
            StringBuilder sb = new StringBuilder();
            Stack<Node> st = new Stack<>(); Node cur = root;
            while (cur != null || !st.isEmpty()) {
                while (cur != null) { st.push(cur); cur = cur.left; }
                cur = st.pop();
                sb.append("  ").append(cur.data).append("\n");
                cur = cur.right;
            }
            return sb.toString();
        }

        // ════════════════════════════════════════════════════════
        //  searchRange(min, max)
        //  Retorna una lista con todos los productos cuyo código
        //  esté dentro del rango [min, max].
        //
        //  Estrategia: in-order iterativo con PODA:
        //    · Si el nodo actual < min  → su subárbol izquierdo
        //      también lo es, ir directo al subárbol derecho.
        //    · Si el nodo actual > max  → detenerse (in-order
        //      garantiza que lo siguiente también supera max).
        // ════════════════════════════════════════════════════════
        public LinkedList<E> searchRange(E min, E max) {
            LinkedList<E> result = new LinkedList<>();
            Stack<Node>   stack  = new Stack<>();
            Node          cur    = root;

            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    if (cur.data.compareTo(min) < 0) {
                        cur = cur.right;   // poda: rama izquierda fuera de rango
                    } else {
                        stack.push(cur);
                        cur = cur.left;
                    }
                }
                if (!stack.isEmpty()) {
                    cur = stack.pop();
                    if (cur.data.compareTo(max) > 0) break;   // superamos el máximo
                    result.add(cur.data);
                    cur = cur.right;
                }
            }
            return result;
        }

        // ════════════════════════════════════════════════════════
        //  countLeaves()
        //  Retorna el número de productos almacenados en nodos
        //  hoja (sin hijos izquierdo ni derecho).
        //  Recorrido DFS iterativo con pila.
        // ════════════════════════════════════════════════════════
        public int countLeaves() {
            if (root == null) return 0;
            int count = 0;
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node n = stack.pop();
                if (n.left == null && n.right == null) count++;
                if (n.right != null) stack.push(n.right);
                if (n.left  != null) stack.push(n.left);
            }
            return count;
        }

        // ════════════════════════════════════════════════════════
        //  printDescending()
        //  Muestra los productos en orden DESCENDENTE (código
        //  mayor primero).
        //
        //  Estrategia: in-order INVERSO iterativo
        //    derecho → raíz → izquierdo
        //  Produce la secuencia de mayor a menor.
        // ════════════════════════════════════════════════════════
        public void printDescending() {
            Stack<Node> stack = new Stack<>();
            Node        cur   = root;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.right;    // bajar por la derecha primero
                }
                cur = stack.pop();
                System.out.println("  " + cur.data);
                cur = cur.left;        // luego subárbol izquierdo
            }
        }
    }

    // ── MAIN – pruebas inventario ───────────────────────────────
    public static void main(String[] args) throws Exception {

        LinkedBST<Producto> inventario = new LinkedBST<>();

        // Insertar productos respetando la propiedad de orden (por código)
        inventario.insert(new Producto(500, "Laptop Dell",       2500.00));
        inventario.insert(new Producto(200, "Mouse Logitech",      85.50));
        inventario.insert(new Producto(800, "Monitor LG 27\"",   1200.00));
        inventario.insert(new Producto(150, "Auriculares Sony",   350.00));
        inventario.insert(new Producto(350, "Teclado Mecánico",   220.00));
        inventario.insert(new Producto(650, "Webcam Logitech",    180.00));
        inventario.insert(new Producto(900, "Impresora HP",       450.00));
        inventario.insert(new Producto(100, "USB Hub 7 puertos",   45.00));
        inventario.insert(new Producto(250, "Pad Mouse XL",        35.00));
        inventario.insert(new Producto(450, "Parlante Bluetooth", 195.00));

        System.out.println("══════════════════════════════════════════");
        System.out.println("       INVENTARIO (in-order / asc)        ");
        System.out.println("══════════════════════════════════════════");
        System.out.print(inventario);

        // ── searchRange ─────────────────────────────────────────
        System.out.println("\n── searchRange(200, 500) ──────────────");
        Producto pMin = new Producto(200, "", 0);
        Producto pMax = new Producto(500, "", 0);
        LinkedList<Producto> rango = inventario.searchRange(pMin, pMax);
        if (rango.isEmpty()) System.out.println("  (sin resultados)");
        else for (Producto p : rango) System.out.println("  " + p);

        System.out.println("\n── searchRange(700, 999) ──────────────");
        pMin = new Producto(700, "", 0);
        pMax = new Producto(999, "", 0);
        rango = inventario.searchRange(pMin, pMax);
        if (rango.isEmpty()) System.out.println("  (sin resultados)");
        else for (Producto p : rango) System.out.println("  " + p);

        // ── countLeaves ─────────────────────────────────────────
        System.out.println("\n── countLeaves() ──────────────────────");
        System.out.println("  Productos en nodos hoja: " + inventario.countLeaves());

        // ── printDescending ─────────────────────────────────────
        System.out.println("\n── printDescending() ──────────────────");
        inventario.printDescending();
    }
}