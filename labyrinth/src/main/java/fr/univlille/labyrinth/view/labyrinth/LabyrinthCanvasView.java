package fr.univlille.labyrinth.view.labyrinth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fr.univlille.labyrinth.app.SettingsManager;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.animation.AnimatableView;
import fr.univlille.labyrinth.view.labyrinth.animation.PlayerAnimation;
import fr.univlille.labyrinth.view.labyrinth.filter.NormalFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.NormalWallFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.RenderingFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.WallFilter;
import fr.univlille.labyrinth.view.labyrinth.renderer.EntityRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.TrapRenderer;
import fr.univlille.labyrinth.view.labyrinth.renderer.ViewConfigResolver;
import fr.univlille.labyrinth.view.labyrinth.renderer.WallRenderer;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class LabyrinthCanvasView implements Observer<ObservableMaze>, AnimatableView {

    private static final int CANVAS_DEFAULT_SIZE = 800;
    private static final int CONTAINER_MAX_SIZE = 850;

    protected Pane container;
    protected Canvas canvas;
    protected ObservableMaze currentMaze;
    protected PlayerAnimation playerAnimation;
    protected HashMap<Integer, Double> playerXMap = new HashMap<>();
    protected HashMap<Integer, Double> playerYMap = new HashMap<>();

    protected LabyrinthLayout layout;
    protected LabyrinthLayoutCalculator layoutCalculator;
    protected ComponentRenderer componentRenderer;

    protected WallRenderer wallRenderer;
    protected EntityRenderer entityRenderer;
    protected TrapRenderer trapRenderer;
    protected ViewConfigResolver configResolver;
    protected RenderingFilter renderingFilter;
    protected WallFilter wallFilter;

    public LabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator, ComponentRenderer componentRenderer, boolean animationEnabled) {
        this.currentMaze = maze;
        this.layoutCalculator = layoutCalculator;
        this.componentRenderer = componentRenderer;

        this.configResolver = new ViewConfigResolver();
        this.renderingFilter = createRenderingFilter(animationEnabled);
        this.wallFilter = createWallFilter();

        this.wallRenderer = new WallRenderer(wallFilter);
        this.entityRenderer = new EntityRenderer(componentRenderer, renderingFilter, configResolver);
        this.trapRenderer = new TrapRenderer(componentRenderer, renderingFilter, configResolver);

        this.playerAnimation = new PlayerAnimation(this);
        if (animationEnabled) {
            playerAnimation.start();
        } else {
            playerAnimation.disable();
        }

        container = new Pane();
        canvas = new Canvas(CANVAS_DEFAULT_SIZE, CANVAS_DEFAULT_SIZE);
        container.getChildren().add(canvas);

        container.setMinSize(0, 0);
        container.setMaxSize(CONTAINER_MAX_SIZE, CONTAINER_MAX_SIZE);
        container.setPrefSize(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        container.widthProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        container.heightProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));

        update(maze);
    }

    public LabyrinthCanvasView(ObservableMaze maze) {
        this(maze, new LabyrinthLayoutCalculator(), new ComponentRenderer(), SettingsManager.get().isAnimationEnabled());
    }

    protected RenderingFilter createRenderingFilter(boolean animationEnabled) {
        return new NormalFilter(animationEnabled);
    }

    protected WallFilter createWallFilter() {
        return new NormalWallFilter();
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(), hauteur * layout.getCellSize());

        wallRenderer.dessinerMurs(gc, currentMaze, layout, hauteur, largeur);
        trapRenderer.dessinerTrap(gc, currentMaze, layout);
        entityRenderer.drawEntities(gc, currentMaze, layout);

        if (renderingFilter.shouldDrawPlayer()) {
            entityRenderer.dessinerJoueur(gc, currentMaze, layout, playerXMap, playerYMap);
        }
    }

    @Override
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        int height = maze.getHeight();
        int width = maze.getWidth();

        layout = layoutCalculator.calculate(canvas.getWidth(), canvas.getHeight(), width, height);

        draw();
    }

    public Pane getView() {
        return container;
    }

    public ObservableMaze getCurrentMaze() {
        return currentMaze;
    }

    public Map<Integer, Double> getPlayerXMap() {
        return Collections.unmodifiableMap(playerXMap);
    }

    public Map<Integer, Double> getPlayerYMap() {
        return Collections.unmodifiableMap(playerYMap);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
