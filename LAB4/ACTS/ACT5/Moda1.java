public class Moda1 {

    // Metodo que calcula la moda
    public static int moda(int array[]) {
        
        int first = 0;
        int end = array.length - 1;
        
        // Caso base
        if (first == end)
            return array[first];
        
        int moda = array[first];
        
        // Calcular frecuencia inicial
        int maxfrec = frecuencia(array, first, end, moda);
        
        // Recorrer arreglo
        for (int i = first + 1; i <= end; i++) {
            int frec = frecuencia(array, i, end, array[i]);
            if (frec > maxfrec) {
                maxfrec = frec;
                moda = array[i];
            }
        }
        return moda;
    }


    // Metodo que calcula la frecuencia
    private static int frecuencia(int[] array,int first,int end,int ele) {
        if (first > end)
            return 0;
        
        int suma = 0;
        
        for (int i = first; i <= end; i++) {
            if (array[i] == ele)
                suma++;
        }
        
        return suma;
    }


    // Metodo para imprimir arreglo
    public static void imprimir(int[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        
        System.out.println();
    }


    // Metodo principal para probar
    public static void main(String[] args) {
        
        // Arreglo de prueba
        int[] datos = {3, 5, 2, 3, 8, 3, 2, 5, 3};
        
        System.out.println("Arreglo");
        imprimir(datos);
        
        int resultado = moda(datos);
        
        System.out.println("\nLa moda es: " + resultado);
    }
}
