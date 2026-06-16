/*
 * EJ4.java
 * Implementa TAD Graph, GraphLink y GraphListEdge con arreglos propios.
 * Analiza propiedades de grafos dirigidos:
 *
 *   - isIsomorfo(otro)        : misma secuencia de grados → misma estructura
 *   - isPlanar()              : condición de Euler: |E| <= 3|V| - 6
 *   - isConexo()              : BFS ignorando dirección, todos conectados
 *   - isAutoComplementario()  : complemento del grafo es isomorfo al original
 */
public class EJ4 {

    // ── Nodo de lista enlazada (vecino en la lista de adyacencia) ──
    static class Nodo {
        int destino;
        Nodo siguiente;

        Nodo(int destino) {
            this.destino = destino;
            this.siguiente = null;
        }
    }

    // ── Registro de una arista en la lista global de aristas ──
    static class Arista {
        int desde, hasta;

        Arista(int desde, int hasta) {
            this.desde = desde;
            this.hasta = hasta;
        }
    }

    // ── TAD Graph: interfaz con operaciones básicas ──
    interface Graph {
        void insertVertex(int v);
        void insertEdge(int desde, int hasta);
        void removeVertex(int v);
        void removeEdge(int desde, int hasta);
        boolean searchVertex(int v);
        boolean searchEdge(int desde, int hasta);
        int[] adjacentVertices(int v); // retorna arreglo de vértices adyacentes
    }

    // ── GraphLink: lista de adyacencia propia (base para GraphListEdge) ──
    static class GraphLink implements Graph {
        int maxV;
        int totalV;
        int[] vertices;      // IDs de los vértices
        Nodo[] adj;          // lista de adyacencia dirigida

        GraphLink(int maxV) {
            this.maxV = maxV;
            this.totalV = 0;
            vertices = new int[maxV];
            adj = new Nodo[maxV];
        }

        // Busca posición interna del vértice v
        int indexOf(int v) {
            for (int i = 0; i < totalV; i++)
                if (vertices[i] == v) return i;
            return -1;
        }

        @Override
        public void insertVertex(int v) {
            if (indexOf(v) == -1 && totalV < maxV) {
                vertices[totalV] = v;
                adj[totalV] = null;
                totalV++;
            }
        }

        // Arista dirigida: solo desde → hasta
        @Override
        public void insertEdge(int desde, int hasta) {
            insertVertex(desde);
            insertVertex(hasta);
            int d = indexOf(desde);
            int h = indexOf(hasta);
            // Solo agrega si no existe ya
            if (!searchEdge(desde, hasta)) {
                Nodo n = new Nodo(h);
                n.siguiente = adj[d];
                adj[d] = n;
            }
        }

