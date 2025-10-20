package fr.univlille.labyrinth.model.cellAlgorithm;

import javafx.scene.paint.Paint;

public enum CellType {
    START(Paint.valueOf("#0000FF")),
    END(Paint.valueOf("#FF0000")),
    SPAWNMONSTER(Paint.valueOf("#00FF00")),
    PATH(Paint.valueOf("#000000"));
    private CellType(Paint paint){
        this.paint=paint;
    }


    private Paint paint;

    public Paint getPaint() {
        return paint;
    }
}
