public class TestGen {
    public static void main(String[] args) {
        
        // Crear cajoneria
        Cajoneria<Golosina> cajoneria = new Cajoneria<Golosina>(8);
        
        // Crear cajas con golosinas
        Caja<Golosina> caja1 = new Caja<Golosina>("Roja");
        Caja<Golosina> caja2 = new Caja<Golosina>("Azul");
        Caja<Golosina> caja3 = new Caja<Golosina>("Verde");
        Caja<Golosina> caja4 = new Caja<Golosina>("Amarilla");
        Caja<Golosina> caja5 = new Caja<Golosina>("Negra");
        
        // Guardar golosinas en cajas
        caja1.guardar(new Golosina("gomitas", 0.5));
        caja2.guardar(new Golosina("caramelos", 0.2));
        caja3.guardar(new Golosina("chicle", 0.1));
        caja4.guardar(new Golosina("menta", 0.3));
        caja5.guardar(new Golosina("toffee", 0.4));
        
        // Agregar cajas a la cajoneria
        cajoneria.add(caja1);
        cajoneria.add(caja2);
        cajoneria.add(caja3);
        cajoneria.add(caja4);
        cajoneria.add(caja5);
        
        System.out.println("_________________________________\n");
        System.out.println("CONTENIDO DE LA CAJONERIA");
        System.out.println(cajoneria);
        System.out.println("_________________________________\n");
        
        // Buscar golosinas
        System.out.println("BUSQUEDA DE GOLOSINAS\n");
        
        System.out.println("Buscando 'gomitas' en la cajoneria");
        System.out.println(cajoneria.search(new Golosina("gomitas", 0.5)));
        
        System.out.println("\nBuscando 'galleta' en la cajoneria");
        System.out.println(cajoneria.search(new Golosina("galleta", 0.9)));
        System.out.println("_________________________________\n");
        
        // Probar delete
        System.out.println("\nUSANDO DELETE:");
        
        Object eliminado =
            cajoneria.delete(
                new Golosina("menta", 0.3)
            );
        
        System.out.println("Eliminado: " + eliminado);
        System.out.println("_________________________________\n");
        
        System.out.println("\nCAJONERIA FINAL:");
        System.out.println(cajoneria);
        System.out.println("_________________________________\n");
    }
}
