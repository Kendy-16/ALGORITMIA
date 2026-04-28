// Clase principal - Ejercicio 8: Sistema de Cola de Reproducción (Spotify)
// Integrantes: [Nombres del grupo]
// Lab 05 - Algoritmos y Estructura de Datos

public class Main {

    public static void main(String[] args) {

        // ── Crear instancia y agregar 6 canciones ──────────────────────────
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        cola.agregarCancion(new Cancion("Bohemian Rhapsody",  "Queen",              354));
        cola.agregarCancion(new Cancion("Blinding Lights",    "The Weeknd",         200));
        cola.agregarCancion(new Cancion("Shape of You",       "Ed Sheeran",         234));
        cola.agregarCancion(new Cancion("Levitating",         "Dua Lipa",           203));
        cola.agregarCancion(new Cancion("Stay",               "Kid Laroi & Bieber", 141));
        cola.agregarCancion(new Cancion("As It Was",          "Harry Styles",       167));

        // ── Mostrar cola inicial ───────────────────────────────────────────
        System.out.println("=== Cola de Reproducción Inicial ===");
        cola.mostrarCola();

        // ── Avanzar 3 canciones con reproducirSiguiente() ─────────────────
        System.out.println("\n--- Avanzando 3 canciones ---");
        for (int i = 0; i < 3; i++) {
            Cancion c = cola.reproducirSiguiente();
            System.out.println("► Reproduciendo ahora: " + c);
        }

        // ── Mostrar estado después de avanzar ─────────────────────────────
        System.out.println("\n--- Estado actual de la cola ---");
        cola.mostrarCola();

        // ── Retroceder 1 canción con reproducirAnterior() ─────────────────
        System.out.println("\n--- Retrocediendo 1 canción ---");
        Cancion anterior = cola.reproducirAnterior();
        System.out.println("◄ Anterior: " + anterior);

        // ── Mezclar y mostrar cola reorganizada ───────────────────────────
        System.out.println("\n=== Mezclando... ===");
        cola.mezclar();
        cola.mostrarCola();

        // ── Duración total en formato mm:ss ───────────────────────────────
        int totalSeg = cola.duracionTotal();
        int minutos  = totalSeg / 60;
        int segundos = totalSeg % 60;
        System.out.printf("%nDuración total: %02d:%02d%n", minutos, segundos);
    }
}