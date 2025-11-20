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
import fr.univlille.labyrinth.view.labyrinth.renderer.WallRenderer;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/*
 * Classe parent des vues de labyrinthe
 * Permet une implémentation facile grâce à des filtres (similaires à des hooks) injectables
 * Architecture SOLID
 */
public class LabyrinthCanvasView implements Observer<ObservableMaze>, AnimatableView {

    private static final int CANVAS_DEFAULT_SIZE = 800;
    private static final int CONTAINER_MAX_SIZE = 850;

    private final int MINSIZEV=0;
    private final int MINSIZEV1=0;

    protected Pane container;
    protected Canvas canvas;
    protected ObservableMaze currentMaze;
    protected PlayerAnimation playerAnimation;
    protected HashMap<Integer, Double> playerXMap = new HashMap<>();
    protected HashMap<Integer, Double> playerYMap = new HashMap<>();

    protected LabyrinthLayout layout;
    protected LabyrinthLayoutCalculator layoutCalculator;
    protected ComponentRenderer componentRenderer;

    // Renderers utilitaires pour déléguer le dessin
    protected WallRenderer wallRenderer;
    protected EntityRenderer entityRenderer;
    protected TrapRenderer trapRenderer;

    // Filtres pour décider de dessiner certaines parties dans les implémentations
    protected RenderingFilter renderingFilter;
    protected WallFilter wallFilter;

    /*
     * Constructeur propre avec injections de dépendance (D de SOLID)
     */
    public LabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                               ComponentRenderer componentRenderer, boolean animationEnabled,
                               RenderingFilter renderingFilter, WallFilter wallFilter,
                               WallRenderer wallRenderer, EntityRenderer entityRenderer,
                               TrapRenderer trapRenderer, PlayerAnimation playerAnimation) {
        this.currentMaze = maze;
        this.layoutCalculator = layoutCalculator;
        this.componentRenderer = componentRenderer;

        this.renderingFilter = renderingFilter;
        this.wallFilter = wallFilter;

        this.wallRenderer = wallRenderer;
        this.entityRenderer = entityRenderer;
        this.trapRenderer = trapRenderer;

        this.playerAnimation = playerAnimation != null ? playerAnimation : new PlayerAnimation(this);
        if (animationEnabled) {
            this.playerAnimation.start();
        } else {
            this.playerAnimation.disable();
        }

        container = new Pane();
        canvas = new Canvas(CANVAS_DEFAULT_SIZE, CANVAS_DEFAULT_SIZE);
        container.getChildren().add(canvas);

        container.setMinSize(MINSIZEV, MINSIZEV1);
        container.setMaxSize(CONTAINER_MAX_SIZE, CONTAINER_MAX_SIZE);
        container.setPrefSize(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        container.widthProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        container.heightProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));

        update(maze);
    }

    // Injection par défaut
    public LabyrinthCanvasView(ObservableMaze maze, LabyrinthLayoutCalculator layoutCalculator,
                               ComponentRenderer componentRenderer, boolean animationEnabled) {
        this(maze, layoutCalculator, componentRenderer, animationEnabled,
             new NormalFilter(animationEnabled), new NormalWallFilter(),
             new WallRenderer(new NormalWallFilter()),
             new EntityRenderer(componentRenderer, new NormalFilter(animationEnabled)),
             new TrapRenderer(componentRenderer, new NormalFilter(animationEnabled)),
             null);
    }

    public LabyrinthCanvasView(ObservableMaze maze) {
        this(maze, new LabyrinthLayoutCalculator(), new ComponentRenderer(), SettingsManager.get().isAnimationEnabled());
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(MINSIZEV, MINSIZEV1, canvas.getWidth(), canvas.getHeight());

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
        return playerXMap;
    }

    public Map<Integer, Double> getPlayerYMap() {
        return playerYMap;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
