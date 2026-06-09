public class BTree<E extends Comparable<E>> {

    private BNode<E> root;      // Nodo raíz del árbol
    private int orden;          // Orden del árbol B
    private boolean up;         // Indica si después de una inserción una clave debe subir al nodo padre
    private BNode<E> nDes;      // Nodo derecho generado cuando ocurre una división

    public BTree(int orden) {   // Constructor
        this.orden = orden;     
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;        // Verifica si el árbol está vacío.
    }

    public void insert(E cl) {      // Método público de inserción

        up = false;
        E mediana;
        BNode<E> nuevaRaiz;

        mediana = push(root, cl);   // Intenta insertar recursivamente. Puede devolver una mediana.

        if (up) {           // Si la raíz se dividió, debemos crear una nueva raíz.
            nuevaRaiz = new BNode<>(orden);
            nuevaRaiz.count = 1;
            nuevaRaiz.keys.set(0, mediana);
            nuevaRaiz.childs.set(0, root);
            nuevaRaiz.childs.set(1, nDes);
            root = nuevaRaiz;
        }
    }

    // Inserción recursiva. Busca la posición correcta para insertar.
    private E push(BNode<E> current, E cl) {

        int pos[] = new int[1];
        E mediana;

        if (current == null) {    // Llegamos a una hoja. La clave debe insertarse aquí.
            up = true;
            nDes = null;
            return cl;
        }

        boolean found;
        found = current.searchNode(cl, pos);   // Busca la clave dentro del nodo actual.

        if (found) {        // Evita insertar duplicados.
            System.out.println("Elemento duplicado");
            up = false;
            return null;
        }

        // Continúa buscando recursivamente en el hijo correspondiente.
        mediana = push(current.childs.get(pos[0]), cl);

        //Si una clave subió desde abajo.
        if (up) {

            // Si el nodo está lleno debemos dividirlo.
            if (current.nodeFull(orden - 1)) {
                mediana = dividedNode(current, mediana, pos[0]);

            } else {    // Si hay espacio simplemente insertamos la clave.
                up = false;
                putNode(current, mediana, nDes, pos[0]);
            }
        }
        return mediana;
    }

    // Inserta una clave dentro de un nodo que todavía tiene espacio.
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {

        int i;

        // Desplaza claves e hijos para abrir espacio.
        for (i = current.count - 1; i >= k; i--) {

            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }

        current.keys.set(k, cl);        // Inserta la nueva clave.
        current.childs.set(k + 1, rd);  // Conecta el nuevo hijo derecho.
        current.count++;
    }

    // Divide un nodo lleno. Es el corazón del Árbol B.
    private E dividedNode(BNode<E> current, E cl, int k) {

        BNode<E> rd = nDes;
        int posMdna;

        // Determina la posición de la mediana.
        if (k <= orden / 2) {
            posMdna = orden / 2;
        } else {
            posMdna = orden / 2 + 1;
        }

        nDes = new BNode<>(orden);     // Crea el nuevo nodo derecho.

        // Copia la mitad derecha al nuevo nodo.
        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        // Actualiza cantidad de claves de ambos nodos.
        nDes.count = (orden - 1) - posMdna;
        current.count = posMdna;

        // Inserta la nueva clave en el lado correcto.
        if (k <= orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);     // La clave central sube.
        nDes.childs.set(0, current.childs.get(current.count));  // Ajusta los enlaces entre nodos.

        current.count--;
        return median;
    }

    // Convierte todo el árbol en texto.
    @Override
    public String toString() {

        if (isEmpty()) {
            return "Árbol vacío";
        }

        return writeTree(root);
    }

    // Recorre recursivamente el árbol y construye una representación en texto.
    private String writeTree(BNode<E> current) {

        if (current == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(current).append("\n");

        //Recorre todos los hijos.
        for (int i = 0; i <= current.count; i++) {
            sb.append(writeTree(current.childs.get(i)));
        }
        return sb.toString();
    }
}