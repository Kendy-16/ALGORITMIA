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

        // =====================================
        // INSERTAR ARISTAS
        // =====================================

        System.out.println("INSERTANDO ARISTAS...\n");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "D");
        g.insertEdge("D", "E");

        // =====================================
        // MOSTRAR GRAFO
        // =====================================

        System.out.println("GRAFO ACTUAL:");
        System.out.println(g);

        // =====================================
        // BUSCAR VERTICES
        // =====================================

        System.out.println("BUSQUEDA DE VERTICES:");

        System.out.println(
                "Existe A? -> "
                + g.searchVertex("A")
        );

        System.out.println(
                "Existe Z? -> "
                + g.searchVertex("Z")
        );

        System.out.println();

        // =====================================
        // BUSCAR ARISTAS
        // =====================================

        System.out.println("BUSQUEDA DE ARISTAS:");

        System.out.println(
                "Existe arista A-B? -> "
                + g.searchEdge("A", "B")
        );

        System.out.println(
                "Existe arista A-E? -> "
                + g.searchEdge("A", "E")
        );

        System.out.println();

        // =====================================
        // MOSTRAR ADYACENTES
        // =====================================

        System.out.println("VERTICES ADYACENTES:");

        g.adjacentVertices("A");
        g.adjacentVertices("D");

        System.out.println();

        // =====================================
        // ELIMINAR ARISTA
        // =====================================

        System.out.println("ELIMINANDO ARISTA C-D...\n");

        g.removeEdge("C", "D");

        System.out.println("GRAFO ACTUALIZADO:");
        System.out.println(g);

        // =====================================
        // ELIMINAR VERTICE
        // =====================================

        System.out.println("ELIMINANDO VERTICE B...\n");

        g.removeVertex("B");

        System.out.println("GRAFO FINAL:");
        System.out.println(g);

        // =====================================
        // PRUEBA FINAL
        // =====================================

        System.out.println("PRUEBAS FINALES:");

        System.out.println(
                "Existe vertice B? -> "
                + g.searchVertex("B")
        );

        System.out.println(
                "Existe arista A-B? -> "
                + g.searchEdge("A", "B")
        );
    }
}
