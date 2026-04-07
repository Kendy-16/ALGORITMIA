public class ViajeRio {

    public static int[][] calcularCostesMinimos(int[][] T, int n) {
        // Matriz C para almacenar los resultados de los costes mínimos
        int[][] C = new int[n][n];

        // Inicializamos la diagonal con 0
        for (int i = 0; i < n; i++) {
            C[i][i] = 0;
        }

        // Resolvemos por programación dinámica
        // Iteramos de abajo hacia arriba en las filas (i)
        // y de izquierda a derecha en las columnas (j)
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                
                // Inicializamos con un valor muy alto o la tarifa directa
                int minCoste = Integer.MAX_VALUE;

                // Probamos todas las escalas posibles 'k' entre i y j
                // Según la fórmula: C[i,j] = Min( T[i,k] + C[k,j] )
                for (int k = i + 1; k <= j; k++) {
                    int costeActual = T[i][k] + C[k][j];
                    if (costeActual < minCoste) {
                        minCoste = costeActual;
                    }
                }
                C[i][j] = minCoste;
            }
        }
        return C;
    }
    public static void main(String[] args) {
        // Ejemplo de matriz de tarifas T (Triangular superior)
        // T[i][j] es el coste directo de i a j.
        // Usamos -1 o 0 para indicar que no hay camino (aunque el problema asume T[i,j] existe)
        int n = 4;
        int[][] T = {
            {0, 10, 15, 80},
            {0,  0, 20, 35},
            {0,  0,  0, 15},
            {0,  0,  0,  0}
        };

        int[][] resultado = calcularCostesMinimos(T, n);

        // Imprimir la matriz de costes mínimos C
        System.out.println("Matriz de Costes Mínimos C:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(resultado[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
