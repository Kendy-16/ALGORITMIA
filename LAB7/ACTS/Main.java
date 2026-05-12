public class Main {
    public static void main(String[] args) {

        // Crear árbol
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {

            // Insertar datos
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);

            // Mostrar recorrido InOrder
            System.out.println("Recorrido InOrder:");
            System.out.println(bst.inOrder());

        } catch (ItemDuplicated e) {

            System.out.println(e.getMessage());
        }
    }
}