import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner ingreso = new Scanner(System.in);
        
        // Ingreso del Rectángulo A
        System.out.println("\nIngrese dos esquinas del Rectangulo A");

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Ax1 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay1 = ingreso.nextDouble();

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Ax2 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay2 = ingreso.nextDouble();

        Coordenada Ap1 = new Coordenada(Ax1, Ay1);
        Coordenada Ap2 = new Coordenada(Ax2, Ay2);

        Rectangulo A = new Rectangulo(Ap1, Ap2);
        System.out.println("\n___________________________________\n");

        // Ingreso del Rectángulo B
        System.out.println("\nIngrese dos esquinas del Rectangulo B");

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Bx1 = ingreso.nextDouble();
        System.out.print("y: ");
        double By1 = ingreso.nextDouble();

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Bx2 = ingreso.nextDouble();
        System.out.print("y: ");
        double By2 = ingreso.nextDouble();

        Coordenada Bp1 = new Coordenada(Bx1, By1);
        Coordenada Bp2 = new Coordenada(Bx2, By2);

        Rectangulo B = new Rectangulo(Bp1, Bp2);
        System.out.println("\n___________________________________\n");
        
        System.out.println("\nRectangulo A\n" + A);
        System.out.println("\nRectangulo B\n" + B);
        System.out.println("\n___________________________________\n");
        
        //avanzar la consigna 2b

        ingreso.close();
    }
}
