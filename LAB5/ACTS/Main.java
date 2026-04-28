// Clase principal donde se prueba el gestor de tareas
public class Main {

    public static void main(String[] args) {

        // Creamos el gestor que usará una lista enlazada de Tarea
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        // Creamos algunas tareas de ejemplo
        Tarea t1 = new Tarea("Estudiar AED", 1, "pendiente");
        Tarea t2 = new Tarea("Hacer informe", 2, "pendiente");
        Tarea t3 = new Tarea("Practicar Java", 3, "pendiente");

        // Insertamos tareas en la lista
        gestor.agregarTarea(t1);
        gestor.agregarTarea(t2);
        gestor.agregarTarea(t3);

        // Mostramos todas las tareas
        System.out.println("\nLista de tareas");
        gestor.imprimirTareas();

        // Contamos cuántas tareas hay
        System.out.println("\nCantidad de tareas");
        System.out.println(gestor.contarTareas());

        // Buscamos una tarea específica
        System.out.println("\nBuscar tarea");
        Tarea buscada = gestor.buscarTarea(t2);
        if (buscada != null) {
            // Si se encuentra, se muestra
            System.out.println("Tarea encontrada: " + buscada);
        } else {
            // Si no se encuentra
            System.out.println("Tarea no encontrada");
        }

        // Eliminamos una tarea
        System.out.println("\nEliminar tarea");
        gestor.eliminarTarea(t3);

        // Mostramos lista después de eliminar
        System.out.println("Lista después de eliminar:");
        gestor.imprimirTareas();

        // Invertimos el orden de la lista
        System.out.println("\nLista invertida");
        gestor.invertirTareas();

        // Mostramos la lista invertida
        gestor.imprimirTareas();

        // Obtenemos la tarea con mayor prioridad
        System.out.println("\nTarea más prioritaria:");
        Tarea prioritaria = gestor.obtenerTareaPrioritaria();

        if (prioritaria != null) {
            System.out.println(prioritaria);
        }
    }
}