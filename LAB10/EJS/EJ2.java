/*
 * EJ2.java
 * Red de ciudades del sur del Perú modelada como grafo ponderado no dirigido.
 * Se usa la librería JGraphT para gestionar el grafo y ejecutar Dijkstra.
 *
 * Funcionalidades:
 *   - Agregar ciudades (vértices) y carreteras con distancia en km (aristas)
 *   - Mostrar todas las ciudades y conexiones registradas
 *   - Calcular el camino más corto entre dos ciudades con Dijkstra (JGraphT)
 *
 * Dependencia: jgrapht-core (Maven: org.jgrapht:jgrapht-core:1.5.2)
 */

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class EJ2 {

    public static void main(String[] args) {

        // Grafo ponderado no dirigido: vértices = String (ciudad), aristas = DefaultWeightedEdge
        Graph<String, DefaultWeightedEdge> red = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // ── Agregar ciudades ──
        agregarCiudad(red, "Arequipa");
        agregarCiudad(red, "Cusco");
        agregarCiudad(red, "Puno");
        agregarCiudad(red, "Tacna");
        agregarCiudad(red, "Moquegua");

        // ── Agregar carreteras con distancia en km ──
        agregarCarretera(red, "Arequipa", "Cusco",    510);
        agregarCarretera(red, "Arequipa", "Moquegua", 230);
        agregarCarretera(red, "Moquegua", "Tacna",    160);
        agregarCarretera(red, "Cusco",    "Puno",     390);
        agregarCarretera(red, "Puno",     "Tacna",    420);

        // ── Mostrar ciudades ──
        System.out.println("=== Ciudades registradas ===");
        for (String ciudad : red.vertexSet())
            System.out.println("  - " + ciudad);

        // ── Mostrar carreteras ──
        System.out.println("\n=== Carreteras registradas ===");
        for (DefaultWeightedEdge arista : red.edgeSet()) {
            String origen   = red.getEdgeSource(arista);
            String destino  = red.getEdgeTarget(arista);
            double distancia = red.getEdgeWeight(arista);
            System.out.printf("  %s <-> %s : %.0f km%n", origen, destino, distancia);
        }

        // ── Caminos más cortos ──
        System.out.println("\n=== Camino más corto: Cusco -> Tacna ===");
        mostrarCamino(red, "Cusco", "Tacna");

        System.out.println("\n=== Camino más corto: Arequipa -> Tacna ===");
        mostrarCamino(red, "Arequipa", "Tacna");

        System.out.println("\n=== Camino más corto: Puno -> Moquegua ===");
        mostrarCamino(red, "Puno", "Moquegua");
    }

    // Agrega una ciudad como vértice al grafo
    static void agregarCiudad(Graph<String, DefaultWeightedEdge> g, String ciudad) {
        g.addVertex(ciudad);
    }

    // Agrega una carretera (arista con peso) entre dos ciudades
    static void agregarCarretera(Graph<String, DefaultWeightedEdge> g,
                                  String origen, String destino, double km) {
        DefaultWeightedEdge arista = g.addEdge(origen, destino);
        g.setEdgeWeight(arista, km);
    }

    /*
     * Dijkstra con JGraphT: DijkstraShortestPath calcula internamente
     * la ruta de menor peso entre origen y destino.
     * getPath() retorna un GraphPath con la lista de vértices y el peso total.
     */
    static void mostrarCamino(Graph<String, DefaultWeightedEdge> g,
                               String origen, String destino) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<>(g);

        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath(origen, destino);

        if (camino == null) {
            System.out.println("  Sin camino entre " + origen + " y " + destino);
        } else {
            System.out.println("  Ruta: " + camino.getVertexList());
            System.out.printf("  Distancia total: %.0f km%n", camino.getWeight());
        }
    }
}