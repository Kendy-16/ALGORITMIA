public class MainComparacion {
    public static void main(String[] args) {

        try {

            System.out.println("========================================");
            System.out.println("CASO DE PRUEBA 1");
            System.out.println("Secuencia: 10 20 30 40 50");
            System.out.println("========================================");

            LinkedBST<Integer> bst1 = new LinkedBST<Integer>();
            AVLTree<Integer> avl1 = new AVLTree<Integer>();

            // -----------------------------------------------------
            System.out.println("\nINSERTANDO EN BST");
            bst1.insert(10);
            bst1.insert(20);
            bst1.insert(30);
            bst1.insert(40);
            bst1.insert(50);

            System.out.println("Recorrido BST:");
            System.out.println(bst1.inOrder());

            System.out.println("El BST tiende a desbalancearse cuando los datos se insertan ordenados");

            // -----------------------------------------------------
            System.out.println("\nINSERTANDO EN AVL");
            avl1.insert(10);
            avl1.insert(20);
            avl1.insert(30);

            System.out.println("Se produce desbalance DD");
            System.out.println("El AVL aplica Rotacion Simple Izquierda");

            avl1.insert(40);
            avl1.insert(50);

            System.out.println("Recorrido AVL:");
            System.out.println(avl1.inOrder());

            System.out.println("El AVL mantiene el arbol balanceado");

            // -----------------------------------------------------
            System.out.println("\nBUSQUEDAS");
            System.out.println("Buscar 30 en BST: "+ bst1.search(30));
            System.out.println("Buscar 30 en AVL: "+ avl1.search(30));

            // -----------------------------------------------------
            System.out.println("\n\n========================================");
            System.out.println("CASO DE PRUEBA 2");
            System.out.println("Secuencia: 50 40 30 20 10");
            System.out.println("========================================");

            LinkedBST<Integer> bst2 = new LinkedBST<Integer>();
            AVLTree<Integer> avl2 = new AVLTree<Integer>();

            // -----------------------------------------------------
            System.out.println("\nINSERTANDO EN BST");

            bst2.insert(50);
            bst2.insert(40);
            bst2.insert(30);
            bst2.insert(20);
            bst2.insert(10);

            System.out.println("Recorrido BST:");
            System.out.println(bst2.inOrder());

            System.out.println("El BST vuelve a desbalancearse dependiendo del orden de insercion");

            // -----------------------------------------------------
            System.out.println("\nINSERTANDO EN AVL");

            avl2.insert(50);
            avl2.insert(40);
            avl2.insert(30);

            System.out.println("Se produce desbalance II");
            System.out.println("El AVL aplica Rotacion Simple Derecha");

            avl2.insert(20);
            avl2.insert(10);

            System.out.println("Recorrido AVL:");
            System.out.println(avl2.inOrder());

            System.out.println("El AVL mantiene nuevamente el equilibrio");

            // -----------------------------------------------------
            System.out.println("\nBUSQUEDAS");

            System.out.println("Buscar 20 en BST: "+ bst2.search(20));
            System.out.println("Buscar 20 en AVL: "+ avl2.search(20));

            // -----------------------------------------------------
            System.out.println("\n========================================");
            System.out.println("CONCLUSION");
            System.out.println("========================================");

            System.out.println("El BST puede crecer desbalanceado dependiendo del orden de insercion.");
            System.out.println("El AVL aplica rotaciones automaticamente para conservar una altura balanceada.");

        } catch (ItemDuplicated e) {

            System.out.println(e.getMessage());
        } catch (ItemNoFound e) {

            System.out.println(e.getMessage());
        }
    }
}