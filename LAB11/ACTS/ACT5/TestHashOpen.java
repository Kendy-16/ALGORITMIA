public class TestHashOpen {
    public static void main(String[] args) {

        // Se crea una tabla hash abierta de tamaño 11
        HashO<String> hash = new HashO<>(11);

        System.out.println("========== INSERCIÓN DE REGISTROS ==========\n");

        // Inserciones
        hash.insert(34, "Ana");
        hash.insert(45, "Luis");
        hash.insert(56, "Pedro");
        hash.insert(67, "Carlos");
        hash.insert(3, "María");
        hash.insert(14, "José");
        hash.insert(25, "Lucía");
        hash.insert(8, "Miguel");
        hash.insert(19, "Andrea");

        // Mostrar tabla
        System.out.println("Tabla después de las inserciones:");
        hash.printTable();

        // Buscar un elemento
        System.out.println("\n========== BÚSQUEDA ==========");

        Register<String> encontrado = hash.search(56);

        if (encontrado != null) {
            System.out.println("Registro encontrado: " + encontrado);
        } else {
            System.out.println("La clave no existe.");
        }

        // Eliminar un elemento
        System.out.println("\n========== ELIMINACIÓN ==========");

        if (hash.delete(45)) {
            System.out.println("Registro eliminado correctamente.");
        } else {
            System.out.println("No se encontró el registro.");
        }

        // Mostrar nuevamente la tabla
        System.out.println("\nTabla después de eliminar la clave 45:");

        hash.printTable();
    }
}