package fr.univlille.labyrinth.view.labyrinth.renderer;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.filter.RenderingFilter;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.GraphicsContext;

public class TrapRenderer {

    private final ComponentRenderer componentRenderer;
    private final RenderingFilter renderingFilter;
    private final ViewConfigResolver configResolver;

    public TrapRenderer(ComponentRenderer componentRenderer, RenderingFilter renderingFilter, ViewConfigResolver configResolver) {
        this.componentRenderer = componentRenderer;
        this.renderingFilter = renderingFilter;
        this.configResolver = configResolver;
    }

    public void dessinerTrap(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout) {
        Trap[][] traps = maze.getTrapManager().getTraps();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                if (renderingFilter.shouldRenderTrap(traps[y][x], x, y)) {
                    GameViewConfig config = configResolver.getConfigForTrap(traps[y][x]);
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(), x, y, layout, 0.6);
                }
            }
        }
    }
}
