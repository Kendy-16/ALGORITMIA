public class TestStack {

    public static void main(String[] args) {
        try {
            System.out.println("=== INTEGER ===");
            
            StackLink<Integer> s1 = new StackLink<>();
            
            // Insertar elementos
            s1.push(10);
            s1.push(20);
            s1.push(30);
            
            System.out.println("Pila: " + s1);
            
            // Ver el tope
            System.out.println("Top: " + s1.top());
            
            // Eliminar elementos (LIFO)
            System.out.println("Pop: " + s1.pop());
            System.out.println("Pila: " + s1);
            
            System.out.println("Pop: " + s1.pop());
            System.out.println("Pila: " + s1);
            
            System.out.println("\n=== STRING ===");
            
            StackLink<String> s2 = new StackLink<>();
            
            s2.push("Ana");
            s2.push("Luis");
            s2.push("Marta");
            
            System.out.println("Pila: " + s2);
            System.out.println("Top: " + s2.top());
            
            System.out.println("Pop: " + s2.pop());
            System.out.println("Pila: " + s2);
            
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}