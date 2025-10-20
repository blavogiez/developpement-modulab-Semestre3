package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Position;

import java.util.Objects;

/**
 * Cette classe agit comme un tableau de nombre entier, utilisé pour la génération de labyrinthe
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class CellAlgorithmBoolean {

    int x;
    int y;

    /**
     * Cette méthode permet de créer une cellule
     *
     * @param x coordonnée des abscisses.
     * @param y coordonnée des ordonnées.
     */
    public CellAlgorithmBoolean(int x, int y){
        this.x=x;
        this.y=y;
    }
    public CellAlgorithmBoolean(Position position){
        this(position.getX(), position.getY());
    }


    /**
     * Cette méthode renvoie une nouvelle cellule, copiant ses coordonnées et en ajoutant des valeurs.
     *
     * @param x coordonnée des abscisses.
     * @param y coordonnée des ordonnées.
     */
    public CellAlgorithmBoolean add(int x, int y) {
        return new CellAlgorithmBoolean(this.x+x,this.y+y);
    }

    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (obj==this) return true;
        if (obj instanceof CellAlgorithmBoolean cellAlgorithmBoolean){
            return this.x== cellAlgorithmBoolean.x && this.y== cellAlgorithmBoolean.y;
        }
        return false;
    }

    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
