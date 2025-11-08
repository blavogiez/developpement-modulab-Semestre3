package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.trap.Trap;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import fr.univlille.labyrinth.view.layout.LabyrinthLayoutCalculator;
import fr.univlille.labyrinth.view.renderer.EntityRenderer;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class LabyrinthCanvasView implements Observer<ObservableMaze> {

    private Pane container;
    protected Canvas canvas;
    protected ObservableMaze currentMaze;
    /*
     * Attributs calculant la façon "responsive"
     */
    protected LabyrinthLayout layout;
    protected LabyrinthLayoutCalculator layoutCalculator;
    protected EntityRenderer entityRenderer;

    protected double playerX, playerY;

    private AnimationTimer playerAnimation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            int targetX = currentMaze.getPlayerPosition().getX();
            int targetY = currentMaze.getPlayerPosition().getY();

            double alpha = 0.3; // plus petit = mouvement plus lent et visible
            playerX += (targetX - playerX) * alpha;
            playerY += (targetY - playerY) * alpha;

            if (Math.abs(playerX - targetX) < 0.01) playerX = targetX;
            if (Math.abs(playerY - targetY) < 0.01) playerY = targetY;

            drawMazeOnly();
            dessinerJoueur(canvas.getGraphicsContext2D(), currentMaze);
        }
    };


    public LabyrinthCanvasView(ObservableMaze maze) {
        this.currentMaze = maze;
        this.layoutCalculator = new LabyrinthLayoutCalculator();
        this.entityRenderer = new EntityRenderer();

        container = new Pane();
        canvas = new Canvas(700, 700);
        container.getChildren().add(canvas);

        container.setMinSize(0, 0);
        container.setMaxSize(canvas.getWidth(), canvas.getHeight());
        container.setPrefSize(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        container.widthProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        container.heightProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));

        playerX = maze.getPlayerPosition().getX();
        playerY = maze.getPlayerPosition().getY();

        update(maze);
        playerAnimation.start();
    }

    private void drawMazeOnly() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(),hauteur * layout.getCellSize());

        dessinerMurs(gc, hauteur, largeur);
        dessinerEntree(gc, currentMaze);
        dessinerSortie(gc, currentMaze);
        dessinerTrap(gc, currentMaze);
        drawEntities(gc, currentMaze);
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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameViewConfig.PATH.getColor());

        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), width * layout.getCellSize(),
                height * layout.getCellSize());

        //dessinerMurs(gc, hauteur, largeur);
        drawMazeOnly();
    }

    protected void dessinerMurs(GraphicsContext gc, int height, int width) {
        gc.setStroke(GameViewConfig.WALL.getColor());
        gc.setLineWidth(layout.getWallThickness());

        verticalsWalls(gc, height, width);

        horizontalsWalls(gc, height, width);
    }

    private void horizontalsWalls(GraphicsContext gc, int height, int width) {
        for (int x = 0; x < width; x++) {
            if (currentMaze.isWall(-1, x, 0, x)) {
                double x1 = layout.getOffsetX() + x * layout.getCellSize();
                double x2 = x1 + layout.getCellSize();
                double y1 = layout.getOffsetY();
                gc.strokeLine(x1, y1, x2, y1);
            }
            for (int y = 0; y < height; y++) {
                if (currentMaze.isWall(y, x, y + 1, x)) {
                    double x1 = layout.getOffsetX() + x * layout.getCellSize();
                    double x2 = x1 + layout.getCellSize();
                    double y1 = layout.getOffsetY() + (y + 1) * layout.getCellSize();
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }

    private void verticalsWalls(GraphicsContext gc, int height, int width) {
        for (int y = 0; y < height; y++) {
                double x1 = layout.getOffsetX();
                double y1 = layout.getOffsetY() + y * layout.getCellSize();
                double y2 = y1 + layout.getCellSize();
                gc.strokeLine(x1, y1, x1, y2);

            for (int x = 0; x < width; x++) {
                if (currentMaze.isWall(y, x, y, x + 1)) {
                    x1 = layout.getOffsetX() + (x + 1) * layout.getCellSize();
                    y1 = layout.getOffsetY() + y * layout.getCellSize();
                    y2 = y1 + layout.getCellSize();
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }
    }

    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, playerY, playerX, GameViewConfig.PLAYER.getColor());
    }

    protected void dessinerTrap(GraphicsContext gc, ObservableMaze maze) {
        Trap[][] traps = maze.getTrapManager().getTraps();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                GameViewConfig config = GameViewConfig.valueOf("TRAP_" + traps[y][x].name());
                dessinerMarqueur(gc, y, x, config.getColor());
            }
        }
    }

    protected void dessinerSortie(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, maze.getExitPosition().getY(), maze.getExitPosition().getX(), GameViewConfig.EXIT.getColor());
    }

    protected void dessinerEntree(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, maze.getEntryPosition().getY(), maze.getEntryPosition().getX(), Color.GREEN);
    }

    protected void dessinerMarqueur(GraphicsContext gc, double y, double x, Color couleur) {
        double tailleMarqueur = layout.getCellSize() * 0.5;
        double marginMarqueur = (layout.getCellSize() - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
                layout.getOffsetX() + x * layout.getCellSize() + marginMarqueur,
                layout.getOffsetY() + y * layout.getCellSize() + marginMarqueur,
                tailleMarqueur,
                tailleMarqueur);
    }

    protected void drawEntities(GraphicsContext gc, ObservableMaze maze) {
        for (Entity entity : maze.getEntityManager().getEntities()) {
            if(!entity.getEntityType().equals(EntityType.PLAYER)){
                entityRenderer.renderEntity(gc, entity, layout);
            }
            
        }
    }

    protected boolean estDansRayon(int x, int y, ObservableMaze maze, int rayon) {
        int playerX = maze.getPlayerPosition().getX();
        int playerY = maze.getPlayerPosition().getY();
        int dx = Math.abs(x - playerX);
        int dy = Math.abs(y - playerY);
        return Math.max(dx, dy) <= rayon;
    }

    protected abstract void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur);

    protected abstract boolean shouldRenderCell(int y, int x, ObservableMaze maze);

    public Pane getView() {
        return container;
    }
}
