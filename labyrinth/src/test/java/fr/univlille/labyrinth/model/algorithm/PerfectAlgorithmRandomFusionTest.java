package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
 class PerfectAlgorithmRandomFusionTest {
    PerfectAlgorithmRandomFusion algo;
    Maze maze1, maze2, maze3;
    Position start1, start2, start3, end1, end2, end3;

    @BeforeEach
    void init(){

        algo = PerfectAlgorithmRandomFusion.getInstance();

        //petit labyrinthe de taille avec distance minimale
        maze1 = new Maze(10,12,10, algo);
        start1 = maze1.getEntryPosition();
        end1 = maze1.getExitPosition();


        // moyen labyrinthe avec petite distance
        maze2 = new Maze(40,12,20, algo);
        start2 = maze2.getEntryPosition();
        end2 = maze2.getExitPosition();

        //grand labyrinthe avec grande distance
        maze3 = new Maze(50,32,30, algo);
        start3 = maze3.getEntryPosition();
        end3 = maze3.getExitPosition();

    }

    @Test
    void should_generate_number_grid_test(){
        assertArrayEquals(new int[][]{{0,1},{2,3}}, algo.generateNumberGrid(2,2));
        assertArrayEquals(new int[][]{{0,1,2},{3,4,5},{6,7,8}}, algo.generateNumberGrid(3,3));
        assertArrayEquals(new int[][]{{0,1,2},{3,4,5}}, algo.generateNumberGrid(2,3));
        assertArrayEquals(new int[][]{{0,1},{2,3},{4,5}}, algo.generateNumberGrid(3,2));
    }

    @Test
    void should_fusion_number_from_two_adjacent_cell(){
        algo.horizontalsWalls = new boolean[2][2];
        algo.verticalsWalls = new boolean[2][2];
        algo.grid = algo.generateNumberGrid(2,2);
        algo.fusionPosition(new Position(1,0), Direction.DOWN);
        assertArrayEquals(new int[][]{{0,1},{2,1}},algo.grid);
        algo.fusionPosition(new Position(0,1), Direction.UP);
        assertArrayEquals(new int[][]{{2,1},{2,1}},algo.grid);
        algo.fusionPosition(new Position(1,0), Direction.LEFT);
        assertArrayEquals(new int[][]{{1,1},{1,1}},algo.grid);

        algo.grid = algo.generateNumberGrid(2,2);
        algo.fusionPosition(new Position(0,0), Direction.RIGHT);
        assertArrayEquals(new int[][]{{0,0},{2,3}},algo.grid);
        algo.fusionPosition(new Position(0,0), Direction.UP);
        assertArrayEquals(new int[][]{{0,0},{2,3}},algo.grid);
    }





    @Test
    void should_detect_if_all_the_number_are_the_same(){
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
    void should_transform_all_the_number(){
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

    @Test
    void should_generate_wall_in_the_maze(){
        Maze maze = new Maze(10,10,5);
        algo.generateMaze(maze);
        assertArrayEquals(maze.getMurHorizontaux(), algo.horizontalsWalls);
        assertArrayEquals(maze.getMurVerticaux(), algo.verticalsWalls);
    }

    @Test
    void start_and_end_should_be_different() {
        assertNotEquals(start1, end1);
        assertNotEquals(start2, end2);
        assertNotEquals(start3, end3);
    }

    @Test
    void path_between_end_and_start_should_exist() {
        // Il doit exister un chemin (PATH = true) entre start et end

        //(la fonction appelée retourne null si rien n'est trouvée ou la distance sinon)
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1, "Un chemin doit exister entre start et end dans maze1");

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2, "Un chemin doit exister entre start et end dans maze2");

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3, "Un chemin doit exister entre start et end dans maze3");
    }

    @Test
    void path_should_have_distance_min() {
        // la distance BFS reelle entre start et end doit etre >= pathLength demande
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);
        assertEquals(10,distance1, "Distance dans maze1 devrait être = 10, mais est " + distance1);

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);
        assertEquals(20,distance2, "Distance dans maze2 devrait être = 20, mais est " + distance2);

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
        assertEquals(30,distance3, "Distance dans maze3 devrait être = 30, mais est " + distance3);
    }

    @Test
    void distance_should_be_calculated() {
        int minDistance;
        int failCount = 0;

        for (int i = 0; i < 50; i++) {
            minDistance = (i%2 == 0) ? 15 : 8 ;
            int taille1 = 15+i;
            int taille2 = 15+(2*i);
            Maze maze;
            if (i%2 == 0) maze = new Maze(taille1, taille2, minDistance);
            else maze = new Maze(taille2, taille1, minDistance);
            Position start = maze.getEntryPosition();
            Position end = maze.getExitPosition();

            Integer distance = MazeDistance.calculateDistance(maze, start, end);
            if (distance == null || distance < minDistance) {
                failCount++;
            }
        }

        // verif que tous les labyrinthes respectent la distance minimale
        assertEquals(0, failCount, failCount + " labyrinthes sur 50 ne respectent pas la distance minimale");
    }


}
