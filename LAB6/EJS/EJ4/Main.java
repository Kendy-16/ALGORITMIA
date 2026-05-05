// package actividadEjercicio4;

class Nodo<T> {
    T dato;
    int valorSecundario;
    Nodo<T> next;
    Nodo(T dato, int v) { this.dato = dato; this.valorSecundario = v; }
    }
class ColaOrdenada<T> {
    private Nodo<T> cabeza;

    // Inserta ordenado por secondaryValue (menor = primero)
    public void insertar(T dato, int v) {
        Nodo<T> nuevo = new Nodo<>(dato, v);
        if (cabeza == null || v < cabeza.valorSecundario) {
            nuevo.next = cabeza;
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.next != null && actual.next.valorSecundario <= v)
                actual = actual.next;
            nuevo.next = actual.next;
            actual.next = nuevo;
        }
    }

    public T desencolar() {
        if (cabeza == null) return null;
        T dato = cabeza.dato;
        cabeza = cabeza.next;
        return dato;
    }

    public boolean estaVacia() { return cabeza == null; }

    public void mostrar() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print("(" + actual.dato + "," + actual.valorSecundario + ")");
            if (actual.next != null) System.out.print(" -> ");
            actual = actual.next;
        }
        System.out.println();
    }
}

class PriorityQueueHybrid<T> {
    private ColaOrdenada<T>[] niveles;
    private int N;

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int N) {
        this.N = N;
        niveles = new ColaOrdenada[N];
        for (int i = 0; i < N; i++) niveles[i] = new ColaOrdenada<>();
    }

    public void encolar(T dato, int prioridad, int valorSecundario) {
        niveles[prioridad].insertar(dato, valorSecundario);
    }

    public T desencolar() {
        for (int i = N - 1; i >= 0; i--)
            if (!niveles[i].estaVacia()) return niveles[i].desencolar();
        return null;
    }

    public void mostrarEstado() {
        for (int i = N - 1; i >= 0; i--) {
            System.out.print("Nivel " + i + ": ");
            if (niveles[i].estaVacia()) System.out.println("vacío");
            else niveles[i].mostrar();
        }
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        PriorityQueueHybrid<String> pq = new PriorityQueueHybrid<>(3);
        pq.encolar("A", 2, 5);
        pq.encolar("B", 2, 1);
        pq.encolar("C", 1, 3);
        pq.encolar("D", 2, 3);

        System.out.println("Resultado interno:");
        pq.mostrarEstado();
        // Nivel 2: (B,1) -> (D,3) -> (A,5)
        // Nivel 1: (C,3)
        // Nivel 0: vacío

        System.out.print("\nSalida (dequeue): ");
        for (int i = 0; i < 4; i++) System.out.print(pq.desencolar() + " ");
        // B D A C
    }
}