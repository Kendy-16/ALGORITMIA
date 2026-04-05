public class HanoiCont
{
    // Contador de movimientos
    static int contador = 0;

    // Metodo recursivo
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3){
        
        // Caso base
        if (discos == 1){
            contador++; // Aumenta contador
            
            System.out.println("Movimiento " + contador +
            ": Mover disco de torre " + torre1 +
            " a torre " + torre3);
        }
        else{
            torresHanoi(discos -1, torre1, torre3, torre2);
            
            contador++; // Aumenta contador
            
            System.out.println("Movimiento " + contador +
            ": Mover disco de torre "+ torre1 +
            " a torre " + torre3);
            
            torresHanoi(discos -1, torre2, torre1, torre3);
        }
    }

    public static void main(String[] args)
    {
        torresHanoi(5,1,2,3);

        // Mostrar total de movimientos
        System.out.println("\nTotal de movimientos: " + contador);
    }
}