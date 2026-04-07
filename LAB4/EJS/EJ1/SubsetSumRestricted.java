import java.util.Scanner;

public class SubsetSumRestricted {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Lectura de datos según el formato de entrada
            if (!sc.hasNextInt()) return;
            int n = sc.nextInt();
            
            int[] arreglo = new int[n];
            for (int i = 0; i < n; i++) {
                arreglo[i] = sc.nextInt();
            }
            int objetivo = sc.nextInt();

            // Ejecución del algoritmo
            if (puedeAlcanzarSuma(0, 0, objetivo, arreglo)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    /**
     * Método recursivo con backtracking y reglas de negocio.
     */
    public static boolean puedeAlcanzarSuma(int index, int sumaActual, int objetivo, int[] arr) {
        // CASO BASE: Si procesamos todos los elementos
        if (index == arr.length) {
            return sumaActual == objetivo;
        }

        int valorActual = arr[index];

        // REGLA 1: Múltiplos de 3 deben incluirse obligatoriamente
        if (valorActual % 3 == 0) {
            return puedeAlcanzarSuma(index + 1, sumaActual + valorActual, objetivo, arr);
        }

        // REGLA 2: Restricción de pares consecutivos
        // Si el actual es par y el siguiente existe y también es par, NO se puede elegir el actual.
        if (index < arr.length - 1) {
            int siguienteValor = arr[index + 1];
            if (valorActual % 2 == 0 && siguienteValor % 2 == 0) {
                // Saltamos el actual sin sumarlo
                return puedeAlcanzarSuma(index + 1, sumaActual, objetivo, arr);
            }
        }

        // REGLA 3: Elección libre (Backtracking)
        // Intentamos incluir el número
        if (puedeAlcanzarSuma(index + 1, sumaActual + valorActual, objetivo, arr)) {
            return true;
        }

        // Si no funcionó, intentamos NO incluir el número
        if (puedeAlcanzarSuma(index + 1, sumaActual, objetivo, arr)) {
            return true;
        }
        
        return false;
    }
}