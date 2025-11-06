package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import javafx.scene.canvas.GraphicsContext;

/*
 * Vue typique d'un labyrinthe, héritant donc la plupart de ses méthodes.
 */
public class NormalLabyrinthCanvasView extends LabyrinthCanvasView {
    public NormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        dessinerEntree(gc, maze);
        dessinerSortie(gc, maze);
        dessinerJoueur(gc, maze);
    }

    @Override
    protected boolean shouldRenderCell(int y, int x, ObservableMaze maze) {
        return true;
    }
}
