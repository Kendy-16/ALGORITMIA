public class MergeSort {

    // Metodo principal MergeSort
    public static void mergeSort(int[] arr, int inicio, int fin) {

        // Caso base
        if (inicio < fin) {
            // Encontrar punto medio
            int medio = (inicio + fin) / 2;
            // Dividir mitad izquierda
            mergeSort(arr, inicio, medio);
            // Dividir mitad derecha
            mergeSort(arr, medio + 1, fin);
            // Combinar mitades
            merge(arr, inicio, medio, fin);
        }
    }

    // Metodo para combinar (merge)
    public static void merge(int[] arr, int inicio, int medio, int fin) {
        
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        int[] izquierda = new int[n1];
        int[] derecha = new int[n2];
        
        // Copiar datos
        for (int i = 0; i < n1; i++) {
            izquierda[i] = arr[inicio + i];
        }
        
        for (int j = 0; j < n2; j++) {
            derecha[j] = arr[medio + 1 + j];
        }
        
        int i = 0;
        int j = 0;
        int k = inicio;
        
        // Mezclar arreglos
        while (i < n1 && j < n2) {
        
            if (izquierda[i] <= derecha[j]) {
                arr[k] = izquierda[i];
                i++;
            } else {
                arr[k] = derecha[j];
                j++;
            }
            k++;
        }
        
        // Copiar sobrantes izquierda
        while (i < n1) {
            arr[k] = izquierda[i];
            i++;
            k++;
        }
        
        // Copiar sobrantes derecha
        while (j < n2) {
            arr[k] = derecha[j];
            j++;
            k++;
        }
    }
    
    // Metodo para imprimir arreglo
    public static void imprimir(int[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // -------- Arreglo 1 --------
        int[] arr1 = {8, 3, 5, 2, 9};
        System.out.println("Arreglo de 5 elementos");
        System.out.print("Antes: ");
        imprimir(arr1);
        mergeSort(arr1, 0, arr1.length - 1);
        System.out.print("Despues: ");
        imprimir(arr1);

        // -------- Arreglo 2 --------
        int[] arr2 = {4, 7, 1, 6, 2, 8, 3, 9};
        System.out.println("\nArreglo de 8 elementos");
        System.out.print("Antes: ");
        imprimir(arr2);
        mergeSort(arr2, 0, arr2.length - 1);
        System.out.print("Despues: ");
        imprimir(arr2);

        // -------- Arreglo 3 --------
        int[] arr3 = {10, 3, 8, 5, 1, 4, 9, 7, 2, 6};
        System.out.println("\nArreglo de 10 elementos");
        System.out.print("Antes: ");
        imprimir(arr3);
        mergeSort(arr3, 0, arr3.length - 1);
        System.out.print("Despues: ");
        imprimir(arr3);
    }
}
