package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import javafx.scene.canvas.GraphicsContext;

/*
 * Vue à gauche dans l'étape 3 et 5 ; tout le labyrinthe, mais pas la position du joueur.
 */
public class HiddenPlayerNormalLabyrinthCanvasView extends NormalLabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int lignes, int colonnes) {
        dessinerSortie(gc, maze);
    }
}
