public class Chocolatina {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String toString() {
        return "marca: " + marca;
    }

    // Metodo equals
    public boolean equals(Object o) {

        if (o instanceof Chocolatina) {
            Chocolatina c = (Chocolatina) o;
            return this.marca.equals(c.marca);
        }
        
        return false;
    }
}
