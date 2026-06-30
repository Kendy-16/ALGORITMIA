public class Node<T> {

    private Register<T> data;
    private Node<T> next;

    public Node(Register<T> data) {
        this.data = data;
        this.next = null;
    }

    public Register<T> getData() {
        return data;
    }

    public void setData(Register<T> data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}