// Clase que representa una canción en la cola de reproducción
public class Cancion {

    private String titulo;
    private String artista;
    private int duracionSeg;

    // Constructor
    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracionSeg() {
        return duracionSeg;
    }

    // Muestra la canción en formato: Titulo - Artista (XXXs)
    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSeg + "s)";
    }
}