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
        Caja<Golosina> caja11 = new Caja<Golosina>("Blanca");
        
        // Guardar golosinas en cajas
        caja1.guardar(new Golosina("gomitas", 0.5));
        caja2.guardar(new Golosina("caramelos", 0.2));
        caja3.guardar(new Golosina("chicle", 0.1));
        caja4.guardar(new Golosina("menta", 0.3));
        caja5.guardar(new Golosina("toffee", 0.4));
        caja11.guardar(new Golosina("gomitas", 0.5));

        // Agregar cajas a la cajoneria
        cajoneria.add(caja1);
        cajoneria.add(caja2);
        cajoneria.add(caja3);
        cajoneria.add(caja4);
        cajoneria.add(caja5);
        cajoneria.add(caja11);
        
        System.out.println("_________________________________\n");
        System.out.println("PRUEBAS CON GOLOSINAS\n");
        System.out.println("_________________________________\n");
        System.out.println("CONTENIDO DE LA CAJONERIA - GOLOSINA");
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
        
        System.out.println("CONTEO DE GOMITAS");
        int cant1 = cajoneria.contar(new Golosina("gomitas", 0.5));
        
        System.out.println("Cantidad de 'gomitas': " + cant1);
        System.out.println("_________________________________");
        System.out.println("_________________________________\n");
        System.out.println("PRUEBAS CON CHOCOLATINAS\n");
        System.out.println("_________________________________\n");
        
        // Crear cajoneria de Chocolatinas
        Cajoneria<Chocolatina> cajCho =
            new Cajoneria<Chocolatina>(8);
        
        // Crear cajas
        Caja<Chocolatina> c1 = new Caja<Chocolatina>("Roja");
        Caja<Chocolatina> c2 = new Caja<Chocolatina>("Azul");
        Caja<Chocolatina> c3 = new Caja<Chocolatina>("Verde");
        Caja<Chocolatina> c4 = new Caja<Chocolatina>("Morado");
        Caja<Chocolatina> c11 = new Caja<Chocolatina>("Negra");
        
        c1.guardar(new Chocolatina("milka"));
        c2.guardar(new Chocolatina("ferrero"));
        c3.guardar(new Chocolatina("iberico"));
        c4.guardar(new Chocolatina("fondu"));
        c11.guardar(new Chocolatina("milka"));
        
        cajCho.add(c1);
        cajCho.add(c2);
        cajCho.add(c3);
        cajCho.add(c4);
        cajCho.add(c11);
        
        System.out.println("_________________________________\n");
        System.out.println("CONTENIDO DE LA CAJONERIA - CHOCOLATINA");
        System.out.println(cajCho);
        System.out.println("_________________________________\n");
        
        // Buscar golosinas
        System.out.println("BUSQUEDA DE CHOCOLATINAS\n");
        
        System.out.println("Buscando 'milka' en la cajoneria");
        System.out.println(
            cajCho.search(
                new Chocolatina("milka")
            )
        );
        
        System.out.println("\nBuscando 'chok' en la cajoneria");
        System.out.println(
            cajCho.search(
                new Chocolatina("chok")
            )
        );
        System.out.println("_________________________________\n");
        
        // Probar delete
        System.out.println("\nUSANDO DELETE:");
        System.out.println("Eliminado: " + cajCho.delete(new Chocolatina("ferrero")));
        System.out.println("_________________________________\n");
        
        System.out.println("\nCAJONERIA FINAL:");
        System.out.println(cajCho);
        System.out.println("_________________________________\n");
        
        System.out.println("CONTEO DE MILKAS");
        int cantCho = cajCho.contar(new Chocolatina("milka"));
        System.out.println("Cantidad de 'milka': " + cantCho);
        System.out.println("_________________________________\n");
    }
}
