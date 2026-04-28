public class Main {

    public static void main(String[] args) {
        
        System.out.println("===== EJERCICIO 1: Buscar elemento =====");
        
        ListLinked<Integer> lista1 = new ListLinked<>();
        
        lista1.insertLast(10);
        lista1.insertLast(20);
        lista1.insertLast(30);
        
        boolean existe = MetodosLista.buscarElemento(lista1, 20);
        System.out.println("¿Existe 20?: " + existe);
        
            
            
        System.out.println("\n===== EJERCICIO 2: Invertir lista =====");
        
        ListLinked<Integer> invertida = MetodosLista.invertirLista(lista1);
        System.out.println("Lista invertida:");
        invertida.print();
        
            
            
        System.out.println("\n===== EJERCICIO 3: Insertar nodo al final =====");
        
        Node<Integer> head = null;
        
        head = OperacionesNodo.insertarAlFinal(head, 5);
        head = OperacionesNodo.insertarAlFinal(head, 10);
        head = OperacionesNodo.insertarAlFinal(head, 15);
        
        Node<Integer> current = head;
        
        while (current != null) {
            
            System.out.println(current.value);
            current = current.next;
        }
        
            
            
        System.out.println("\n===== EJERCICIO 4: Contar nodos =====");
        
        int total = OperacionesNodo.contarNodos(head);
        System.out.println("Total nodos: " + total);
        
            
            
        System.out.println("\n===== EJERCICIO 5: Comparar listas =====");
        
        ListLinked<Integer> lista2 = new ListLinked<>();
        
        lista2.insertLast(10);
        lista2.insertLast(20);
        lista2.insertLast(30);
        
        boolean iguales = MetodosLista.sonIguales(lista1, lista2);
        System.out.println("¿Las listas son iguales?: " + iguales);
        
            
            
        System.out.println("\n===== EJERCICIO 6: Concatenar listas =====");
        
        ListLinked<Integer> lista3 = new ListLinked<>();
        
        lista3.insertLast(40);
        lista3.insertLast(50);
        
        ListLinked<Integer> concatenada = MetodosLista.concatenarListas(lista1, lista3);
        System.out.println("Lista concatenada:");
        concatenada.print();
        
            
            
        System.out.println("\n===== EJERCICIO 7: Lista ordenada =====");
        
        SortedListLinked<Integer> listaOrdenada = new SortedListLinked<>();
        
        listaOrdenada.insertOrden(30);
        listaOrdenada.insertOrden(10);
        listaOrdenada.insertOrden(20);
        listaOrdenada.insertOrden(5);
        
        System.out.println("Lista ordenada:");
        listaOrdenada.print();
        
    }
}
