package fr.univlille.labyrinth.view.layout;

public class LabyrinthLayout {
    private final double cellSize;
    private final double offsetX;
    private final double offsetY;
    private final double wallThickness;

    public LabyrinthLayout(double cellSize, double offsetX, double offsetY, double wallThickness) {
        this.cellSize = cellSize;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.wallThickness = wallThickness;
    }

    /** 
     * @return double
     */
    public double getCellSize() {
        return cellSize;
    }

    /** 
     * @return double
     */
    public double getOffsetX() {
        return offsetX;
    }

    /** 
     * @return double
     */
    public double getOffsetY() {
        return offsetY;
    }

    /** 
     * @return double
     */
    public double getWallThickness() {
        return wallThickness;
    }
}
