import java.util.*;

public class Principal {
    
    // consigna c
    public static void mostrarRectangulo(Rectangulo r){
        System.out.println(r);
    }
    public static void main(String[] args) {
        
        Scanner ingreso = new Scanner(System.in);
        
        // Ingreso del Rectángulo A
        System.out.println("\n___________________________________\n");
        System.out.println("Ingrese dos esquinas del Rectangulo A");

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Ax1 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay1 = ingreso.nextDouble();

        System.out.println("\nCoordenadas de la segunda esquina");
        System.out.print("x: ");
        double Ax2 = ingreso.nextDouble();
        System.out.print("y: ");
        double Ay2 = ingreso.nextDouble();

        Coordenada Ap1 = new Coordenada(Ax1, Ay1);
        Coordenada Ap2 = new Coordenada(Ax2, Ay2);

        Rectangulo A = new Rectangulo(Ap1, Ap2);
        System.out.println("___________________________________\n");

        // Ingreso del Rectángulo B
        System.out.println("Ingrese dos esquinas del Rectangulo B");

        System.out.println("\nCoordenadas de la primera esquina");
        System.out.print("x: ");
        double Bx1 = ingreso.nextDouble();
        System.out.print("y: ");
        double By1 = ingreso.nextDouble();

        System.out.println("\nCoordenadas de la segunda esquina");
        System.out.print("x: ");
        double Bx2 = ingreso.nextDouble();
        System.out.print("y: ");
        double By2 = ingreso.nextDouble();

        Coordenada Bp1 = new Coordenada(Bx1, By1);
        Coordenada Bp2 = new Coordenada(Bx2, By2);

        Rectangulo B = new Rectangulo(Bp1, Bp2);
        System.out.println("___________________________________\n");
        
        System.out.println("Rectangulo A");
        mostrarRectangulo(A);

        System.out.println("\nRectangulo B");
        mostrarRectangulo(B);

        System.out.println("___________________________________\n");
        if(Verificador.esSobrePos(A,B)){
            System.out.println("Los rectángulos se sobreponen");
        }else if(Verificador.esJunto(A,B)){
            System.out.println("Los rectángulos están juntos");
        }else if(Verificador.esDisjunto(A,B)){
            System.out.println("Los rectángulos están separados");
        }
        System.out.println("___________________________________\n");
        
        ingreso.close();
    }
}
