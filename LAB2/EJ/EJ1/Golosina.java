public class Golosina {
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String toString() {
        return nombre + " - " + peso + " g";
    }

    // Metodo equals
    public boolean equals(Object o) {

        if (o instanceof Golosina) {
            Golosina g = (Golosina) o;
            return (this.nombre.equals(g.nombre)&&(this.peso == g.peso));
        }
        
        return false;
    }
}
