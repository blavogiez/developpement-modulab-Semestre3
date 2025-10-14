package fr.univlille.labyrinth.model.algorithm;

public class Cell {
    int x;
    int y;

    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }




    public Cell add(int i, int i1) {
        return new Cell(this.x+i,this.y+i1);
    }
}
