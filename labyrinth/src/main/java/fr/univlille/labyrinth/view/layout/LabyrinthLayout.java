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

    public double getCellSize() {
        return cellSize;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getWallThickness() {
        return wallThickness;
    }
}
