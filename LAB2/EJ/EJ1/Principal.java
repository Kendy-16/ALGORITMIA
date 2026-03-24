public class Principal {
    
    // Método genérico solicitado
    public static <T> boolean exist(T[] arreglo, T elemento) {
        
        for (int i = 0; i < arreglo.length; i++) {
        
            if (arreglo[i].equals(elemento)) {
                return true;
            }
        }
        
        return false;
    }

    // Método genérico recorredor
    public static <T> void mostrarBolsa(Bolsa<T[]> bolsa) {
        
        for (T[] arreglo : bolsa) {
            
            for (int i = 0; i < arreglo.length; i++) {
                System.out.println(arreglo[i]);
            }
        }
    }
    public static void main(String[] args) {
        
        // Bolsa de arreglos de Chocolatinas
        Bolsa<Chocolatina[]> bolsaCho = new Bolsa<Chocolatina[]>(4);
        
        // Crear arreglo de chocolatinas
        Chocolatina[] arrCho = {
            new Chocolatina("milka"),
            new Chocolatina("ferrero"),
            new Chocolatina("iberico")
        };
        
        // Agregar arreglo a la bolsa
        bolsaCho.add(arrCho);
        System.out.println("_________________________________\n");
        
        // Recorrer bolsa
        mostrarBolsa(bolsaCho);
        System.out.println("_________________________________\n");
        
        // Bolsa de arreglos de Golosinas
        Bolsa<Golosina[]> bolsaGolo = new Bolsa<Golosina[]>(4);
        
        // Crear arreglo de golosinas
        Golosina[] arrGolo = {
            new Golosina("gomitas", 0.5),
            new Golosina("caramelos", 0.2),
            new Golosina("chicle", 0.1)
        };
        
        // Agregar arreglo
        bolsaGolo.add(arrGolo);
        
        // Mostrar contenido
        mostrarBolsa(bolsaGolo);
        System.out.println("_________________________________\n");
        
        System.out.println("PRUEBA DEL METODO EXIST");
        System.out.println("_________________________________\n");
        
        // Buscar en arreglo de Chocolatinas
        boolean existeCho = exist(arrCho, new Chocolatina("milka"));
        boolean existeFo = exist(arrCho, new Chocolatina("fondu"));
        System.out.println("¿Existe milka en Chocolatina?: " + existeCho);
        System.out.println("¿Existe fondu en Chocolatina?: " + existeFo);
        
        
        // Buscar en arreglo de Golosinas
        boolean existeGolo = exist(arrGolo,new Golosina("gomitas", 0.5));
        boolean existeChi = exist(arrGolo,new Golosina("chicle", 0.8));
        System.out.println("\n¿Existe gomitas en Golosina?: " + existeGolo);
        System.out.println("¿Existe chicle en Golosina?: " + existeChi);
        
        System.out.println("_________________________________\n");
    }
}
