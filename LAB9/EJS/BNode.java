import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    public int count;
    public ArrayList<E> keys;
    public ArrayList<BNode<E>> childs;
    public int idNode; // ID único para identificar el nodo
    private static int nodeCounter = 0;

    public BNode(int orden) {
        this.count = 0;
        this.idNode = ++nodeCounter;
        this.keys = new ArrayList<>();
        this.childs = new ArrayList<>();
        for (int i = 0; i < orden; i++) keys.add(null);
        for (int i = 0; i < orden + 1; i++) childs.add(null);
    }

    public static void resetCounter() {
        nodeCounter = 0;
    }

    public boolean nodeFull(int orden) {
        return this.count >= orden - 1;
    }

    public boolean searchNode(E cl, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.count && cl.compareTo(this.keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        return pos[0] < this.count && cl.compareTo(this.keys.get(pos[0])) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nodo[" + idNode + "]{");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
