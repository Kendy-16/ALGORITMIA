public class TestHash {
    public static void main(String[] args) {
        HashC<String> hash = new HashC<>(11);

        System.out.println("=== Inserciones ===");
        hash.insert(34, "Ana");
        hash.insert(3, "Bruno");
        hash.insert(7, "Carla");
        hash.insert(30, "David");
        hash.insert(11, "Elena");
        hash.insert(8, "Fabio");
        hash.insert(7, "Gabriela");   // repetido, actualiza dato
        hash.insert(23, "Hugo");
        hash.insert(41, "Inés");
        hash.insert(16, "Juan");
        hash.insert(34, "Karla");     // repetido, actualiza dato

        System.out.println("\nTabla hash antes de eliminar la clave 30:");
        hash.printTable();

        System.out.println("\n=== Búsqueda de la clave 23 ===");
        Register<String> found = hash.search(23);
        if (found != null) {
            System.out.println("Encontrado: " + found);
        } else {
            System.out.println("La clave 23 no fue encontrada.");
        }

        System.out.println("\n=== Eliminación de la clave 30 ===");
        if (hash.delete(30)) {
            System.out.println("Clave 30 eliminada lógicamente.");
        } else {
            System.out.println("La clave 30 no fue encontrada.");
        }

        System.out.println("\nTabla hash después de eliminar la clave 30:");
        hash.printTable();
    }
}