import java.util.*;
public class Coordenada{
    private double x;
    private double y;
    
    //Constructor, initialize x, y attributes to zero
    public Coordenada( )
    {
        this.x = 0;
        this.y = 0;
    }
    
    //Constructor
    public Coordenada(double x, double y ){
        this.x = x;
        this.y = y;
    }
    
    //Constructor
    public Coordenada(Coordenada c ){
        //a
    }

    //métodos setter
    void setX(double x) {
        // fill in the code here
    }
    void setY(double y){
        // fill in the code here
    }
    //métodos getter
    double getX(){
        // fill in the code here
    }
    double getY(){
        // fill in the code here
    }
    //método de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
    // fill in the code here
    }
    //método de clase que calcula la distancia euclideana
    static double distancia(Coordenada c1, Coordenada c2){
    // fill in the code here
    }
    //método que devuelve los valores de una coordenada en determinado formato
    String toString(){
    // fill in the code here
    }
}
