// ============================================================
//  EJ3 – remove(E cl)
//  Elimina una clave del Árbol B manteniendo el balance.
//
//  CASOS DE ELIMINACIÓN:
//    ① Clave en nodo HOJA       → eliminar directamente.
//    ② Clave en nodo INTERNO    → reemplazar con sucesor inorden
//                                  (mínimo del subárbol derecho)
//                                  y eliminar el sucesor en la hoja.
//
//  CORRECCIÓN DE UNDERFLOW (nodo con < ⌈(orden-1)/2⌉ - 1 claves):
//    a) Redistribuir desde hermano IZQUIERDO (si tiene claves extra).
//       Rotación: última clave del hermano sube al padre,
//                 separador del padre baja al nodo deficiente.
//    b) Redistribuir desde hermano DERECHO   (si tiene claves extra).
//       Rotación: primera clave del hermano sube al padre,
//                 separador del padre baja al nodo deficiente.
//    c) FUSIÓN (merge) con hermano izquierdo o derecho
//       cuando ninguno tiene claves extra:
//       nodo_izq + separador_padre + nodo_der → nodo_izq
//       El padre pierde un separador → puede propagar underflow.
//    d) Si la raíz queda vacía tras una fusión → bajar un nivel.
// ============================================================

public class EJ3_Remove {

    // ── Método público ──────────────────────────────────────
    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("Árbol vacío.");
            return;
        }
        boolean[] underflow = {false};
        removeHelper(root, cl, underflow);

        // Si la raíz quedó vacía tras una fusión total, bajar la raíz
        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }
    }

    // ── Método de soporte: recorrido recursivo ──────────────
    private void removeHelper(BNode<E> current, E cl,
                              boolean[] underflow) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            underflow[0] = false;
            return;
        }

        int pos = 0;
        while (pos < current.count
                && cl.compareTo(current.keys.get(pos)) > 0) pos++;

        if (pos < current.count
                && cl.compareTo(current.keys.get(pos)) == 0) {

            if (current.childs.get(0) == null) {
                // ① HOJA: eliminar directamente
                removeFromLeaf(current, pos);
                underflow[0] = current.count < minKeys();

            } else {
                // ② INTERNO: reemplazar con sucesor inorden
                E succ = getMinKey(current.childs.get(pos + 1));
                current.keys.set(pos, succ);
                removeHelper(current.childs.get(pos + 1), succ, underflow);
                if (underflow[0]) fixUnderflow(current, pos + 1, underflow);
            }

        } else {
            // Descender al hijo correspondiente
            removeHelper(current.childs.get(pos), cl, underflow);
            if (underflow[0]) fixUnderflow(current, pos, underflow);
        }
    }

    // ── Eliminar clave de una hoja ──────────────────────────
    private void removeFromLeaf(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    // ── Sucesor inorden: mínimo del subárbol ───────────────
    private E getMinKey(BNode<E> node) {
        while (node.childs.get(0) != null) node = node.childs.get(0);
        return node.keys.get(0);
    }

    // ── Mínimo de claves permitido por nodo ────────────────
    private int minKeys() {
        return (int) Math.ceil((orden - 1) / 2.0) - 1;
    }

    // ── fixUnderflow: redistribuir o fusionar ──────────────
    private void fixUnderflow(BNode<E> parent, int childIdx,
                              boolean[] underflow) {
        BNode<E> child = parent.childs.get(childIdx);
        BNode<E> left  = (childIdx > 0)
                ? parent.childs.get(childIdx - 1) : null;
        BNode<E> right = (childIdx < parent.count)
                ? parent.childs.get(childIdx + 1) : null;
        int minK = minKeys();

        if (left != null && left.count > minK) {
            // a) Redistribuir desde hermano izquierdo
            redistributeFromLeft(parent, child, left, childIdx);
            underflow[0] = false;

        } else if (right != null && right.count > minK) {
            // b) Redistribuir desde hermano derecho
            redistributeFromRight(parent, child, right, childIdx);
            underflow[0] = false;

        } else if (left != null) {
            // c) Fusionar con hermano izquierdo
            merge(parent, left, child, childIdx - 1);
            underflow[0] = parent.count < minK;

        } else {
            // c) Fusionar con hermano derecho
            merge(parent, child, right, childIdx);
            underflow[0] = parent.count < minK;
        }
    }

    // ── a) Redistribuir desde hermano izquierdo ─────────────
    //   última_clave(left) → padre → primera_clave(child)
    private void redistributeFromLeft(BNode<E> parent, BNode<E> child,
                                      BNode<E> left, int childIdx) {
        // Desplazar child una posición a la derecha
        child.childs.set(child.count + 1, child.childs.get(child.count));
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
            child.childs.set(i, child.childs.get(i - 1));
        }
        // Bajar separador del padre a child[0]
        child.keys.set(0, parent.keys.get(childIdx - 1));
        child.childs.set(0, left.childs.get(left.count));
        child.count++;
        // Subir la última clave de left al padre
        parent.keys.set(childIdx - 1, left.keys.get(left.count - 1));
        left.keys.set(left.count - 1, null);
        left.childs.set(left.count, null);
        left.count--;
    }

    // ── b) Redistribuir desde hermano derecho ───────────────
    //   primera_clave(right) → padre → última_clave(child)
    private void redistributeFromRight(BNode<E> parent, BNode<E> child,
                                       BNode<E> right, int childIdx) {
        // Bajar separador del padre al final de child
        child.keys.set(child.count, parent.keys.get(childIdx));
        child.childs.set(child.count + 1, right.childs.get(0));
        child.count++;
        // Subir la primera clave de right al padre
        parent.keys.set(childIdx, right.keys.get(0));
        // Desplazar right una posición a la izquierda
        right.childs.set(0, right.childs.get(1));
        for (int i = 0; i < right.count - 1; i++) {
            right.keys.set(i, right.keys.get(i + 1));
            right.childs.set(i + 1, right.childs.get(i + 2));
        }
        right.keys.set(right.count - 1, null);
        right.childs.set(right.count, null);
        right.count--;
    }

    // ── c) Fusión: left + sep_padre + right → left ──────────
    private void merge(BNode<E> parent, BNode<E> left, BNode<E> right,
                       int sepIdx) {
        // Bajar separador del padre a left
        left.keys.set(left.count, parent.keys.get(sepIdx));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;
        // Copiar claves e hijos de right a left
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

    // ── Pruebas ─────────────────────────────────────────────
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> bt = new BTree<>(5);

        int[] vals = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int v : vals) bt.insert(v);

        System.out.println("Árbol inicial:");
        bt.printTree();
        System.out.print("Inorder: "); bt.inorder();

        // 1) Hoja extremo izquierdo
        System.out.println("\n-- remove(10) [hoja, extremo izquierdo] --");
        bt.remove(10);
        System.out.print("Inorder: "); bt.inorder();

        // 2) Hoja extremo derecho
        System.out.println("-- remove(90) [hoja, extremo derecho] --");
        bt.remove(90);
        System.out.print("Inorder: "); bt.inorder();

        // 3) Clave en la raíz
        System.out.println("-- remove(" + bt.getRoot().keys.get(0) + ") [raíz] --");
        bt.remove(bt.getRoot().keys.get(0));
        System.out.print("Inorder: "); bt.inorder();

        // 4) Clave inexistente
        System.out.println("-- remove(999) [no existe] --");
        bt.remove(999);
        System.out.print("Inorder: "); bt.inorder();

        // 5) Con posible redistribución / fusión
        System.out.println("-- remove(40) [posible underflow] --");
        bt.remove(40);
        System.out.print("Inorder: "); bt.inorder();
    }
}
