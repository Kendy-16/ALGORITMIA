public class Moda2 {
    public static int moda2(int array[]) {
        int first = 1;
        int p = 0;
        int end = array.length - 1;
        int moda = array[0];
        int frec = 1;
        int maxfrec = 0;
        
        while (first <= end) {
            if (array[p] == array[first]) {
                frec++;
                first++;
            } else {
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[p];
                }
                p = first;
                first = p + 1;
                frec = 1;
            }
        }
        if (frec > maxfrec) {
            maxfrec = frec;
            moda = array[p];
        }
        return moda;
    }


    // Metodo imprimir
    public static void imprimir(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        // IMPORTANTE: debe estar ordenado
        int[] datos = {2, 2, 2, 3, 3, 5, 6, 6, 6, 6};
        System.out.println("Arreglo:");
        imprimir(datos);
        
        int resultado = moda2(datos);
        
        System.out.println("La moda es: " + resultado);
    }
}
