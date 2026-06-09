public class Main {
    public static void main(String[] args) {

        BTree<Integer> arbol = new BTree<>(4);

        System.out.println("=================================");
        System.out.println("PRUEBA 1: INSERCIONES BASICAS");
        System.out.println("=================================");

        arbol.insert(50);
        arbol.insert(20);
        arbol.insert(70);

        System.out.println(arbol);

        System.out.println("\n=================================");
        System.out.println("PRUEBA 2: PRIMERA DIVISION");
        System.out.println("=================================");

        arbol.insert(10);

        System.out.println(arbol);

        System.out.println("\n=================================");
        System.out.println("PRUEBA 3: MAS INSERCIONES");
        System.out.println("=================================");

        arbol.insert(30);
        arbol.insert(60);
        arbol.insert(80);

        System.out.println(arbol);

        System.out.println("\n=================================");
        System.out.println("PRUEBA 4: DIVISIONES INTERNAS");
        System.out.println("=================================");

        arbol.insert(25);
        arbol.insert(27);
        arbol.insert(26);
        arbol.insert(65);
        arbol.insert(75);
        arbol.insert(85);
        arbol.insert(5);

        System.out.println(arbol);

        System.out.println("\n=================================");
        System.out.println("PRUEBA 5: DUPLICADOS");
        System.out.println("=================================");

        arbol.insert(50);
        arbol.insert(25);

        System.out.println(arbol);

        System.out.println("\n=================================");
        System.out.println("ARBOL FINAL");
        System.out.println("=================================");

        System.out.println(arbol);
    }
}