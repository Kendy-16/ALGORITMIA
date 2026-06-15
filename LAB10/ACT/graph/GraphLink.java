package graph;

import listlinked.ListLinked;

public class GraphLink<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {

        graph = new ListLinked<>();
    }

    // INSERTAR VERTICE
    public void insertVertex(E data) {

        Vertex<E> vertex = new Vertex<>(data);

        graph.addLast(new AdjList<>(vertex));
    }

    // BUSCAR VERTICE
    private AdjList<E> findVertex(E data) {

        for (int i = 0; i < graph.size(); i++) {

            AdjList<E> adj = graph.get(i);

            if (adj.getVertex().getData().equals(data)) {

                return adj;
            }
        }

        return null;
    }

    // INSERTAR ARISTA
    public void insertEdge(E origin, E destination) {

        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        v1.getEdges().addLast(
                new Edge<>(v2.getVertex())
        );

        v2.getEdges().addLast(
                new Edge<>(v1.getVertex())
        );
    }

    // MOSTRAR GRAFO
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {

            AdjList<E> adj = graph.get(i);

            sb.append(adj.getVertex())
              .append(" -> ");

            for (int j = 0; j < adj.getEdges().size(); j++) {

                sb.append(
                        adj.getEdges().get(j)
                ).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}