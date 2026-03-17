package LAB1.EJERCICIOS.Ejercicio2;

public class AnalizadorMinero {

    public static void analizar(Zona[] zonas, int filas, int columnas, int k) {

        double maxValor = -1;
        int mejorFila = 0;
        int mejorColumna = 0;

        for (int i = 0; i <= filas - k; i++) {
            for (int j = 0; j <= columnas - k; j++) {

                double suma = 0;

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {

                        int index = x * columnas + y;
                        suma += zonas[index].getValorEconomico();
                    }
                }

                if (suma > maxValor) {
                    maxValor = suma;
                    mejorFila = i;
                    mejorColumna = j;
                }
            }
        }

        mostrarResultado(zonas, filas, columnas, mejorFila, mejorColumna, k, maxValor);
    }

    private static void mostrarResultado(Zona[] zonas, int filas, int columnas,
                                         int fila, int col, int k, double valor) {

        System.out.println("Región más valiosa encontrada:");
        System.out.println("Posición inicial: (" + fila + "," + col + ")");
        System.out.println("Tamaño de la región: " + k + " x " + k);

        System.out.println("\nZonas analizadas:");

        String[] minerales = new String[k * k];
        int[] conteo = new int[k * k];
        int size = 0;

        for (int i = fila; i < fila + k; i++) {
            for (int j = col; j < col + k; j++) {

                int index = i * columnas + j;
                Zona z = zonas[index];

                System.out.println("[ " + z + " ]");

                String mineral = z.getMineral();
                boolean encontrado = false;

                for (int t = 0; t < size; t++) {
                    if (minerales[t].equals(mineral)) {
                        conteo[t]++;
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    minerales[size] = mineral;
                    conteo[size] = 1;
                    size++;
                }
            }
        }

        System.out.println("\nValor total estimado: " + valor);

        String predominante = "";
        int max = 0;

        for (int i = 0; i < size; i++) {
            if (conteo[i] > max) {
                max = conteo[i];
                predominante = minerales[i];
            }
        }

        System.out.println("\nMineral predominante en la región: " + predominante);
    }
}