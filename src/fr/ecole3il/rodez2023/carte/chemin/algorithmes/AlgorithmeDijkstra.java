package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E>{
    
    /**
     * Trouve le chemin le plus court entre un nœud de départ et un nœud d'arrivée dans un graphe donné.
     * 
     * @param graphe Le graphe dans lequel chercher le chemin.
     * @param depart Le nœud de départ du chemin.
     * @param arrivee Le nœud d'arrivée du chemin.
     * @return Une liste contenant le chemin le plus court du nœud de départ au nœud d'arrivée.
     */
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des coûts et des nœuds précédents
        Map<Noeud<E>, Double> costs = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> previous = new HashMap<>();
        List<Noeud<E>> noeuds = graphe.getNoeuds();

        // Initialisation des coûts pour chaque nœud avec les coûts des autres nœuds mis à l'infini
        for (int i = 0; i < noeuds.size(); i++) {
            Noeud<E> noeud = noeuds.get(i);
            costs.put(noeud, Double.POSITIVE_INFINITY);
            previous.put(noeud, null);
        }

        // Initialisation du coût du nœud de départ à zéro
        costs.put(depart, 0.0);

        // File de priorité pour suivre les nœuds à explorer
        PriorityQueue<Noeud<E>> noeudsAExplorer = new PriorityQueue<>((node1, node2) -> Double.compare(costs.get(node1), costs.get(node2)));
        noeudsAExplorer.add(depart);

        // Boucle principale de l'algorithme
        while (!noeudsAExplorer.isEmpty()) 
        {
            // Récupérer le nœud avec le coût le plus bas
            Noeud<E> n = noeudsAExplorer.poll();

            // Si le nœud d'arrivée est atteint, terminer
            if (n.equals(arrivee)) 
            {
                break;
            }

            // Explorer les voisins du nœud actuel
            List<Noeud<E>> voisins = graphe.getVoisins(n);
            for (int i = 0; i < voisins.size(); i++) 
            {
                Noeud<E> voisin = voisins.get(i);
                // Calculer le nouveau coût
                double nouveauCout = costs.get(n) + graphe.getCoutArete(n, voisin);
                // Si le nouveau coût est plus petit que l'ancien
                if (nouveauCout < costs.get(voisin)) 
                {
                    costs.put(voisin, nouveauCout); // Mettre à jour le coût du voisin
                    previous.put(voisin, n); // Mettre à jour le nœud précédent du voisin
                    noeudsAExplorer.add(voisin); // Ajouter le voisin à la file de priorité
                }
            }
        }

        // Reconstruction du chemin le plus court
        Noeud<E> noeudActuel = arrivee;
        LinkedList<Noeud<E>> chemin = new LinkedList<>();
        chemin.addFirst(noeudActuel); // Ajouter le nœud d'arrivée au début du chemin
        while (noeudActuel != depart) 
        {
            Noeud<E> predecesseur = previous.get(noeudActuel);
            chemin.addFirst(predecesseur); // Ajouter le prédécesseur au début du chemin
            noeudActuel = predecesseur; // Avancer vers le prédécesseur suivant
        }
        return chemin; // Retourner le chemin trouvé
    }
}
