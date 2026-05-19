public class AVLNode<E> extends Node<E> {

    protected int height;

    public AVLNode(E data) {
        super(data);
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}