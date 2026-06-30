/*
 * EJERCICIO 2: Comparacion de metodos de sondeo en hash cerrado
 *
 * Tabla hash de tamano 7, funcion h(x) = x % 7.
 * Se insertan los valores 10, 17, 24, 31, 4 usando primero
 * sondeo lineal y luego sondeo cuadratico (f(i) = i^2).
 *
 * Direcciones hash iniciales (antes de aplicar sondeo):
 *   h(10) = 10 % 7 = 3
 *   h(17) = 17 % 7 = 3
 *   h(24) = 24 % 7 = 3
 *   h(31) = 31 % 7 = 3
 *   h(4)  = 4  % 7 = 4
 * Todos (menos el 4) chocan en el indice 3, por lo que es un buen
 * ejemplo para comparar cuantos saltos hace cada metodo de sondeo.
 */
public class EJ2 {

    static final int TAMANO = 7;

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("===== SONDEO LINEAL =====");
        int[] tablaLineal = new int[TAMANO];
        for (int i = 0; i < TAMANO; i++) tablaLineal[i] = -1;
        for (int valor : valores) {
            insertarLineal(tablaLineal, valor);
            imprimirTabla(tablaLineal);
        }

        System.out.println("\n===== SONDEO CUADRATICO =====");
        int[] tablaCuadratica = new int[TAMANO];
        for (int i = 0; i < TAMANO; i++) tablaCuadratica[i] = -1;
        for (int valor : valores) {
            insertarCuadratico(tablaCuadratica, valor);
            imprimirTabla(tablaCuadratica);
        }

        System.out.println("\n¿Cual metodo produce menos saltos ante colisiones consecutivas?");
        System.out.println("El sondeo lineal explora posiciones consecutivas (3,4,5,6,...),");
        System.out.println("por lo que cuando hay varias claves que chocan en el mismo indice");
        System.out.println("tiende a generar 'clustering' (agrupamiento): cada nueva insercion");
        System.out.println("debe recorrer cada vez mas posiciones ya ocupadas por inserciones");
        System.out.println("anteriores. El sondeo cuadratico separa mas rapido las posiciones");
        System.out.println("probadas (saltos de 1, 4, 9, ...), por lo que en este ejemplo, con");
        System.out.println("varias colisiones seguidas sobre el mismo indice, suele alcanzar una");
        System.out.println("posicion libre explorando menos celdas ya usadas que el sondeo lineal.");
    }

    // h(x) = x % 7
    static int funcionHash(int x) {
        return x % TAMANO;
    }

    // Insercion con sondeo lineal: probamos (h(x) + i) % TAMANO
    static void insertarLineal(int[] tabla, int valor) {
        int base = funcionHash(valor);
        int intentos = 0;
        for (int i = 0; i < TAMANO; i++) {
            int indice = (base + i) % TAMANO;
            intentos++;
            if (tabla[indice] == -1) {
                tabla[indice] = valor;
                System.out.println("Lineal: insertar " + valor + " en indice " + indice
                        + " (posiciones exploradas: " + intentos + ")");
                return;
            }
        }
        System.out.println("Lineal: tabla llena, no se pudo insertar " + valor);
    }

    // Insercion con sondeo cuadratico: probamos (h(x) + i*i) % TAMANO
    static void insertarCuadratico(int[] tabla, int valor) {
        int base = funcionHash(valor);
        int intentos = 0;
        for (int i = 0; i < TAMANO; i++) {
            int indice = (base + i * i) % TAMANO;
            intentos++;
            if (tabla[indice] == -1) {
                tabla[indice] = valor;
                System.out.println("Cuadratico: insertar " + valor + " en indice " + indice
                        + " (posiciones exploradas: " + intentos + ")");
                return;
            }
        }
        System.out.println("Cuadratico: no se encontro posicion libre para " + valor
                + " (puede pasar si i*i no cubre toda la tabla)");
    }

    static void imprimirTabla(int[] tabla) {
        StringBuilder sb = new StringBuilder("   Estado: [");
        for (int i = 0; i < TAMANO; i++) {
            sb.append(tabla[i] == -1 ? "_" : tabla[i]);
            if (i < TAMANO - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
