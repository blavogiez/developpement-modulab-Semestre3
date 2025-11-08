package fr.univlille.labyrinth.view;

import javafx.scene.paint.Color;

public enum GameViewConfig {
    PLAYER("#e01111ff", Shape.CIRCLE),
    EXIT("#78A821", Shape.SQUARE),
    MONSTER("#821111", Shape.TRIANGLE),
    WALL("#555555"),
    PATH("#FFFFFF"),
    OUT_OF_BOUNDS("#808080"),
    COMPLETED("#66BB6A"),
    TRAP_PATH("#FFFFFF"),
    TRAP_RANDOM("#FFFFFF"),
    TRAP_USED("#dbdbdb"),
    TRAP_TELEPORTER("#800080"),
    TRAP_FAKE("#78A821"),
    TRAP_PUSH("#FFFFFF"),
    TRAP_STUN("#FFFFFF"),
    TRAP_REGENERATE_WALL("#FFFFFF"),
    TRAP_TURN_WALL("#FFFFFF"),
    TRAP_MONSTER_SPAWN("#FFFFFF"),
    TRAP_TELEPORT_EXIT("#FFFFFF"),
    TRAP_LAVA("#FFFFFF");

    private final String colorCode;
    private final Shape shape;

    GameViewConfig(String colorCode, Shape shape) {
        this.colorCode = colorCode;
        this.shape = shape;
    }

    GameViewConfig(String colorCode) {
        this(colorCode, Shape.TRIANGLE);
    }

    public Color getColor() {
        return Color.web(colorCode);
    }

    public String getColorCode() {
        return colorCode;
    }

    public Shape getShape() {
        return shape;
    }
}
