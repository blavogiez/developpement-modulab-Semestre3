package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplorationLabyrinthCanvasView extends LabyrinthCanvasView {
    private static final int EXPLORATION_RADIUS = 5;

    private boolean[][] cellulesExplorees;

    public ExplorationLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        update(maze);
    }

    @Override
    public void update(ObservableMaze maze) {
        if (cellulesExplorees == null ||
            cellulesExplorees.length != maze.getHeight() ||
            cellulesExplorees[0].length != maze.getWidth()) {
            cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        }
        super.update(maze);
    }

    @Override
    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(), hauteur * layout.getCellSize());

        marquerCellulesExplorees(currentMaze);
        dessinerZonesNonExplorees(gc, currentMaze, hauteur, largeur);
        
        dessinerMurs(gc, hauteur, largeur);
        dessinerTrap(gc, currentMaze);
        drawEntities(gc, currentMaze);

        if (shouldDrawPlayer()) {
            dessinerJoueur(gc, currentMaze);
        }
    }

    @Override
    protected boolean shouldDrawVerticalWall(int y, int x, int height, int width) {
        if (x == -1) {
            return cellulesExplorees[y][0];
        }
        return cellulesExplorees[y][x] && (x + 1 >= width || cellulesExplorees[y][x + 1]);
    }

    @Override
    protected boolean shouldDrawHorizontalWall(int y, int x, int height, int width) {
        if (y == -1) {
            return cellulesExplorees[0][x];
        }
        return cellulesExplorees[y][x] && (y + 1 >= height || cellulesExplorees[y + 1][x]);
    }

    private void marquerCellulesExplorees(ObservableMaze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (estDansRayon(j, i, maze, EXPLORATION_RADIUS)) {
                    cellulesExplorees[i][j] = true;
                }
            }
        }
    }

    private void dessinerZonesNonExplorees(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        gc.setFill(Color.BLACK);

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (!cellulesExplorees[y][x]) {
                    double xCoord = layout.getOffsetX() + x * layout.getCellSize();
                    double yCoord = layout.getOffsetY() + y * layout.getCellSize();
                    gc.fillRect(xCoord, yCoord, layout.getCellSize(), layout.getCellSize());
                }
            }
        }
    }

    private boolean isExplored(int x, int y) {
        return cellulesExplorees[y][x];
    }

    /*
     * Ne dessine que les entités dans le rayon 
     */
    @Override
    protected boolean shouldRenderEntity(Entity entity) {
        return isExplored(entity.getPosition().getX(),entity.getPosition().getY()) && entity.getEntityType()!=EntityType.PLAYER;
    }

    /*
     * Ne dessine que les pièges dans le rayon 
     */
    @Override
    protected boolean shouldRenderTrap(Trap trap, int x, int y) {
        return isExplored(x,y);
    }

    @Override
    protected boolean isWall(ObservableMaze currentMaze2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isWall'");
    }
}
