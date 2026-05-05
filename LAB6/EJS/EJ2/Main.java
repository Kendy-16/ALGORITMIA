// ColaArreglo.java
public class ColaArreglo <> {
    private int[]datos;
    private int frente, f in, tamaño, capacidad;

    public ColaArreglo(int capacidad) {
        this.capacidad = capacidad;
        datos = new int[capacidad];
        frente = fin = tamaño = 0;
    }

    public void encolar(int cliente) {
        if (tamaño == capacidad) { System.out.println("Cola llena"); return; }
        datos[fin] = cliente;
        fin = (fin + 1) % capacidad;
        tamaño++;
    }

    public void desencolar() {
        if (tamaño == 0) { System.out.println("Cola vacía"); return; }
        System.out.println("Atendiendo cliente: " + datos[frente]);
        frente = (frente + 1) % capacidad;
        tamaño--;
    }

    public void verFrente() {
        if (tamaño == 0) { System.out.println("Cola vacía"); return; }
        System.out.println("Cliente en frente: " + datos[frente]);
    }

    public boolean estaVacia() { return tamaño == 0; }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        ColaArreglo cola = new ColaArreglo(5);

        cola.encolar(101);
        cola.encolar(102);
        cola.encolar(103);
        cola.encolar(104);
        cola.encolar(105);
        cola.encolar(106);       // Cola llena

        cola.desencolar();       // 101
        cola.desencolar();       // 102

        cola.verFrente();        // 103

        cola.encolar(106);
        cola.encolar(107);       // comportamiento circular

        while (!cola.estaVacia()) cola.desencolar();

        cola.desencolar();       // Cola vacía
    }
}