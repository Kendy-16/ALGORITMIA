import java.util.Random;

// Cola de reproducción usando lista doblemente enlazada
// El puntero "actual" simula la canción que está sonando ahora mismo
public class ColaReproduccion<T> {

    private NodoDoble<T> cabeza;   // primer nodo de la lista
    private NodoDoble<T> cola;     // último nodo de la lista
    private NodoDoble<T> actual;   // nodo que se está reproduciendo
    private int tamano;

    public ColaReproduccion() {
        cabeza = null;
        cola = null;
        actual = null;
        tamano = 0;
    }
    // Agrega una canción al final de la cola
    public void agregarCancion(T cancion) {
        NodoDoble<T> nuevo = new NodoDoble<>(cancion);
        if (cabeza == null) {
            // Lista vacía: el nuevo nodo es cabeza, cola y actual
            cabeza = nuevo;
            cola = nuevo;
            actual = nuevo;
        } else {
            // Enlazar al final
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamano++;
    }
    // Avanza al siguiente nodo y retorna la canción
    // Si ya está en el último, se queda ahí (como Spotify al final de la cola)
    public T reproducirSiguiente() {
        if (actual == null) return null;

        if (actual.siguiente != null) {
            actual = actual.siguiente;
        } else {
            System.out.println("  [Ya estás en la última canción de la cola]");
        }
        return actual.dato;
    }
    // Retrocede al nodo anterior usando el puntero prev
    // Si ya está en el primero, se queda ahí
    public T reproducirAnterior() {
        if (actual == null) return null;

        if (actual.anterior != null) {
            actual = actual.anterior;
        } else {
            System.out.println("  [Ya estás en la primera canción de la cola]");
        }
        return actual.dato;
    }
    // Reorganiza aleatoriamente los nodos usando Fisher-Yates
    // Guarda los datos en array, mezcla, y los vuelve a colocar en los nodos
    public void mezclar() {
        if (tamano <= 1) return;
        // Paso 1: Guardar todos los datos en un array
        @SuppressWarnings("unchecked")
        T[] arreglo = (T[]) new Object[tamano];

        NodoDoble<T> temp = cabeza;
        int i = 0;
        while (temp != null) {
            arreglo[i] = temp.dato;
            i++;
            temp = temp.siguiente;
        }
        // Paso 2: Algoritmo Fisher-Yates (mezcla de atrás hacia adelante)
        Random rand = new Random();
        for (int j = tamano - 1; j > 0; j--) {
            int idx = rand.nextInt(j + 1); // índice aleatorio entre 0 y j
            T aux = arreglo[j];
            arreglo[j] = arreglo[idx];
            arreglo[idx] = aux;
        }

        // Paso 3: Volver a colocar los datos mezclados en los nodos
        temp = cabeza;
        i = 0;
        while (temp != null) {
            temp.dato = arreglo[i];
            i++;
            temp = temp.siguiente;
        }

        // Reiniciar el reproductor a la primera canción después de mezclar
        actual = cabeza;
    }

    // Imprime todas las canciones indicando cuál es la actual con el símbolo ►
    public void mostrarCola() {
        if (cabeza == null) {
            System.out.println("  [La cola está vacía]");
            return;
        }

        NodoDoble<T> temp = cabeza;
        int num = 1;
        while (temp != null) {
            if (temp == actual) {
                System.out.println("► " + num + ". " + temp.dato);
            } else {
                System.out.println("  " + num + ". " + temp.dato);
            }
            num++;
            temp = temp.siguiente;
        }
    }
    // Retorna la suma de las duraciones de todas las canciones
    // Necesita castear a Cancion porque T es genérico
    public int duracionTotal() {
        int totalSeg = 0;
        NodoDoble<T> temp = cabeza;

        while (temp != null) {
            if (temp.dato instanceof Cancion) {
                totalSeg += ((Cancion) temp.dato).getDuracionSeg();
            }
            temp = temp.siguiente;
        }
        return totalSeg;
    }
}