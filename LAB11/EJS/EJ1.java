/*
 * EJERCICIO 1: Tabla hash sin colisiones - analisis de funcion hash
 *
 * Tabla hash de tamano 11 (numero primo), funcion h(x) = x % 11.
 * Se implementa como un arreglo de enteros inicializado en -1 (posicion vacia).
 *
 * Valores a insertar: 3, 14, 25, 36, 47, 58
 *
 * CALCULO MANUAL DE LAS DIRECCIONES HASH (antes de codificar):
 *   h(3)  = 3  % 11 = 3
 *   h(14) = 14 % 11 = 3   -> colisiona con el 3
 *   h(25) = 25 % 11 = 3   -> colisiona con el 3 y el 14
 *   h(36) = 36 % 11 = 3   -> colisiona con los anteriores
 *   h(47) = 47 % 11 = 3   -> colisiona con los anteriores
 *   h(58) = 58 % 11 = 3   -> colisiona con los anteriores
 *
 * Nota: los 6 valores difieren entre si por multiplos de 11 (14-3=11,
 * 25-14=11, etc.), por lo que TODOS caen en el mismo indice (3).
 * Como esta tabla es de direccionamiento abierto simple (sin manejo de
 * colisiones), cada nueva insercion sobre el indice 3 SOBRESCRIBE al
 * valor anterior. Al final solo queda el ultimo valor insertado (58)
 * en la posicion 3, y el resto de la tabla queda vacia.
 */
public class EJ1 {

    static final int TAMANO = 11;

    public static void main(String[] args) {
        int[] tabla = new int[TAMANO];

        // Inicializamos toda la tabla en -1 (posicion vacia)
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = -1;
        }

        int[] valores = {3, 14, 25, 36, 47, 58};

        // Insertamos cada valor calculando su direccion hash
        for (int valor : valores) {
            int direccion = funcionHash(valor);
            if (tabla[direccion] != -1) {
                System.out.println("Colision: el valor " + valor + " quiere ir al indice "
                        + direccion + ", pero ya esta ocupado por " + tabla[direccion]
                        + ". Se sobrescribe (esta tabla no maneja colisiones).");
            } else {
                System.out.println("Insertando " + valor + " en el indice " + direccion);
            }
            tabla[direccion] = valor; // direccionamiento directo, sin sondeo
        }

        // Mostramos la tabla final
        System.out.println("\nTabla hash final:");
        for (int i = 0; i < TAMANO; i++) {
            if (tabla[i] == -1) {
                System.out.println("Indice " + i + " -> [vacio]");
            } else {
                System.out.println("Indice " + i + " -> " + tabla[i]);
            }
        }

        // Respuesta a la pregunta 3 del enunciado
        System.out.println("\n¿Por que conviene que el tamano de la tabla sea primo?");
        System.out.println("Usar un numero primo como tamano ayuda a distribuir mejor las");
        System.out.println("claves cuando estas tienen patrones regulares (por ejemplo, multiplos");
        System.out.println("de un mismo numero), reduciendo la probabilidad de que muchas claves");
        System.out.println("caigan siempre en el mismo indice. Sin embargo, como se ve en este");
        System.out.println("ejemplo, si las claves difieren entre si por multiplos exactos del");
        System.out.println("tamano de la tabla (11), el ser primo NO evita la colision: todas");
        System.out.println("terminan en el mismo indice. Es decir, el numero primo reduce el");
        System.out.println("riesgo de colisiones en el caso general, pero no lo elimina si los");
        System.out.println("datos de entrada siguen un patron aritmetico relacionado con M.");
    }

    // Funcion hash: h(x) = x % 11
    static int funcionHash(int x) {
        return x % TAMANO;
    }
}
