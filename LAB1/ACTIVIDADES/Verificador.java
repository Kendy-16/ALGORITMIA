public class Verificador {
    
    // esSobrePos: Los rectángulos se sobreponen
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2){

        //Coordenadas de esuqina1
        double a1x = r1.getEsquina1().getX();
        double a1y = r1.getEsquina1().getY();
        double a2x = r1.getEsquina2().getX();
        double a2y = r1.getEsquina2().getY();

        //Coordenadas de esquina2
        double b1x = r2.getEsquina1().getX();
        double b1y = r2.getEsquina1().getY();
        double b2x = r2.getEsquina2().getX();
        double b2y = r2.getEsquina2().getY();

        //Ordenar puntos en el plano - Rectangulo A
        double aIzq = Math.min(a1x, a2x);
        double aDer = Math.max(a1x, a2x);
        double aBajo = Math.min(a1y, a2y);
        double aRriba = Math.max(a1y, a2y);

        //Ordenar puntos en el plano - Rectangulo B
        double bIzq = Math.min(b1x, b2x);
        double bDer = Math.max(b1x, b2x);
        double bBajo = Math.min(b1y, b2y);
        double bRriba = Math.max(b1y, b2y);

        //Si los rangos se cruzan entonces comparten área
        if((aIzq < bDer) && (aDer > bIzq) && (aBajo < bRriba) && (aRriba > bBajo)){
            return true;
        }

        return false;
    }

    // esDisjunto: Los rectángulos están separados
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2){
        return false; //avanzar
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2){
        return true; //avanzar
    }

}
