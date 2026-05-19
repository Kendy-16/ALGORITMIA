// Interfaz BST
public interface BinarySearchTree<E extends Comparable<E>> {
    
    void insert(E data) throws ItemDuplicated;                  // Inserta un dato
    E search(E data) throws ItemNoFound;                        // Busca un dato
    void delete(E data) throws ItemNoFound, ExceptionIsEmpty;   // Elimina un dato
    boolean isEmpty();                                          // Verifica si está vacío
    void destroy() throws ExceptionIsEmpty;                     // Vacía el árbol
    String inOrder();                                           // Recorrido InOrder
    String preOrder();                                          // Recorrido PreOrder
    String postOrder();                                         // Recorrido PostOrder
}