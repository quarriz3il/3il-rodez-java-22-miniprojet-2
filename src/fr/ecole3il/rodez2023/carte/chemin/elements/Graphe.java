package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E>
{
    private List<Noeud<E>> noeuds;
    private Map<List<Noeud<E>>,Double> arete;

    /**
     * Constructeur par défaut de la classe Graphe.
     * Initialise la liste des nœuds et la map des arêtes.
     */
    public Graphe()
     {
        this.noeuds = new ArrayList<>();
        this.arete = new HashMap<>();
    }

    /**
     * Ajoute un nœud au graphe s'il n'existe pas déjà.
     * @param noeud le nœud à ajouter.
     */
    public void ajouterNoeud(Noeud<E> noeud)
    {
        if(!noeuds.contains(noeud))
        {
            noeuds.add(noeud);
        }
    }

    /**
     * Ajoute une arête pondérée entre deux nœuds du graphe.
     * Si les nœuds spécifiés n'existent pas, ils sont ajoutés au graphe.
     * @param depart  le nœud de départ de l'arête.
     * @param arrivee le nœud d'arrivée de l'arête.
     * @param cout    le coût de l'arête.
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout)
    {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        
        List<Noeud<E>> chemin = new ArrayList<>(); 
        chemin.add(depart);
        chemin.add(arrivee);

        arete.put(chemin, cout);
        depart.ajouterVoisin(arrivee);
    }

      /**
     * Récupère le coût d'une arête entre deux nœuds spécifiés.
     * @param depart  le nœud de départ de l'arête.
     * @param arrivee le nœud d'arrivée de l'arête.
     * @return le coût de l'arête entre les deux nœuds.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee)
    {
        List<Noeud<E>> chemin = new ArrayList<>(); 
        chemin.add(depart);
        chemin.add(arrivee);
        return arete.get(chemin);
    }

    /**
     * Récupère une liste contenant tous les nœuds du graphe.
     * @return une liste contenant tous les nœuds du graphe.
     */
    public List<Noeud<E>> getNoeuds()
    {
        return noeuds;
    }

    /**
     * Récupère une liste des voisins d'un nœud spécifié.
     * @param noeud le nœud pour lequel récupérer les voisins.
     * @return une liste des voisins du nœud spécifié.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud)
    {
        if(!noeuds.contains(noeud))
        {
            return new ArrayList<>();
        }
        return noeud.getVoisin();
    }
}
