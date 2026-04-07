package LAB2.LAB3.EJ;
import java.util.Arrays;

public class MergeSort {
    // Método principal de ordenamiento
    public static void mergeSort(int[] arreglo, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            // Ordenar la mitad izquierda
            mergeSort(arreglo, izquierda, medio);

            // Ordenar la mitad derecha
            mergeSort(arreglo, medio + 1, derecha);

            // Fusionar ambas mitades ordenadas
            merge(arreglo, izquierda, medio, derecha);
        }
    }
    // Método para fusionar dos subarreglos ordenados
    public static void merge(int[] arreglo, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;
        // Arreglos temporales
        int[] L = new int[n1];
        int[] R = new int[n2];
        // Copiar datos al arreglo temporal izquierdo
        for (int i = 0; i < n1; i++) {
            L[i] = arreglo[izquierda + i];
        }
        // Copiar datos al arreglo temporal derecho
        for (int j = 0; j < n2; j++) {
            R[j] = arreglo[medio + 1 + j];
        }
        int i = 0, j = 0;
        int k = izquierda;
        // Fusionar los arreglos temporales en el arreglo original
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                i++;
            } else {
                arreglo[k] = R[j];
                j++;
            }
            k++;
        }
        // Copiar elementos restantes de L, si existen
        while (i < n1) {
            arreglo[k] = L[i];
            i++;
            k++;
        }
        // Copiar elementos restantes de R, si existen
        while (j < n2) {
            arreglo[k] = R[j];
            j++;
            k++;
        }
    }
    // Método principal para probar el algoritmo
    public static void main(String[] args) {
        int[] arreglo = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Arreglo original:");
        System.out.println(Arrays.toString(arreglo));

        mergeSort(arreglo, 0, arreglo.length - 1);

        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(arreglo));
    }
}
