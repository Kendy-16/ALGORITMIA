import java.util.*;

public class Principal {
    
    // consigna c - mostrar rectangulos
    public static void mostrarRectangulo(Rectangulo r){
        System.out.println(r);
    }

    //consigna c - obtener rectangulo
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2){
        
        // Se obtienen la coordenada mas grande de la izquierda
        double x1 = Math.max(Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX()),
            Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX()));
        
        // Se obtienen la coordenada mas grande de abajo
        double y1 = Math.max(Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY()),
            Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY()));
        
        // Se obtienen la coordenada mas pequeña de la derecha
        double x2 = Math.min(Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX()),
            Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX()));
        
        // Se obtienen la coordenada mas pequeña de arriba
        double y2 = Math.min(Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY()),
            Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY()));
        
        //Nuevas esquinas
        Coordenada p1 = new Coordenada(x1,y1);
        Coordenada p2 = new Coordenada(x2,y2);
        
        //Nuevo rectangulo
        Rectangulo r = new Rectangulo(p1,p2);
        return r;
    }
    public static void main(String[] args) {
        
        Scanner ingreso = new Scanner(System.in);
        ContainerRect cont = new ContainerRect(10);
        
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
        cont.addRectangulo(A);
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
        cont.addRectangulo(B);
        System.out.println("___________________________________\n");
        
        System.out.println("Rectangulo A");
        mostrarRectangulo(A);
        
        System.out.println("\nRectangulo B");
        mostrarRectangulo(B);
        
        System.out.println("\nListado de rectangulos guardados:");
        System.out.println(cont);
        
        System.out.println("___________________________________\n");
        if(Verificador.esSobrePos(A,B)){
            Rectangulo sobre = rectanguloSobre(A,B);
            
            System.out.println("Los rectángulos se sobreponen");
            mostrarRectangulo(sobre);
            
            System.out.println("\nArea de sobreposición: " + sobre.calculoArea());
        }else if(Verificador.esJunto(A,B)){
            System.out.println("Los rectángulos están juntos");
        }else if(Verificador.esDisjunto(A,B)){
            System.out.println("Los rectángulos están separados");
        }
        System.out.println("___________________________________\n");
        
        ingreso.close();
    }
}
