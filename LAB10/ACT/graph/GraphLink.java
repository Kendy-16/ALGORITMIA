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

        // ORIGEN -> DESTINO
        v1.getEdges().addLast(
                new Edge<>(v2.getVertex())
        );

        // DESTINO -> ORIGEN
        v2.getEdges().addLast(
                new Edge<>(v1.getVertex())
        );
    }

    // ELIMINAR ARISTA
    public void removeEdge(E origin, E destination) {

        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {

            return;
        }

        // eliminar en v1
        for (int i = 0; i < v1.getEdges().size(); i++) {

            Edge<E> edge = v1.getEdges().get(i);

            if (edge.getDestination().getData().equals(destination)) {

                v1.getEdges().remove(i);
                break;
            }
        }

        // eliminar en v2
        for (int i = 0; i < v2.getEdges().size(); i++) {

            Edge<E> edge = v2.getEdges().get(i);

            if (edge.getDestination().getData().equals(origin)) {

                v2.getEdges().remove(i);
                break;
            }
        }
    }

    // ELIMINAR VERTICE
    public void removeVertex(E data) {

        AdjList<E> vertex = findVertex(data);

        if (vertex == null) {

            return;
        }

        // eliminar referencias
        for (int i = 0; i < graph.size(); i++) {

            AdjList<E> adj = graph.get(i);

            for (int j = 0; j < adj.getEdges().size(); j++) {

                Edge<E> edge = adj.getEdges().get(j);

                if (edge.getDestination().getData().equals(data)) {

                    adj.getEdges().remove(j);
                    j--;
                }
            }
        }

        // eliminar vertice
        for (int i = 0; i < graph.size(); i++) {

            if (graph.get(i).getVertex().getData().equals(data)) {

                graph.remove(i);
                break;
            }
        }
    }

    // BUSCAR VERTICE
    public boolean searchVertex(E data) {

        return findVertex(data) != null;
    }

    // BUSCAR ARISTA
    public boolean searchEdge(E origin, E destination) {

        AdjList<E> v = findVertex(origin);

        if (v == null) {

            return false;
        }

        for (int i = 0; i < v.getEdges().size(); i++) {

            Edge<E> edge = v.getEdges().get(i);

            if (edge.getDestination().getData().equals(destination)) {

                return true;
            }
        }

        return false;
    }

    // MOSTRAR ADYACENTES
    public void adjacentVertices(E data) {

        AdjList<E> v = findVertex(data);

        if (v == null) {

            return;
        }

        System.out.print(data + " -> ");

        for (int i = 0; i < v.getEdges().size(); i++) {

            System.out.print(
                    v.getEdges().get(i) + " "
            );
        }

        System.out.println();
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
