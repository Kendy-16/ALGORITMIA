// Representa una tarea con prioridad y estado
public class Tarea implements Comparable<Tarea> {

    private String titulo;
    private int prioridad; // 1 alta, 2 media, 3 baja
    private String estado; // pendiente o completada

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    // Compara prioridades (menor número = mayor prioridad)
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    // Permite imprimir la tarea en texto
    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }

    // Permite comparar tareas correctamente
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) return true;
        
        if (obj == null || getClass() != obj.getClass()) {
            
            return false;
        }
        
        Tarea otra = (Tarea) obj;
        
        return prioridad == otra.prioridad && titulo.equals(otra.titulo) && estado.equals(otra.estado);
    }
}