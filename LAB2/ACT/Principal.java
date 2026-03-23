public class Principal {
    public static void main(String[] args) {

        // Bolsa de Chocolatinas
        Bolsa<Chocolatina> bolsaCho = new Bolsa<Chocolatina>(8);
        Chocolatina c = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");

        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);

        System.out.println("_________________________________");
        for (Chocolatina chocolatina : bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        System.out.println("_________________________________");

        // Bolsa de Golosinas
        Bolsa<Golosina> bolsaGolo = new Bolsa<Golosina>(5);
        Golosina g1 = new Golosina("gomitas", 0.5);
        Golosina g2 = new Golosina("caramelos", 0.2);
        Golosina g3 = new Golosina("chicle", 0.1);

        bolsaGolo.add(g1);
        bolsaGolo.add(g2);
        bolsaGolo.add(g3);

        for (Golosina g : bolsaGolo) {
            System.out.println(g.getNombre() + " - " + g.getPeso() + " g");
        }
        System.out.println("_________________________________");
    }
}
