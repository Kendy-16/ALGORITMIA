import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<T> {

    private ArrayList<T> lista = new ArrayList<T>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(T objeto) {
        if (lista.size() < tope) {
            lista.add(objeto);
        } else {
            throw new RuntimeException("no caben más cajas");
        }
    }

    public Iterator<T> iterator() {
        return lista.iterator();
    }
}
