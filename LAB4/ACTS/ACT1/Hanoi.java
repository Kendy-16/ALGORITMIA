public class Hanoi
{
    //Creando el metodo recursivo para la solucion de las torres hanoi
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3){
        //Caso base
        if (discos == 1){
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else{
            //Dominio (problema - 1)
            torresHanoi(discos -1, torre1, torre3, torre2);
            System.out.println("mover disco de torre "+ torre1 + " a torre " + torre3);
            torresHanoi(discos -1, torre2, torre1, torre3);
        }
    }

    public static void main(String[] args)
    {
        torresHanoi(3,1,2,3);
    }
}