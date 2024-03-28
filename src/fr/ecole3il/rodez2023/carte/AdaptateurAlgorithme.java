package fr.ecole3il.rodez2023.carte;

import java.util.ArrayList;
import java.util.List;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

public class AdaptateurAlgorithme {
    /**
     * Trouve le chemin le plus court entre deux cases sur la carte en utilisant l'algorithme spécifié.
     *
     * @param algorithme L'algorithme à utiliser pour trouver le chemin.
     * @param carte La carte sur laquelle chercher le chemin.
     * @param xDepart La coordonnée x de la case de départ.
     * @param yDepart La coordonnée y de la case de départ.
     * @param xArrivee La coordonnée x de la case d'arrivée.
     * @param yArrivee La coordonnée y de la case d'arrivée.
     * @return Le chemin le plus court entre les deux cases, ou null si aucun chemin n'est trouvé.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee)
    {
        Graphe<Case> graphe = creerGraphe(carte);

        // Récupération des nœuds de départ et d'arrivée dans le graphe
        Noeud<Case> depart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> arrivee = graphe.getNoeud(xArrivee, yArrivee);

        // Vérification si les nœuds de départ et d'arrivée sont valides
        if (depart == null || arrivee == null) 
        {
            System.err.println("Les coordonnées de départ ou d'arrivée sont invalides.");
            return null;
        }

        // Utilisation de l'algorithme pour trouver le chemin le plus court
        List<Noeud<Case>> listeNoeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);

        // Conversion de la liste de nœuds en liste de cases
        List<Case> casesChemin = new ArrayList<>();
        for (Noeud<Case> noeud : listeNoeudsChemin) 
        {
            casesChemin.add(noeud.getValeur());
        }

        // Création de l'objet Chemin à partir de la liste de cases
        Chemin chemin = new Chemin(casesChemin);
        return chemin;
    }

    /**
     * Crée un graphe représentant la carte en utilisant les cases comme nœuds
     * et ajoute des arêtes entre les cases voisines.
     *
     * @param carte La carte à partir de laquelle créer le graphe.
     * @return Le graphe représentant la carte avec des cases comme nœuds et des arêtes entre les cases voisines.
     */
    public static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Parcours de toutes les cases de la carte
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                 Noeud<Case> caseCourante = new Noeud<>(carte.getCase(i, j));
                graphe.ajouterNoeud(caseCourante); // Ajout de la case courante comme nœud dans le graphe
            }
        }

        // Ajout des arêtes entre les cases voisines
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                Noeud<Case> caseCourante = graphe.getNoeud(i, j);
                ajouterAretesVoisines(graphe, caseCourante, i, j, largeur, hauteur);
            }
        }

        return graphe;
    }

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentNoeud, int x, int y, int largeur, int hauteur) {
           
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Noeud<Case> voisinNoeud = graphe.getNoeud(newX, newY);
                    if (voisinNoeud != null) {
                        double cout = calculerCout(currentNoeud.getValeur(), voisinNoeud.getValeur());
                        graphe.ajouterArete(currentNoeud, voisinNoeud, cout);
                        currentNoeud.ajouterVoisin(voisinNoeud);
                    }
                }
            }
        }
    }

    /**
     * Calcule le coût pour se déplacer d'une case à une autre.
     *
     * @param from La case de départ.
     * @param to La case d'arrivée.
     * @return Le coût pour se déplacer de la case de départ à la case d'arrivée.
     */
    public static double calculerCout(Case from, Case to) {
        // Exemple de calcul de coût : distance euclidienne entre les deux cases
        double distance = Math.sqrt(Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getY() - from.getY(), 2));
        return distance;
    }

    public static void afficherChemin(List<Noeud<Case>> chemin) {
        System.out.println("Chemin trouvé :");
        for (Noeud<Case> noeud : chemin) {
            Case caseCourante = noeud.getValeur();
            System.out.println("[" + caseCourante.getX() + ", " + caseCourante.getY() + "]");
        }
    }
}
