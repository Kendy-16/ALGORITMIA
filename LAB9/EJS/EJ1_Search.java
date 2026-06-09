// ============================================================
//  EJ1 – search(E cl)
//  Busca una clave en el Árbol B.
//  Retorna true si existe, false si no.
//  Si existe, imprime el idNode y la posición dentro del nodo.
// ============================================================

public class EJ1_Search {

    // ── Método público ──────────────────────────────────────
    public boolean search(E cl) {
        return searchHelper(root, cl);
    }

    // ── Método de soporte ───────────────────────────────────
    /**
     * Recorre el árbol de forma descendente.
     *   1. Avanza en el nodo mientras cl > keys[pos].
     *   2. Si cl == keys[pos]  →  encontrado: imprime info y retorna true.
     *   3. Si llega a null     →  no encontrado: retorna false.
     *
     * Complejidad: O(log n)
     */
    private boolean searchHelper(BNode<E> current, E cl) {
        if (current == null) return false;

        int pos = 0;

        // Avanzar mientras la clave buscada sea mayor que keys[pos]
        while (pos < current.count && cl.compareTo(current.keys.get(pos)) > 0) {
            pos++;
        }

        // ¿Clave encontrada en este nodo?
        if (pos < current.count && cl.compareTo(current.keys.get(pos)) == 0) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posición " + pos);
            return true;
        }

        // Descender al hijo childs[pos]
        return searchHelper(current.childs.get(pos), cl);
    }

    // ── Pruebas ─────────────────────────────────────────────
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> bt = new BTree<>(5);          // orden 5

        int[] valores = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100,
                          5, 15, 25, 35, 45, 55, 65, 75, 85, 95, 52, 3, 99};
        for (int v : valores) bt.insert(v);

        System.out.println("Árbol B (orden 5):");
        bt.printTree();
        System.out.println();

        // 1) Dato en hoja / nodo intermedio
        System.out.println("-- Buscar 52 --");
        System.out.println("Resultado: " + bt.search(52));

        // 2) Extremo inicial (hoja izquierda)
        System.out.println("\n-- Buscar 3 (extremo inicial) --");
        System.out.println("Resultado: " + bt.search(3));

        // 3) Extremo final (hoja derecha)
        System.out.println("\n-- Buscar 99 (extremo final) --");
        System.out.println("Resultado: " + bt.search(99));

        // 4) Clave en la raíz
        System.out.println("\n-- Buscar clave en raíz (" + bt.getRoot().keys.get(0) + ") --");
        System.out.println("Resultado: " + bt.search(bt.getRoot().keys.get(0)));

        // 5) Dato no encontrado
        System.out.println("\n-- Buscar 77 (no existe) --");
        System.out.println("Resultado: " + bt.search(77));
    }
}