        @Override
        public void removeVertex(int v) {
            int idx = indexOf(v);
            if (idx == -1) return;

            // Eliminar referencias a idx en otras listas
            for (int i = 0; i < totalV; i++) {
                adj[i] = quitarNodo(adj[i], idx);
            }
            // Reemplazar con el último
            int ultimo = totalV - 1;
            if (idx != ultimo) {
                vertices[idx] = vertices[ultimo];
                adj[idx] = adj[ultimo];
                // Actualizar índice del último en las listas
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

        // Auxiliar: elimina el primer nodo con destino == target de una lista
        Nodo quitarNodo(Nodo cabeza, int target) {
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

        @Override
        public void removeEdge(int desde, int hasta) {
            int d = indexOf(desde), h = indexOf(hasta);
            if (d == -1 || h == -1) return;
            adj[d] = quitarNodo(adj[d], h);
        }

        @Override
        public boolean searchVertex(int v) {
            return indexOf(v) != -1;
        }

        @Override
        public boolean searchEdge(int desde, int hasta) {
            int d = indexOf(desde), h = indexOf(hasta);
            if (d == -1 || h == -1) return false;
            Nodo cur = adj[d];
            while (cur != null) {
                if (cur.destino == h) return true;
                cur = cur.siguiente;
            }
            return false;
        }

        @Override
        public int[] adjacentVertices(int v) {
            int idx = indexOf(v);
            if (idx == -1) return new int[0];
            int count = 0;
            Nodo cur = adj[idx];
            while (cur != null) { count++; cur = cur.siguiente; }
            int[] result = new int[count];
            cur = adj[idx];
            for (int i = 0; i < count; i++) {
                result[i] = vertices[cur.destino];
                cur = cur.siguiente;
            }
            return result;
        }

        // Grado de salida de la posición interna i
        int outDegreeAt(int i) {
            int count = 0;
            Nodo cur = adj[i];
            while (cur != null) { count++; cur = cur.siguiente; }
            return count;
        }

        // Grado de entrada de la posición interna i
        int inDegreeAt(int i) {
            int count = 0;
            for (int k = 0; k < totalV; k++) {
                Nodo cur = adj[k];
                while (cur != null) {
                    if (cur.destino == i) count++;
                    cur = cur.siguiente;
                }
            }
            return count;
        }

        // Total de aristas del grafo
        int edgeCount() {
            int count = 0;
            for (int i = 0; i < totalV; i++) count += outDegreeAt(i);
            return count;
        }
    }

    // ── GraphListEdge: extiende GraphLink guardando lista global de aristas ──
    // y agrega los 4 métodos de análisis de propiedades
    static class GraphListEdge extends GraphLink {
        Arista[] aristas;    // lista global de todas las aristas
        int totalAristas;

        GraphListEdge(int maxV) {
            super(maxV);
            aristas = new Arista[maxV * maxV]; // máximo posible
            totalAristas = 0;
        }

        // Sobreescribe insertEdge para también guardar en aristas[]
        @Override
        public void insertEdge(int desde, int hasta) {
            super.insertEdge(desde, hasta);
            aristas[totalAristas++] = new Arista(indexOf(desde), indexOf(hasta));
        }

        // Sobreescribe removeEdge para también eliminarlo de aristas[]
        @Override
        public void removeEdge(int desde, int hasta) {
            int d = indexOf(desde), h = indexOf(hasta);
            super.removeEdge(desde, hasta);
            // Quitar de aristas[] compactando el arreglo
            for (int i = 0; i < totalAristas; i++) {
                if (aristas[i].desde == d && aristas[i].hasta == h) {
                    aristas[i] = aristas[--totalAristas];
                    break;
                }
            }
        }

        /*
         * isIsomorfo: condición necesaria de isomorfismo.
         * Dos grafos son potencialmente isomorfos si tienen la misma
         * cantidad de vértices, aristas y la misma secuencia de grados
         * (entrada + salida) ordenada de mayor a menor.
         */
        boolean isIsomorfo(GraphListEdge otro) {
            if (totalV != otro.totalV) return false;
            if (edgeCount() != otro.edgeCount()) return false;

            // Construir secuencias de grados y ordenarlas (burbuja)
            int[] seq1 = getSecuenciaGrados();
            int[] seq2 = otro.getSecuenciaGrados();
            ordenar(seq1);
            ordenar(seq2);

            for (int i = 0; i < totalV; i++)
                if (seq1[i] != seq2[i]) return false;
            return true;
        }

        // Retorna arreglo con grado total (entrada + salida) de cada vértice
        int[] getSecuenciaGrados() {
            int[] seq = new int[totalV];
            for (int i = 0; i < totalV; i++)
                seq[i] = inDegreeAt(i) + outDegreeAt(i);
            return seq;
        }

        // Ordenamiento burbuja descendente
        void ordenar(int[] arr) {
            for (int i = 0; i < arr.length - 1; i++)
                for (int j = 0; j < arr.length - i - 1; j++)
                    if (arr[j] < arr[j + 1]) {
                        int tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    }
        }

        /*
         * isPlanar: condición necesaria de Euler para planaridad.
         * Un grafo simple es planar si |E| <= 3*|V| - 6 (para |V| >= 3).
         * Si viola esta condición, definitivamente NO es planar.
         * Si la cumple, puede o no ser planar (condición necesaria, no suficiente).
         */
        boolean isPlanar() {
            if (totalV < 3) return true;
            return edgeCount() <= 3 * totalV - 6;
        }

        /*
         * isConexo: BFS ignorando la dirección de las aristas (conexidad débil).
         * Se parte de cualquier vértice y se visitan todos los vecinos
         * (tanto en dirección directa como inversa). Si se visitan todos → conexo.
         */
        boolean isConexo() {
            if (totalV == 0) return true;
            boolean[] visitado = new boolean[totalV];
            int[] cola = new int[totalV]; // cola manual
            int frente = 0, fin = 0;

            cola[fin++] = 0;
            visitado[0] = true;
            int conteo = 1;

            while (frente < fin) {
                int u = cola[frente++];

                // Vecinos directos (aristas de salida)
                Nodo cur = adj[u];
                while (cur != null) {
                    if (!visitado[cur.destino]) {
                        visitado[cur.destino] = true;
                        cola[fin++] = cur.destino;
                        conteo++;
                    }
                    cur = cur.siguiente;
                }

                // Vecinos inversos (aristas de entrada ignorando dirección)
                for (int k = 0; k < totalV; k++) {
                    if (!visitado[k]) {
                        Nodo cur2 = adj[k];
                        while (cur2 != null) {
                            if (cur2.destino == u) {
                                visitado[k] = true;
                                cola[fin++] = k;
                                conteo++;
                                break;
                            }
                            cur2 = cur2.siguiente;
                        }
                    }
                }
            }
            return conteo == totalV;
        }

        /*
         * isAutoComplementario: construye el grafo complemento
         * (aristas donde el original NO las tiene) y verifica si
         * es isomorfo al original. Si lo es → autocomplementario.
         */
        boolean isAutoComplementario() {
            GraphListEdge complemento = getComplemento();
            return isIsomorfo(complemento);
        }

        // Construye el complemento: agrega aristas donde el original no tiene
        GraphListEdge getComplemento() {
            GraphListEdge comp = new GraphListEdge(maxV);
            for (int i = 0; i < totalV; i++)
                comp.insertVertex(vertices[i]);

            for (int i = 0; i < totalV; i++) {
                for (int j = 0; j < totalV; j++) {
                    if (i != j && !searchEdge(vertices[i], vertices[j])) {
                        comp.insertEdge(vertices[i], vertices[j]);
                    }
                }
            }
            return comp;
        }

        // Muestra el grafo
        void print() {
            System.out.print("  Vértices: ");
            for (int i = 0; i < totalV; i++) System.out.print(vertices[i] + " ");
            System.out.println();
            System.out.print("  Aristas:  ");
            for (int i = 0; i < totalAristas; i++)
                System.out.print("(" + vertices[aristas[i].desde] + "->" +
                        vertices[aristas[i].hasta] + ") ");
            System.out.println();
        }
    }

    // ── Main: pruebas ──
    public static void main(String[] args) {

        // G1: ciclo de 4 vértices
        GraphListEdge g1 = new GraphListEdge(10);
        g1.insertEdge(0, 1);
        g1.insertEdge(1, 2);
        g1.insertEdge(2, 3);
        g1.insertEdge(3, 0);

        System.out.println("=== G1 (ciclo 4 vértices) ===");
        g1.print();
        System.out.println("  ¿Es conexo?             " + g1.isConexo());
        System.out.println("  ¿Es plano?              " + g1.isPlanar());
        System.out.println("  ¿Es autocomplementario? " + g1.isAutoComplementario());

        // G2: ciclo de 4 vértices con etiquetas distintas (isomorfo a G1)
        GraphListEdge g2 = new GraphListEdge(10);
        g2.insertEdge(10, 11);
        g2.insertEdge(11, 12);
        g2.insertEdge(12, 13);
        g2.insertEdge(13, 10);

        System.out.println("\n=== G2 (ciclo 4 vértices, etiquetas distintas) ===");
        g2.print();
        System.out.println("  ¿G1 isomorfo a G2?     " + g1.isIsomorfo(g2));

        // G3: estrella (diferente estructura a G1)
        GraphListEdge g3 = new GraphListEdge(10);
        g3.insertEdge(0, 1);
        g3.insertEdge(0, 2);
        g3.insertEdge(0, 3);

        System.out.println("\n=== G3 (estrella) ===");
        g3.print();
        System.out.println("  ¿Es conexo?             " + g3.isConexo());
        System.out.println("  ¿Es plano?              " + g3.isPlanar());
        System.out.println("  ¿G1 isomorfo a G3?      " + g1.isIsomorfo(g3));
        System.out.println("  ¿Es autocomplementario? " + g3.isAutoComplementario());

        // G4: grafo desconectado
        GraphListEdge g4 = new GraphListEdge(10);
        g4.insertEdge(0, 1);
        g4.insertVertex(2); // vértice aislado
        g4.insertVertex(3);

        System.out.println("\n=== G4 (desconectado) ===");
        g4.print();
        System.out.println("  ¿Es conexo?             " + g4.isConexo());

        // P4: camino de 4 vértices (autocomplementario clásico)
        GraphListEdge p4 = new GraphListEdge(10);
        p4.insertEdge(0, 1);
        p4.insertEdge(1, 2);
        p4.insertEdge(2, 3);

        System.out.println("\n=== P4 (camino de 4 nodos) ===");
        p4.print();
        System.out.println("  ¿Es autocomplementario? " + p4.isAutoComplementario());

        // Prueba removeEdge y searchEdge
        System.out.println("\n=== Prueba removeEdge en G1 ===");
        System.out.println("  ¿Arista 1->2 existe?    " + g1.searchEdge(1, 2));
        g1.removeEdge(1, 2);
        System.out.println("  Tras removeEdge(1,2):");
        System.out.println("  ¿Arista 1->2 existe?    " + g1.searchEdge(1, 2));
        System.out.println("  ¿Es conexo ahora?       " + g1.isConexo());
    }
}
