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
    
}
