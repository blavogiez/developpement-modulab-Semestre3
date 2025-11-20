package fr.univlille.labyrinth.model.algorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

public class PerfectAlgorithmFusionKruskalUnionTest {
    PerfectAlgorithmFusionKruskalUnion algo = PerfectAlgorithmFusionKruskalUnion.getInstance();
    Maze maze1, maze2, maze3;
    Position start1, start2, start3, end1, end2, end3;

    @BeforeEach
    public void init() {
        maze1 = new Maze(10, 12, 10, algo);
        start1 = maze1.getEntryPosition();
        end1 = maze1.getExitPosition();

        maze2 = new Maze(40, 12, 20, algo);
        start2 = maze2.getEntryPosition();
        end2 = maze2.getExitPosition();

        maze3 = new Maze(50, 32, 30, algo);
        start3 = maze3.getEntryPosition();
        end3 = maze3.getExitPosition();
    }

    @Test
    public void should_generate_maze() {
        Maze maze = new Maze(10, 10, 5);
        algo.generateMaze(maze);
        assertArrayEquals(maze.getMurHorizontaux(), algo.horizontalsWalls);
        assertArrayEquals(maze.getMurVerticaux(), algo.verticalsWalls);
    }

    @Test
    public void should_have_different_start_and_end() {
        assertNotEquals(start1, end1);
        assertNotEquals(start2, end2);
        assertNotEquals(start3, end3);
    }

    @Test
    public void should_have_path_between_start_and_end() {
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
    }

    @Test
    public void should_respect_minimum_path_length() {
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);
        assertEquals(10, distance1);

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);
        assertEquals(20, distance2);

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
        assertEquals(30, distance3);
    }

    @Test
    public void should_generate_mazes_with_minimum_distance() {
        int minDistance = 8;
        int failCount = 0;

        for (int i = 0; i < 50; i++) {
            minDistance = (i % 2 == 0) ? 15 : 8;

            Maze maze = new Maze(15 + i, 15 + (2 * i), minDistance);
            Position start = maze.getEntryPosition();
            Position end = maze.getExitPosition();

            Integer distance = MazeDistance.calculateDistance(maze, start, end);
            if (distance == null || distance < minDistance) {
                failCount++;
            }
        }

        assertEquals(0, failCount);
    }

    @Test
    void should_union_merge_two_singletons() {
        algo.parent = new int[]{0, 1, 2, 3};
        algo.size = new int[]{1, 1, 1, 1};
        algo.components = 4;

        algo.union(0, 1);

        int root0 = algo.find(0);
        int root1 = algo.find(1);

        assertEquals(root0, root1);
        assertEquals(3, algo.components);
        assertEquals(2, algo.size[root0]);
    }

    @Test
    void should_do_nothing_when_union_on_same_component() {
        algo.parent = new int[]{0, 0, 2, 3};
        algo.size = new int[]{2, 1, 1, 1};
        algo.components = 3;

        algo.union(0, 1);

        assertEquals(3, algo.components);
        assertEquals(2, algo.size[0]);
        assertEquals(0, algo.find(1));
    }

    @Test
    void should_keep_bigger_root_with_union_by_size_heuristic() {
        algo.parent = new int[]{0, 0, 2};
        algo.size = new int[]{2, 1, 1};
        algo.components = 2;

        algo.union(2, 0);

        int root0 = algo.find(0);
        int root2 = algo.find(2);

        assertEquals(root0, root2);
        assertEquals(0, root0);
        assertEquals(3, algo.size[root0]);
    }
}
