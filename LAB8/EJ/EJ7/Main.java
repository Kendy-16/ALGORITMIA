/**
 * Clase principal - Ejercicio 7.
 *
 * Demuestra la inserción y eliminación en un árbol AVL verificando
 * que se aplican correctamente los cuatro tipos de rotación:
 *
 *   LL → Rotación simple a la derecha.
 *   RR → Rotación simple a la izquierda.
 *   LR → Rotación doble: izquierda + derecha.
 *   RL → Rotación doble: derecha + izquierda.
 *
 * Escenario:
 *   1. Árbol inicialmente vacío.
 *   2. Inserción de claves que provocan los 4 tipos de desbalance.
 *   3. Eliminación de claves que fuerzan nuevas rotaciones.
 */
public class Main {

    private static final String SEP = "═".repeat(52);
    private static final String sep = "─".repeat(52);

    public static void main(String[] args) {

        System.out.println("╔" + SEP + "╗");
        System.out.println("║   EJERCICIO 7 - INSERCIÓN Y ELIMINACIÓN AVL     ║");
        System.out.println("║         con casos de rotación                   ║");
        System.out.println("╚" + SEP + "╝");
        System.out.println();

        AVLTree avl = new AVLTree();

        // =====================================================
        //  FASE 1 - INSERCIONES QUE GENERAN ROTACIONES
        // =====================================================
        System.out.println(SEP);
        System.out.println("FASE 1: INSERCIONES");
        System.out.println(SEP);

        // Caso RR: insertar 10, 20, 30 → rotación izquierda en 10
        System.out.println(sep);
        System.out.println(">> Caso RR (rotación izquierda) al insertar 10-20-30:");
        System.out.println(sep);
        avl.insertar(10);
        avl.insertar(20);
        avl.insertar(30); // desbalance RR en nodo 10
        avl.imprimirEstado("insertar 10-20-30");
        System.out.println();

        // Caso LL: insertar 5, 3 → rotación derecha en 10
        System.out.println(sep);
        System.out.println(">> Caso LL (rotación derecha) al insertar 5-3:");
        System.out.println(sep);
        avl.insertar(5);
        avl.insertar(3);  // desbalance LL
        avl.imprimirEstado("insertar 5-3");
        System.out.println();

        // Caso LR: insertar 7 → desbalance LR
        System.out.println(sep);
        System.out.println(">> Caso LR (rotación doble izq-der) al insertar 7:");
        System.out.println(sep);
        avl.insertar(7);  // desbalance LR
        avl.imprimirEstado("insertar 7");
        System.out.println();

        // Caso RL: insertar 25 → desbalance RL
        System.out.println(sep);
        System.out.println(">> Caso RL (rotación doble der-izq) al insertar 25:");
        System.out.println(sep);
        avl.insertar(25); // desbalance RL
        avl.imprimirEstado("insertar 25");
        System.out.println();

        // Inserciones adicionales para enriquecer el árbol
        System.out.println(sep);
        System.out.println(">> Inserción masiva: 15, 8, 35, 1, 40");
        System.out.println(sep);
        for (int c : new int[]{15, 8, 35, 1, 40}) avl.insertar(c);
        avl.imprimirEstado("inserción masiva");
        System.out.println();

        // =====================================================
        //  FASE 2 - ELIMINACIONES QUE GENERAN ROTACIONES
        // =====================================================
        System.out.println(SEP);
        System.out.println("FASE 2: ELIMINACIONES");
        System.out.println(SEP);

        // Eliminar hoja
        System.out.println(sep);
        System.out.println(">> Eliminar hoja: 1");
        System.out.println(sep);
        avl.eliminar(1);
        avl.imprimirEstado("eliminar 1");
        System.out.println();

        // Eliminar nodo con un hijo
        System.out.println(sep);
        System.out.println(">> Eliminar nodo con un hijo: 40");
        System.out.println(sep);
        avl.eliminar(40);
        avl.imprimirEstado("eliminar 40");
        System.out.println();

        // Eliminar nodo con dos hijos (sucesor inorden)
        System.out.println(sep);
        System.out.println(">> Eliminar nodo con dos hijos: 20 (raíz probable)");
        System.out.println(sep);
        avl.eliminar(20);
        avl.imprimirEstado("eliminar 20");
        System.out.println();

        // Eliminar que fuerza rebalanceo
        System.out.println(sep);
        System.out.println(">> Eliminar 30 (posible rebalanceo)");
        System.out.println(sep);
        avl.eliminar(30);
        avl.imprimirEstado("eliminar 30");
        System.out.println();

        // =====================================================
        //  RESUMEN FINAL
        // =====================================================
        System.out.println("╔" + SEP + "╗");
        System.out.println("║                   RESUMEN FINAL                ║");
        System.out.println("╠" + SEP + "╣");
        System.out.printf ("║  Rotaciones totales aplicadas: %-19d║%n",
                           avl.getContadorRotaciones());
        System.out.printf ("║  Altura final del árbol:       %-19d║%n",
                           avl.getAltura());
        System.out.println("╠" + SEP + "╣");
        System.out.println("║  Tipos de rotación AVL:                        ║");
        System.out.println("║    LL → rotarDerecha(nodo)                     ║");
        System.out.println("║    RR → rotarIzquierda(nodo)                   ║");
        System.out.println("║    LR → rotarIzquierda(nodo.left)              ║");
        System.out.println("║         + rotarDerecha(nodo)                   ║");
        System.out.println("║    RL → rotarDerecha(nodo.right)               ║");
        System.out.println("║         + rotarIzquierda(nodo)                 ║");
        System.out.println("╚" + SEP + "╝");
    }
}
