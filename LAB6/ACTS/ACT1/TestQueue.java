// Clase de prueba para verificar el funcionamiento de la cola
public class TestQueue {
    public static void main(String[] args) {
        try {
            
            // ===== PRUEBA CON INTEGER =====
            QueueArray<Integer> colaInt = new QueueArray<>(5);
            
            colaInt.enqueue(16); // inserta
            colaInt.enqueue(24);
            colaInt.enqueue(32);
            
            System.out.println("\nCola: " + colaInt);
            System.out.println("Frente: " + colaInt.front()); // ver primero
            System.out.println("Eliminado: " + colaInt.dequeue()); // eliminar
            System.out.println("Cola: " + colaInt);
            
            // ===== PRUEBA CON STRING =====
            QueueArray<String> colaStr = new QueueArray<>(4);
            
            colaStr.enqueue("Konnor");
            colaStr.enqueue("Lydiac");
            colaStr.enqueue("Ruth");
            
            System.out.println("\nCola: " + colaStr);
            System.out.println("Frente: " + colaStr.front());
            System.out.println("Eliminado: " + colaStr.dequeue());
            System.out.println("Cola: " + colaStr);
            
        } catch (ExceptionIsEmpty e) {
            // Manejo de error si la cola está vacía
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            // Manejo de error si la cola está llena
            System.out.println("Error: " + e.getMessage());
        }
    }
}