package fr.univlille.labyrinth.view.labyrinth.renderer;

import java.util.Map;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.filter.RenderingFilter;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EntityRenderer {

    private final ComponentRenderer componentRenderer;
    private final RenderingFilter renderingFilter;
    private final ViewConfigResolver configResolver;

    public EntityRenderer(ComponentRenderer componentRenderer, RenderingFilter renderingFilter, ViewConfigResolver configResolver) {
        this.componentRenderer = componentRenderer;
        this.renderingFilter = renderingFilter;
        this.configResolver = configResolver;
    }

    public void drawEntities(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout) {
        for (Entity entity : maze.getEntityManager().getEntities()) {
            int x = entity.getPosition().getX();
            int y = entity.getPosition().getY();

            if (renderingFilter.shouldRenderEntity(entity, x, y)) {
                GameViewConfig config = configResolver.getConfigForEntity(entity);
                componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(), x, y, layout, 0.6);
            }
        }
    }

    public void dessinerJoueur(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout, Map<Integer, Double> playerXMap, Map<Integer, Double> playerYMap) {
        for (PlayerEntity player : maze.getEntityManager().getEntitiesByType(PlayerEntity.class)) {
            int id = player.getID();
            Double x = playerXMap.get(id);
            Double y = playerYMap.get(id);

            if (x != null && y != null) {
                Color color = configResolver.getConfigForPlayer(id).getColor();
                dessinerMarqueur(gc, y, x, color, layout);
            }
        }
    }

    private void dessinerMarqueur(GraphicsContext gc, double y, double x, Color couleur, LabyrinthLayout layout) {
        double tailleMarqueur = layout.getCellSize() * 0.6;
        double marginMarqueur = (layout.getCellSize() - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
                layout.getOffsetX() + x * layout.getCellSize() + marginMarqueur,
                layout.getOffsetY() + y * layout.getCellSize() + marginMarqueur,
                tailleMarqueur,
                tailleMarqueur);
    }
}
