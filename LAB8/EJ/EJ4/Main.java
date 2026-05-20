/**
 * Clase principal - Ejercicio 4.
 *
 * Prueba el recorrido por amplitud RECURSIVO con dos árboles AVL distintos
 * y lo compara con los recorridos inorden y preorden.
 *
 * Árbol 1 → inserción: 50, 30, 70, 20, 40, 60, 80, 10, 25, 65
 * Árbol 2 → inserción: 15, 10, 20, 8, 12, 17, 25, 5, 11
 *
 * 
 */
public class Main {

    public static void main(String[] args) {

        // ==========================
        //  ÁRBOL AVL N.° 1
        // ==========================
        System.out.println("=".repeat(55));
        System.out.println("  ÁRBOL AVL N.° 1");
        System.out.println("  Inserción: 50, 30, 70, 20, 40, 60, 80, 10, 25, 65");
        System.out.println("=".repeat(55));

        AVLTree arbol1 = new AVLTree();
        int[] claves1 = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int c : claves1) arbol1.insertar(c);

        System.out.println("Altura del árbol: " + arbol1.getAltura());
        System.out.println();

        arbol1.recorridoAmplitud(); // BFS recursivo
        arbol1.inorden();
        arbol1.preorden();

        // ==========================
        //  ÁRBOL AVL N.° 2
        // ==========================
        System.out.println();
        System.out.println("=".repeat(55));
        System.out.println("  ÁRBOL AVL N.° 2");
        System.out.println("  Inserción: 15, 10, 20, 8, 12, 17, 25, 5, 11");
        System.out.println("=".repeat(55));

        AVLTree arbol2 = new AVLTree();
        int[] claves2 = {15, 10, 20, 8, 12, 17, 25, 5, 11};
        for (int c : claves2) arbol2.insertar(c);

        System.out.println("Altura del árbol: " + arbol2.getAltura());
        System.out.println();

        arbol2.recorridoAmplitud();
        arbol2.inorden();
        arbol2.preorden();

        // ==========================
        //  ANÁLISIS COMPARATIVO
        // ==========================
        System.out.println();
        System.out.println("=".repeat(55));
        System.out.println("  COMPARACIÓN DE RECORRIDOS");
        System.out.println("=".repeat(55));
        System.out.println("BFS Recursivo: visita nodo a nodo por nivel (de");
        System.out.println("  arriba hacia abajo, izquierda a derecha).");
        System.out.println("Inorden:       produce los elementos ORDENADOS");
        System.out.println("  de menor a mayor (propiedad del BST/AVL).");
        System.out.println("Preorden:      útil para clonar/serializar el árbol.");
        System.out.println("=".repeat(55));
    }
}
