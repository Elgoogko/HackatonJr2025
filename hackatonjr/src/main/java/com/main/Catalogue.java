public class Catalogue {
    public static ArrayList<Vetement> Vetements = new ArrayList<>();
    public static ArrayList<Nourriture> Nourritures = new ArrayList<>();
    public Catalogue() {
        Vetement.add(new Vetement("Chapeau de Chaozu", 200.0f, 5.0f, TYPE_VETEMENT.Tete));
        Vetement.add(new Vetement("Echarpe", 100.0f, 3.0f, TYPE_VETEMENT.Tete));
        Vetement.add(new Vetement("Turban de Piccolo", 400.0f, 7.0f, TYPE_VETEMENT.Tete));
        Vetement.add(new Vetement("Haut de Goku", 500.0f, 8.0f, TYPE_VETEMENT.Haut));
        Vetement.add(new Vetement("Haut de Piccolo", 800.0f, 9.0f, TYPE_VETEMENT.Haut));
        Vetement.add(new Vetement("Haut de Vegeta", 600.0f, 8.0f, TYPE_VETEMENT.Haut));
        Vetement.add(new Vetement("Bas de Goku", 300.0f, 6.0f, TYPE_VETEMENT.Bas));
        Vetement.add(new Vetement("Bas de Vegeta", 400.0f, 6.0f, TYPE_VETEMENT.Bas));
        Vetement.add(new Vetement("Bas de Piccolo", 500.0f, 7.0f, TYPE_VETEMENT.Bas));

        
    }
}
