public class Main {
    public static void main(String[] args) {

        // CASO 1 : DERECHA - DERECHA (DD)
        System.out.println("====================================");
        System.out.println("CASO DD -> Rotacion Simple Izquierda");
        System.out.println("====================================");

        AVLTree<Integer> avlDD = new AVLTree<Integer>();

        try {

            System.out.println("Insertando: 10");
            avlDD.insert(10);

            System.out.println("Insertando: 20");
            avlDD.insert(20);

            System.out.println("Insertando: 30");
            avlDD.insert(30);

            System.out.println("Se produce desbalance DD");
            System.out.println("Se aplica Rotacion Simple Izquierda");

            System.out.println("Recorrido InOrder:");
            System.out.println(avlDD.inOrder());

        }

        catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // CASO 2 : IZQUIERDA - IZQUIERDA (II)
        System.out.println("\n====================================");
        System.out.println("CASO II -> Rotacion Simple Derecha");
        System.out.println("====================================");

        AVLTree<Integer> avlII = new AVLTree<Integer>();

        try {

            System.out.println("Insertando: 30");
            avlII.insert(30);

            System.out.println("Insertando: 20");
            avlII.insert(20);

            System.out.println("Insertando: 10");
            avlII.insert(10);

            System.out.println("Se produce desbalance II");
            System.out.println("Se aplica Rotacion Simple Derecha");

            System.out.println("Recorrido InOrder:");
            System.out.println(avlII.inOrder());

        }

        catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // CASO 3 : IZQUIERDA - DERECHA (ID)
        System.out.println("\n====================================");
        System.out.println("CASO ID -> Rotacion Doble");
        System.out.println("====================================");

        AVLTree<Integer> avlID = new AVLTree<Integer>();

        try {

            System.out.println("Insertando: 30");
            avlID.insert(30);

            System.out.println("Insertando: 10");
            avlID.insert(10);

            System.out.println("Insertando: 20");
            avlID.insert(20);

            System.out.println("Se produce desbalance ID");
            System.out.println("Se aplica Rotacion Doble Izquierda-Derecha");

            System.out.println("Recorrido InOrder:");
            System.out.println(avlID.inOrder());

        }

        catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // CASO 4 : DERECHA - IZQUIERDA (DI)
        System.out.println("\n====================================");
        System.out.println("CASO DI -> Rotacion Doble");
        System.out.println("====================================");

        AVLTree<Integer> avlDI = new AVLTree<Integer>();

        try {

            System.out.println("Insertando: 10");
            avlDI.insert(10);

            System.out.println("Insertando: 30");
            avlDI.insert(30);

            System.out.println("Insertando: 20");
            avlDI.insert(20);

            System.out.println("Se produce desbalance DI");
            System.out.println("Se aplica Rotacion Doble Derecha-Izquierda");

            System.out.println("Recorrido InOrder:");
            System.out.println(avlDI.inOrder());

        }

        catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // PRUEBA EXTRA
        System.out.println("\n====================================");
        System.out.println("PRUEBA GENERAL AVL");
        System.out.println("====================================");

        AVLTree<Integer> avl = new AVLTree<Integer>();

        try {

            avl.insert(50);
            avl.insert(30);
            avl.insert(70);

            avl.insert(20);
            avl.insert(40);

            avl.insert(60);
            avl.insert(80);

            avl.insert(10);
            avl.insert(25);

            System.out.println("Recorrido InOrder:");
            System.out.println(avl.inOrder());

            System.out.println("\nArbol AVL balanceado correctamente");

        }

        catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }
}