public class Main {
    public static void main(String[] args) {

        // Crear árbol
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            System.out.println("¿Árbol vacío?");
            System.out.println(bst.isEmpty());
            System.out.println("======================================");

            // Insertar datos
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);
            System.out.println("Datos insertados");
            System.out.println("======================================");

            // InOrder
            System.out.println("Recorrido InOrder:");
            System.out.println(bst.inOrder());
            System.out.println("======================================");

            // PreOrder
            System.out.println("Recorrido PreOrder:");
            System.out.println(bst.preOrder());
            System.out.println("======================================");

            // PostOrder
            System.out.println("Recorrido PostOrder:");
            System.out.println(bst.postOrder());
            System.out.println("======================================");

            // Buscar elemento
            System.out.println("Buscar 200:");
            System.out.println(bst.search(200));
            System.out.println("======================================");

            // Mostrar mínimo
            System.out.println("Valor mínimo:");
            System.out.println(bst.getMin());
            System.out.println("======================================");

            // Mostrar máximo
            System.out.println("Valor máximo:");
            System.out.println(bst.getMax());
            System.out.println("======================================");

            // Eliminar nodo
            bst.delete(100);
            System.out.println("Árbol después de eliminar 100:");
            System.out.println(bst.inOrder());
            System.out.println("======================================");

            // Mostrar toString
            System.out.println("toString:");
            System.out.println(bst.toString());
            System.out.println("======================================");

            // Vaciar árbol
            bst.destroy();
            System.out.println("Árbol destruido");
            System.out.println("======================================");

            // Verificar vacío
            System.out.println("¿Árbol vacío?");
            System.out.println(bst.isEmpty());

        } catch (ItemDuplicated e) {

            System.out.println("Error duplicado");
            System.out.println(e.getMessage());

        } catch (ItemNoFound e) {

            System.out.println("Dato no encontrado");
            System.out.println(e.getMessage());

        } catch (ExceptionIsEmpty e) {

            System.out.println("Árbol vacío");
            System.out.println(e.getMessage());
        }
    }
}
