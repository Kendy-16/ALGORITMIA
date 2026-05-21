/**
 * Clase ResultadoEliminacion - Encapsula la información de una operación
 * de eliminación AVL para documentarla en la tabla de pruebas.
 *
 * Registra:
 *   • claveEliminada  → Clave del nodo eliminado del árbol.
 *   • casoBST         → Tipo de caso BST presentado (0, 1 o 2 hijos).
 *   • sucesorUsado    → Sucesor/antecesor inorden utilizado (-1 si no aplica).
 *   • nodoDesbalance  → Nodo donde se detectó el desbalance (-1 si ninguno).
 *   • rotacionAplicada→ Tipo de rotación ejecutada para restaurar AVL.
 *
 * 
 */
public class ResultadoEliminacion {

    // ─── Campos ───────────────────────────────────────────────
    public final int    claveEliminada;
    public final String casoBST;
    public final int    sucesorUsado;       // -1 = no aplica
    public final int    nodoDesbalance;     // -1 = sin desbalance
    public final String rotacionAplicada;

    // ─── Constructor ──────────────────────────────────────────
    public ResultadoEliminacion(int claveEliminada,
                                 String casoBST,
                                 int    sucesorUsado,
                                 int    nodoDesbalance,
                                 String rotacionAplicada) {
        this.claveEliminada   = claveEliminada;
        this.casoBST          = casoBST;
        this.sucesorUsado     = sucesorUsado;
        this.nodoDesbalance   = nodoDesbalance;
        this.rotacionAplicada = rotacionAplicada;
    }

    // ─── Formateo para tabla ──────────────────────────────────

    /** Convierte el sucesor a texto legible. */
    public String sucesorTexto() {
        return (sucesorUsado == -1) ? "N/A" : String.valueOf(sucesorUsado);
    }

    /** Convierte el nodo de desbalance a texto legible. */
    public String desbalanceTexto() {
        return (nodoDesbalance == -1) ? "Ninguno" : String.valueOf(nodoDesbalance);
    }
}
