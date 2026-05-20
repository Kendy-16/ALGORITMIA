/**
 * Clase principal - Ejercicio 5.
 *
 * Verifica que el recorrido por amplitud recursivo produce exactamente:
 *   50  30  70  20  40  60  80  10  25  65
 *
 * 
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   EJERCICIO 5 - BFS RECURSIVO EN ÁRBOL AVL   ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println();

        // Secuencia de inserción indicada en el enunciado
        int[] claves = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};

        AVLTree avl = new AVLTree();

        System.out.print("Insertando claves: ");
        for (int c : claves) {
            System.out.print(c + " ");
            avl.insertar(c);
        }
        System.out.println();
        System.out.println("Altura resultante del árbol AVL: " + avl.getAltura());
        System.out.println();

        // Recorrido por amplitud recursivo
        avl.recorridoPorAmplitud();

        System.out.println();
        System.out.println("Resultado esperado según enunciado:");
        System.out.println("  50  30  70  20  40  60  80  10  25  65");
        System.out.println();

        // Verificación inorden (debe estar ordenado ascendentemente)
        avl.inorden();

        System.out.println();
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("NOTA: El BFS recursivo usa imprimirNivel() como");
        System.out.println("      método auxiliar, evitando completamente el");
        System.out.println("      uso de una cola (Queue). Complejidad: O(n·h)");
        System.out.println("──────────────────────────────────────────────────");
    }
}
