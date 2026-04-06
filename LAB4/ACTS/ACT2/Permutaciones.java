import java.util.*;

public class Permutaciones
{
    public static void permutar(int[] arr, List<Integer> actual, boolean[] usado)
    {
        // Caso base: cuando ya se llenó una permutación
        if (actual.size() == arr.length) {
            System.out.println(actual);
            return;
        }
    
    for (int i = 0; i < arr.length; i++)
        {
            if (!usado[i]) {
                usado[i] = true;        // Marcar como usado
                actual.add(arr[i]);     // Agregar elemento
                permutar(arr, actual, usado);
                
                // Backtracking
                actual.remove(actual.size()- 1);
                usado[i] = false;       // Desmarcar
            }
        }
    }
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3};
        boolean[] usado = new boolean[arr.length];
        permutar(arr, new ArrayList<>(), usado);
    }
}
