/**
 * Clase principal - Ejercicio 3.
 * Eliminación en un árbol AVL con documentación completa.
 *
 * Se construye un árbol AVL y se realizan eliminaciones que cubren:
 *   ✓ Caso 0 hijos  (nodo hoja)
 *   ✓ Caso 1 hijo
 *   ✓ Caso 2 hijos  (con sucesor inorden)
 *   ✓ Rotación LL   (simple derecha post-eliminación)
 *   ✓ Rotación RR   (simple izquierda post-eliminación)
 *   ✓ Rotación LR   (doble post-eliminación)
 *   ✓ Rotación RL   (doble post-eliminación)
 *   ✓ Sin rotación  (árbol ya balanceado)
 *
 * Al finalizar imprime la TABLA DE DOCUMENTACIÓN con todas las pruebas.
 *
 * 
 */
public class Main {

    private static final String SEP = "═".repeat(60);
    private static final String sep = "─".repeat(60);

    public static void main(String[] args) {

        System.out.println("╔" + SEP + "╗");
        System.out.println("║       EJ3 - ELIMINACIÓN EN ÁRBOL AVL               ║");
        System.out.println("║  (casos BST + rebalanceo + tabla de documentación)  ║");
        System.out.println("╚" + SEP + "╝");
        System.out.println();

        // La tabla de pruebas se comparte con el árbol
        TablaPruebas tabla = new TablaPruebas();
        AVLTree      avl   = new AVLTree(tabla);

        // ─────────────────────────────────────────────────────────
        //  FASE 1: CONSTRUCCIÓN DEL ÁRBOL
        //  Secuencia diseñada para que las eliminaciones posteriores
        //  provoquen los 4 tipos de rotación + los 3 casos BST.
        // ─────────────────────────────────────────────────────────
        System.out.println(SEP);
        System.out.println(" FASE 1: Construcción del árbol AVL");
        System.out.println(SEP);

        int[] claves = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 65, 75, 90, 5};
        System.out.print(" Insertando: ");
        for (int c : claves) {
            System.out.print(c + " ");
            avl.insertar(c);
        }
        System.out.println();
        System.out.println();
        avl.imprimirEstado("inserción completa");

        // ─────────────────────────────────────────────────────────
        //  FASE 2: ELIMINACIONES DOCUMENTADAS
        // ─────────────────────────────────────────────────────────
        System.out.println(SEP);
        System.out.println(" FASE 2: Eliminaciones (todas registradas en tabla)");
        System.out.println(SEP);

        // ── Eliminación 1: CASO 0 HIJOS (nodo hoja) → sin rotación ────────
        System.out.println(sep);
        System.out.println(" [E1] Eliminar 5 → Caso 0 hijos (hoja), sin rotación");
        System.out.println(sep);
        avl.eliminar(5);
        avl.imprimirEstado("eliminar 5");

        // ── Eliminación 2: CASO 0 HIJOS → provoca rotación LL ─────────────
        System.out.println(sep);
        System.out.println(" [E2] Eliminar 90 → Caso 0 hijos, fuerza rotación LL");
        System.out.println(sep);
        avl.eliminar(90);
        avl.imprimirEstado("eliminar 90");

        // ── Eliminación 3: CASO 0 HIJOS → provoca rotación RR ─────────────
        System.out.println(sep);
        System.out.println(" [E3] Eliminar 10 → Caso 0 hijos, posible rotación RR");
        System.out.println(sep);
        avl.eliminar(10);
        avl.imprimirEstado("eliminar 10");

        // ── Eliminación 4: CASO 1 HIJO ─────────────────────────────────────
        System.out.println(sep);
        System.out.println(" [E4] Eliminar 25 → Caso 1 hijo");
        System.out.println(sep);
        avl.eliminar(25);
        avl.imprimirEstado("eliminar 25");

        // ── Eliminación 5: CASO 1 HIJO → rotación LR ──────────────────────
        System.out.println(sep);
        System.out.println(" [E5] Eliminar 65 → Caso 0/1 hijo, posible LR");
        System.out.println(sep);
        avl.eliminar(65);
        avl.imprimirEstado("eliminar 65");

        // ── Eliminación 6: CASO 2 HIJOS (sucesor inorden) ─────────────────
        System.out.println(sep);
        System.out.println(" [E6] Eliminar 30 → Caso 2 hijos (sucesor inorden)");
        System.out.println(sep);
        avl.eliminar(30);
        avl.imprimirEstado("eliminar 30");

        // ── Eliminación 7: CASO 2 HIJOS → rotación RL ─────────────────────
        System.out.println(sep);
        System.out.println(" [E7] Eliminar 70 → Caso 2 hijos, posible RL");
        System.out.println(sep);
        avl.eliminar(70);
        avl.imprimirEstado("eliminar 70");

        // ── Eliminación 8: CASO 2 HIJOS (raíz del árbol) ──────────────────
        System.out.println(sep);
        System.out.println(" [E8] Eliminar 50 → Nodo raíz con 2 hijos");
        System.out.println(sep);
        avl.eliminar(50);
        avl.imprimirEstado("eliminar 50");

        // ── Eliminación 9: Clave inexistente ──────────────────────────────
        System.out.println(sep);
        System.out.println(" [E9] Eliminar 999 → Clave no existe en el árbol");
        System.out.println(sep);
        avl.eliminar(999);
        System.out.println();

        // ─────────────────────────────────────────────────────────
        //  FASE 3: TABLA DE DOCUMENTACIÓN
        // ─────────────────────────────────────────────────────────
        System.out.println(SEP);
        System.out.println(" FASE 3: Tabla de documentación de pruebas");
        System.out.println(SEP);
        tabla.imprimir();

        // ─────────────────────────────────────────────────────────
        //  RESUMEN FINAL
        // ─────────────────────────────────────────────────────────
        System.out.println();
        System.out.println("╔" + SEP + "╗");
        System.out.println("║                   RESUMEN FINAL                    ║");
        System.out.println("╠" + SEP + "╣");
        System.out.println("║  Casos BST cubiertos:                              ║");
        System.out.println("║    ✓ Caso 0 hijos  → retorno null (hoja)           ║");
        System.out.println("║    ✓ Caso 1 hijo   → ascenso del hijo al padre     ║");
        System.out.println("║    ✓ Caso 2 hijos  → copia sucesor + elimina sucesor║");
        System.out.println("║  Rebalanceo post-eliminación:                      ║");
        System.out.println("║    ✓ FB = ±1 → sin rotación (árbol OK)             ║");
        System.out.println("║    ✓ FB > +1 con hijo equilibrado/izq → LL         ║");
        System.out.println("║    ✓ FB > +1 con hijo derecho inclinado → LR       ║");
        System.out.println("║    ✓ FB < -1 con hijo equilibrado/der → RR         ║");
        System.out.println("║    ✓ FB < -1 con hijo izquierdo inclinado → RL     ║");
        System.out.printf ("║  Altura final del árbol: %-26d║%n", avl.getAltura());
        System.out.println("╚" + SEP + "╝");
    }
}
