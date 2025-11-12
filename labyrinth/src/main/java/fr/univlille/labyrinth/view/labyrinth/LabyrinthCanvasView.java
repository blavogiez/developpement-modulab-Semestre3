package fr.univlille.labyrinth.view.labyrinth;

import java.util.HashMap;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.Observable;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LabyrinthCanvasView implements Observer<ObservableMaze> {

    protected Pane container;
    protected Canvas canvas;
    protected ObservableMaze currentMaze;
    protected PlayerAnimation playerAnimation;
    protected HashMap<Integer, Double> playerXMap = new HashMap<>();
    protected HashMap<Integer, Double> playerYMap = new HashMap<>();

    protected LabyrinthLayout layout;
    protected LabyrinthLayoutCalculator layoutCalculator;
    protected ComponentRenderer componentRenderer;

    public LabyrinthCanvasView(ObservableMaze maze) {
        this.currentMaze = maze;
        this.layoutCalculator = new LabyrinthLayoutCalculator();
        this.componentRenderer = new ComponentRenderer();
        this.playerAnimation = new PlayerAnimation(this);
        playerAnimation.start();

        container = new Pane();
        canvas = new Canvas(800, 800);
        container.getChildren().add(canvas);

        container.setMinSize(0, 0);
        container.setMaxSize(canvas.getWidth(), canvas.getHeight());
        container.setPrefSize(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        container.widthProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        container.heightProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        
        update(maze);
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(), hauteur * layout.getCellSize());

        dessinerMurs(gc, hauteur, largeur);
        dessinerTrap(gc, currentMaze);
        drawEntities(gc, currentMaze);

        if (shouldDrawPlayer()) {
            dessinerJoueur(gc, currentMaze);
        }
    }

    /** 
     * @param maze
     */
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

    /** 
     * @param gc
     * @param height
     * @param width
     */
    protected void dessinerMurs(GraphicsContext gc, int height, int width) {
        gc.setStroke(GameViewConfig.WALL.getColor());
        gc.setLineWidth(layout.getWallThickness());

        verticalsWalls(gc, height, width);

        horizontalsWalls(gc, height, width);
    }

    /** 
     * @param gc
     * @param height
     * @param width
     */
    protected void horizontalsWalls(GraphicsContext gc, int height, int width) {
        for (int x = 0; x < width; x++) {
            if (shouldDrawHorizontalWall(-1, x, height, width)) {
                double x1 = layout.getOffsetX() + x * layout.getCellSize();
                double x2 = x1 + layout.getCellSize();
                double y1 = layout.getOffsetY();
                gc.strokeLine(x1, y1, x2, y1);
            }
            for (int y = 0; y < height; y++) {
                if (MazeWallChecker.isWall(currentMaze, y, x, y + 1, x) && shouldDrawHorizontalWall(y, x, height, width)) {
                    double x1 = layout.getOffsetX() + x * layout.getCellSize();
                    double x2 = x1 + layout.getCellSize();
                    double y1 = layout.getOffsetY() + (y + 1) * layout.getCellSize();
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }


    /** 
     * @param gc
     * @param height
     * @param width
     */
    protected void verticalsWalls(GraphicsContext gc, int height, int width) {
        for (int y = 0; y < height; y++) {
            if (shouldDrawVerticalWall(y, -1, height, width)) {
                double x1 = layout.getOffsetX();
                double y1 = layout.getOffsetY() + y * layout.getCellSize();
                double y2 = y1 + layout.getCellSize();
                gc.strokeLine(x1, y1, x1, y2);
            }

            for (int x = 0; x < width; x++) {
                if (MazeWallChecker.isWall(currentMaze, y, x, y, x + 1) && shouldDrawVerticalWall(y, x, height, width)) {
                    double x1 = layout.getOffsetX() + (x + 1) * layout.getCellSize();
                    double y1 = layout.getOffsetY() + y * layout.getCellSize();
                    double y2 = y1 + layout.getCellSize();
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }
    }

    /** 
     * @param gc
     * @param maze
     */
    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        for (PlayerEntity player : maze.getEntityManager().getPlayerEntities()) {
            int id = player.getID();
            Double x = playerXMap.get(id);
            Double y = playerYMap.get(id);

            if (x != null && y != null) {
                Color color = GameViewConfig.valueOf("PLAYER" + id).getColor();
                dessinerMarqueur(gc, y, x, color);
            }
        }
    }

    /** 
     * @param gc
     * @param maze
     */
    protected void dessinerTrap(GraphicsContext gc, ObservableMaze maze) {
        Trap[][] traps = maze.getTrapManager().getTraps();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                if (shouldRenderTrap(traps[y][x], x, y)) {
                    //System.out.println(traps[y][x].name());
                    GameViewConfig config = GameViewConfig.valueOf(traps[y][x].name());
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(), x, y, layout, 0.6);
                }
            }
        }
    }

    /** 
     * @param gc
     * @param y
     * @param x
     * @param couleur
     */
    protected void dessinerMarqueur(GraphicsContext gc, double y, double x, Color couleur) {
        double tailleMarqueur = layout.getCellSize() * 0.6;
        double marginMarqueur = (layout.getCellSize() - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
                layout.getOffsetX() + x * layout.getCellSize() + marginMarqueur,
                layout.getOffsetY() + y * layout.getCellSize() + marginMarqueur,
                tailleMarqueur,
                tailleMarqueur);
    }

    /** 
     * @param gc
     * @param maze
     */
    protected void drawEntities(GraphicsContext gc, ObservableMaze maze) {
        for (Entity entity : maze.getEntityManager().getEntities()) {
            if (shouldRenderEntity(entity)) {
                GameViewConfig config = GameViewConfig.valueOf(entity.getEntityType().name());
                componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(),
                    entity.getPosition().getX(), entity.getPosition().getY(), layout, 0.6);
            }
        }
    }

    /** 
     * @param x
     * @param y
     * @param maze
     * @param rayon
     * @return boolean
     */
    protected boolean estDansRayon(int x, int y, ObservableMaze maze, int rayon) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(0);
        if (player == null) return false;
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        int dx = Math.abs(x - playerX);
        int dy = Math.abs(y - playerY);
        return Math.max(dx, dy) <= rayon;
    }

    /** 
     * @return boolean
     */
    /*
     * "hook" utile pour le cas de la vue locale (à gauche, on ne dessine pas le joueur)
     */
    protected boolean shouldDrawPlayer() {
        return true;
    }

    /** 
     * @param trapFactory
     * @param x
     * @param y
     * @return boolean
     */
    protected boolean shouldRenderTrap(Trap trapFactory, int x, int y) {
        return true;
    }

    /** 
     * @param entity
     * @return boolean
     */
    protected boolean shouldRenderEntity(Entity entity) {
        return entity.getEntityType() != EntityType.PLAYER;
    }

    /** 
     * @param y
     * @param x
     * @param height
     * @param width
     * @return boolean
     */
    protected boolean shouldDrawVerticalWall(int y, int x, int height, int width) {
        return true;
    }

    /** 
     * @param y
     * @param x
     * @param height
     * @param width
     * @return boolean
     */
    protected boolean shouldDrawHorizontalWall(int y, int x, int height, int width) {
        return true;
    }

    /** 
     * @return Pane
     */
    public Pane getView() {
        return container;
    }

    /** 
     * @return ObservableMaze
     */
    public ObservableMaze getCurrentMaze() {
        return currentMaze;
    }

    /** 
     * @return HashMap<Integer, Double>
     */
    public HashMap<Integer, Double> getPlayerXMap() {
        return playerXMap;
    }

    /** 
     * @return HashMap<Integer, Double>
     */
    public HashMap<Integer, Double> getPlayerYMap() {
        return playerYMap;
    }

    /** 
     * @return Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

}
