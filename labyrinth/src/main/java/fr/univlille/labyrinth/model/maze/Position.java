package fr.univlille.labyrinth.model.maze;

import java.util.Objects;

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

    public void setX(int newX) {
        if(newX>=0) this.x=newX;
    }

    public void setY(int newY) {
        if(newY>=0) this.y=newY;
    }

    public void addX(int x){
        this.x+=x;
    }

    public void addY(int y){
        this.y+=y;
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


}
