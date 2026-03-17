package LAB1.EJERCICIOS.Ejercicio2;

public class Main {
    public static void main(String[] args) throws Exception {
        // Nombre del archivo de texto y tamaño de la región a buscar
        String nombreArchivo = "datos.txt"; // Ruta al archivo de texto con los datos (modificar según sea necesario)
        int k = 2; // Tamaño de la región k x k

        // Creamos la instancia y ejecutamos
        AnalisisMinero explorador = new AnalisisMinero();
        explorador.ejecutarAnalisis(nombreArchivo, k);
    }
}
