/*
 * EJ1.java
 * Grafo no dirigido ponderado implementado con arreglos propios (sin librerías).
 * Usa una lista de adyacencia manual: cada vértice tiene un arreglo de nodos
 * vecinos encadenados (lista enlazada propia).
 *
 * Métodos implementados:
 *   - insertEdgeWeight(v, z, w) : agrega arista con peso entre v y z
 *   - shortPath(v, z)           : ruta más corta como arreglo (Dijkstra manual)
 *   - isConexo()                : verifica si todos los vértices están conectados
 *   - Dijkstra(v, w)            : ruta más corta en un Stack propio
 */
public class EJ1 {

    // ── Nodo de la lista enlazada (representa un vecino en la lista de adyacencia) ──
    static class Nodo {
        int destino;
        int peso;
        Nodo siguiente; // puntero al próximo vecino

        Nodo(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
            this.siguiente = null;
        }
    }

    // ── Stack propio usando arreglo ──
    static class Stack {
        int[] datos;
        int tope;

        Stack(int capacidad) {
            datos = new int[capacidad];
            tope = -1;
        }

        void push(int valor) {
            datos[++tope] = valor;
        }

        int pop() {
            return datos[tope--];
        }

        boolean isEmpty() {
            return tope == -1;
        }
    }

    // ── GraphLink: grafo con lista de adyacencia usando nodos enlazados ──
    static class GraphLink {
        int numVertices;
        Nodo[] adyacencia; // adyacencia[v] apunta al primer vecino de v

        GraphLink(int numVertices) {
            this.numVertices = numVertices;
            adyacencia = new Nodo[numVertices];
            // todos los vértices empiezan sin vecinos
            for (int i = 0; i < numVertices; i++)
                adyacencia[i] = null;
        }

        // Inserta arista no dirigida con peso: agrega v→z y z→v
        void insertEdgeWeight(int v, int z, int w) {
            // Insertar al inicio de la lista de v
            Nodo nuevoVZ = new Nodo(z, w);
            nuevoVZ.siguiente = adyacencia[v];
            adyacencia[v] = nuevoVZ;

            // Insertar al inicio de la lista de z (no dirigido)
            Nodo nuevoZV = new Nodo(v, w);
            nuevoZV.siguiente = adyacencia[z];
            adyacencia[z] = nuevoZV;
        }

        /*
         * shortPath(v, z): Dijkstra manual con arreglos.
         * Idea: se mantiene un arreglo de distancias mínimas conocidas.
         * En cada paso se elige el vértice no visitado con menor distancia
         * y se "relajan" sus vecinos (si pasar por él es más corto, se actualiza).
         * Se guarda el predecesor de cada vértice para reconstruir el camino.
         */
        int[] shortPath(int v, int z) {
            int INF = Integer.MAX_VALUE;
            int[] dist = new int[numVertices];    // distancia mínima desde v
            int[] prev = new int[numVertices];    // predecesor en el camino
            boolean[] visitado = new boolean[numVertices];

            // Inicializar: todas las distancias infinitas, sin predecesores
            for (int i = 0; i < numVertices; i++) {
                dist[i] = INF;
                prev[i] = -1;
            }
            dist[v] = 0; // la distancia al origen es 0

            for (int iter = 0; iter < numVertices; iter++) {
                // Buscar el vértice no visitado con menor distancia conocida
                int u = -1;
                for (int i = 0; i < numVertices; i++) {
                    if (!visitado[i] && (u == -1 || dist[i] < dist[u]))
                        u = i;
                }
                if (u == -1 || dist[u] == INF) break; // todos alcanzados o sin camino
                visitado[u] = true;

                // Relajar vecinos de u: si dist[u] + peso < dist[vecino], actualizar
                Nodo actual = adyacencia[u];
                while (actual != null) {
                    int nuevaDist = dist[u] + actual.peso;
                    if (nuevaDist < dist[actual.destino]) {
                        dist[actual.destino] = nuevaDist;
                        prev[actual.destino] = u;
                    }
                    actual = actual.siguiente;
                }
            }

            // Reconstruir camino desde z hacia v siguiendo predecesores
            if (dist[z] == INF) return new int[0]; // sin camino

            // Contar longitud del camino
            int longitud = 0;
            for (int cur = z; cur != -1; cur = prev[cur]) longitud++;

            // Llenar el arreglo de atrás hacia adelante
            int[] camino = new int[longitud];
            int idx = longitud - 1;
            for (int cur = z; cur != -1; cur = prev[cur])
                camino[idx--] = cur;

            return camino;
        }

        /*
         * isConexo(): BFS manual con arreglo como cola.
         * Se parte del vértice 0, se visitan todos los vecinos y sus vecinos.
         * Si al final se visitaron todos los vértices → el grafo es conexo.
         */
        boolean isConexo() {
            boolean[] visitado = new boolean[numVertices];
            int[] cola = new int[numVertices]; // arreglo usado como cola
            int frente = 0, fin = 0;

            // Empezar BFS desde el vértice 0
            cola[fin++] = 0;
            visitado[0] = true;
            int conteo = 1;

            while (frente < fin) {
                int u = cola[frente++];
                Nodo actual = adyacencia[u];
                while (actual != null) {
                    if (!visitado[actual.destino]) {
                        visitado[actual.destino] = true;
                        cola[fin++] = actual.destino;
                        conteo++;
                    }
                    actual = actual.siguiente;
                }
            }
            return conteo == numVertices; // conexo si se visitaron todos
        }

        /*
         * Dijkstra(v, w): misma lógica que shortPath pero retorna un Stack propio.
         * El camino se apila en orden inverso, así al hacer pop() se obtiene v primero.
         */
        Stack Dijkstra(int v, int w) {
            int[] camino = shortPath(v, w);
            Stack stack = new Stack(numVertices);

            // Apilar en orden inverso para que pop() devuelva v → ... → w
            for (int i = camino.length - 1; i >= 0; i--)
                stack.push(camino[i]);

            return stack;
        }
    }

    // ── Main: pruebas ──
    public static void main(String[] args) {
        GraphLink g = new GraphLink(5);

        g.insertEdgeWeight(0, 1, 4);
        g.insertEdgeWeight(0, 2, 1);
        g.insertEdgeWeight(2, 1, 2);
        g.insertEdgeWeight(1, 3, 1);
        g.insertEdgeWeight(2, 3, 5);
        g.insertEdgeWeight(3, 4, 3);

        // shortPath retorna arreglo
        int[] ruta = g.shortPath(0, 4);
        System.out.print("shortPath (0->4): ");
        for (int v : ruta) System.out.print(v + " ");
        System.out.println();

        System.out.println("¿Es conexo? " + g.isConexo());

        // Dijkstra retorna Stack propio
        Stack stack = g.Dijkstra(0, 4);
        System.out.print("Dijkstra Stack (0->4): ");
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
        System.out.println();

        // Grafo desconectado
        GraphLink desconectado = new GraphLink(4);
        desconectado.insertEdgeWeight(0, 1, 2);
        desconectado.insertEdgeWeight(2, 3, 3);
        System.out.println("¿Desconectado es conexo? " + desconectado.isConexo());
    }
}
