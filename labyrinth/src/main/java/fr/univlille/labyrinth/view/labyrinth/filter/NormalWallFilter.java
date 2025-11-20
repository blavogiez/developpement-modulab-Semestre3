package fr.univlille.labyrinth.view.labyrinth.filter;

public class NormalWallFilter implements WallFilter {

    /** 
     * @param y
     * @param x
     * @param height
     * @param width
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldDrawVerticalWall(int y, int x, int height, int width) {
        return true;
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
        return true;
    }
}
