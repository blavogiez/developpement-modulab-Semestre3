package fr.univlille.labyrinth.view.labyrinth.filter;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

/*
 * Filtre pour la vue d'exploration : les positions explorées sont stockées dans un tableau
 */
public class ExplorationFilter implements RenderingFilter {

    private static final int EXPLORATION_RADIUS = 5;
    private final boolean[][] cellulesExplorees;
    private final ObservableMaze maze;
    private final boolean animationEnabled;

    public ExplorationFilter(ObservableMaze maze, boolean animationEnabled) {
        this.maze = maze;
        this.animationEnabled = animationEnabled;
        this.cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
    }

    public void marquerCellulesExplorees() {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (estDansRayon(j, i, EXPLORATION_RADIUS)) {
                    cellulesExplorees[i][j] = true;
                }
            }
        }
    }

    /** 
     * @param x
     * @param y
     * @param rayon
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    private boolean estDansRayon(int x, int y, int rayon) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(0);
        if (player == null) return false;
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        int dx = Math.abs(x - playerX);
        int dy = Math.abs(y - playerY);
        return Math.max(dx, dy) <= rayon;
    }

    /** 
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    public boolean isExplored(int x, int y) {
        if (y < 0 || y >= cellulesExplorees.length) return false;
        if (x < 0 || x >= cellulesExplorees[0].length) return false;
        return cellulesExplorees[y][x];
    }

    /** 
     * @param entity
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderEntity(Entity entity, int x, int y) {
        return false ;
    }

    /** 
     * @param trap
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderTrap(Trap trap, int x, int y) {
        return isExplored(x, y);
    }

    /** 
     * @return false
     */
    @Override
    public boolean shouldDrawPlayer() {
        return false ;
    }

    /** 
     * @return {@code boolean}
     */
    public boolean[][] getCellulesExplorees() {
        return cellulesExplorees;
    }
}
