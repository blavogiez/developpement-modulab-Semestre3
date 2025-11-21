package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import javafx.scene.paint.Color;

/**
 * Configuration d'affichage pour les différents éléments du jeu.
 * Les couleurs et formes sont définies pour chaque type d'entité et de piège dans le labyrinthe.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
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

    /**
     * Constructeur de la configuration d'affichage avec code couleur et forme.
     *
     * @param colorCode Le code couleur au format hexadécimal
     * @param shape La forme à utiliser pour l'affichage
     */
    GameViewConfig(String colorCode, Shape shape) {
        this.colorCode = colorCode;
        this.shape = shape;
    }

    /**
     * Constructeur de la configuration d'affichage avec code couleur seulement.
     *
     * @param colorCode Le code couleur au format hexadécimal
     */
    GameViewConfig(String colorCode) {
        this(colorCode, null);
    }

    /**
     * Constructeur de la configuration d'affichage par défaut.
     */
    GameViewConfig() {
        this("#FFFFFF",Shape.CIRCLE);
    }

    /**
     * Retourne la couleur associée à cette configuration.
     *
     * @return La couleur au format JavaFX Color
     */
    public Color getColor() {
        return Color.web(colorCode);
    }

    /**
     * Retourne le code couleur associé à cette configuration.
     *
     * @return Le code couleur au format hexadécimal
     */
    public String getColorCode() {
        return colorCode;
    }

    /**
     * Retourne la forme associée à cette configuration.
     *
     * @return La forme à utiliser pour l'affichage
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Méthode d'identification statique pour la vue d'un piège.
     *
     * @param trap Le piège pour lequel obtenir la configuration d'affichage
     * @return La configuration d'affichage correspondant au piège, ou TRAP_RANDOM si le piège n'est pas reconnu
     */
    public static GameViewConfig forTrap(Trap trap) {
        try {
            return valueOf(trap.name());
        } catch (IllegalArgumentException e) {
            return TRAP_RANDOM;
        }
    }

    /**
     * Méthode d'identification statique pour la vue d'une entité.
     *
     * @param entity L'entité pour laquelle obtenir la configuration d'affichage
     * @return La configuration d'affichage correspondant à l'entité
     */
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

    /**
     * Méthode d'identification statique pour la vue d'un joueur.
     *
     * @param playerId L'identifiant du joueur
     * @return La configuration d'affichage correspondant au joueur
     */
    public static GameViewConfig forPlayer(int playerId) {
        try {
            return valueOf("PLAYER" + playerId);
        } catch (IllegalArgumentException e) {
            return PLAYER;
        }
    }
}
