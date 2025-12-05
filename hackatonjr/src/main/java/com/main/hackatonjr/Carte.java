package com.main.hackatonjr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

@Component
public class Carte {
    private ArrayList<Lieu> lieux;
    private ArrayList<Chemin> chemins;

    public Carte() {
        init_liste_lieux();
    }

    public List<Lieu> getLieux() {
        return lieux;
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public ArrayList<Lieu> plusCourtChemin(Lieu depart, Lieu arrivee, ModeTransport modeTransport) {
        // Implémentation de l'algorithme de Dijkstra pour trouver le plus court chemin
        if (modeTransport == ModeTransport.PILIER) {
            // mode pilier : on peut aller directement au lieu d'arrivée
            ArrayList<Lieu> cheminsFinal = new ArrayList<Lieu>();
            cheminsFinal.add(depart);
            cheminsFinal.add(arrivee);
            return cheminsFinal;
        }
        // tab avec distance minimale entre chaque lieu et le départ
        Map<Lieu, Float> distances = new HashMap<>();

        // Pour reconstruire le chemin final, tab des précédents
        Map<Lieu, Lieu> precedent = new HashMap<>();

        // Noeuds à explorer (ordre par distance minimale)
        PriorityQueue<Lieu> file = new PriorityQueue<>(Comparator.comparing(distances::get));

        // Initialisation
        distances.put(depart, 0f);
        file.add(depart);

        while (!file.isEmpty()) { // tant qu'il reste des noeuds à explorer
            Lieu courant = file.poll(); // Lieu avec la distance minimale et le retire

            if (courant.equals(arrivee)) { // arrivée atteinte
                break;
            }

            for (Chemin chemin : courant.getChemins()) { // pour chaque chemin adjacent au lieu courant

                boolean transportOk = modeTransport == chemin.getModeTransportOk()
                        || chemin.getModeTransportOk() == ModeTransport.TOUS;
                Lieu voisin = chemin.getAutre(courant); // donne le lieu auquel mène le chemin
                float nouvelleDist = distances.getOrDefault(courant, Float.MAX_VALUE)
                        + chemin.getDistance(); // dist = distance pour aller au courant (ou l'infini par defaut si
                                                // vide) + distance du chemin

                if (nouvelleDist < distances.getOrDefault(voisin, Float.MAX_VALUE) && transportOk
                        && !voisin.estCondamne()) {
                    // si cette nouvelle distance est plus courte que celle deja connue (tjr vrai si
                    // pas encore visitée)
                    distances.put(voisin, nouvelleDist);
                    precedent.put(voisin, courant);
                    file.add(voisin); // ajoute le voisin à la file pour exploration future
                }
            }
        }

        // Reconstruction du chemin
        ArrayList<Lieu> cheminFinal = new ArrayList<>();
        Lieu actuel = arrivee;

        while (actuel != null) {
            cheminFinal.add(0, actuel); // insert au début de la liste
            actuel = precedent.get(actuel); // remonte au lieu précédent (rappel : ils sont indexés par le lieu actuel)
        }

        // Si le premier lieu n'est pas le départ --> aucun chemin trouvé
        if (cheminFinal.isEmpty() || !cheminFinal.get(0).equals(depart)) {
            return new ArrayList<>();
        }

        return cheminFinal;
    }

    public float totalDistanceChemin(ArrayList<Lieu> cheminLieux, ModeTransport modeTransport) {
        float totalDistance = 0f;
        if (modeTransport == ModeTransport.PILIER) {
            return Coordonnees.distance(cheminLieux.get(0).getCoordonnees(),
                    cheminLieux.get(cheminLieux.size() - 1).getCoordonnees());
        }
        for (int i = 0; i < cheminLieux.size() - 1; i++) {
            Lieu actuel = cheminLieux.get(i);
            Lieu suivant = cheminLieux.get(i + 1);
            // Trouver le chemin entre les deux lieux
            for (Chemin c : actuel.getChemins()) {
                if (c.getAutre(actuel).equals(suivant)) {
                    totalDistance += c.getDistance();
                    break;
                }
            }
        }
        return totalDistance;
    }

    public ArrayList<Lieu> plusCourtCheminAvecEtape(Lieu depart, Lieu etape, Lieu arrivee,
            ModeTransport modeTransport) {
        ArrayList<Lieu> premierSegment = plusCourtChemin(depart, etape, modeTransport);
        ArrayList<Lieu> deuxiemeSegment = plusCourtChemin(etape, arrivee, modeTransport);
        if (premierSegment.isEmpty() || deuxiemeSegment.isEmpty()) {
            return new ArrayList<>(); // Aucun chemin trouvé
        }
        // Fusionner les deux segments en évitant de dupliquer l'étape
        premierSegment.remove(premierSegment.size() - 1); // Retirer l'étape du premier segment
        premierSegment.addAll(deuxiemeSegment); // Ajouter le deuxième segment
        return premierSegment;
    }

    public ArrayList<Lieu> init_liste_lieux() {
        lieux = new ArrayList<Lieu>();
        lieux.add(new Lieu(0, "Village du grand chef", 25, "C'est un village. Il est dirigé par un très grand chef.",
                null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(50, 950)));
        lieux.add(new Lieu(1, "Canyon central", 30, "Un canyon immense avec des parois abruptes.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(150, 850)));
        lieux.add(new Lieu(2, "Vaisseau de Freezer", 5, "Le vaisseau spatial de Freezer est posé ici.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(75, 600)));
        lieux.add(new Lieu(3, "Village de Moori", 28, "Un village paisible habité par des Nameks.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(400, 500)));
        lieux.add(new Lieu(4, "Maison de Guru", 15, "Maison du grand Namek Guru.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(600, 600)));
        lieux.add(new Lieu(5, "Vieux temple", 22, "Un temple ancien et mystérieux.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(550, 50)));
        lieux.add(new Lieu(6, "Fôret de Namek", 27, "Un grand fret rempli de marchandises.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(50, 50)));
        lieux.add(new Lieu(7, "Pont rocheux", 26, "Un pont naturel fait de roches.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(950, 300)));
        lieux.add(new Lieu(8, "Temple sous-marin", 18, "Un temple mystérieux sous l'eau.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(900, 800)));
        lieux.add(new Lieu(9, "Mines de Namek", 29, "Des mines riches en minerais précieux.", null,
                new java.util.ArrayList<Chemin>(),
                new Coordonnees(750, 900)));

        chemins = new ArrayList<Chemin>();
        // relier les points qui se suivent entre eux
        for (int i = 0; i < lieux.size() - 1; i++) {
            Chemin c = new Chemin(lieux.get(i), lieux.get(i + 1), ModeTransport.TOUS);
            chemins.add(c);
            lieux.get(i).addChemin(c);
            lieux.get(i + 1).addChemin(c);
        }
        // relier quelques points supplémentaires
        Chemin c1 = new Chemin(lieux.get(0), lieux.get(2), ModeTransport.NUAGE);
        Chemin c2 = new Chemin(lieux.get(1), lieux.get(9), ModeTransport.VOITURE);
        Chemin c3 = new Chemin(lieux.get(1), lieux.get(4), ModeTransport.TOUS);
        Chemin c4 = new Chemin(lieux.get(2), lieux.get(6), ModeTransport.NUAGE);
        chemins.add(c1);
        chemins.add(c2);
        chemins.add(c3);
        chemins.add(c4);
        lieux.get(0).addChemin(c1);
        lieux.get(2).addChemin(c1);
        lieux.get(1).addChemin(c2);
        lieux.get(9).addChemin(c2);
        lieux.get(1).addChemin(c3);
        lieux.get(4).addChemin(c3);
        lieux.get(2).addChemin(c4);
        lieux.get(6).addChemin(c4);

        return lieux;
    }

    public void afficherLieux(){
        int compteur=1;
        System.out.println("Lieux (" + this.lieux.size() + " éléments) : ");
        for(Lieu lieu : this.lieux){
            System.out.println(compteur + ". " + lieu.getNom());
            compteur++;
        }
    }

    public void afficherChemins(){
        int compteur=1;
        System.out.println("\nChemins : ");
        for(Lieu lieu : this.lieux){
            ArrayList<Chemin> chemins = lieu.getChemins();
            System.out.println(compteur + ". " + lieu.getNom());
            for(Chemin c : chemins){
                if(c.getLieuA() == lieu){
                    System.out.println("## " + c.getLieuA().getNom() + " --> " + c.getLieuB().getNom());
                }
                else{
                    System.out.println("## " + c.getLieuB().getNom() + " --> " + c.getLieuA().getNom());
                }
            }
            System.out.print("\n");
            compteur++;
        }
    }
}
