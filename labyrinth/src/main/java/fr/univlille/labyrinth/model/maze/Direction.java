package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.exceptions.DirectionException;

import java.util.Random;

/**
 * Représente les directions haut, bas, droite et gauche
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public enum     Direction {
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
    final int x;
    final int y;


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

    public static Direction getDirection(int x, int y){
        if (x==0 && y<0) return UP;
        else if (x==0 && y>0) return DOWN;
        else if (x<0 && y == 0) return LEFT;
        else if (x>0 && y == 0) return RIGHT;
        else throw new DirectionException("Impossible d'obtenir la direction si les valeurs sont nuls !");
    }

    public static Direction getDirection(Position playerPosition, Position playerPosition2){

        return getDirection(playerPosition.getX()-playerPosition2.getX(), playerPosition.getY()-playerPosition2.getY());
    }

    public static Direction getRandomDirection(){
        return values()[new Random().nextInt(0,values().length)];
    }
}
