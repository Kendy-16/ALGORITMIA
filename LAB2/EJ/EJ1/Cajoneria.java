import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<Caja<T>> {

    private ArrayList<Caja<T>> lista = new ArrayList<Caja<T>>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(Caja<T> caja) {
        if (lista.size() < tope) {
            lista.add(caja);
        } else {
            throw new RuntimeException("no caben más cajas");
        }
    }

    // Buscar
    public String search(Object obj) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            if (caja.obtener().equals(obj)) {
                
                return "Posicion: " + i + " Color: " + caja.getColor();
            }
        }
        return "Elemento no encontrado";
    }

    // Borrar
    public Object delete(Object obj) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            if (caja.obtener().equals(obj)) {
                Object eliminado = caja.obtener();
                caja.guardar(null);
                return eliminado;
            }
        }
        return null;
    }

    // ToString
    public String toString() {
        String resultado = "";
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            resultado += "Posicion: " + i + " | Color: " + caja.getColor() + " | Contenido: " + caja.obtener()+ " |\n";
        }
        return resultado;
    }

    // Metodo para contar
    public int contar(T obj) {
        int contador = 0;
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            if (caja.obtener() != null && caja.obtener().equals(obj)) {
                contador++;
            }
        }
        return contador;
    }
    
    public Iterator<Caja<T>> iterator() {
        return lista.iterator();
    }
}
