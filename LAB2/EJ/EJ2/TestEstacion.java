package LAB2.EJ.EJ2;

import java.util.ArrayList;

// 1. La Interfaz: Define el "contrato" que los dispositivos deben cumplir
interface Cargable {
    double getConsumoVatios();
    int getNivelBateria();
    void cargar(int cantidad);
}

// 2. Clases de Entidad (Smartphone)
class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int nivelBateria; // Obligatorio por la interfaz

    public Smartphone(String modelo, double consumoVatios) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 100; // Asumimos que inicia cargado
    }

    // --- Métodos de la interfaz Cargable ---
    @Override
    public double getConsumoVatios() { return this.consumoVatios; }

    @Override
    public int getNivelBateria() { return this.nivelBateria; }

    @Override
    public void cargar(int cantidad) { this.nivelBateria += cantidad; }

    // El método equals es clave para que buscarDispositivo funcione bien
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Si es exactamente el mismo objeto en memoria
        if (obj == null || getClass() != obj.getClass()) return false; 
        
        Smartphone otro = (Smartphone) obj;
        // Comparamos los atributos clave para saber si "son el mismo teléfono"
        return Double.compare(otro.consumoVatios, consumoVatios) == 0 && modelo.equals(otro.modelo);
    }
}

// 2. Clases de Entidad (Laptop) - Muy similar a Smartphone
class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    private int nivelBateria;

    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 100;
    }

    @Override
    public double getConsumoVatios() { return this.consumoVatios; }

    @Override
    public int getNivelBateria() { return this.nivelBateria; }

    @Override
    public void cargar(int cantidad) { this.nivelBateria += cantidad; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Laptop otra = (Laptop) obj;
        return Double.compare(otra.consumoVatios, consumoVatios) == 0 && marca.equals(otra.marca);
    }
}

// 3. Clase Genérica Restringida (Bounded Type)
// <T extends Cargable> significa "T puede ser cualquier cosa, SIEMPRE Y CUANDO implemente Cargable"
class PowerStation<T extends Cargable> {
    private ArrayList<T> dispositivos;

    public PowerStation() {
        this.dispositivos = new ArrayList<>();
    }

    public void conectar(T dispositivo) {
        dispositivos.add(dispositivo);
    }

    public double calcularConsumoTotal() {
        double total = 0;
        // Gracias al "extends Cargable", Java sabe que es seguro llamar a getConsumoVatios()
        for (T dispositivo : dispositivos) {
            total += dispositivo.getConsumoVatios();
        }
        return total;
    }

    public int buscarDispositivo(T prototipo) {
        for (int i = 0; i < dispositivos.size(); i++) {
            // Aquí usamos el equals() que programamos en Smartphone/Laptop
            if (dispositivos.get(i).equals(prototipo)) {
                return i;
            }
        }
        return -1; // Retorna -1 si no lo encuentra en el contenedor
    }

    public void mostrarReporte() {
        System.out.println("Posición\tConsumo");
        System.out.println("------------------------");
        for (int i = 0; i < dispositivos.size(); i++) {
            System.out.println(i + "\t\t" + dispositivos.get(i).getConsumoVatios() + " W");
        }
    }
}

// 4. Método Principal (Clase de Prueba)
public class TestEstacion {
    public static void main(String[] args) {
        
        // Creamos la estación especificando que será para Smartphones
        PowerStation<Smartphone> zonaCelulares = new PowerStation<>();

        Smartphone s1 = new Smartphone("iPhone 15", 20.5);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0);

        zonaCelulares.conectar(s1);
        zonaCelulares.conectar(s2);

        System.out.println("Consumo Total: " + zonaCelulares.calcularConsumoTotal() + "W\n");
        
        zonaCelulares.mostrarReporte();

        // Probando la búsqueda
        System.out.println("\nBuscando dispositivo...");
        Smartphone prototipoBusqueda = new Smartphone("Galaxy S24", 25.0);
        int indice = zonaCelulares.buscarDispositivo(prototipoBusqueda);
        System.out.println("El Galaxy S24 está en el índice (posición): " + indice);
    }
}