package listlinked;

public class ListLinked<E> {

    private Node<E> head;
    private int size;

    public ListLinked() {

        head = null;
        size = 0;
    }

    // AGREGAR AL FINAL
    public void addLast(E data) {

        Node<E> newNode = new Node<>(data);

        if (head == null) {

            head = newNode;
        } else {

            Node<E> temp = head;

            while (temp.next != null) {

                temp = temp.next;
            }

            temp.next = newNode;
        }

        size++;
    }

    // OBTENER ELEMENTO
    public E get(int index) {

        if (index < 0 || index >= size) {

            return null;
        }

        Node<E> temp = head;

        for (int i = 0; i < index; i++) {

            temp = temp.next;
        }

        return temp.data;
    }

    // TAMAÑO
    public int size() {

        return size;
    }
}