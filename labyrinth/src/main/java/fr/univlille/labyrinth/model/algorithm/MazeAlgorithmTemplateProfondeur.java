package fr.univlille.labyrinth.model.algorithm;

public abstract class MazeAlgorithmTemplateProfondeur extends MazeAlgorithmTemplate {
    public boolean[][] createMaze(int width, int heigth, double percentageWall, int pathLength){
        return createMaze(width,heigth,percentageWall);
    }
    public abstract boolean[][] createMaze(int width, int heigth, double percentageWall);
}
