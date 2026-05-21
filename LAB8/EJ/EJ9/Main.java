/**
 * Clase principal - Ejercicio 9.
 * Sistema de cola de turnos para clínica pequeña usando árbol AVL.
 *
 * Escenario: La clínica atiende pacientes por número de turno.
 *   • Los turnos se asignan conforme llegan los pacientes.
 *   • El sistema permite buscar si un turno está en espera.
 *   • Al ser atendido, el turno se elimina del sistema.
 *
 * Preguntas adicionales resueltas:
 *   P1 → ¿Qué nodo quedó como raíz después de insertar 30?
 *   P2 → ¿Qué diferencia hay entre buscar en lista y en AVL?
 *   P3 → ¿Qué caso BST ocurre al eliminar el turno 10?
 *

 */
public class Main {

    private static final String SEP = "═".repeat(56);
    private static final String sep = "─".repeat(56);

    public static void main(String[] args) {

        System.out.println("╔" + SEP + "╗");
        System.out.println("║     EJ9 - COLA DE TURNOS EN CLÍNICA (AVL)       ║");
        System.out.println("╚" + SEP + "╝");
        System.out.println();

        AVLTree clinica = new AVLTree();

        // ─────────────────────────────────────────────────────
        //  FASE 1: REGISTRO DE TURNOS
        //  Secuencia: 20, 10, 30 → responde P1
        // ─────────────────────────────────────────────────────
        System.out.println(SEP);
        System.out.println(" FASE 1: Registro de turnos de pacientes");
        System.out.println(SEP);
        System.out.println(" Asignando turnos al llegar los pacientes...");
        System.out.println(sep);

        // Insertar 20, 10, 30 primero para responder P1
        clinica.insertarTurno(20);
        clinica.insertarTurno(10);
        clinica.insertarTurno(30); // ← P1: ¿cuál es la raíz después de este?

        System.out.println();
        System.out.println("  ► Respuesta P1: La raíz después de insertar 30 es: "
                           + clinica.getRaiz());
        System.out.println("    Los turnos 20-10-30 forman un árbol balanceado;");
        System.out.println("    no se aplicó rotación y la raíz sigue siendo 20.");
        System.out.println();

        // Turnos adicionales que fuerzan rotaciones
        System.out.println(sep);
        System.out.println(" Registrando más pacientes (pueden surgir rotaciones):");
        System.out.println(sep);
        clinica.insertarTurno(5);
        clinica.insertarTurno(15);
        clinica.insertarTurno(25);
        clinica.insertarTurno(40);
        clinica.insertarTurno(3);   // Puede provocar rotación LL
        clinica.insertarTurno(7);   // Puede provocar rotación LR
        clinica.insertarTurno(35);  // Puede provocar rotación RL

        System.out.println();
        clinica.mostrarEstado();

        // ─────────────────────────────────────────────────────
        //  FASE 2: BÚSQUEDA DE TURNOS  (P2)
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println(SEP);
        System.out.println(" FASE 2: Consulta de turnos en espera");
        System.out.println(SEP);

        clinica.buscarTurno(15);    // Existe
        clinica.buscarTurno(40);    // Existe
        clinica.buscarTurno(99);    // No existe
        clinica.buscarTurno(1);     // No existe

        System.out.println();
        System.out.println(sep);
        System.out.println("  ► Respuesta P2: Diferencia entre lista y AVL:");
        System.out.println(sep);
        System.out.println("  LISTA ENLAZADA:");
        System.out.println("    • Búsqueda secuencial, elemento por elemento.");
        System.out.println("    • Complejidad: O(n) en el peor caso.");
        System.out.println("    • Con 1.000 turnos → hasta 1.000 comparaciones.");
        System.out.println();
        System.out.println("  ÁRBOL AVL:");
        System.out.println("    • Búsqueda binaria sobre estructura dinámica.");
        System.out.println("    • Complejidad: O(log n) garantizado.");
        System.out.println("    • Con 1.000 turnos → máximo ~10 comparaciones.");
        System.out.println("    • El árbol siempre está balanceado (rotaciones AVL),");
        System.out.println("      evitando la degeneración en lista del BST puro.");

        // ─────────────────────────────────────────────────────
        //  FASE 3: ELIMINACIÓN DE TURNOS ATENDIDOS  (P3)
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println(SEP);
        System.out.println(" FASE 3: Eliminación de turnos atendidos");
        System.out.println(SEP);

        // Eliminar turno 10 → responde P3
        System.out.println(" Atendiendo paciente con turno 10...");
        System.out.println(sep);
        clinica.eliminarTurno(10);  // ← P3: ¿qué caso BST?

        System.out.println();
        System.out.println(sep);
        System.out.println("  ► Respuesta P3: Caso BST al eliminar turno 10:");
        System.out.println("    El nodo 10 tiene dos hijos (5 y 15), por lo tanto");
        System.out.println("    se aplica CASO 2 HIJOS:");
        System.out.println("      1. Se localiza el sucesor inorden de 10,");
        System.out.println("         que es el mínimo del subárbol derecho (15).");
        System.out.println("      2. El valor 10 se reemplaza con 15.");
        System.out.println("      3. El nodo 15 se elimina de su posición original.");
        System.out.println("      4. El árbol se rebalancea si el factor de balance");
        System.out.println("         de algún antecesor supera ±1.");
        System.out.println(sep);

        // Eliminar más turnos atendidos
        System.out.println();
        System.out.println(" Atendiendo más pacientes...");
        clinica.eliminarTurno(3);   // Hoja → Caso 0 hijos
        clinica.eliminarTurno(40);  // Nodo con posible 1 hijo
        clinica.eliminarTurno(20);  // Raíz → 2 hijos
        clinica.eliminarTurno(50);  // No existe

        System.out.println();
        clinica.mostrarEstado();

        // ─────────────────────────────────────────────────────
        //  RESUMEN
        // ─────────────────────────────────────────────────────
        System.out.println();
        System.out.println("╔" + SEP + "╗");
        System.out.println("║                  RESUMEN FINAL                  ║");
        System.out.println("╠" + SEP + "╣");
        System.out.println("║  P1: La raíz tras insertar 30 fue → 20          ║");
        System.out.println("║      (no hubo rotación; árbol ya balanceado)     ║");
        System.out.println("║                                                  ║");
        System.out.println("║  P2: AVL O(log n) vs Lista O(n)                 ║");
        System.out.println("║      10x más rápido con 1.000 registros         ║");
        System.out.println("║                                                  ║");
        System.out.println("║  P3: Turno 10 → CASO 2 HIJOS (sucesor inorden) ║");
        System.out.println("║      Sucesor = mínimo subárbol derecho = 15     ║");
        System.out.println("╚" + SEP + "╝");
    }
}
