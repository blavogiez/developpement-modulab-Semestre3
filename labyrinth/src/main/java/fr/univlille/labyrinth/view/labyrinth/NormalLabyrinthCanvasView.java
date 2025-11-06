package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import javafx.scene.canvas.GraphicsContext;

public class NormalLabyrinthCanvasView extends LabyrinthCanvasView {
    public NormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        drawEntities(gc, maze);
    }

    @Override
    protected boolean shouldRenderCell(int y, int x, ObservableMaze maze) {
        return true;
    }
}
