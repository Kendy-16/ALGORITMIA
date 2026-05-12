public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // Raíz del árbol
    private Node<E> root;

    public LinkedBST() {
        root = null;
    }

    // Verifica si está vacío
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // Inserta un dato
    @Override
    public void insert(E data) throws ItemDuplicated {

        // Si el árbol está vacío
        if (root == null) {
            root = new Node<>(data);
            return;
        }

        Node<E> current = root;

        while (true) {

            // Valida duplicados
            if (data.compareTo(current.getData()) == 0) {
                throw new ItemDuplicated();
            }

            if (data.compareTo(current.getData()) < 0) {    // Va a la izquierda

                // Inserta si está vacío
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(data));
                    return;
                }

                // Avanza
                current = current.getLeft();
                
            } else {                                        // Va a la derecha

                // Inserta si está vacío
                if (current.getRight() == null) {
                    current.setRight(new Node<>(data));
                    return;
                }

                // Avanza
                current = current.getRight();
            }
        }
    }

    // Busca un dato
    @Override
    public E search(E data) throws ItemNoFound {

        Node<E> current = root;

        while (current != null) {
            
            if (data.compareTo(current.getData()) == 0) {
                return current.getData();       // Dato encontrado
            }

            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();    // Busca izquierda
            } else {
                current = current.getRight();   // Busca derecha
            }
        }

        throw new ItemNoFound();        // No encontrado
    }

    //Encontrar el minimo
    private Node<E> findMin(Node<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    //Encontrar el maximo
    private Node<E> findMax(Node<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node;
    }

    // Retorna el menor valor del árbol
    public E getMin() {

        if (root == null) {
            return null;
        }

        return findMin(root).getData();
    }

    // Retorna el mayor valor del árbol
    public E getMax() {

        if (root == null) {
            return null;
        }

        return findMax(root).getData();
    }

    //Elimina un Nodo
    private Node<E> deleteNode(Node<E> node, E data) {

        if (node == null) {
            return null;    // Nodo no encontrado
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(deleteNode(node.getLeft(), data)); // Busca izquierda
        
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(deleteNode(node.getRight(), data)); // Busca derecha
        
        } else {

            // Caso 1: sin hijos
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            // Caso 2: solo hijo derecho
            if (node.getLeft() == null) {
                return node.getRight();
            }

            // Caso 3: solo hijo izquierdo
            if (node.getRight() == null) {
                return node.getLeft();
            }

            // Caso 3: dos hijos
            Node<E> min = findMin(node.getRight()); // obtener dato menor
            node.setData(min.getData()); // sobreescribirlo
            node.setRight(deleteNode(node.getRight(),min.getData())); // borrar menor duplicado
        }
        return node;
    }

    // Elimina un dato
    @Override
    public void delete(E data) throws ItemNoFound, ExceptionIsEmpty {

        // Valida árbol vacío
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }

        // Valida existencia
        search(data);

        root = deleteNode(root, data);
    }


    // Vacía el árbol
    @Override
    public void destroy() throws ExceptionIsEmpty {

        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        root = null;
    }

    // Método auxiliar InOrder
    private String inOrder(Node<E> node) {

        if (node == null) {
            return "";
        }

        // izquierda -> raíz -> derecha
        return inOrder(node.getLeft())+node.getData()+" "+ inOrder(node.getRight());
    }

    // Recorrido InOrder
    @Override
    public String inOrder() {
        return inOrder(root);
    }

    // Método auxiliar PreOrder
    private String preOrder(Node<E> node) {

        if (node == null) {
            return "";
        }

        // raíz -> izquierda -> derecha
        return node.getData()+" "+ preOrder(node.getLeft())+preOrder(node.getRight());
    }

    // Recorrido PreOrder
    @Override
    public String preOrder() {
        return preOrder(root);
    }

    // Método auxiliar PostOrder
    private String postOrder(Node<E> node) {

        // Caso base
        if (node == null) {
            return "";
        }

        // izquierda -> derecha -> raíz
        return postOrder(node.getLeft())
                + postOrder(node.getRight())
                + node.getData() + " ";
    }

    // Recorrido PostOrder
    @Override
    public String postOrder() {
        return postOrder(root);
    }

    // Muestra árbol
    @Override
    public String toString() {
        return inOrder();
    }
}