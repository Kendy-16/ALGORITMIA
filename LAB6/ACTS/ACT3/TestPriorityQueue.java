// Clase de prueba para verificar el funcionamiento
public class TestPriorityQueue {
    public static void main(String[] args) {
        
        try {
            PriorityQueueLinkSort<String, Integer> pq = new PriorityQueueLinkSort<>();
            
            // Insertamos elementos con distintas prioridades
            pq.enqueue("A", 2);
            pq.enqueue("B", 5);
            pq.enqueue("C", 3);
            pq.enqueue("D", 5);
            
            // La cola se mantiene ordenada automáticamente
            System.out.println("Cola: " + pq);
            
            // Elemento con mayor prioridad
            System.out.println("Front: " + pq.front());
            
            // Elemento con menor prioridad
            System.out.println("Back: " + pq.back());
            
            // Eliminamos elementos (siempre el de mayor prioridad)
            System.out.println("Dequeue: " + pq.dequeue());
            System.out.println("Cola: " + pq);
            
            System.out.println("Dequeue: " + pq.dequeue());
            System.out.println("Cola: " + pq);
            
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}