package fr.univlille.labyrinth.model.maze;

/**
 * Représente les directions haut, bas, droite et gauche
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public enum Direction {
    /**
     * go UP
     */
    UP(0,-1),
    /**
     * go Down
     */
    DOWN(0,1),
    /**
     * go LEFT
     */
    LEFT(-1,0),
    /**
     * go RIGHT
     */
    RIGHT(1,0);
    int x;
    int y;


    private Direction(int x,int y){
        this.x=x;
        this.y=y;
    }
    /**
     * @return int
     */
    public int getX() {
        return x;
    }
    /**
     * @return int
     */
    public int getY() {
        return y;
    }
}
