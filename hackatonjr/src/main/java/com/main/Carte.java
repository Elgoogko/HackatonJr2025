package com.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Carte {
    private List<Lieu> lieux;
    private List<Chemin> chemins;

    public Carte(List<Lieu> lieux, List<Chemin> chemins) {
        this.lieux = lieux;
        this.chemins = chemins;
    }

    public List<Lieu> getLieux() {
        return lieux;
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public ArrayList<Lieu> plusCourtChemin(Lieu depart, Lieu arrivee, ModeTransport modeTransport) {
        // Implémentation de l'algorithme de Dijkstra pour trouver le plus court chemin

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

                if (nouvelleDist < distances.getOrDefault(voisin, Float.MAX_VALUE) && transportOk) {
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

    public ArrayList<Lieu> init_liste_lieux() {
        ArrayList<Lieu> lieux = new ArrayList<Lieu>();
        lieux.add(new Lieu(0, "Village du grand chef", 25, "C'est un village. Il est dirigé par un très grand chef.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(50, 1000)));
        lieux.add(new Lieu(1, "Canyon central", 30, "Un canyon immense avec des parois abruptes.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(150, 850)));
        lieux.add(new Lieu(2, "Vaisseau de Freezer", 5, "Le vaisseau spatial de Freezer est posé ici.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(75, 600)));
        lieux.add(new Lieu(3, "Village de Moori", 28, "Un village paisible habité par des Nameks.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(400, 500)));
        lieux.add(new Lieu(4, "Maison de Guru", 15, "Maison du grand Namek Guru.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(600, 600)));
        lieux.add(new Lieu(5, "Vieux temple", 22, "Un temple ancien et mystérieux.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(550, 50)));
        lieux.add(new Lieu(6, "Fret de Namek", 27, "Un grand fret rempli de marchandises.", null, new java.util.ArrayList<Chemin>(),
                new Coordonnees(50, 50)));
        
                
        return lieux;
    }
}
