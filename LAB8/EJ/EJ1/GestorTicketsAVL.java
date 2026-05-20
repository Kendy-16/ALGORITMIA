public class GestorTicketsAVL {
    public static void main(String[] args) {

        AVLTree<Integer> tickets = new AVLTree<Integer>();
        try {

            // INSERCIONES
            System.out.println("=================================");
            System.out.println("INSERTANDO TICKETS");
            System.out.println("=================================");

            System.out.println("\nInsertando ticket: 30");  // INSERTAR 30
            tickets.insert(30);

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nInsertando ticket: 10");  // INSERTAR 10
            tickets.insert(10);

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nInsertando ticket: 20");  // INSERTAR 30
            tickets.insert(20);

            System.out.println("Se produce desbalance Izquierda-Derecha");
            System.out.println("Se aplica Rotacion Doble Izquierda-Derecha");

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nInsertando ticket: 40");  // INSERTAR 40
            tickets.insert(40);

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nInsertando ticket: 50");  // INSERTAR 30
            tickets.insert(50);

            System.out.println("Se produce desbalance Derecha-Derecha");
            System.out.println("Se aplica Rotacion Simple Izquierda");

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nInsertando ticket: 25");  // INSERTAR 30
            tickets.insert(25);

            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            // BUSQUEDAS
            System.out.println("\n=================================");
            System.out.println("BUSQUEDA DE TICKETS");
            System.out.println("=================================");

            System.out.println("\nBuscando ticket 20"); // Buscar 20
            try {
                System.out.println("Ticket encontrado: " + tickets.search(20));
            } catch (ItemNoFound e) {
                System.out.println("Ticket no encontrado");
            }

            System.out.println("\nBuscando ticket 60"); // Buscar 60
            try {
                System.out.println("Ticket encontrado: " + tickets.search(60));
            } catch (ItemNoFound e) {
                System.out.println("Ticket no encontrado");
            }

            // ELIMINACIONES
            System.out.println("\n=================================");
            System.out.println("ELIMINANDO TICKETS");
            System.out.println("=================================");

            System.out.println("\nEliminando ticket: 10");
            tickets.delete(10); // ELIMINAR 10
            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nEliminando ticket: 40");
            tickets.delete(40); // ELIMINAR 40
            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\nEliminando ticket: 30");
            tickets.delete(30); // ELIMINAR 30
            System.out.println("InOrder:");
            System.out.println(tickets.inOrder());

            System.out.println("\n=================================");
            System.out.println("ARBOL AVL FINAL");
            System.out.println("=================================");

            System.out.println(tickets.inOrder());

            System.out.println("\nAVL balanceado correctamente");

        } catch (ItemDuplicated e) {

            System.out.println(e.getMessage());
        } catch (ExceptionIsEmpty e) {

            System.out.println(e.getMessage());
        } catch (ItemNoFound e) {

            System.out.println(e.getMessage());
        }
    }
}