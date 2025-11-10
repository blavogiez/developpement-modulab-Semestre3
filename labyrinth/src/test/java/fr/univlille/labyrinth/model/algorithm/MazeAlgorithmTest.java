package fr.univlille.labyrinth.model.algorithm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

// Test à fix (je n'y touche pas)

public class MazeAlgorithmTest {

    MazeAlgorithm algo;
    @BeforeEach
    public void init(){
        algo = new MazeAlgorithm() {
            @Override
            public void generateMaze(Maze maze) {

            }
        };
    }

    @Test
    public void removeWallTest(){
        int height = 3;
        int width = 3;
        algo.horizontalsWalls = new boolean[height - 1][width];
        MazeAlgorithm.allAreTrue(algo.horizontalsWalls);
        algo.verticalsWalls = new boolean[width - 1][height];
        MazeAlgorithm.allAreTrue(algo.verticalsWalls);
        assertTrue(algo.verticalsWalls[0][0]);
        algo.removeWall(new Position(0,0), Direction.RIGHT);
        assertFalse(algo.verticalsWalls[0][0]);
        assertTrue(algo.horizontalsWalls[0][0]);
        algo.removeWall(new Position(0,0), Direction.DOWN);
        assertFalse(algo.horizontalsWalls[0][0]);

        algo.removeWall(new Position(0,0), Direction.UP);
        algo.removeWall(new Position(0,0), Direction.LEFT);
    }

    @Test
    public void estPositionCorrecteTest(){
        for (int x = 5; x < 15; x++){
            for (int y = 5; y<15; y++){
                for (int i = 0; i<10;i++){
                    for (int j = 0; j<10;j++){
                        if (i>=x || j>=y){
                            assertFalse(MazeAlgorithm.positionCorrecte(j,i, y,x));
                        } else {
                            assertTrue(MazeAlgorithm.positionCorrecte(j,i, y,x));
                        }
                    }
                }
            }
        }

        assertTrue(MazeAlgorithm.positionCorrecte(new Position(0,0), new boolean[1][1]));
        assertTrue(MazeAlgorithm.positionCorrecte(new Position(1,1), new boolean[2][2]));
        assertFalse(MazeAlgorithm.positionCorrecte(new Position(1,1), new boolean[1][1]));


    }

    @Test
    public void allAreTrueTest(){
        boolean[][] tab = new boolean[100][100];
        MazeAlgorithm.allAreTrue(tab);
        for (boolean[] booleans : tab){
            for (boolean bool : booleans){
                assertTrue(bool);
            }
        }
    }
}
