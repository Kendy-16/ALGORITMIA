public class HashO<T> {

    // Arreglo de listas enlazadas que representa la tabla hash
    private LinkedList<T>[] table;

    // Tamaño de la tabla
    private int size;

    // Constructor
    @SuppressWarnings("unchecked")
    public HashO(int size) {

        this.size = size;

        table = (LinkedList<T>[]) new LinkedList[size];

        // Se crea una lista enlazada para cada posición de la tabla
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Constructor por defecto
    public HashO() {
        this(11);
    }

    // Función hash
    private int hash(int key) {
        return Math.floorMod(key, size);
    }

    // Inserta un nuevo registro en la lista correspondiente
    public void insert(int key, T data) {

        int index = hash(key);

        Register<T> register = new Register<>(key, data);

        table[index].insert(register);
    }

    // Busca un registro por su clave
    public Register<T> search(int key) {

        int index = hash(key);

        return table[index].search(key);
    }

    // Elimina un registro por su clave
    public boolean delete(int key) {

        int index = hash(key);

        return table[index].delete(key);
    }

    // Muestra toda la tabla hash
    public void printTable() {

        System.out.println("\n=========== TABLA HASH ABIERTA ===========");

        for (int i = 0; i < size; i++) {

            System.out.print("Índice " + i + " -> ");

            table[i].print();

            System.out.println();
        }

        System.out.println("==========================================");
    }
}