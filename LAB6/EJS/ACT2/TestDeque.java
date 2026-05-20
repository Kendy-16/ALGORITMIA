// Clase de prueba para verificar el funcionamiento
public class TestDeque {
    public static void main(String[] args) {
        
        try {
            System.out.println("=== INTEGER ===");
            
            DequeLink<Integer> d1 = new DequeLink<>();
            
            // Insertamos por ambos lados
            d1.addFirst(16);
            d1.addFirst(24);
            d1.addLast(32);
            
            System.out.println("Deque: " + d1);
            System.out.println("Primero: " + d1.getFirst());
            System.out.println("Último: " + d1.getLast());
            
            // Eliminamos por ambos lados
            System.out.println("Eliminar inicio: " + d1.removeFirst());
            System.out.println("Eliminar final: " + d1.removeLast());
            
            System.out.println("Estado final: " + d1);
        
            System.out.println("\n=== STRING ===");
            
            DequeLink<String> d2 = new DequeLink<>();
            
            d2.addLast("Lydiac");
            d2.addLast("Konnor");
            d2.addFirst("Yamiko");
            
            System.out.println("Deque: " + d2);
            System.out.println("Primero: " + d2.getFirst());
            
            System.out.println("Eliminar inicio: " + d2.removeFirst());
            System.out.println("Estado final: " + d2);
            
        } catch (ExceptionIsEmpty e) {
            // Captura errores si la estructura está vacía
            System.out.println("Error: " + e.getMessage());
        }
    }
}