import graph.GraphLink;

public class Main {
    public static void main(String[] args) {

        GraphLink<String> g = new GraphLink<>();

        // =====================================
        // INSERTAR VERTICES
        // =====================================
        System.out.println("INSERTANDO VERTICES...\n");
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");

        // =====================================
        // INSERTAR ARISTAS
        // =====================================
        System.out.println("INSERTANDO ARISTAS...\n");
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "E");
        g.insertEdge("D", "E");
        g.insertEdge("E", "F");

        // =====================================
        // MOSTRAR GRAFO
        // =====================================
        System.out.println("GRAFO ACTUAL:");
        System.out.println(g);

        // =====================================
        // BUSCAR VERTICES
        // =====================================
        System.out.println("BUSQUEDA DE VERTICES:");
        System.out.println("Existe A? -> "+ g.searchVertex("A"));
        System.out.println("Existe Z? -> "+ g.searchVertex("Z"));
        System.out.println();

        // =====================================
        // BUSCAR ARISTAS
        // =====================================
        System.out.println("BUSQUEDA DE ARISTAS:");
        System.out.println("Existe arista A-B? -> "+ g.searchEdge("A", "B"));
        System.out.println("Existe arista A-F? -> "+ g.searchEdge("A", "F"));
        System.out.println();

        // =====================================
        // ADYACENTES
        // =====================================
        System.out.println("VERTICES ADYACENTES:");
        g.adjacentVertices("A");
        g.adjacentVertices("E");
        System.out.println();

        // =====================================
        // BFS
        // =====================================
        System.out.println("RECORRIDO BFS:");
        g.BFS("A");
        System.out.println();

        // =====================================
        // DFS
        // =====================================
        System.out.println("RECORRIDO DFS:");
        g.DFS("A");
        System.out.println();

        // =====================================
        // GRAFO CONEXO
        // =====================================
        System.out.println("El grafo es conexo? -> "+ g.isConnected());
        System.out.println();

        // =====================================
        // ELIMINAR ARISTA
        // =====================================
        System.out.println("ELIMINANDO ARISTA D-E...\n");
        g.removeEdge("D", "E");
        System.out.println(g);

        // =====================================
        // ELIMINAR VERTICE
        // =====================================
        System.out.println("ELIMINANDO VERTICE C...\n");
        g.removeVertex("C");
        System.out.println(g);

        // =====================================
        // PRUEBAS FINALES
        // =====================================
        System.out.println("PRUEBAS FINALES:");
        System.out.println("Existe vertice C? -> "+ g.searchVertex("C"));
        System.out.println("Existe arista A-C? -> "+ g.searchEdge("A", "C"));
    }
}
