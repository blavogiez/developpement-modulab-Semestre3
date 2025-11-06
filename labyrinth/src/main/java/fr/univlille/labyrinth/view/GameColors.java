package fr.univlille.labyrinth.view;

import javafx.scene.paint.Color;

// Transformer en enum compatible avec canvas pour éviter Magic colors

public enum GameColors {
    PLAYER ("#FF0000"),
    EXIT("#66BB6A"),
    WALL ("#555555"),
    PATH ("#FFFFFF"),
    TELEPORTER_TRAP("#800080"),
    OUT_OF_BOUNDS("#808080"),
    COMPLETED ("#66BB6A"), TRAP_USED("#dbdbdb");

    private String colorCode ;

    private GameColors(String colorCode) {
        this.colorCode=colorCode;
    }

    public Color getColor() {
        return Color.web(colorCode);
    }

    public String getColorCode(){
        return this.colorCode;
    }
}
