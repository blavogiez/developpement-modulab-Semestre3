package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.filter.ExplorationFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.ExplorationWallFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.RenderingFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.WallFilter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplorationLabyrinthCanvasView extends LabyrinthCanvasView {

    private ExplorationFilter explorationFilter;

    public ExplorationLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    protected RenderingFilter createRenderingFilter(boolean animationEnabled) {
        explorationFilter = new ExplorationFilter(currentMaze, animationEnabled);
        return explorationFilter;
    }

    @Override
    protected WallFilter createWallFilter() {
        return new ExplorationWallFilter(explorationFilter);
    }

    @Override
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (explorationFilter == null ||
            explorationFilter.getCellulesExplorees().length != maze.getHeight() ||
            explorationFilter.getCellulesExplorees()[0].length != maze.getWidth()) {
            this.renderingFilter = createRenderingFilter(playerAnimation.isEnabled());
            this.wallFilter = createWallFilter();
            this.wallRenderer = new fr.univlille.labyrinth.view.labyrinth.renderer.WallRenderer(wallFilter);
            this.entityRenderer = new fr.univlille.labyrinth.view.labyrinth.renderer.EntityRenderer(componentRenderer, renderingFilter, configResolver);
            this.trapRenderer = new fr.univlille.labyrinth.view.labyrinth.renderer.TrapRenderer(componentRenderer, renderingFilter, configResolver);
        }

        super.update(maze);
    }

    @Override
    public void draw() {
        explorationFilter.marquerCellulesExplorees();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(), hauteur * layout.getCellSize());

        dessinerZonesNonExplorees(gc, hauteur, largeur);

        wallRenderer.dessinerMurs(gc, currentMaze, layout, hauteur, largeur);
        trapRenderer.dessinerTrap(gc, currentMaze, layout);
        entityRenderer.drawEntities(gc, currentMaze, layout);

        if (renderingFilter.shouldDrawPlayer()) {
            entityRenderer.dessinerJoueur(gc, currentMaze, layout, playerXMap, playerYMap);
        }
    }

    private void dessinerZonesNonExplorees(GraphicsContext gc, int hauteur, int largeur) {
        gc.setFill(Color.BLACK);

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (!explorationFilter.isExplored(x, y)) {
                    double xCoord = layout.getOffsetX() + x * layout.getCellSize();
                    double yCoord = layout.getOffsetY() + y * layout.getCellSize();
                    gc.fillRect(xCoord, yCoord, layout.getCellSize(), layout.getCellSize());
                }
            }
        }
    }
}
