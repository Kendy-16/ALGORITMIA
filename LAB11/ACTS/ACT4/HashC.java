public class HashC<T> {

    // Estados posibles de cada celda de la tabla hash.
    public static final int EMPTY = 0;
    public static final int OCCUPIED = 1;
    public static final int DELETED = 2;

    // Clase interna que representa una posición de la tabla
    // cada una almacena un registro y el estado en el que se encuentra.
    private static class Cell<T> {
        Register<T> register;
        int status;

        Cell() {
            this.register = null;
            this.status = EMPTY;
        }
    }

    // Arreglo que representa la tabla hash.
    private final Cell<T>[] table;

    // Tamaño fijo de la tabla.
    private final int size;

    // Cantidad de elementos actualmente almacenados.
    private int count;

    // Constructor que crea la tabla hash e inicializa todas las celdas como vacías.
    @SuppressWarnings("unchecked")
    public HashC(int size) {
        this.size = size;
        this.table = (Cell<T>[]) new Cell[size];

        for (int i = 0; i < size; i++) {
            table[i] = new Cell<>();
        }

        this.count = 0;
    }

    // Constructor por defecto.
    public HashC() {
        this(11);
    }

    // Función hash.
    // Calcula la posición inicial donde debe almacenarse una clave.
    private int hash(int key) {
        return Math.floorMod(key, size);
    }

    // Inserta un nuevo registro utilizando sondeo lineal.
    public boolean insert(int key, T data) {

        // Si la tabla está llena ya no es posible insertar.
        if (count == size) {
            System.out.println("La tabla está llena.");
            return false;
        }

        int initial = hash(key);
        int pos = initial;

        // Guarda la primera posición eliminada encontrada para poder reutilizarla posteriormente.
        int firstDeleted = -1;

        do {

            Cell<T> cell = table[pos];

            // Si la posición está ocupada
            if (cell.status == OCCUPIED) {

                // y la clave ya existe,
                // simplemente actualizamos el dato.
                if (cell.register != null &&
                        cell.register.getKey() == key) {

                    cell.register.setData(data);

                    System.out.println("Clave existente. Dato actualizado.");
                    return true;
                }

            }

            // Si encontramos una celda eliminada,
            // guardamos su posición.
            else if (cell.status == DELETED) {

                if (firstDeleted == -1)
                    firstDeleted = pos;

            }

            // Si encontramos una posición vacía,
            // insertamos el nuevo registro.
            else {

                int target = (firstDeleted != -1) ? firstDeleted : pos;
                table[target].register = new Register<>(key, data);
                table[target].status = OCCUPIED;

                count++;
                return true;
            }

            // SONDEO LINEAL:
            // Avanza una posición.
            pos = (pos + 1) % size;

        } while (pos != initial);

        // Si no hubo posiciones vacías,
        // reutiliza la primera eliminada encontrada.
        if (firstDeleted != -1) {

            table[firstDeleted].register =new Register<>(key, data);
            table[firstDeleted].status = OCCUPIED;

            count++;
            return true;
        }

        return false;
    }

    // Busca un registro utilizando sondeo lineal.
    public Register<T> search(int key) {

        int initial = hash(key);
        int pos = initial;

        do {

            Cell<T> cell = table[pos];

            // Si llega a una posición vacía, significa que la clave no existe.
            if (cell.status == EMPTY){
                return null;
            }
            // Si encuentra la clave, devuelve el registro.
            if (cell.status == OCCUPIED && cell.register != null && cell.register.getKey() == key) {
                return cell.register;
            }

            // Continúa buscando en la siguiente posición.
            pos = (pos + 1) % size;

        } while (pos != initial);

        return null;
    }

    // Eliminación lógica. No elimina físicamente el registro, únicamente cambia el estado de la celda.
    public boolean delete(int key) {

        int initial = hash(key);
        int pos = initial;

        do {

            Cell<T> cell = table[pos];

            if (cell.status == EMPTY){
                return false;
            }
            if (cell.status == OCCUPIED && cell.register != null && cell.register.getKey() == key) {

                cell.register = null;

                // La posición queda marcada como DELETED.
                cell.status = DELETED;
                count--;
                return true;
            }

            pos = (pos + 1) % size;

        } while (pos != initial);

        return false;
    }

    // Muestra el contenido completo de la tabla hash. Se imprime índice, estado y registro almacenado.
    public void printTable() {

        System.out.println("-----------------------------------------------");
        System.out.println("Indice\tEstado\t\tRegistro");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < size; i++) {

            String state;

            if (table[i].status == EMPTY)
                state = "EMPTY";
            else if (table[i].status == OCCUPIED)
                state = "OCCUPIED";
            else
                state = "DELETED";

            String reg = (table[i].register == null) ? "-" : table[i].register.toString();

            System.out.printf("%2d\t%-10s%s%n", i, state, reg);
        }

        System.out.println("-----------------------------------------------");
    }

    public int size() {
        return size;
    }

    public int count() {
        return count;
    }
}