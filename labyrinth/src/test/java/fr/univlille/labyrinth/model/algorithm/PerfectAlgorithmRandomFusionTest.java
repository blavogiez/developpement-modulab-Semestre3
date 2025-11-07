package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PerfectAlgorithmRandomFusionTest {
    PerfectAlgorithmRandomFusion algo;

    @BeforeEach
    public void init(){
        algo = PerfectAlgorithmRandomFusion.getInstance();
    }

    @Test
    public void generateNumberGridTest(){
        assertEquals(new int[][]{{0,1},{2,3}}, algo.generateNumberGrid(2,2));
        assertEquals(new int[][]{{0,1,2},{3,4,5},{6,7,8}}, algo.generateNumberGrid(3,3));
        assertEquals(new int[][]{{0,1,2},{3,4,5}}, algo.generateNumberGrid(2,3));
        assertEquals(new int[][]{{0,1},{2,3},{4,5}}, algo.generateNumberGrid(3,2));
    }

    @Test
    public void fusionPositionTest(){
        algo.grid = algo.generateNumberGrid(2,2);
        algo.fusionPosition(new Position(1,0), Direction.DOWN);
        assertEquals(new int[][]{{0,1},{2,1}},algo.grid);
        algo.fusionPosition(new Position(0,1), Direction.UP);
        assertEquals(new int[][]{{2,3},{2,3}},algo.grid);
        algo.fusionPosition(new Position(1,0), Direction.LEFT);
        assertEquals(new int[][]{{3,3},{3,3}},algo.grid);

        algo.grid = algo.generateNumberGrid(2,2);
        algo.fusionPosition(new Position(0,0), Direction.RIGHT);
        assertEquals(new int[][]{{0,0},{2,3}},algo.grid);
        algo.fusionPosition(new Position(0,0), Direction.UP);
        assertEquals(new int[][]{{0,0},{2,3}},algo.grid);
    }





    @Test
    public void isAlltheSameNumberTest(){
        algo.grid = new int[][]{{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1}};
        assertTrue(algo.isAllTheSameNumber());
        algo.grid = new int[][]{{1,1,1,1,1,2,1,1,1},{1,1,1,1,1,1,1,1,1}};
        assertFalse(algo.isAllTheSameNumber());
        algo.grid = new int[][]{{753,753},{753,753},{753,753},{753,753},{753,753},{753,753}};
        assertTrue(algo.isAllTheSameNumber());
        algo.grid = new int[][]{{1,1},{1,1},{1,1},{1,3},{1,1},{1,1}};
        assertFalse(algo.isAllTheSameNumber());

    }

    @Test
    public void transformAllNumberTest(){
        algo.grid = algo.generateNumberGrid(10,10);
        for (int i = 0; i<algo.grid.length*algo.grid[0].length;i++){
            algo.transformAllNumber(i,0);
        }
        assertTrue(algo.isAllTheSameNumber());

        algo.grid = new int[][]{{1,1,1,1,1,2},{0,0,0,0,0,0}};
        algo.transformAllNumber(1,2);
        algo.transformAllNumber(2,0);
        assertTrue(algo.isAllTheSameNumber());
    }


}
