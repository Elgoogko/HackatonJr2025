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
            
                boolean transportOk = modeTransport == chemin.getModeTransportOk() || chemin.getModeTransportOk() == ModeTransport.TOUS;
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
}
