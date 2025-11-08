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
    TRAP_PATH("#FFFFFF", Shape.CIRCLE),
    TRAP_RANDOM("#FFFFFF", Shape.CIRCLE),
    TRAP_USED("#dbdbdb", Shape.CIRCLE),
    TRAP_TELEPORTER("#800080", Shape.CIRCLE),
    TRAP_FAKE("#78A821", Shape.SQUARE),
    TRAP_PUSH("#FFFFFF", Shape.CIRCLE),
    TRAP_STUN("#FFFFFF", Shape.CIRCLE),
    TRAP_REGENERATE_WALL("#FFFFFF", Shape.CIRCLE),
    TRAP_TURN_WALL("#FFFFFF", Shape.CIRCLE),
    TRAP_MONSTER_SPAWN("#FFFFFF", Shape.CIRCLE),
    TRAP_TELEPORT_EXIT("#FFFFFF", Shape.CIRCLE),
    TRAP_LAVA("#FFFFFF", Shape.CIRCLE);

    private final String colorCode;
    private final Shape shape;

    GameViewConfig(String colorCode, Shape shape) {
        this.colorCode = colorCode;
        this.shape = shape;
    }

    GameViewConfig(String colorCode) {
        this.colorCode = colorCode;
        this.shape = null;
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
