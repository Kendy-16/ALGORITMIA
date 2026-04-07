public class Laberinto {

    // Tamaño del laberinto
    static int N = 3;

    // Metodo para verificar si se puede avanzar
    public static boolean esValido(int[][] lab, int x, int y, int[][] sol) {
        if (x >= 0 && x < N && y >= 0 && y < N && lab[x][y] == 0 && sol[x][y] == 0){
            return true;
        }
        return false;
    }

    // Metodo recursivo con backtracking
    public static boolean resolverLaberinto(int[][] lab, int x, int y, int[][] sol) {
        // Si llegamos al destino
        if (x == N - 1 && y == N - 1 && lab[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }

        // Verificamos si el movimiento es valido
        if (esValido(lab, x, y, sol)) {
            sol[x][y] = 1;

            // Movimiento hacia abajo
            if (resolverLaberinto(
                    lab, x + 1, y, sol))
                return true;
                
            // Movimiento hacia la derecha
            if (resolverLaberinto(
                    lab, x, y + 1, sol))
                return true;
                
            // Movimiento hacia arriba
            if (resolverLaberinto(
                    lab, x - 1, y, sol))
                return true;
                
            // Movimiento hacia la izquierda
            if (resolverLaberinto(
                    lab, x, y - 1, sol))
                return true;
                
            // Backtracking (retroceder)
            sol[x][y] = 0;
            
            return false;
        }
        
        return false;
    }

    // Metodo para imprimir solucion
    public static void imprimirSolucion(int[][] sol) {
        
        for (int i = 0; i < N; i++) {
            
            for (int j = 0; j < N; j++) {
                
                System.out.print(sol[i][j] + " ");
                
            }
            
            System.out.println();
            
        }
    }

    // MAIN
    public static void main(String[] args) {
        
        int[][] laberinto = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}
        };

        int[][] solucion = new int[N][N];
        
        if (resolverLaberinto( laberinto, 0, 0, solucion)) {
            
            System.out.println("true");
            System.out.println("Camino encontrado:");
            imprimirSolucion(solucion);
            
        } else {
            System.out.println("false");
        }
    }
}
