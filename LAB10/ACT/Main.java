import graph.GraphLink;

public class Main {

    public static void main(String[] args) {

        GraphLink<String> g = new GraphLink<>();

        // INSERTAR VERTICES
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        // INSERTAR ARISTAS
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        // MOSTRAR
        System.out.println(g);
    }
}
