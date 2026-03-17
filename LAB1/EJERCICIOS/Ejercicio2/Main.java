package LAB1.EJERCICIOS.Ejercicio2;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner archivo = new Scanner(new File("zonas.txt"));

            int filas = archivo.nextInt();
            int columnas = archivo.nextInt();

            Zona[] zonas = new Zona[filas * columnas];

            for (int i = 0; i < zonas.length; i++) {

                String mineral = archivo.next();
                double cantidad = archivo.nextDouble();
                double pureza = archivo.nextDouble();

                zonas[i] = new Zona(mineral, cantidad, pureza);
            }

            archivo.close();

            Scanner teclado = new Scanner(System.in);
            System.out.print("Ingrese tamaño de subregión k: ");
            int k = teclado.nextInt();
            teclado.close();

            AnalizadorMinero.analizar(zonas, filas, columnas, k);

        } catch (Exception e) {
            System.out.println("Error leyendo el archivo");
        }
    }
}