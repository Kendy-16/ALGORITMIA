// package actividadPrioridadColas;

class Nodo<T> {
    T dato;
    Nodo<T> siguiente;
    Nodo(T dato) { this.dato = dato; }
}

class ColaEnlazada<T> {
    private Nodo<T> frente, fin;

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin != null) fin.siguiente = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
    }

    public T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return dato;
    }

    public boolean estaVacia() { return frente == null; }
}

class ColaPrioridad<T> {
    private ColaEnlazada<T>[] colas;
    private int niveles;

    @SuppressWarnings("unchecked")
    public ColaPrioridad(int niveles) {
        this.niveles = niveles;
        colas = new ColaEnlazada[niveles];
        for (int i = 0; i < niveles; i++) colas[i] = new ColaEnlazada<>();
    }

    public void encolar(T dato, int prioridad) { colas[prioridad].encolar(dato); }

    public T desencolar() {
        for (int i = niveles - 1; i >= 0; i--)
            if (!colas[i].estaVacia()) return colas[i].desencolar();
        return null;
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        ColaPrioridad<String> cp = new ColaPrioridad<>(3);
        cp.encolar("A", 0);
        cp.encolar("B", 2);
        cp.encolar("C", 1);
        cp.encolar("D", 2);

        System.out.print("Orden de salida: ");
        for (int i = 0; i < 4; i++) System.out.print(cp.desencolar() + " ");
        // B D C A
    }
}