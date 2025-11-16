package fr.univlille.labyrinth.view.layout;

/*
 * Classe utilitaire qui gère le calcul des dimensions de la vue (Responsive)
 */
public class LabyrinthLayoutCalculator {
    private static final double MARGIN_RATIO = 0.0;
    private static final double WALL_THICKNESS_RATIO = 0.1;

    /** 
     * @param canvasWidth
     * @param canvasHeight
     * @param mazeWidth
     * @param mazeHeight
     * @return LabyrinthLayout
     */
    public LabyrinthLayout calculate(double canvasWidth, double canvasHeight, int mazeWidth, int mazeHeight) {
        double margin = Math.min(canvasWidth, canvasHeight) * MARGIN_RATIO;
        double availableWidth = canvasWidth - 2 * margin;
        double availableHeight = canvasHeight - 2 * margin;

        double cellSize = Math.min(availableWidth / mazeWidth, availableHeight / mazeHeight);

        double totalMazeWidth = cellSize * mazeWidth;
        double totalMazeHeight = cellSize * mazeHeight;
        double offsetX = (canvasWidth - totalMazeWidth) / 2;
        double offsetY = (canvasHeight - totalMazeHeight) / 2;

        double wallThickness = cellSize * WALL_THICKNESS_RATIO;

        return new LabyrinthLayout(cellSize, offsetX, offsetY, wallThickness);
    }
}
