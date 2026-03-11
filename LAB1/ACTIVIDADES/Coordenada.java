package LAB1.ACTIVIDAD;

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
        this.x = c.x;
        this.y = c.y;
    }

    //métodos setter
    void setX(double x) {
        this.x = x;
    }
    
    void setY(double y){
        this.y = y;
    }
    
    //métodos getter
    double getX(){
        return this.x;
    }
    
    double getY(){
        return this.y;
    }
    
    //método de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
        double d = Math.sqrt(Math.pow((c.x - this.x),2)+Math.pow((c.y - this.y),2));
        return d;
    }
    
    //método de clase que calcula la distancia euclideana
    static double distancia(Coordenada c1, Coordenada c2){
        double d = Math.sqrt(Math.pow((c1.x - c2.x),2)+Math.pow((c1.y - c2.y),2));
        return d;
    }

    //método que devuelve los valores de una coordenada en determinado formato
    String toString(){
        String message = "a";
        return message;
    }
}
