package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class GenerateTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        revealTrap(position, maze.getTrapManager());
        MazeAlgorithm algo = maze.getWallPercentage() < 1.0
            ? MazeAlgorithmFactory.RANDOM.getAlgorithm()
            : MazeAlgorithmFactory.PERFECT.getAlgorithm();
        algo.generateMaze(maze);
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_GENERATE";
    }
}
