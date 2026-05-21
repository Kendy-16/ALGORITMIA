/**
 * Clase principal - Ejercicio 6.
 *
 * Construye CUATRO árboles AVL con diferentes secuencias de inserción
 * y aplica el recorrido preOrder() para verificar el resultado.
 * Cada árbol muestra también inOrder() y BFS para comparación.
 *
 *
 */
public class Main {

    // Construye e imprime recorridos de un árbol AVL con las claves dadas
    private static void probarArbol(String nombre, int[] claves) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.printf ("│  %-47s│%n", nombre);
        System.out.print(  "│  Inserción: ");
        StringBuilder sb = new StringBuilder();
        for (int c : claves) sb.append(c).append(", ");
        String seq = sb.toString().replaceAll(", $", "");
        System.out.printf("%-37s│%n", seq);
        System.out.println("├─────────────────────────────────────────────────┤");

        AVLTree avl = new AVLTree();
        for (int c : claves) avl.insertar(c);

        System.out.printf("│  Altura: %-40d│%n", avl.getAltura());
        System.out.println("│                                                 │");

        // Imprimir recorridos
        System.out.print("│  "); avl.preOrder();
        System.out.print("│  "); avl.inOrder();
        System.out.print("│  "); avl.bfsRecursivo();

        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   EJERCICIO 6 - RECORRIDO PREORDEN EN ÁRBOL AVL  ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println();

        // --- Árbol 1: secuencia balanceada
        probarArbol(
            "ÁRBOL 1 - Secuencia balanceada",
            new int[]{50, 30, 70, 20, 40, 60, 80}
        );

        // --- Árbol 2: inserción ascendente (fuerza rotaciones RR)
        probarArbol(
            "ÁRBOL 2 - Inserción ascendente (rotaciones RR)",
            new int[]{10, 20, 30, 40, 50, 60, 70}
        );

        // --- Árbol 3: inserción descendente (fuerza rotaciones LL)
        probarArbol(
            "ÁRBOL 3 - Inserción descendente (rotaciones LL)",
            new int[]{70, 60, 50, 40, 30, 20, 10}
        );

        // --- Árbol 4: secuencia mixta con rotaciones LR y RL
        probarArbol(
            "ÁRBOL 4 - Secuencia mixta (LR / RL)",
            new int[]{30, 10, 40, 5, 20, 35, 45, 15, 25}
        );

        // --- Análisis del preorden
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("ANÁLISIS DEL RECORRIDO PREORDEN:");
        System.out.println("  • PreOrder (Raíz-Izq-Der): el primer elemento");
        System.out.println("    impreso es siempre la RAÍZ del árbol.");
        System.out.println("  • InOrder  (Izq-Raíz-Der): produce los elementos");
        System.out.println("    en orden ASCENDENTE (propiedad BST/AVL).");
        System.out.println("  • BFS      (nivel a nivel): muestra la jerarquía");
        System.out.println("    del árbol de arriba hacia abajo.");
        System.out.println("  • PreOrder es útil para CLONAR o SERIALIZAR");
        System.out.println("    la estructura del árbol.");
        System.out.println("═══════════════════════════════════════════════════");
    }
}
