/*
 * EJERCICIO 5: Factor de carga y redimensionamiento de tabla hash
 *
 * Factor de carga: alpha = n / M  (n = elementos insertados, M = tamano de la tabla)
 * Se recomienda mantener alpha <= 0.75 para conservar el rendimiento O(1) promedio.
 *
 * Tabla hash cerrada (sondeo lineal), tamano inicial 7.
 * Valores a insertar: 2, 9, 16, 23, 4, 11
 * Cuando alpha supere 0.75, se hace rehashing a una tabla de tamano 17.
 */
public class EJ5 {

    Integer[] tabla;     // usamos Integer para poder representar "vacio" con null
    int tamano;          // M
    int cantidad;        // n

    public EJ5(int tamanoInicial) {
        this.tamano = tamanoInicial;
        this.tabla = new Integer[tamano];
        this.cantidad = 0;
    }

    int funcionHash(int x) {
        return x % tamano;
    }

    // Inserta un valor usando sondeo lineal y luego revisa el factor de carga
    void insertar(int valor) {
        int base = funcionHash(valor);
        for (int i = 0; i < tamano; i++) {
            int indice = (base + i) % tamano;
            if (tabla[indice] == null) {
                tabla[indice] = valor;
                cantidad++;
                System.out.println("Insertado " + valor + " en el indice " + indice
                        + " (tabla de tamano " + tamano + ")");
                break;
            }
        }

        double factorCarga = (double) cantidad / tamano;
        System.out.printf("   Factor de carga actual: %d/%d = %.2f%n", cantidad, tamano, factorCarga);

        if (factorCarga > 0.75) {
            rehashing();
        }
    }

    // Crea una nueva tabla de tamano 17 (el siguiente primo) y reinserta todos los elementos
    void rehashing() {
        System.out.println("\n>>> Factor de carga supero 0.75: se realiza REHASHING a tamano 17 <<<");
        System.out.println("Tabla ANTES del rehashing:");
        imprimirTabla();

        Integer[] tablaVieja = tabla;
        tamano = 17;             // siguiente numero primo
        tabla = new Integer[tamano];
        cantidad = 0;

        // Reinsertamos todos los elementos existentes en la nueva tabla
        for (Integer valor : tablaVieja) {
            if (valor != null) {
                int base = funcionHash(valor);
                for (int i = 0; i < tamano; i++) {
                    int indice = (base + i) % tamano;
                    if (tabla[indice] == null) {
                        tabla[indice] = valor;
                        cantidad++;
                        break;
                    }
                }
            }
        }

        System.out.println("Tabla DESPUES del rehashing:");
        imprimirTabla();
        System.out.println();
    }

    void imprimirTabla() {
        for (int i = 0; i < tamano; i++) {
            System.out.println("  Indice " + i + ": " + (tabla[i] == null ? "[vacio]" : tabla[i]));
        }
    }

    public static void main(String[] args) {
        EJ5 ht = new EJ5(7);

        int[] valores = {2, 9, 16, 23, 4, 11};
        for (int valor : valores) {
            ht.insertar(valor);
        }

        System.out.println("\n¿Por que cambian las posiciones de los elementos tras el rehashing?");
        System.out.println("La funcion hash depende del tamano de la tabla (x % M). Al cambiar M");
        System.out.println("de 7 a 17, el resultado de x % M cambia para la mayoria de las claves,");
        System.out.println("por lo que cada elemento debe recalcular su direccion hash y puede");
        System.out.println("terminar en una posicion distinta a la que tenia originalmente.");
    }
}
