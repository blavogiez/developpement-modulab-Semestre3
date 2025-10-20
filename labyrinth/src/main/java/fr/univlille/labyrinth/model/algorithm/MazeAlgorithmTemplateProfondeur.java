package fr.univlille.labyrinth.model.algorithm;

public abstract class MazeAlgorithmTemplateProfondeur extends MazeAlgorithmTemplate {
    /** 
     * @param width
     * @param heigth
     * @param percentageWall
     * @param pathLength
     * @return boolean[][]
     */
    public boolean[][] createMaze(int width, int heigth, double percentageWall, int pathLength){
        return createMaze(width,heigth,percentageWall);
    }
    public abstract boolean[][] createMaze(int width, int heigth, double percentageWall);
}
