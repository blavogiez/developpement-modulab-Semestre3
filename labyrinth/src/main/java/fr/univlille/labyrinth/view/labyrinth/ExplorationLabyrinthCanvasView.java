package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.app.SettingsManager;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.filter.ExplorationFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.ExplorationWallFilter;
import fr.univlille.labyrinth.view.labyrinth.renderer.EntityRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.TrapRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.WallRenderer;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplorationLabyrinthCanvasView extends LabyrinthCanvasView {

    private ExplorationFilter explorationFilter;

    public ExplorationLabyrinthCanvasView(ObservableMaze maze) {
        this(maze, new LabyrinthLayoutCalculator(), new ComponentRenderer(), SettingsManager.get().isAnimationEnabled());
    }

    public ExplorationLabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                                          ComponentRenderer componentRenderer, boolean animationEnabled) {
        this(maze, layoutCalculator, componentRenderer, animationEnabled, new ExplorationFilter(maze, animationEnabled));
    }

    private ExplorationLabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                                           ComponentRenderer componentRenderer, boolean animationEnabled,
                                           ExplorationFilter filter) {
        super(maze, layoutCalculator, componentRenderer, animationEnabled,
              filter, new ExplorationWallFilter(filter),
              new WallRenderer(new ExplorationWallFilter(filter)),
              new EntityRenderer(componentRenderer, filter),
              new TrapRenderer(componentRenderer, filter),
              null);
        this.explorationFilter = filter;
    }

    @Override
    public void update(ObservableMaze maze) {
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
