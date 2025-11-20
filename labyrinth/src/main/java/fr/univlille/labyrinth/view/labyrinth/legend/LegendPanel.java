package fr.univlille.labyrinth.view.labyrinth.legend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.TrapManager;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/*
Panel d'informations observant le labyrinthe pour mettre à jour les données à chaque changement 
 */
public class LegendPanel extends VBox implements Observer<ObservableMaze> {
    private static final double PANEL_PADDING = 10;
    private static final double PANEL_SPACING = 5;

    private final LegendType type;

    public enum LegendType {
        ENTITIES,
        TRAPS
    }

    public LegendPanel(String title, LegendType type) {
        this.type = type;
        setPadding(new Insets(PANEL_PADDING));
        setSpacing(PANEL_SPACING);
        setAlignment(Pos.TOP_LEFT);

        Label titleLabel = new Label(title);
        getChildren().add(titleLabel);
    }

    @Override
    public void update(ObservableMaze maze) {
        if (type == LegendType.ENTITIES) {
            updateEntities(maze.getEntityManager().getEntities());
        } else if (type == LegendType.TRAPS) {
            updateTraps(maze.getTrapManager());
        }
    }

    public void updateEntities(List<Entity> entities) {
        getChildren().removeIf(LegendItemView.class::isInstance);

        Map<String, EntityCount> entityCounts = new HashMap<>();

        for (Entity entity : entities) {
            GameViewConfig config = GameViewConfig.forEntity(entity);
            String key = config.name();
            String displayName = getEntityDisplayName(entity, config);

            entityCounts.putIfAbsent(key, new EntityCount(displayName, config.getShape(), config.getColor()));
            entityCounts.get(key).increment();
        }

        entityCounts.values().forEach(count ->
            getChildren().add(new LegendItemView(count.name, count.shape, count.color, count.count))
        );
    }

    public void updateTraps(TrapManager trapManager) {
        getChildren().removeIf(LegendItemView.class::isInstance);

        Map<String, EntityCount> trapCounts = new HashMap<>();
        int height = trapManager.height();
        int width = trapManager.width();

        for (int y = 0; y<height;y++) {
            for (int x = 0; x<width;x++) {
                Trap trap = trapManager.getTrap(y,x);
                if (trap != null) {
                    GameViewConfig config = GameViewConfig.forTrap(trap);

                    if (isMazeElement(config)) {
                        continue;
                    }

                    String key = config.name();
                    String displayName = getTrapDisplayName(trap.name());

                    trapCounts.putIfAbsent(key, new EntityCount(displayName, config.getShape(), config.getColor()));
                    trapCounts.get(key).increment();
                }
            }
        }

        trapCounts.values().forEach(count ->
            getChildren().add(new LegendItemView(count.name, count.shape, count.color, count.count))
        );
    }

    private boolean isMazeElement(GameViewConfig config) {
        return config == GameViewConfig.PATH ||
               config == GameViewConfig.WALL ||
               config == GameViewConfig.OUT_OF_BOUNDS ||
               config == GameViewConfig.COMPLETED;
    }

    private String getEntityDisplayName(Entity entity, GameViewConfig config) {
        if (entity instanceof PlayerEntity playerEntity) {
            int id = playerEntity.getId();
            return "Player " + (id + 1);
        }
        return switch (config) {
            case EXIT -> "Exit";
            case MONSTER -> "Monster";
            default -> config.name();
        };
    }

    private String getTrapDisplayName(String trapName) {
        return switch (trapName) {
            case "TRAP_LAVA" -> "Lava";
            case "TRAP_TELEPORT" -> "Teleport";
            case "TRAP_FAKE_EXIT" -> "Fake Exit";
            case "TRAP_USED" -> "Used";
            case "TRAP_PUSH" -> "Push";
            case "TRAP_GENERATE" -> "Generate";
            case "TRAP_STUN" -> "Stun";
            case "TRAP_HIDE_WALL" -> "Hide Wall";
            case "TRAP_TELEPORT_EXIT" -> "Teleport Exit";
            default -> trapName.replace("TRAP_", "");
        };
    }
}
