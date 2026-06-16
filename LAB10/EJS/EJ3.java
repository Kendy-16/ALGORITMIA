/*
 * EJ3.java
 * Extiende EJ2 definiendo un TAD Graph (interfaz) e implementándolo con GraphLink.
 * Todo con arreglos y nodos propios, sin librerías.
 *
 * Estructura:
 *   - Interfaz Graph          : operaciones básicas del grafo
 *   - Clase GraphLink         : implementa Graph con lista de adyacencia propia
 *   - Clase EJ3               : programa principal con la red de ciudades
 */
public class EJ3 {

    // ── Nodo de lista enlazada propia ──
    static class Nodo {
        int destino;
        int peso;
        Nodo siguiente;

        Nodo(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
            this.siguiente = null;
        }
    }

    // ── TAD Graph: define las operaciones básicas que debe tener cualquier grafo ──
    interface Graph {
        void insertVertex(String nombre);
        void insertEdge(String origen, String destino, int peso);
        void removeVertex(String nombre);
        void removeEdge(String origen, String destino);
        boolean searchVertex(String nombre);
        boolean searchEdge(String origen, String destino);
        String[] adjacentVertices(String nombre); // retorna arreglo de nombres vecinos
    }

    // ── GraphLink: implementa Graph usando lista de adyacencia con nodos propios ──
    static class GraphLink implements Graph {
        int maxV;
        int totalV;
        String[] nombres;   // nombres de los vértices
        Nodo[] adj;         // adj[i] = lista enlazada de vecinos del vértice i

        GraphLink(int maxV) {
            this.maxV = maxV;
            this.totalV = 0;
            nombres = new String[maxV];
            adj = new Nodo[maxV];
        }

        // Busca el índice de un vértice por nombre
        int indexOf(String nombre) {
            for (int i = 0; i < totalV; i++)
                if (nombres[i].equals(nombre)) return i;
            return -1;
        }

        // Agrega un vértice nuevo si no existe
        @Override
        public void insertVertex(String nombre) {
            if (indexOf(nombre) == -1 && totalV < maxV) {
                nombres[totalV] = nombre;
                adj[totalV] = null;
                totalV++;
            }
        }

        // Agrega arista no dirigida con peso entre dos vértices (por nombre)
        @Override
        public void insertEdge(String origen, String destino, int peso) {
            insertVertex(origen);
            insertVertex(destino);
            int o = indexOf(origen);
            int d = indexOf(destino);

            // Agregar d a la lista de o
            Nodo n1 = new Nodo(d, peso);
            n1.siguiente = adj[o];
            adj[o] = n1;

            // Agregar o a la lista de d (no dirigido)
            Nodo n2 = new Nodo(o, peso);
            n2.siguiente = adj[d];
            adj[d] = n2;
        }

        /*
         * removeVertex: eliminar vértice y reasignar índices.
         * Se reemplaza el vértice eliminado con el último y se actualizan
         * todos los índices en las listas de adyacencia.
         */
        @Override
        public void removeVertex(String nombre) {
            int idx = indexOf(nombre);
            if (idx == -1) return;

            // Eliminar todas las aristas que apunten a idx
            for (int i = 0; i < totalV; i++) {
                if (i == idx) continue;
                Nodo prev = null, cur = adj[i];
                while (cur != null) {
                    if (cur.destino == idx) {
                        if (prev == null) adj[i] = cur.siguiente;
                        else prev.siguiente = cur.siguiente;
                    } else {
                        prev = cur;
                    }
                    cur = cur.siguiente;
                }
            }

            // Reemplazar idx con el último vértice para no dejar huecos
            int ultimo = totalV - 1;
            if (idx != ultimo) {
                nombres[idx] = nombres[ultimo];
                adj[idx] = adj[ultimo];
                // Actualizar referencias al último (que ahora es idx)
                for (int i = 0; i < totalV - 1; i++) {
                    Nodo cur = adj[i];
                    while (cur != null) {
                        if (cur.destino == ultimo) cur.destino = idx;
                        cur = cur.siguiente;
                    }
                }
            }
            totalV--;
        }

        // Elimina la arista entre origen y destino (ambas direcciones)
        @Override
        public void removeEdge(String origen, String destino) {
            int o = indexOf(origen);
            int d = indexOf(destino);
            if (o == -1 || d == -1) return;

            // Eliminar d de la lista de o
            adj[o] = eliminarDeListaEn(adj[o], d);
            // Eliminar o de la lista de d
            adj[d] = eliminarDeListaEn(adj[d], o);
        }

        // Auxiliar: recorre la lista y elimina el nodo con destino == target
        Nodo eliminarDeListaEn(Nodo cabeza, int target) {
            Nodo prev = null, cur = cabeza;
            while (cur != null) {
                if (cur.destino == target) {
                    if (prev == null) cabeza = cur.siguiente;
                    else prev.siguiente = cur.siguiente;
                    return cabeza;
                }
                prev = cur;
                cur = cur.siguiente;
            }
            return cabeza;
        }

        // Retorna true si el vértice existe
        @Override
        public boolean searchVertex(String nombre) {
            return indexOf(nombre) != -1;
        }

