/**
 * Enumeración que identifica el tipo de rotación AVL aplicada
 * durante la inserción de un código de producto.
 *
 */
public enum TipoRotacion {
    NINGUNA   ("Sin rotación       - el árbol ya estaba balanceado."),
    LL        ("Rotación LL        - rotación simple a la DERECHA."),
    RR        ("Rotación RR        - rotación simple a la IZQUIERDA."),
    LR        ("Rotación LR        - rotación doble: izquierda + derecha."),
    RL        ("Rotación RL        - rotación doble: derecha + izquierda.");

    private final String descripcion;

    TipoRotacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
