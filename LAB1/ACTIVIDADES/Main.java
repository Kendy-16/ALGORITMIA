import java.util.*;

public class Main {
    public static void main(String[] args) { 
        
        Scanner ingreso = new Scanner(System.in);
        
        // Ingreso del Rectángulo A
        System.out.println("Ingrese dos puntos del Rectangulo A");

        System.out.println("\nCoordenadas del primer punto");
        System.out.print("x: ");
        double Ax1 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay1 = ingreso.nextDouble();

        System.out.println("\nCoordenadas del segundo punto");
        System.out.print("x: ");
        double Ax2 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay2 = ingreso.nextDouble();

        Coordenada p1 = new Coordenada(Ax1, Ay1);
        Coordenada p2 = new Coordenada(Ax2, Ay2);

        Rectangulo A = new Rectangulo(p1, p2);
        
    }
}
