package fr.univlille.labyrinth.model.maze;

import java.util.Objects;
import java.util.Random;

/**
 * Représente une position dans le labyrinthe avec des coordonnées X et Y.
 * Cette classe permet de manipuler les positions et effectuer des opérations
 * telles que l'ajout de déplacements ou la comparaison de positions.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructeur de la position.
     *
     * @param x la coordonnée X
     * @param y la coordonnée Y
     */
    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }

    /**
     * Retourne la coordonnée X de la position.
     *
     * @return la coordonnée X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée Y de la position.
     *
     * @return la coordonnée Y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Définit la coordonnée X de la position.
     *
     * @param newX la nouvelle coordonnée X (doit être >= 0)
     */
    public void setX(int newX) {
        if(newX>=0) this.x=newX;
    }

    /**
     * Définit la coordonnée Y de la position.
     *
     * @param newY la nouvelle coordonnée Y (doit être >= 0)
     */
    public void setY(int newY) {
        if(newY>=0) this.y=newY;
    }

    /**
     * Ajoute une valeur à la coordonnée X.
     *
     * @param x la valeur à ajouter à la coordonnée X
     */
    public void addX(int x){
        this.x+=x;
    }

    /**
     * Ajoute une valeur à la coordonnée Y.
     *
     * @param y la valeur à ajouter à la coordonnée Y
     */
    public void addY(int y){
        this.y+=y;
    }

    /**
     * Crée une copie de la position.
     *
     * @return une nouvelle instance de Position avec les mêmes coordonnées
     */
    public Position copy(){
        return new Position(this.x,this.y);
    }

    /**
     * Crée une nouvelle position en ajoutant des déplacements aux coordonnées actuelles.
     *
     * @param x le déplacement à ajouter à la coordonnée X
     * @param y le déplacement à ajouter à la coordonnée Y
     * @return une nouvelle instance de Position avec les coordonnées modifiées
     */
    public Position add(int x, int y){
        return new Position(this.x+x,this.y+y);
    }

    /**
     * Retourne la position avec les coordonnées minimales entre cette position et une autre.
     *
     * @param next l'autre position à comparer
     * @return la position avec les coordonnées minimales
     */
    public Position min(Position next) {
        if (this.x<next.x || this.y<next.y) return this;
        return next;
    }

    /**
     * Calcule la direction entre cette position et une autre.
     *
     * @param next l'autre position
     * @return la direction entre les deux positions
     */
    public Direction diff(Position next) {
        int[] values = new int[]{this.x-next.x,this.y- next.y};
        return Direction.getDirection(values[0],values[1]);
    }

    private static final Random RANDOM = new Random();

    /** 
     * @param height
     * @param width
     * @return Position
    /**
     * Génère une position aléatoire dans les limites spécifiées.
     *
     * @param height la hauteur maximale (limite Y)
     * @param width la largeur maximale (limite X)
     * @return une position aléatoire dans les limites
     */
    public static Position getRandomPosition(int height, int width){

        int x = RANDOM.nextInt(width);
        int y = RANDOM.nextInt(height);
        return new Position(x,y);
    }


    /**
     * Vérifie l'égalité avec un autre objet.
     *
     * @param o l'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Calcule le code de hachage de la position.
     *
     * @return le code de hachage basé sur les coordonnées X et Y
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Retourne une représentation textuelle de la position.
     *
     * @return une chaîne de caractères décrivant la position
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