        // Retorna true si existe arista entre origen y destino
        @Override
        public boolean searchEdge(String origen, String destino) {
            int o = indexOf(origen);
            int d = indexOf(destino);
            if (o == -1 || d == -1) return false;
            Nodo cur = adj[o];
            while (cur != null) {
                if (cur.destino == d) return true;
                cur = cur.siguiente;
            }
            return false;
        }

        // Retorna arreglo con los nombres de los vértices adyacentes
        @Override
        public String[] adjacentVertices(String nombre) {
            int idx = indexOf(nombre);
            if (idx == -1) return new String[0];

            // Contar vecinos primero
            int count = 0;
            Nodo cur = adj[idx];
            while (cur != null) { count++; cur = cur.siguiente; }

            // Llenar arreglo de nombres
            String[] result = new String[count];
            cur = adj[idx];
            for (int i = 0; i < count; i++) {
                result[i] = nombres[cur.destino];
                cur = cur.siguiente;
            }
            return result;
        }

        /*
         * Dijkstra por nombre: igual que EJ1/EJ2, pero usando índices internos.
         * Retorna el camino como arreglo de nombres de ciudades.
         */
        String[] dijkstra(String origen, String destino) {
            int o = indexOf(origen);
            int d = indexOf(destino);
            if (o == -1 || d == -1) return new String[0];

            int INF = Integer.MAX_VALUE;
            int[] dist = new int[totalV];
            int[] prev = new int[totalV];
            boolean[] visitado = new boolean[totalV];

            for (int i = 0; i < totalV; i++) { dist[i] = INF; prev[i] = -1; }
            dist[o] = 0;

            for (int iter = 0; iter < totalV; iter++) {
                int u = -1;
                for (int i = 0; i < totalV; i++)
                    if (!visitado[i] && (u == -1 || dist[i] < dist[u])) u = i;
                if (u == -1 || dist[u] == INF) break;
                visitado[u] = true;

                Nodo cur = adj[u];
                while (cur != null) {
                    int nueva = dist[u] + cur.peso;
                    if (nueva < dist[cur.destino]) {
                        dist[cur.destino] = nueva;
                        prev[cur.destino] = u;
                    }
                    cur = cur.siguiente;
                }
            }

            if (dist[d] == INF) return new String[0];

            int pasos = 0;
            for (int cur = d; cur != -1; cur = prev[cur]) pasos++;
            String[] camino = new String[pasos];
            int idx = pasos - 1;
            for (int cur = d; cur != -1; cur = prev[cur])
                camino[idx--] = nombres[cur];

            return camino;
        }

        // Muestra la lista de adyacencia del grafo
        void print() {
            for (int i = 0; i < totalV; i++) {
                System.out.print("  " + nombres[i] + " -> ");
                Nodo cur = adj[i];
                while (cur != null) {
                    System.out.print("[" + nombres[cur.destino] + " " + cur.peso + "km] ");
                    cur = cur.siguiente;
                }
                System.out.println();
            }
        }
    }

    // ── Main ──
    public static void main(String[] args) {
        GraphLink g = new GraphLink(10);

        // Insertar ciudades
        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Puno");
        g.insertVertex("Tacna");
        g.insertVertex("Moquegua");

        // Insertar carreteras
        g.insertEdge("Arequipa", "Cusco", 510);
        g.insertEdge("Arequipa", "Moquegua", 230);
        g.insertEdge("Moquegua", "Tacna", 160);
        g.insertEdge("Cusco", "Puno", 390);
        g.insertEdge("Puno", "Tacna", 420);

        System.out.println("=== Lista de adyacencia ===");
        g.print();

        System.out.println("\n¿Existe 'Cusco'?             " + g.searchVertex("Cusco"));
        System.out.println("¿Existe 'Lima'?              " + g.searchVertex("Lima"));
        System.out.println("¿Arista Puno-Tacna?          " + g.searchEdge("Puno", "Tacna"));
        System.out.println("¿Arista Arequipa-Puno?       " + g.searchEdge("Arequipa", "Puno"));

        System.out.print("\nVecinos de Arequipa: ");
        for (String v : g.adjacentVertices("Arequipa")) System.out.print(v + " ");
        System.out.println();

        // Eliminar arista y mostrar resultado
        g.removeEdge("Puno", "Tacna");
        System.out.println("\nTras eliminar Puno-Tacna:");
        System.out.println("¿Arista Puno-Tacna?  " + g.searchEdge("Puno", "Tacna"));

        // Dijkstra
        System.out.println("\n=== Dijkstra: Cusco -> Tacna ===");
        String[] ruta = g.dijkstra("Cusco", "Tacna");
        if (ruta.length == 0) System.out.println("Sin camino.");
        else { for (String c : ruta) System.out.print(c + " "); System.out.println(); }

        // Eliminar vértice y probar
        g.removeVertex("Moquegua");
        System.out.println("\nTras eliminar Moquegua:");
        System.out.println("¿Existe 'Moquegua'?  " + g.searchVertex("Moquegua"));
        System.out.println("Vecinos de Arequipa: ");
        for (String v : g.adjacentVertices("Arequipa")) System.out.print(v + " ");
        System.out.println();
    }
}
