package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import javafx.scene.paint.Color;

public enum GameViewConfig {
    PLAYER("#e01111ff", Shape.CIRCLE),
    PLAYER0("#e01111ff", Shape.CIRCLE),
    PLAYER1("#1155ff", Shape.CIRCLE),
    PLAYER2("#11dd55", Shape.CIRCLE),
    PLAYER3("#ff9911", Shape.CIRCLE),
    EXIT("#78A821", Shape.SQUARE),
    MONSTER("#821111", Shape.TRIANGLE),
    WALL("#555555"),
    PATH("#FFFFFF"),
    OUT_OF_BOUNDS("#808080"),
    COMPLETED("#66BB6A"),
    TRAP_RANDOM,
    TRAP_USED("#dbdbdb", Shape.SQUARE),
    TRAP_TELEPORT("#800080", Shape.SQUARE),
    TRAP_FAKE_EXIT("#78A821", Shape.SQUARE),
    TRAP_PUSH,
    TRAP_GENERATE,
    TRAP_STUN,
    TRAP_HIDE_WALL,
    TRAP_TELEPORT_EXIT,
    TRAP_LAVA("#cf1020", Shape.SQUARE);

    private final String colorCode;
    private final Shape shape;

    GameViewConfig(String colorCode, Shape shape) {
        this.colorCode = colorCode;
        this.shape = shape;
    }

    GameViewConfig(String colorCode) {
        this(colorCode, null);
    }

    GameViewConfig() {
        this("#FFFFFF",Shape.CIRCLE);
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

    /*
     * Méthodes d'identifications static pour la vue
     */
    public static GameViewConfig forTrap(Trap trap) {
        try {
            return valueOf(trap.name());
        } catch (IllegalArgumentException e) {
            return TRAP_RANDOM;
        }
    }

    public static GameViewConfig forEntity(Entity entity) {
        if (entity.getEntityType()==EntityType.PLAYER) {
            return forPlayer(((PlayerEntity) entity).getID());
        }
        try {
            return valueOf(entity.getEntityType().name());
        } catch (IllegalArgumentException e) {
            return PLAYER;
        }
    }

    public static GameViewConfig forPlayer(int playerId) {
        try {
            return valueOf("PLAYER" + playerId);
        } catch (IllegalArgumentException e) {
            return PLAYER;
        }
    }
}
