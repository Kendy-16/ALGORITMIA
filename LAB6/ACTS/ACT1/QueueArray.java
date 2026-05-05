// Implementación de la cola usando un arreglo (estructura estática)
public class QueueArray<E> implements Queue<E> {
    
    private E[] array;   // Arreglo donde se guardan los elementos
    private int front;   // Posición del primer elemento
    private int rear;    // Posición del último elemento
    private int size;    // Cantidad de elementos actuales
    
    @SuppressWarnings("unchecked")
    public QueueArray(int n) {
        array = (E[]) new Object[n]; // Se crea el arreglo con tamaño fijo
        front = 0;
        rear = -1;
        size = 0;
    }
    
    @Override
    public void enqueue(E x) {
        // Verifica si la cola está llena
        if (isFull()) {
            throw new IllegalStateException("La cola está llena");
        }
        
        // Avanza circularmente y agrega el elemento
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }
    
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        // Verifica si está vacía
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        
        // Guarda el elemento del frente
        E aux = array[front];
        array[front] = null;
        
        // Avanza circularmente el frente
        front = (front + 1) % array.length;
        size--;
        
        return aux;
    }
    
    @Override
    public E front() throws ExceptionIsEmpty {
        // Retorna el primer elemento sin eliminarlo
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return array[front];
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0; // Vacía si no hay elementos
    }
    
    public boolean isFull() {
        return size == array.length; // Llena si alcanza su capacidad
    }
    
    @Override
    public String toString() {
        // Muestra los elementos desde front hasta rear
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < size; i++) {
            int index = (front + i) % array.length;
            sb.append(array[index]);
            
            if (i < size - 1) sb.append(", ");
        }
        
        sb.append("]");
        return sb.toString();
    }
}