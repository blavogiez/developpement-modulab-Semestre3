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

    public TrapRenderer(ComponentRenderer componentRenderer, RenderingFilter renderingFilter) {
        this.componentRenderer = componentRenderer;
        this.renderingFilter = renderingFilter;
    }

    /** 
     * @param gc
     * @param maze
     * @param layout
     */
    public void dessinerTrap(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout) {
        Trap[][] traps = maze.getTrapManager().getTraps();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                if (renderingFilter.shouldRenderTrap(traps[y][x], x, y)) {
                    GameViewConfig config = GameViewConfig.forTrap(traps[y][x]);
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(), x, y, layout, 0.6);
                }
            }
        }
    }
}
