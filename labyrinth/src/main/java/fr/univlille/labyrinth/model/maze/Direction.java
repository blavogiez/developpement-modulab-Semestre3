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
     * Retourne la composante X de la direction.
     *
     * @return la composante X (-1 pour gauche, 1 pour droite, 0 pour haut/bas)
     */
    public int getX() {
        return x;
    }
    /**
     * Retourne la composante Y de la direction.
     *
     * @return la composante Y (-1 pour haut, 1 pour bas, 0 pour gauche/droite)
     */
    public int getY() {
        return y;
    }

    /**
     * Obtient une direction à partir de coordonnées X et Y.
     *
     * @param x la composante X (-1, 0 ou 1)
     * @param y la composante Y (-1, 0 ou 1)
     * @return la direction correspondant aux coordonnées
     * @throws DirectionException si les coordonnées ne correspondent à aucune direction
     */
    public static Direction getDirection(int x, int y){
        if (x==0 && y<0) return UP;
        else if (x==0 && y>0) return DOWN;
        else if (x<0 && y == 0) return LEFT;
        else if (x>0 && y == 0) return RIGHT;
        else throw new DirectionException("Impossible d'obtenir la direction si les valeurs sont nuls !");
    }

    /**
     * Obtient une direction entre deux positions.
     *
     * @param playerPosition la position de départ
     * @param playerPosition2 la position d'arrivée
     * @return la direction entre les deux positions
     */
    public static Direction getDirection(Position playerPosition, Position playerPosition2){

        return getDirection(playerPosition.getX()-playerPosition2.getX(), playerPosition.getY()-playerPosition2.getY());
    }
    private static Random random;

    /**
     * Obtient une direction aléatoire.
     *
     * @return une direction aléatoire parmi les quatre directions possibles
     */
    public static Direction getRandomDirection(){
        if (random==null) random=new Random();
        return values()[random.nextInt(0,values().length)];
    }
}
