package LAB2.EJ.EJ2;

import java.util.Arrays;

public class UtilidadesArreglo {

    // Se declara <T> antes del tipo de retorno para indicar que es un método genérico
    public static <T> void swap(T[] arreglo, int i, int j) {
        
        // 1. Verificar índices válidos dentro de los límites del arreglo
        if (arreglo == null || i < 0 || i >= arreglo.length || j < 0 || j >= arreglo.length) {
            System.out.println("Error: Índices fuera de rango o arreglo nulo.");
            return; // Detenemos la ejecución si los índices no son válidos
        }

        // 2. Intercambiar los elementos usando una variable temporal del tipo genérico T
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static void main(String[] args) {
        // Prueba 1: Arreglo de String
        String[] letras = {"A", "B", "C", "D"};
        System.out.println("String original: " + Arrays.toString(letras));
        swap(letras, 1, 3);
        System.out.println("String después del swap (i=1, j=3): " + Arrays.toString(letras));

        System.out.println("--------------------------------------------------");

        // Prueba 2: Arreglo de Integer (Recuerda: no usar tipos primitivos como int[])
        Integer[] numeros = {10, 20, 30, 40};
        System.out.println("Integer original: " + Arrays.toString(numeros));
        swap(numeros, 0, 2);
        System.out.println("Integer después del swap (i=0, j=2): " + Arrays.toString(numeros));
    }
}