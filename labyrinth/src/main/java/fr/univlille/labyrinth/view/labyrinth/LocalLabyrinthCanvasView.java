package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import fr.univlille.labyrinth.model.maze.entities.Entity;
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

    @Override
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        layout = layoutCalculator.calculate(canvas.getWidth(), canvas.getHeight(), VIEW_SIZE, VIEW_SIZE);

        draw();
    }

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

    private boolean shouldDrawVerticalWall(int globalY, int globalX) {
        if (globalX <= 0) return true;
        if (!currentMaze.positionCorrecte(globalY, globalX)) return false;
        if (!currentMaze.positionCorrecte(globalY, globalX - 1)) return true;
        return currentMaze.isWall(globalY, globalX - 1, globalY, globalX);
    }

    private boolean shouldDrawHorizontalWall(int globalY, int globalX) {
        if (globalY <= 0) return true;
        if (!currentMaze.positionCorrecte(globalY, globalX)) return false;
        if (!currentMaze.positionCorrecte(globalY - 1, globalX)) return true;
        return currentMaze.isWall(globalY - 1, globalX, globalY, globalX);
    }

    private void drawVerticalWallAt(GraphicsContext gc, int localX, int localY) {
        double x = layout.getOffsetX() + localX * layout.getCellSize();
        double y1 = layout.getOffsetY() + localY * layout.getCellSize();
        double y2 = y1 + layout.getCellSize();
        gc.strokeLine(x, y1, x, y2);
    }

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
                        local.getX(), local.getY(), layout, 0.5);
                }
            }
        }
    }

    @Override
    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, VIEW_RADIUS, VIEW_RADIUS, GameViewConfig.PLAYER.getColor());
    }

    private boolean isOutOfBounds(int globalX, int globalY) {
        return !currentMaze.positionCorrecte(globalY, globalX);
    }

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
