package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud<E> 
{
    // La valeur stockée dans le nœud.
    private E valeur;

    // La liste des nœuds adjacents.
    private List<Noeud<E>> voisin;

    /**
     * Construit un nouveau nœud avec la valeur donnée.
     * @param valeur la valeur à stocker dans le nœud
     */
    public Noeud(E valeur)
    {
        this.valeur = valeur;
        this.voisin = new ArrayList<Noeud<E>>();
    }

    /**
     * Récupère la valeur stockée dans le nœud.
     * @return la valeur stockée dans le nœud
     */
    public E getValeur()
    {
        return valeur;
    }

    /**
     * Récupère la liste des nœuds adjacents.
     * @return la liste des nœuds adjacents
     */
    public List<Noeud<E>> getVoisin()
    {
        return voisin;
    }

    /**
     * Ajoute un nœud voisin à la liste des nœuds adjacents.
     * @param voisin le nœud voisin à ajouter
     */
    public void ajouterVoisin(Noeud<E> voisin)
    {
        this.voisin.add(voisin);
    }
}
