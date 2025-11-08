package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.trap.Trap;
import fr.univlille.labyrinth.view.GameViewConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        this.playerAnimation.disable();;
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        drawEntities(gc, maze);
        dessinerTrap(gc, maze);
    }

    @Override
    protected void drawMazeOnly() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int hauteur = currentMaze.getHeight();
        int largeur = currentMaze.getWidth();

        gc.setFill(GameViewConfig.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), largeur * layout.getCellSize(),hauteur * layout.getCellSize());

        dessinerMurs(gc, hauteur, largeur);
        dessinerElements(gc, currentMaze, hauteur, largeur);
    }

    /*
     * N'affiche que les pièges de fausses entrées
     */
    @Override
    protected void dessinerTrap(GraphicsContext gc, ObservableMaze maze) {
        Trap[][] traps = maze.getTrapManager().getTraps();
        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                if(traps[y][x].name().equals("FAKE")) {
                    GameViewConfig config = GameViewConfig.valueOf("TRAP_" + traps[y][x].name());
                    componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(), x, y, layout, 0.5);
                }
            }
        }
    }
    
    @Override
    protected void drawEntities(GraphicsContext gc, ObservableMaze maze) {
        for (Entity entity : maze.getEntityManager().getEntities()) {
            if (entity.getEntityType() == EntityType.EXIT) {
                GameViewConfig config = GameViewConfig.valueOf(entity.getEntityType().name());
                componentRenderer.renderComponentAt(gc, config.getShape(), config.getColor(),
                    entity.getPosition().getX(), entity.getPosition().getY(), layout, 0.6);
            }
        }
    }

    @Override
    protected boolean shouldRenderCell(int y, int x, ObservableMaze maze) {
        return true;
    }
}
