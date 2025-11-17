package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.labyrinth.filter.HiddenPlayerFilter;
import fr.univlille.labyrinth.view.labyrinth.filter.RenderingFilter;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        this.playerAnimation.disable();
    }

    @Override
    protected RenderingFilter createRenderingFilter(boolean animationEnabled) {
        return new HiddenPlayerFilter();
    }
}
