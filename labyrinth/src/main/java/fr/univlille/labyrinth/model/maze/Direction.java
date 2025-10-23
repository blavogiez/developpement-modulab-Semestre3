package fr.univlille.labyrinth.model.maze;

/**
 * Représente les directions haut, bas, droite et gauche
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public enum Direction {

    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0);
    final int x;
    final int y;

    private Direction(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
