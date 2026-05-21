/**
 * Clase principal - Ejercicio 8.
 * Sistema de registro de códigos de productos usando árbol AVL.
 *
 * Escenario: Una pequeña tienda administra sus productos durante el día.
 *   • Los productos se insertan conforme llegan al almacén.
 *   • Se buscan productos para verificar su disponibilidad.
 *   • Se eliminan los que ya no están en stock.
 *
 * Al finalizar cada fase se responden las preguntas adicionales:
 *   P1 → ¿Qué rotación se aplicó al insertar un código?
 *   P2 → ¿Por qué el recorrido inorden muestra los productos ordenados?
 *   P3 → ¿Qué sucede al buscar un producto que no existe?
 *

 */
public class Main {

    private static final String SEP  = "═".repeat(56);
    private static final String sep  = "─".repeat(56);

    public static void main(String[] args) {

        System.out.println("╔" + SEP + "╗");
        System.out.println("║   EJ8 - REGISTRO DE CÓDIGOS DE PRODUCTOS EN AVL  ║");
        System.out.println("╚" + SEP + "╝");
        System.out.println();

        AVLTree inventario = new AVLTree();

        // ─────────────────────────────────────────────────────
        //  FASE 1: INSERCIÓN DE PRODUCTOS
        //  (Se insertan en orden que provoca distintas rotaciones)
        // ─────────────────────────────────────────────────────
        System.out.println(SEP);
        System.out.println(" FASE 1: Registro de nuevos productos");
        System.out.println(SEP);
        System.out.println(" Insertando códigos en el sistema...");
        System.out.println(sep);

        // Código  1001, 1002, 1003 → provoca rotación RR
        inventario.insertar(1001);
        inventario.insertar(1002);
        inventario.insertar(1003); // RR → rotación izquierda

        // Código 1005, 1004 → provoca rotación RL
        inventario.insertar(1005);
        inventario.insertar(1004); // RL → rotación doble

        // Código 999, 998 → provoca rotación LL
        inventario.insertar(999);
        inventario.insertar(998);  // LL → rotación derecha

        // Código 1000 → provoca rotación LR
        inventario.insertar(1000); // LR → rotación doble

        // Códigos adicionales sin rotación
        inventario.insertar(1010);
        inventario.insertar(1007);

        System.out.println();
        inventario.listarProductos();

        // ─────────────────────────────────────────────────────
        //  FASE 2: BÚSQUEDA DE PRODUCTOS
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println(SEP);
        System.out.println(" FASE 2: Búsqueda de productos");
        System.out.println(SEP);

        inventario.buscar(1003);  // Existe
        inventario.buscar(1004);  // Existe
        inventario.buscar(2000);  // NO existe → responde P3
        inventario.buscar(500);   // NO existe → responde P3

        // ─────────────────────────────────────────────────────
        //  FASE 3: ELIMINACIÓN DE PRODUCTOS
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println(SEP);
        System.out.println(" FASE 3: Eliminación de productos sin stock");
        System.out.println(SEP);

        inventario.eliminar(998);   // Hoja
        inventario.eliminar(1002);  // Nodo interno con posible rebalanceo
        inventario.eliminar(1005);  // Nodo con sucesor inorden
        inventario.eliminar(9999);  // No existe

        System.out.println();
        inventario.listarProductos();

        // ─────────────────────────────────────────────────────
        //  PREGUNTAS ADICIONALES
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println("╔" + SEP + "╗");
        System.out.println("║              PREGUNTAS ADICIONALES               ║");
        System.out.println("╠" + SEP + "╣");

        System.out.println("║                                                  ║");
        System.out.println("║  P1 ¿Qué rotación se aplicó al insertar?        ║");
        System.out.println("║     Cada inserción reportó su rotación arriba.  ║");
        System.out.println("║     AVL usa 4 tipos según el desbalance:        ║");
        System.out.println("║       LL → rotación simple DERECHA              ║");
        System.out.println("║       RR → rotación simple IZQUIERDA            ║");
        System.out.println("║       LR → doble: izquierda + derecha           ║");
        System.out.println("║       RL → doble: derecha + izquierda           ║");

        System.out.println("║                                                  ║");
        System.out.println("║  P2 ¿Por qué inorden muestra orden ascendente? ║");
        System.out.println("║     Invariante BST: nodo.left < nodo < nodo.right║");
        System.out.println("║     El recorrido Izq→Raíz→Der respeta esto     ║");
        System.out.println("║     para cada nodo, resultando en secuencia     ║");
        System.out.println("║     ordenada de menor a mayor.                  ║");

        System.out.println("║                                                  ║");
        System.out.println("║  P3 ¿Qué sucede al buscar un código inexistente?║");
        System.out.println("║     El recorrido llega a un nodo null (hoja     ║");
        System.out.println("║     vacía) sin encontrar la clave. El sistema   ║");
        System.out.println("║     retorna false e informa al usuario. No se   ║");
        System.out.println("║     lanza excepción ni se corrompe el árbol.    ║");
        System.out.println("║                                                  ║");

        System.out.println("╚" + SEP + "╝");
    }
}
