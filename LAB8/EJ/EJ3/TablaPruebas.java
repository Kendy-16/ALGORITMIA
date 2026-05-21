import java.util.ArrayList;
import java.util.List;

/**
 * Clase TablaPruebas - Registra y muestra la tabla de documentación
 * de las operaciones de eliminación realizadas sobre el árbol AVL.
 *
 * Columnas de la tabla (según enunciado):
 *   1. Clave eliminada
 *   2. Caso BST  (nodo hoja / nodo con 1 hijo / nodo con 2 hijos)
 *   3. Sucesor/antecesor inorden utilizado
 *   4. Nodo desbalanceado
 *   5. Rotación aplicada
 *
 */
public class TablaPruebas {

    private final List<ResultadoEliminacion> registros = new ArrayList<>();

    /** Agrega una operación de eliminación a la tabla. */
    public void registrar(ResultadoEliminacion r) {
        registros.add(r);
    }

    /** Imprime la tabla formateada en consola. */
    public void imprimir() {
        // ── Encabezado ─────────────────────────────────────────────────────
        String borde  = "═".repeat(103);
        String divI   = "╠" + "═".repeat(10) + "╪" + "═".repeat(28) + "╪"
                            + "═".repeat(12) + "╪" + "═".repeat(16) + "╪"
                            + "═".repeat(33) + "╣";
        String divN   = "├" + "─".repeat(10) + "┼" + "─".repeat(28) + "┼"
                            + "─".repeat(12) + "┼" + "─".repeat(16) + "┼"
                            + "─".repeat(33) + "┤";

        System.out.println();
        System.out.println("╔" + borde + "╗");
        System.out.println("║" + centrar("TABLA DE DOCUMENTACIÓN DE PRUEBAS - ELIMINACIÓN AVL", 103) + "║");
        System.out.println(divI);
        System.out.printf("║ %-9s│ %-27s│ %-11s│ %-15s│ %-32s│%n",
                "Clave", "Caso BST", "Sucesor", "Nodo desbalan.", "Rotación aplicada");
        System.out.println(divI);

        // ── Filas ──────────────────────────────────────────────────────────
        for (int i = 0; i < registros.size(); i++) {
            ResultadoEliminacion r = registros.get(i);
            System.out.printf("║ %-9d│ %-27s│ %-11s│ %-15s│ %-32s│%n",
                    r.claveEliminada,
                    r.casoBST,
                    r.sucesorTexto(),
                    r.desbalanceTexto(),
                    r.rotacionAplicada);
            if (i < registros.size() - 1) System.out.println(divN);
        }

        // ── Cierre ─────────────────────────────────────────────────────────
        System.out.println("╚" + "═".repeat(10) + "╧" + "═".repeat(28) + "╧"
                               + "═".repeat(12) + "╧" + "═".repeat(16) + "╧"
                               + "═".repeat(33) + "╝");
        System.out.println();
        System.out.println("  Leyenda de casos BST:");
        System.out.println("    • Caso 0 hijos  → Nodo hoja: eliminación directa.");
        System.out.println("    • Caso 1 hijo   → Reemplazar nodo con su único hijo.");
        System.out.println("    • Caso 2 hijos  → Reemplazar con sucesor inorden");
        System.out.println("                      (mínimo del subárbol derecho).");
        System.out.println("  Leyenda de rotaciones:");
        System.out.println("    • Ninguna  → El árbol quedó balanceado sin rotar.");
        System.out.println("    • LL       → Rotación simple a la derecha.");
        System.out.println("    • RR       → Rotación simple a la izquierda.");
        System.out.println("    • LR       → Rotación doble: izquierda + derecha.");
        System.out.println("    • RL       → Rotación doble: derecha + izquierda.");
    }

    // Utilidad: centra un texto en un ancho dado
    private String centrar(String texto, int ancho) {
        int padding = (ancho - texto.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + texto
             + " ".repeat(Math.max(0, ancho - texto.length() - padding));
    }

    public boolean estaVacia() { return registros.isEmpty(); }
}
