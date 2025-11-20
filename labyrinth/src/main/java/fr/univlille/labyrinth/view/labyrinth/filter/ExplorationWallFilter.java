package fr.univlille.labyrinth.view.labyrinth.filter;

public class ExplorationWallFilter implements WallFilter {

    private final ExplorationFilter explorationFilter;

    public ExplorationWallFilter(ExplorationFilter explorationFilter) {
        this.explorationFilter = explorationFilter;
    }

    /** 
     * @param y
     * @param x
     * @param height
     * @param width
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldDrawVerticalWall(int y, int x, int height, int width) {
        if (x == -1) {
            return explorationFilter.isExplored(0, y);
        }
        return explorationFilter.isExplored(x, y) && (x + 1 >= width || explorationFilter.isExplored(x + 1, y));
    }

    /** 
     * @param y
     * @param x
     * @param height
     * @param width
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldDrawHorizontalWall(int y, int x, int height, int width) {
        if (y == -1) {
            return explorationFilter.isExplored(x, 0);
        }
        return explorationFilter.isExplored(x, y) && (y + 1 >= height || explorationFilter.isExplored(x, y + 1));
    }
}
