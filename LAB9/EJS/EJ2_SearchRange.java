// ============================================================
//  EJ2 – searchRange(E min, E max)
//  Muestra todas las claves en [min, max] en orden ascendente.
//
//  PODA DE RAMAS:
//  En cada nodo se calculan:
//    · startPos → primer índice con keys[startPos] >= min
//    · endPos   → último  índice con keys[endPos]  <= max
//
//  Solo se desciende por childs[startPos..endPos+1].
//  Ramas descartadas:
//    · childs[0 .. startPos-1]  → todas sus claves son < min (inútiles)
//    · childs[endPos+2 .. count]→ todas sus claves son > max (inútiles)
//
//  Complejidad: O(log n + k)  donde k = claves en el rango.
// ============================================================

public class EJ2_SearchRange {

    // ── Método público ──────────────────────────────────────
    public void searchRange(E min, E max) {
        // Validar rango
        if (min.compareTo(max) > 0) {
            System.out.println("Rango inválido: min (" + min
                    + ") es mayor que max (" + max + ").");
            return;
        }

        System.out.print("Claves en [" + min + ", " + max + "]: ");
        boolean[] found = {false};
        searchRangeHelper(root, min, max, found);

        if (!found[0]) System.out.print("(ninguna clave en ese rango)");
        System.out.println();
    }

    // ── Método de soporte ───────────────────────────────────
    private void searchRangeHelper(BNode<E> current, E min, E max,
                                   boolean[] found) {
        if (current == null) return;

        // startPos: primer índice donde keys[startPos] >= min
        int startPos = 0;
        while (startPos < current.count
                && min.compareTo(current.keys.get(startPos)) > 0) {
            startPos++;
        }

        // endPos: último índice donde keys[endPos] <= max
        int endPos = current.count - 1;
        while (endPos >= 0
                && max.compareTo(current.keys.get(endPos)) < 0) {
            endPos--;
        }

        // ► Bajar solo por childs[startPos] (primer hijo relevante)
        //   childs[0..startPos-1] DESCARTADOS (claves < min)
        searchRangeHelper(current.childs.get(startPos), min, max, found);

        // Recorrer claves del nodo comprendidas en [min, max]
        for (int i = startPos; i <= endPos; i++) {
            E key = current.keys.get(i);
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                System.out.print(key + " ");
                found[0] = true;
            }
            // ► Bajar por cada hijo entre claves dentro del rango
            //   childs[endPos+2..count] DESCARTADOS (claves > max)
            searchRangeHelper(current.childs.get(i + 1), min, max, found);
        }
    }

    // ── Pruebas ─────────────────────────────────────────────
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> bt = new BTree<>(5);

        int[] valores = {10, 15, 20, 25, 30, 35, 40, 45,
                          5,  50, 55, 60, 70, 80, 90, 3, 99};
        for (int v : valores) bt.insert(v);

        System.out.println("Árbol B (orden 5):");
        bt.printTree();
        System.out.println();

        // 1) Rango válido con varias claves
        System.out.println("-- searchRange(20, 40) --");
        bt.searchRange(20, 40);

        // 2) Rango inválido (min > max)
        System.out.println("-- searchRange(50, 10) [INVÁLIDO] --");
        bt.searchRange(50, 10);

        // 3) Rango inexistente en el árbol
        System.out.println("-- searchRange(101, 200) [INEXISTENTE] --");
        bt.searchRange(101, 200);

        // 4) Rango en extremo izquierdo
        System.out.println("-- searchRange(3, 5) --");
        bt.searchRange(3, 5);

        // 5) Rango completo
        System.out.println("-- searchRange(3, 99) --");
        bt.searchRange(3, 99);
    }
}
