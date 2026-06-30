/*
 * EJERCICIO 4: Eliminacion logica y reinsercion en hash cerrado
 *
 * Tabla hash de tamano 7 con sondeo lineal, h(x) = x % 7.
 * Cada celda es un objeto Entry con estado EMPTY, OCCUPIED o DELETED.
 *
 * Claves a insertar: 5, 12, 19, 26
 *   h(5)  = 5
 *   h(12) = 5  -> colisiona con 5
 *   h(19) = 5  -> colisiona con 5 y 12
 *   h(26) = 5  -> colisiona con 5, 12 y 19
 */
public class EJ4 {

    static final int TAMANO = 7;

    // Posibles estados de una celda de la tabla
    enum Estado { EMPTY, OCCUPIED, DELETED }

    // Cada celda de la tabla es una Entry con su valor y su estado
    static class Entry {
        int valor;
        Estado estado;

        Entry() {
            this.estado = Estado.EMPTY;
        }
    }

    Entry[] tabla = new Entry[TAMANO];

    public EJ4() {
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = new Entry();
        }
    }

    static int funcionHash(int x) {
        return x % TAMANO;
    }

    // Inserta una clave usando sondeo lineal. Reutiliza celdas DELETED si las encuentra.
    void insertar(int clave) {
        int base = funcionHash(clave);
        for (int i = 0; i < TAMANO; i++) {
            int indice = (base + i) % TAMANO;
            // Una celda EMPTY o DELETED puede recibir una nueva clave
            if (tabla[indice].estado == Estado.EMPTY || tabla[indice].estado == Estado.DELETED) {
                tabla[indice].valor = clave;
                tabla[indice].estado = Estado.OCCUPIED;
                System.out.println("Insertado " + clave + " en el indice " + indice);
                return;
            }
        }
        System.out.println("Tabla llena, no se pudo insertar " + clave);
    }

    // Eliminacion logica: solo cambia el estado a DELETED, no borra el valor fisicamente
    void eliminarLogico(int clave) {
        int base = funcionHash(clave);
        for (int i = 0; i < TAMANO; i++) {
            int indice = (base + i) % TAMANO;
            if (tabla[indice].estado == Estado.EMPTY) {
                // Si llegamos a una celda EMPTY, la clave no existe en la tabla
                System.out.println("Clave " + clave + " no encontrada.");
                return;
            }
            if (tabla[indice].estado == Estado.OCCUPIED && tabla[indice].valor == clave) {
                tabla[indice].estado = Estado.DELETED;
                System.out.println("Clave " + clave + " marcada como DELETED en el indice " + indice);
                return;
            }
        }
    }

    // Busqueda: una celda DELETED NO debe detener el sondeo, porque la clave buscada
    // pudo haberse insertado mas adelante (despues de una colision) y seguir alli.
    // Si nos detenemos en DELETED, perderiamos claves validas que estan mas adelante.
    int buscar(int clave) {
        int base = funcionHash(clave);
        for (int i = 0; i < TAMANO; i++) {
            int indice = (base + i) % TAMANO;
            if (tabla[indice].estado == Estado.EMPTY) {
                return -1; // EMPTY si detiene la busqueda: ahi nunca hubo nada
            }
            if (tabla[indice].estado == Estado.OCCUPIED && tabla[indice].valor == clave) {
                return indice;
            }
            // si esta DELETED, simplemente continuamos al siguiente indice
        }
        return -1;
    }

    void imprimirTabla() {
        for (int i = 0; i < TAMANO; i++) {
            System.out.println("Indice " + i + ": " + tabla[i].estado
                    + (tabla[i].estado == Estado.OCCUPIED ? " (" + tabla[i].valor + ")" : ""));
        }
    }

    public static void main(String[] args) {
        EJ4 ht = new EJ4();

        ht.insertar(5);
        ht.insertar(12);
        ht.insertar(19);
        ht.insertar(26);

        System.out.println("\nTabla despues de las inserciones:");
        ht.imprimirTabla();

        // 1) Eliminar logicamente la clave 12
        System.out.println("\nEliminando logicamente la clave 12...");
        ht.eliminarLogico(12);
        ht.imprimirTabla();

        // 2) Buscar la clave 19 despues de la eliminacion
        int indice19 = ht.buscar(19);
        System.out.println("\nBuscar clave 19 -> indice encontrado: " + indice19);
        System.out.println("La celda DELETED no debe detener el sondeo porque, en sondeo");
        System.out.println("lineal, las claves se insertan en la primera celda libre que");
        System.out.println("encuentran tras una colision. Si una busqueda se detuviera al");
        System.out.println("toparse con DELETED, no llegaria a revisar las celdas siguientes");
        System.out.println("donde realmente puede estar la clave buscada (en este caso, 19");
        System.out.println("quedo despues de la celda que ahora esta DELETED).");

        // 3) Reinsertar la clave 33 (h(33) = 33 % 7 = 5, mismo indice base)
        System.out.println("\nReinsertando la clave 33...");
        ht.insertar(33);
        ht.imprimirTabla();

        System.out.println("\nDiferencia entre eliminacion logica y fisica:");
        System.out.println("- Logica: solo se cambia el estado de la celda a DELETED, el dato");
        System.out.println("  puede seguir fisicamente en el arreglo, pero se considera 'borrado'.");
        System.out.println("  Permite que el sondeo siga funcionando correctamente para otras claves.");
        System.out.println("- Fisica: se borra/limpia realmente la celda (se vuelve a EMPTY de forma");
        System.out.println("  directa), lo cual puede romper cadenas de sondeo de otras claves");
        System.out.println("  insertadas despues, haciendo que parezcan 'perdidas'.");
        System.out.println("Conviene la eliminacion logica cuando se usa sondeo (lineal/cuadratico),");
        System.out.println("y la fisica solo cuando se reorganiza toda la tabla (por ejemplo, en un rehashing).");
    }
}
