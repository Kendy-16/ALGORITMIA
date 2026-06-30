/*
 * EJERCICIO 3: Tabla hash abierta con colisiones multiples
 *
 * Tabla hash con encadenamiento (hash abierto) de tamano 7.
 * Funcion h(k) = k % 7.
 *
 * IMPORTANTE: no se usa LinkedList ni ninguna clase de java.util.
 * La lista enlazada de cada posicion (bucket) esta implementada
 * manualmente con una clase Nodo propia (nodo.siguiente).
 *
 * Pares (clave, nombre) a insertar:
 *   (10,"Juan"), (17,"Ana"), (24,"Luis"), (31,"Rosa"), (5,"Pedro"), (12,"Carla")
 *
 * Direcciones hash:
 *   h(10) = 3   h(17) = 3   h(24) = 3   h(31) = 3   -> colisionan en el indice 3
 *   h(5)  = 5   (sin colision)
 *   h(12) = 5   -> colisiona con el 5 en el indice 5
 */
public class EJ3 {

    static final int TAMANO = 7;

    // Nodo de la lista enlazada propia: guarda clave, nombre y un puntero al siguiente nodo
    static class Nodo {
        int clave;
        String nombre;
        Nodo siguiente;

        Nodo(int clave, String nombre) {
            this.clave = clave;
            this.nombre = nombre;
            this.siguiente = null;
        }
    }

    // Lista enlazada simple, implementada desde cero (sin java.util)
    static class ListaEnlazada {
        Nodo cabeza; // primer nodo de la lista (null si esta vacia)
        int tamanoLista;

        ListaEnlazada() {
            cabeza = null;
            tamanoLista = 0;
        }

        boolean estaVacia() {
            return cabeza == null;
        }

        // Agrega un nuevo nodo al final de la lista
        void agregarAlFinal(int clave, String nombre) {
            Nodo nuevo = new Nodo(clave, nombre);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            tamanoLista++;
        }

        // Busca un nodo por clave y retorna su nombre, o null si no existe
        String buscar(int clave) {
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.clave == clave) {
                    return actual.nombre;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // Elimina el nodo con la clave indicada. Retorna true si lo elimino, false si no existia.
        boolean eliminar(int clave) {
            if (cabeza == null) return false;

            // Caso especial: el nodo a eliminar es la cabeza
            if (cabeza.clave == clave) {
                cabeza = cabeza.siguiente;
                tamanoLista--;
                return true;
            }

            Nodo anterior = cabeza;
            Nodo actual = cabeza.siguiente;
            while (actual != null) {
                if (actual.clave == clave) {
                    anterior.siguiente = actual.siguiente; // saltamos el nodo a eliminar
                    tamanoLista--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        // Representacion en texto de toda la lista: (clave, nombre) -> (clave, nombre) -> ...
        public String toString() {
            if (cabeza == null) return "[vacio]";
            StringBuilder sb = new StringBuilder();
            Nodo actual = cabeza;
            while (actual != null) {
                sb.append("(").append(actual.clave).append(", ").append(actual.nombre).append(")");
                if (actual.siguiente != null) sb.append(" -> ");
                actual = actual.siguiente;
            }
            return sb.toString();
        }
    }

    // La tabla es un arreglo de listas enlazadas propias (encadenamiento)
    ListaEnlazada[] tabla = new ListaEnlazada[TAMANO];

    public EJ3() {
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = new ListaEnlazada();
        }
    }

    static int funcionHash(int k) {
        return k % TAMANO;
    }

    // Inserta un nuevo par (clave, nombre) al final de la lista del indice correspondiente
    void insertar(int clave, String nombre) {
        int indice = funcionHash(clave);
        if (!tabla[indice].estaVacia()) {
            System.out.println("Colision en indice " + indice + " al insertar clave " + clave);
        }
        tabla[indice].agregarAlFinal(clave, nombre);
    }

    // Busca el nombre asociado a una clave
    String buscar(int clave) {
        int indice = funcionHash(clave);
        return tabla[indice].buscar(clave);
    }

    // Elimina una clave de su lista enlazada (si existe)
    boolean eliminar(int clave) {
        int indice = funcionHash(clave);
        return tabla[indice].eliminar(clave);
    }

    // Muestra el estado completo de la tabla, indice por indice
    void imprimirTabla() {
        for (int i = 0; i < TAMANO; i++) {
            System.out.println("Indice " + i + ": " + tabla[i]);
        }
    }

    public static void main(String[] args) {
        EJ3 ht = new EJ3();

        ht.insertar(10, "Juan");
        ht.insertar(17, "Ana");
        ht.insertar(24, "Luis");
        ht.insertar(31, "Rosa");
        ht.insertar(5, "Pedro");
        ht.insertar(12, "Carla");

        System.out.println("\nEstado final de la tabla:");
        ht.imprimirTabla();

        // 1) Buscar la clave 24
        int indiceBuscado = funcionHash(24);
        String nombre = ht.buscar(24);
        // El nodo es el tercero insertado en esa lista (despues de 10 y 17)
        System.out.println("\nBusqueda de la clave 24: nombre = " + nombre
                + ", se encuentra en el indice " + indiceBuscado
                + ", en el 3er nodo de la lista enlazada de ese indice.");

        // 2) Eliminar la clave 17
        ht.eliminar(17);
        System.out.println("\nEstado de la tabla luego de eliminar la clave 17:");
        ht.imprimirTabla();
        int indice17 = funcionHash(17);
        System.out.println("La cadena del indice " + indice17 + " ahora tiene "
                + ht.tabla[indice17].tamanoLista + " nodo(s).");
    }
}
