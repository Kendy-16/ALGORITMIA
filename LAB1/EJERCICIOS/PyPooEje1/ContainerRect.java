public class ContainerRect {

    Rectangulo[] rectangulos;
    double[] distancias;
    double[] areas;
    int n;
    
    static int numRec = 0;
    
    public ContainerRect(int n){
        this.n = n;
        rectangulos = new Rectangulo[n];
        distancias = new double[n];
        areas = new double[n];
    }
    
    public void addRectangulo(Rectangulo r){
        if(numRec < n){
            rectangulos[numRec] = r;
            double x1 = r.getEsquina1().getX();
            double y1 = r.getEsquina1().getY();
            double x2 = r.getEsquina2().getX();
            double y2 = r.getEsquina2().getY();
            double dx = x2 - x1;
            double dy = y2 - y1;
            distancias[numRec] = Math.sqrt(dx*dx + dy*dy);
            areas[numRec] = r.calculoArea();
            numRec++;
        }else{
            System.out.println("No es posible guardar más rectángulos");
        }
    }
}
