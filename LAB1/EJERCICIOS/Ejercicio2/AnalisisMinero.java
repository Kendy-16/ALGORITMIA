package LAB1.EJERCICIOS.Ejercicio2;
import java.io.File;
import java.util.Scanner;

public class AnalisisMinero {
    private int filas;
    private int columnas;

    public void ejecutarAnalisis(String nombreArchivo, int k) throws Exception {
        Zona[] terreno = cargarTerreno(nombreArchivo);
        analizarMejorRegion(terreno, k);
    }

    private Zona[] cargarTerreno(String nombreArchivo) throws Exception {
        Scanner scanner = new Scanner(new File(nombreArchivo));

        // Leemos las dimensiones de la matriz
        filas = Integer.parseInt(scanner.next());
        columnas = Integer.parseInt(scanner.next());
        
        // Creamos un arreglo unidimensional
        Zona[] arreglo = new Zona[filas * columnas];

        for (int i = 0; i < filas * columnas; i++) {
            String mineral = scanner.next();
            // Convertimos el texto a decimales usando Double.parseDouble
            double cantidad = Double.parseDouble(scanner.next());
            double pureza = Double.parseDouble(scanner.next());
            
            arreglo[i] = new Zona(mineral, cantidad, pureza);
        }
        scanner.close();
        return arreglo;
    }

    private void analizarMejorRegion(Zona[] terreno, int k) {
        double maxValorTotal = -1.0;
        int mejorFila = 0;
        int mejorColumna = 0;

        // 1. Encontrar la región con el mayor valor económico
        for (int i = 0; i <= filas - k; i++) {
            for (int j = 0; j <= columnas - k; j++) {
                
                double valorActual = 0.0;
                
                for (int x = 0; x < k; x++) {
                    for (int y = 0; y < k; y++) {
                        // Fórmula para acceder al arreglo 1D como si fuera matriz
                        int indice = (i + x) * columnas + (j + y);
                        valorActual += terreno[indice].getValorEconomico();
                    }
                }

                if (valorActual > maxValorTotal) {
                    maxValorTotal = valorActual;
                    mejorFila = i;
                    mejorColumna = j;
                }
            }
        }

        // 2. Imprimir los resultados generales
        System.out.println("Región más valiosa encontrada:\n");
        System.out.println("Posición inicial: (" + mejorFila + "," + mejorColumna + ")");
        System.out.println("Tamaño de la región: " + k + " x " + k + "\n");
        System.out.println("Zonas analizadas:");

        // Arreglo temporal para guardar los minerales de la mejor región
        String[] mineralesRegion = new String[k * k];
        int contador = 0;

        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                int indice = (mejorFila + x) * columnas + (mejorColumna + y);
                Zona z = terreno[indice];
                System.out.println(z.toString());
                
                mineralesRegion[contador] = z.getMineral();
                contador++;
            }
        }

        // 3. Encontrar el mineral predominante mediante comparación directa
        String mineralPredominante = "";
        int maxFrecuencia = -1;

        for (int i = 0; i < mineralesRegion.length; i++) {
            int frecuenciaActual = 0;
            
            for (int j = 0; j < mineralesRegion.length; j++) {
                if (mineralesRegion[i].equals(mineralesRegion[j])) {
                    frecuenciaActual++;
                }
            }

            if (frecuenciaActual > maxFrecuencia) {
                maxFrecuencia = frecuenciaActual;
                mineralPredominante = mineralesRegion[i];
            }
        }

        System.out.println("\nValor total estimado: " + maxValorTotal);
        System.out.println("Mineral predominante en la región: " + mineralPredominante);
    }
}