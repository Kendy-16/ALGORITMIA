import java.util.Scanner;

public class QuickSelect {
    
    // Metodo para intercambiar valores
    public static void intercambiar(int[] arr, int i, int j) {
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Metodo para dividir el arreglo (particion)
    public static int particion(int[] arr, int inicio, int fin) {
        
        int pivote = arr[fin];
        int i = inicio;
        
        for (int j = inicio; j < fin; j++) {
            
            if (arr[j] > pivote) { // mayor primero (k-esimo mayor)
                intercambiar(arr, i, j);
                i++;
            }
        }
        intercambiar(arr, i, fin);
        return i;
    }

    // Metodo QuickSelect (Divide y Vencerás)
    public static int quickSelect(int[] arr, int inicio, int fin, int k) {
        
        if (inicio <= fin) {
            
            int posicionPivote = particion(arr, inicio, fin);
            
            if (posicionPivote == k - 1) {
                return arr[posicionPivote];
                
            } else if (posicionPivote > k - 1) {
                return quickSelect(arr, inicio, posicionPivote - 1, k);
                
            } else {
                return quickSelect(arr, posicionPivote + 1, fin,k);
                
            }
        }
        return -1;
    }

    // MAIN
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\nIngrese cantidad de elementos: ");
        int n = sc.nextInt();
        System.out.print("==========================================");
        
        int[] arr = new int[n];
        System.out.println("\nIngrese los elementos");
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.print("==========================================");

        System.out.print("\nIngrese valor k: ");
        int k = sc.nextInt();
        System.out.print("==========================================");
        
        int resultado = quickSelect(arr, 0, n - 1, k);
        System.out.println("\nEl " + k +"° elemento mayor es: "+ resultado);
        sc.close();
    }
}
