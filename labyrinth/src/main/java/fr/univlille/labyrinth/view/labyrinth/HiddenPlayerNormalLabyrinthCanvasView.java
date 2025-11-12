package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        this.playerAnimation.disable();
    }

    /** 
     * @return boolean
     */
    @Override
    protected boolean shouldDrawPlayer() {
        return false;
    }

    /** 
     * @param trap
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    protected boolean shouldRenderTrap(Trap trap, int x, int y) {
        return trap.name().equals("FAKE_EXIT_TRAP");
    }

    /** 
     * @param entity
     * @return boolean
     */
    @Override
    protected boolean shouldRenderEntity(Entity entity) {
        return entity.getEntityType() == EntityType.EXIT;
    }

}
