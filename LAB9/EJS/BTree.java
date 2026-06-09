public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // ─────────────────────── INSERT ───────────────────────
    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
        }
        return mediana;
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // ─────────────────────── 01. SEARCH ───────────────────────
    /**
     * Busca una clave en el árbol B.
     * @return true si la clave existe, false en caso contrario.
     * Imprime el nodo e índice donde se encontró.
     */
    public boolean search(E cl) {
        return searchHelper(root, cl);
    }

    private boolean searchHelper(BNode<E> current, E cl) {
        if (current == null) return false;
        int pos = 0;
        // Avanzar mientras la clave buscada sea mayor que la clave actual
        while (pos < current.count && cl.compareTo(current.keys.get(pos)) > 0) {
            pos++;
        }
        // Clave encontrada en este nodo
        if (pos < current.count && cl.compareTo(current.keys.get(pos)) == 0) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos);
            return true;
        }
        // Descender al hijo correspondiente
        return searchHelper(current.childs.get(pos), cl);
    }

    // ─────────────────────── 02. SEARCH RANGE ───────────────────────
    /**
     * Muestra todas las claves en [min, max] en orden ascendente.
     *
     * Descarte de ramas:
     *  - Si todas las claves del nodo son < min  → solo bajar por el hijo más a la derecha visitado.
     *  - Si todas las claves del nodo son > max  → solo bajar por el hijo más a la izquierda visitado.
     *  - Si el hijo-i lleva exclusivamente a claves < min → se descarta (pos < startPos).
     *  - Si el hijo-i lleva exclusivamente a claves > max → se descarta (pos > endPos+1).
     * Esto evita recorrer ramas que no pueden contener claves en el rango.
     */
    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Rango inválido: min (" + min + ") es mayor que max (" + max + ").");
            return;
        }
        System.out.print("Claves en [" + min + ", " + max + "]: ");
        boolean[] found = {false};
        searchRangeHelper(root, min, max, found);
        if (!found[0]) System.out.print("(ninguna clave en ese rango)");
        System.out.println();
    }

    private void searchRangeHelper(BNode<E> current, E min, E max, boolean[] found) {
        if (current == null) return;

        // startPos: primer índice con clave >= min
        int startPos = 0;
        while (startPos < current.count && min.compareTo(current.keys.get(startPos)) > 0) {
            startPos++;
        }

        // endPos: último índice con clave <= max
        int endPos = current.count - 1;
        while (endPos >= 0 && max.compareTo(current.keys.get(endPos)) < 0) {
            endPos--;
        }

        // Bajar por el hijo startPos (primero que puede tener claves >= min)
        searchRangeHelper(current.childs.get(startPos), min, max, found);

        // Recorrer claves del nodo dentro del rango
        for (int i = startPos; i <= endPos; i++) {
            E key = current.keys.get(i);
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                System.out.print(key + " ");
                found[0] = true;
            }
            // Bajar por cada hijo entre claves dentro del rango
            searchRangeHelper(current.childs.get(i + 1), min, max, found);
        }
    }

    // ─────────────────────── 03. REMOVE ───────────────────────
    /**
     * Elimina una clave del árbol B.
     * Si el nodo queda con menos del mínimo de claves se realiza:
     *  1. Redistribución con hermano izquierdo o derecho (si es posible).
     *  2. Fusión (merge) con un hermano si no hay redistribución posible.
     */
    public void remove(E cl) {
        if (isEmpty()) { System.out.println("Árbol vacío."); return; }
        boolean[] underflow = {false};
        removeHelper(root, cl, underflow);
        // Si la raíz quedó vacía (fusión total), bajar la raíz
        if (root.count == 0) {
            root = root.childs.get(0);
        }
    }

    private void removeHelper(BNode<E> current, E cl, boolean[] underflow) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            underflow[0] = false;
            return;
        }
        int pos = 0;
        while (pos < current.count && cl.compareTo(current.keys.get(pos)) > 0) pos++;

        if (pos < current.count && cl.compareTo(current.keys.get(pos)) == 0) {
            // Clave encontrada en current
            if (current.childs.get(0) == null) {
                // Nodo hoja: eliminar directamente
                removeFromLeaf(current, pos);
                underflow[0] = current.count < minKeys();
            } else {
                // Nodo interno: reemplazar con sucesor inorden (mínimo del subárbol derecho)
                E succ = getMinKey(current.childs.get(pos + 1));
                current.keys.set(pos, succ);
                // Eliminar el sucesor del subárbol derecho
                removeHelper(current.childs.get(pos + 1), succ, underflow);
                if (underflow[0]) fixUnderflow(current, pos + 1, underflow);
            }
        } else {
            // Descender al hijo correspondiente
            removeHelper(current.childs.get(pos), cl, underflow);
            if (underflow[0]) fixUnderflow(current, pos, underflow);
        }
    }

    private void removeFromLeaf(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private E getMinKey(BNode<E> node) {
        while (node.childs.get(0) != null) node = node.childs.get(0);
        return node.keys.get(0);
    }

    private int minKeys() {
        return (int) Math.ceil((orden - 1) / 2.0) - 1;
    }

    /**
     * Corrige el underflow en current.childs.get(childIdx).
     * Intenta redistribuir con hermano izquierdo, luego derecho.
     * Si no, fusiona.
     */
    private void fixUnderflow(BNode<E> parent, int childIdx, boolean[] underflow) {
        BNode<E> child = parent.childs.get(childIdx);
        BNode<E> leftSibling = (childIdx > 0) ? parent.childs.get(childIdx - 1) : null;
        BNode<E> rightSibling = (childIdx < parent.count) ? parent.childs.get(childIdx + 1) : null;

        int minK = minKeys();

        if (leftSibling != null && leftSibling.count > minK) {
            // Redistribuir desde hermano izquierdo
            redistributeFromLeft(parent, child, leftSibling, childIdx);
            underflow[0] = false;
        } else if (rightSibling != null && rightSibling.count > minK) {
            // Redistribuir desde hermano derecho
            redistributeFromRight(parent, child, rightSibling, childIdx);
            underflow[0] = false;
        } else if (leftSibling != null) {
            // Fusionar child con hermano izquierdo
            merge(parent, leftSibling, child, childIdx - 1);
            underflow[0] = parent.count < minK;
        } else {
            // Fusionar child con hermano derecho
            merge(parent, child, rightSibling, childIdx);
            underflow[0] = parent.count < minK;
        }
    }

    private void redistributeFromLeft(BNode<E> parent, BNode<E> child, BNode<E> left, int childIdx) {
        // Desplazar claves de child a la derecha
        child.childs.set(child.count + 1, child.childs.get(child.count));
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
            child.childs.set(i, child.childs.get(i - 1));
        }
        // Bajar separador del padre
        child.keys.set(0, parent.keys.get(childIdx - 1));
        child.childs.set(0, left.childs.get(left.count));
        child.count++;
        // Subir la última clave del hermano izquierdo al padre
        parent.keys.set(childIdx - 1, left.keys.get(left.count - 1));
        left.keys.set(left.count - 1, null);
        left.childs.set(left.count, null);
        left.count--;
    }

    private void redistributeFromRight(BNode<E> parent, BNode<E> child, BNode<E> right, int childIdx) {
        // Bajar separador del padre al final de child
        child.keys.set(child.count, parent.keys.get(childIdx));
        child.childs.set(child.count + 1, right.childs.get(0));
        child.count++;
        // Subir la primera clave del hermano derecho al padre
        parent.keys.set(childIdx, right.keys.get(0));
        // Desplazar claves de right a la izquierda
        right.childs.set(0, right.childs.get(1));
        for (int i = 0; i < right.count - 1; i++) {
            right.keys.set(i, right.keys.get(i + 1));
            right.childs.set(i + 1, right.childs.get(i + 2));
        }
        right.keys.set(right.count - 1, null);
        right.childs.set(right.count, null);
        right.count--;
    }

    /**
     * Fusiona left + separador_padre + right → left.
     * Elimina el separador del padre y el puntero a right.
     */
    private void merge(BNode<E> parent, BNode<E> left, BNode<E> right, int sepIdx) {
        // Bajar separador del padre a left
        left.keys.set(left.count, parent.keys.get(sepIdx));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;
        // Copiar claves y hijos de right a left
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }
        // Eliminar separador del padre y puntero a right
        for (int i = sepIdx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    // ─────────────────────── UTILIDADES ───────────────────────
    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(BNode<E> current) {
        if (current == null) return;
        for (int i = 0; i < current.count; i++) {
            inorderHelper(current.childs.get(i));
            System.out.print(current.keys.get(i) + " ");
        }
        inorderHelper(current.childs.get(current.count));
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(BNode<E> current) {
        if (current == null) return 0;
        return 1 + heightHelper(current.childs.get(0));
    }

    public int totalKeys() {
        return countKeys(root);
    }

    private int countKeys(BNode<E> current) {
        if (current == null) return 0;
        int count = current.count;
        for (int i = 0; i <= current.count; i++) count += countKeys(current.childs.get(i));
        return count;
    }

    /** Muestra el árbol nivel por nivel */
    public void printTree() {
        if (isEmpty()) { System.out.println("Árbol vacío."); return; }
        java.util.Queue<BNode<E>> queue = new java.util.LinkedList<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            System.out.print("Nivel " + level + ": ");
            for (int i = 0; i < sz; i++) {
                BNode<E> node = queue.poll();
                System.out.print(node + "  ");
                for (int j = 0; j <= node.count; j++) {
                    if (node.childs.get(j) != null) queue.add(node.childs.get(j));
                }
            }
            System.out.println();
            level++;
        }
    }

    public BNode<E> getRoot() { return root; }
    public int getOrden() { return orden; }
}
