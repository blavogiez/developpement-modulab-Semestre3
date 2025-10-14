package fr.univlille.labyrinth.model.algorithm;

/**
 * Cette classe agit comme un tableau de nombre entier, utilisé pour la génération de labyrinthe
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Cell {

    int x;
    int y;

    /**
     * Cette méthode permet de créer une cellule
     *
     * @param x coordonnée des abscisses.
     * @param y coordonnée des ordonnées.
     */
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Cette méthode renvoie une nouvelle cellule, copiant ses coordonnées et en ajoutant des valeurs.
     *
     * @param x coordonnée des abscisses.
     * @param y coordonnée des ordonnées.
     */
    public Cell add(int x, int y) {
        return new Cell(this.x+x,this.y+y);
    }
}
