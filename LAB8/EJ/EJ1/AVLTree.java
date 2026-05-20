public class AVLTree<E extends Comparable<E>> extends LinkedBST<E> {

    // Obtiene altura
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }

        AVLNode<E> avl = (AVLNode<E>) node;
        return avl.getHeight();
    }

    // Calcula balance
    private int getBalance(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return height(node.getRight()) - height(node.getLeft());
    }

    // Actualiza altura
    private void updateHeight(AVLNode<E> node) {

        int leftH = height(node.getLeft());
        int rightH = height(node.getRight());

        if (leftH > rightH) {
            node.setHeight(leftH + 1);
        } else {
            node.setHeight(rightH + 1);
        }
    }

    // Rotación simple izquierda
    private AVLNode<E> rotateLeft(AVLNode<E> x) {

        AVLNode<E> y = (AVLNode<E>) x.getRight();
        Node<E> temp = y.getLeft();

        y.setLeft(x);
        x.setRight(temp);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Rotación simple derecha
    private AVLNode<E> rotateRight(AVLNode<E> y) {

        AVLNode<E> x = (AVLNode<E>) y.getLeft();
        Node<E> temp = x.getRight();

        x.setRight(y);
        y.setLeft(temp);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Inserción recursiva AVL
    private AVLNode<E> insertAVL(AVLNode<E> node, E data)
            throws ItemDuplicated {

        if (node == null) {
            return new AVLNode<E>(data);
        }

        int cmp = data.compareTo(node.getData());

        if (cmp < 0) {
            node.setLeft(insertAVL((AVLNode<E>) node.getLeft(), data));
        }

        else if (cmp > 0) {
            node.setRight(insertAVL((AVLNode<E>) node.getRight(), data));
        }

        else {
            throw new ItemDuplicated("Dato duplicado");
        }

        updateHeight(node);

        int balance = getBalance(node);

        // Derecha-Derecha
        if (balance == 2 &&
                data.compareTo(node.getRight().getData()) > 0) {

            return rotateLeft(node);
        }

        // Izquierda-Izquierda
        if (balance == -2 &&
                data.compareTo(node.getLeft().getData()) < 0) {

            return rotateRight(node);
        }

        // Derecha-Izquierda
        if (balance == 2 &&
                data.compareTo(node.getRight().getData()) < 0) {

            node.setRight(
                    rotateRight((AVLNode<E>) node.getRight())
            );

            return rotateLeft(node);
        }

        // Izquierda-Derecha
        if (balance == -2 &&
                data.compareTo(node.getLeft().getData()) > 0) {

            node.setLeft(
                    rotateLeft((AVLNode<E>) node.getLeft())
            );

            return rotateRight(node);
        }

        return node;
    }

    // Inserción pública
    @Override
    public void insert(E data) throws ItemDuplicated {

        root = insertAVL((AVLNode<E>) root, data);
    }
}