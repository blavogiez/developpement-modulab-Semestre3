package fr.univlille.labyrinth.model.maze;

import java.util.Objects;
import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }

    /**
     * @return coordonnée x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return coordonnée y
     */
    public int getY() {
        return this.y;
    }

    /** 
     * @param newX
     */
    public void setX(int newX) {
        if(newX>=0) this.x=newX;
    }

    /** 
     * @param newY
     */
    public void setY(int newY) {
        if(newY>=0) this.y=newY;
    }

    /** 
     * @param x
     */
    public void addX(int x){
        this.x+=x;
    }

    /** 
     * @param y
     */
    public void addY(int y){
        this.y+=y;
    }

    /** 
     * @return Position
     */
    public Position copy(){
        return new Position(this.x,this.y);
    }

    /** 
     * @param x
     * @param y
     * @return Position
     */
    public Position add(int x, int y){
        return new Position(this.x+x,this.y+y);
    }

    /** 
     * @param next
     * @return Position
     */
    public Position min(Position next) {
        if (this.x<next.x || this.y<next.y) return this;
        return next;
    }

    /** 
     * @param next
     * @return Direction
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
     */
    public static Position getRandomPosition(int height, int width){

        int x = RANDOM.nextInt(width);
        int y = RANDOM.nextInt(height);
        return new Position(x,y);
    }


    /**
     * @param o objet comparé
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * @return le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
