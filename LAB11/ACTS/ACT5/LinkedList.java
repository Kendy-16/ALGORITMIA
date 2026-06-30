public class LinkedList<T> {

    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    // Inserta un nuevo registro al final de la lista
    public void insert(Register<T> register) {

        Node<T> newNode = new Node<>(register);

        // Si la lista está vacía, el nuevo nodo será el primero
        if (head == null) {
            head = newNode;
            return;
        }

        // Recorre la lista hasta el último nodo
        Node<T> current = head;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        // Enlaza el nuevo nodo al final
        current.setNext(newNode);
    }

    // Busca un registro por su clave
    public Register<T> search(int key) {

        Node<T> current = head;

        while (current != null) {

            if (current.getData().getKey() == key) {
                return current.getData();
            }

            current = current.getNext();
        }

        return null;
    }

    // Elimina un registro por su clave
    public boolean delete(int key) {

        if (head == null)
            return false;

        // Si el primer nodo contiene la clave
        if (head.getData().getKey() == key) {
            head = head.getNext();
            return true;
        }

        Node<T> previous = head;
        Node<T> current = head.getNext();

        while (current != null) {

            if (current.getData().getKey() == key) {

                previous.setNext(current.getNext());

                return true;
            }

            previous = current;
            current = current.getNext();
        }

        return false;
    }

    // Muestra todos los registros de la lista
    public void print() {

        Node<T> current = head;

        if (current == null) {
            System.out.print("vacío");
            return;
        }

        while (current != null) {

            System.out.print(current.getData());

            if (current.getNext() != null)
                System.out.print(" -> ");

            current = current.getNext();
        }
    }

    // Indica si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }
}