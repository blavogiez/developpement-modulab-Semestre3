package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LocalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int VIEW_RADIUS = 5;
    private static final int VIEW_SIZE = VIEW_RADIUS * 2 + 1;

    public LocalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
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

        layout = layoutCalculator.calculate(canvas.getWidth(), canvas.getHeight(), VIEW_SIZE, VIEW_SIZE);

        draw();
    }

    /** 
     * @param gc
     * @param height
     * @param width
     */
    @Override
    protected void dessinerMurs(GraphicsContext gc, int height, int width) {
        gc.setStroke(GameViewConfig.WALL.getColor());
        gc.setLineWidth(layout.getWallThickness());

        PlayerEntity playerEntity = currentMaze.getEntityManager().getPlayerEntityByID(0);
        if (playerEntity == null) return;
        Position player = playerEntity.getPosition();

        for (int localY = 0; localY < VIEW_SIZE; localY++) {
            for (int localX = 0; localX <= VIEW_SIZE; localX++) {
                int globalY = player.getY() - VIEW_RADIUS + localY;
                int globalX = player.getX() - VIEW_RADIUS + localX;

                if (shouldDrawVerticalWall(globalY, globalX)) {
                    drawVerticalWallAt(gc, localX, localY);
                }
            }
        }

        for (int localY = 0; localY <= VIEW_SIZE; localY++) {
            for (int localX = 0; localX < VIEW_SIZE; localX++) {
                int globalY = player.getY() - VIEW_RADIUS + localY;
                int globalX = player.getX() - VIEW_RADIUS + localX;

                if (shouldDrawHorizontalWall(globalY, globalX)) {
                    drawHorizontalWallAt(gc, localX, localY);
                }
            }
        }
    }

    /** 
     * @param globalY
     * @param globalX
     * @return boolean
     */
    private boolean shouldDrawVerticalWall(int globalY, int globalX) {
        Position p1 = new Position(globalY, globalX);
        Position p2 = new Position(globalY, globalX - 1);
        boolean p1Valid = MazeWallChecker.positionCorrecte(p1, currentMaze);
        boolean p2Valid = MazeWallChecker.positionCorrecte(p2, currentMaze);

        if (!p1Valid && !p2Valid) {
            return false;
        }
        if (!p1Valid || !p2Valid) {
            return true;
        }
        return MazeWallChecker.isWall(currentMaze, globalY, globalX - 1, globalY, globalX);
    }

    /** 
     * @param globalY
     * @param globalX
     * @return boolean
     */
    private boolean shouldDrawHorizontalWall(int globalY, int globalX) {
        Position p1 = new Position(globalY, globalX);
        Position p2 = new Position(globalY - 1, globalX);
        boolean p1Valid = MazeWallChecker.positionCorrecte(p1, currentMaze);
        boolean p2Valid = MazeWallChecker.positionCorrecte(p2, currentMaze);

        if (!p1Valid && !p2Valid) {
            return false;
        }
        if (!p1Valid || !p2Valid) {
            return true;
        }
        return MazeWallChecker.isWall(currentMaze, globalY - 1, globalX, globalY, globalX);
    }

    /** 
     * @param gc
     * @param localX
     * @param localY
     */
    private void drawVerticalWallAt(GraphicsContext gc, int localX, int localY) {
        double x = layout.getOffsetX() + localX * layout.getCellSize();
        double y1 = layout.getOffsetY() + localY * layout.getCellSize();
        double y2 = y1 + layout.getCellSize();
        gc.strokeLine(x, y1, x, y2);
    }

    /** 
     * @param gc
     * @param localX
     * @param localY
     */
    private void drawHorizontalWallAt(GraphicsContext gc, int localX, int localY) {
        double x1 = layout.getOffsetX() + localX * layout.getCellSize();
        double x2 = x1 + layout.getCellSize();
        double y = layout.getOffsetY() + localY * layout.getCellSize();
        gc.strokeLine(x1, y, x2, y);
    }

    @Override
    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PlayerEntity playerEntity = currentMaze.getEntityManager().getPlayerEntityByID(0);
        if (playerEntity == null) return;
        Position player = playerEntity.getPosition();

        for (int localY = 0; localY < VIEW_SIZE; localY++) {
            for (int localX = 0; localX < VIEW_SIZE; localX++) {
                int globalX = player.getX() - VIEW_RADIUS + localX;
                int globalY = player.getY() - VIEW_RADIUS + localY;

                Color cellColor = isOutOfBounds(globalX, globalY) ? Color.BLACK : GameViewConfig.PATH.getColor();

                gc.setFill(cellColor);
                gc.fillRect(
                    layout.getOffsetX() + localX * layout.getCellSize(),
                    layout.getOffsetY() + localY * layout.getCellSize(),
                    layout.getCellSize(),
                    layout.getCellSize()
                );
            }
        }

        dessinerMurs(gc, VIEW_SIZE, VIEW_SIZE);
        dessinerTrap(gc, currentMaze);
        drawEntities(gc, currentMaze);

        if (shouldDrawPlayer()) {
            dessinerJoueur(gc, currentMaze);
        }
    }

    /** 
     * @param entity
     * @return boolean
     */
    @Override
    protected boolean shouldRenderEntity(Entity entity) {
        return entity.getEntityType() != EntityType.PLAYER;
    }

    /** 
     * @param gc
     * @param maze
     */
    @Override
    protected void drawEntities(GraphicsContext gc, ObservableMaze maze) {
        PlayerEntity playerEntity = maze.getEntityManager().getPlayerEntityByID(0);
        if (playerEntity == null) return;
        Position playerPos = playerEntity.getPosition();
        for (Entity entity : maze.getEntityManager().getEntities()) {
            if (shouldRenderEntity(entity)) {
                int localX = entity.getPosition().getX() - playerPos.getX() + VIEW_RADIUS;
                int localY = entity.getPosition().getY() - playerPos.getY() + VIEW_RADIUS;
                if (localX >= 0 && localX < VIEW_SIZE && localY >= 0 && localY < VIEW_SIZE) {
                    GameViewConfig config = GameViewConfig.valueOf(entity.getEntityType().name());
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(),
                        localX, localY, layout, 0.6);
                }
            }
        }
    }

    /** 
     * @param gc
     * @param maze
     */
    @Override
    protected void dessinerTrap(GraphicsContext gc, ObservableMaze maze) {
        System.out.println("Début du dessin des traps");
        Trap[][] trapFactories = maze.getTrapManager().getTraps();
        for (int globalY = 0; globalY < trapFactories.length; globalY++) {
            for (int globalX = 0; globalX < trapFactories[globalY].length; globalX++) {
                Position local = toLocalCoordinates(globalX, globalY);
                if (local != null) {
                    System.out.println(trapFactories[globalY][globalX].name());
                    GameViewConfig config = GameViewConfig.valueOf(trapFactories[globalY][globalX].name());
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(),
                        local.getX(), local.getY(), layout, 0.6);
                }
            }
        }
    }

    /** 
     * @param gc
     * @param maze
     */
    @Override
    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, VIEW_RADIUS, VIEW_RADIUS, GameViewConfig.PLAYER0.getColor());
    }

    /** 
     * @param globalX
     * @param globalY
     * @return boolean
     */
    private boolean isOutOfBounds(int globalX, int globalY) {
        Position p=new Position(globalX, globalY);
        return !MazeWallChecker.positionCorrecte(p,currentMaze);
    }

    /** 
     * @param globalX
     * @param globalY
     * @return Position
     */
    private Position toLocalCoordinates(int globalX, int globalY) {
        PlayerEntity playerEntity = currentMaze.getEntityManager().getPlayerEntityByID(0);
        if (playerEntity == null) return null;
        Position player = playerEntity.getPosition();
        int localX = globalX - player.getX() + VIEW_RADIUS;
        int localY = globalY - player.getY() + VIEW_RADIUS;
        if (localX < 0 || localX >= VIEW_SIZE || localY < 0 || localY >= VIEW_SIZE) {
            return null;
        }
        return new Position(localX, localY);
    }

}
