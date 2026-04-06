package ACTS.ACT5;

import java.util.ArrayList;

public class Moda3 {

    // LIMITES TAD
    static class Limits {
        int prim;
        int ult;
        
        Limits(int p, int u) {
            prim = p;
            ult = u;
        }
        
        int longitud() {
            return ult - prim + 1;
        }
    }

    // VECTORES TAD
    static class SetVectors {
        
        ArrayList<Limits> lista;
        
        SetVectors() {
            lista = new ArrayList<>();
        }
        
        void insertar(Limits p) {
            lista.add(p);
        }
        
        boolean esVacio() {
            return lista.isEmpty();
        }
        
        int longMayor() {
            int max = 0;
            
            for (Limits l : lista) {
                if (l.longitud() > max) {
                    max = l.longitud();
                }
            }
            
            return max;
        }

        Limits mayor() {
            if (lista.isEmpty()) return null;
            
            Limits mayor = lista.get(0);
            
            for (Limits l : lista) {
                if (l.longitud() > mayor.longitud()) {
                    mayor = l;
                }
            }
            
            lista.remove(mayor);
            
            return mayor;
        }
        
        void destruir() {
            lista.clear();
        }
    }

    // PIVOTE2 - Divide en 3 partes
    static void pivote2(int[] a, int mediana, int prim, int ult, int[] izqDer) {
        int i = prim;
        int izq = prim;
        int der = ult;
        
        while (i <= der) {
            if (a[i] < mediana) {
                
                int temp = a[i];
                a[i] = a[izq];
                a[izq] = temp;
                
                i++;
                izq++;
                
            } else if (a[i] > mediana) {
                
                int temp = a[i];
                a[i] = a[der];
                a[der] = temp;
                
                der--;
                
            } else {
                
                i++;
                
            }
        }
        
        izqDer[0] = izq;
        izqDer[1] = der + 1;
    }

    // MODA 3 (Divide y Vencerás)
    static int moda3(int[] a, int prim, int ult) {
        
        SetVectors homogeneo = new SetVectors();
        SetVectors heterogeneo = new SetVectors();
        
        Limits p = new Limits(prim, ult);
        
        heterogeneo.insertar(p);
        
        while (heterogeneo.longMayor() > homogeneo.longMayor()) {
            
            p = heterogeneo.mayor();
            
            int mediana = a[(p.prim + p.ult) / 2];
            
            int[] izqDer = new int[2];
            
            pivote2(a, mediana, p.prim, p.ult, izqDer);
            
            Limits p1 = new Limits(p.prim, izqDer[0] - 1);
            Limits p2 = new Limits(izqDer[0], izqDer[1] - 1);
            Limits p3 = new Limits(izqDer[1], p.ult);
            
            if (p1.prim < p1.ult)
                heterogeneo.insertar(p1);
            
            if (p3.prim < p3.ult)
                heterogeneo.insertar(p3);
            
            if (p2.prim <= p2.ult)
                homogeneo.insertar(p2);
        }
        
        if (homogeneo.esVacio())
            return a[prim];
        
        p = homogeneo.mayor();
        
        homogeneo.destruir();
        heterogeneo.destruir();
        
        return a[p.prim];
    }

    public static void main(String[] args) {
        
        int[] arr = {6, 6, 2, 6, 3, 6, 2};
        int moda = moda3(arr, 0, arr.length - 1);
        System.out.println("La moda es: " + moda);
    }
}
