package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.entities.ExitEntity;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import javafx.geometry.Pos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrapTest {
    boolean[][] verticalsWalls;
    boolean[][] horizontalsWalls;
    ObservableMaze maze;
    PlayerEntity player;
    Position playerPosition;

    @BeforeEach
    public void init(){
        verticalsWalls=new boolean[2][3];
        horizontalsWalls=new boolean[2][3];
        maze = new ObservableMaze(3,3,10,"DEFAULT", MazeAlgorithmFactory.PERFECT.getAlgorithm(),"DEFAULT");
        maze.setMurVerticaux(verticalsWalls);
        maze.setMurHorizontaux(horizontalsWalls);
        player = maze.getEntityManager().getEntitiesByType(PlayerEntity.class).get(0);
        player.setPosition(new Position(0,0));
        playerPosition = player.getPosition().copy();
    }

    @Test
    public void push_trap_pushing_one_away_and_used_test(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.PUSH_TRAP.generateTrap();

        playerMoving(Direction.RIGHT);

        Assertions.assertEquals(new Position(0,0), player.getPosition());

        playerMoving(Direction.RIGHT);

        Assertions.assertEquals(new Position(1,0), player.getPosition());



    }

    @Test
    public void push_trap_pushing_multiple_away_test(){
        maze.getTrapManager().getTraps()[0][2] = TrapFactory.PUSH_TRAP.generateTrap();

        playerMoving(Direction.RIGHT);

        Assertions.assertEquals(new Position(1,0), player.getPosition());

        playerMoving(Direction.RIGHT);

        Assertions.assertEquals(new Position(0,0), player.getPosition());
    }

    @Test
    public void push_trap_against_wall_test(){
        maze.getTrapManager().getTraps()[0][2] = TrapFactory.PUSH_TRAP.generateTrap();

        verticalsWalls[0][0] = true;

        maze.setMurVerticaux(verticalsWalls);

        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(0,0), player.getPosition());
        playerMoving(Direction.DOWN);
        Assertions.assertEquals(new Position(0,1), player.getPosition());
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(1,1), player.getPosition());
        playerMoving(Direction.UP);
        Assertions.assertEquals(new Position(1,0), player.getPosition());
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(1,0), player.getPosition());
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(2,0), player.getPosition());
    }

    @Test
    public void push_trap_in_every_direction(){
        maze.getTrapManager().getTraps()[1][1] = TrapFactory.PUSH_TRAP.generateTrap();
        playerMoving(Direction.RIGHT);
        playerMoving(Direction.DOWN);
        Assertions.assertEquals(new Position(1,0), player.getPosition());
        playerMoving(Direction.DOWN);
        playerMoving(Direction.LEFT);
        maze.getTrapManager().getTraps()[1][1] = TrapFactory.PUSH_TRAP.generateTrap();
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(0,1), player.getPosition());
        playerMoving(Direction.RIGHT);
        playerMoving(Direction.RIGHT);
        maze.getTrapManager().getTraps()[1][1] = TrapFactory.PUSH_TRAP.generateTrap();
        playerMoving(Direction.LEFT);
        Assertions.assertEquals(new Position(2,1), player.getPosition());
        playerMoving(Direction.DOWN);
        playerMoving(Direction.LEFT);
        maze.getTrapManager().getTraps()[1][1] = TrapFactory.PUSH_TRAP.generateTrap();
        playerMoving(Direction.UP);
        Assertions.assertEquals(new Position(1,2), player.getPosition());
    }



    @Test
    public void random_teleport_trap_should_teleport_to_free_cell_test() {
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.TELEPORTER_TRAP.generateTrap();
        boolean[][] actualVisited = new boolean[3][3];


        for (int i = 0; i < 1000; i++) {
            teleportPlayerFrom(new Position(0, 1));
            markVisited(actualVisited, player.getPosition());
        }

        boolean[][] expectedVisited = expectedFreeCells(maze);
        expectedVisited[0][1] = false;

        Assertions.assertArrayEquals(expectedVisited, actualVisited);
    }

    @Test
    public void fake_trap_used_when_player_on_top(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.FAKE_EXIT_TRAP.generateTrap();
        Assertions.assertEquals("TRAP_FAKE_EXIT", maze.getTrapManager().getTraps()[0][1].name());
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals("TRAP_USED", maze.getTrapManager().getTraps()[0][1].name());
    }

    @Test
    public void generate_trap_test_reload_algorithm_test(){
        boolean[][] initialVerticalWalls = cloneMatrix(verticalsWalls);
        boolean[][] initialHorizontalsWalls = cloneMatrix(horizontalsWalls);
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.GENERATE_TRAP.generateTrap();
        Assertions.assertTrue(areEqualMatrices(initialVerticalWalls, maze.getMurVerticaux()) && areEqualMatrices(initialHorizontalsWalls, maze.getMurVerticaux()));
        playerMoving(Direction.RIGHT);
        Assertions.assertFalse(areEqualMatrices(initialVerticalWalls, maze.getMurVerticaux()) && areEqualMatrices(initialHorizontalsWalls, maze.getMurVerticaux()));
    }

    @Test
    public void teleport_exit_trap_should_teleport_exit_and_be_used(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.TELEPORT_EXIT_TRAP.generateTrap();
        Assertions.assertEquals("TRAP_TELEPORT_EXIT", maze.getTrapManager().getTraps()[0][1].name());
        List<Position> exitOldPositions = maze.getEntityManager().getEntitiesByType(ExitEntity.class).parallelStream().map(x -> x.getPosition().copy()).toList();
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals("TRAP_USED", maze.getTrapManager().getTraps()[0][1].name());
        List<Position> exitNewPositions = maze.getEntityManager().getEntitiesByType(ExitEntity.class).parallelStream().map(x -> x.getPosition().copy()).toList();


        for (int i = 0; i < exitOldPositions.size(); i++) {
            Position oldPos = exitOldPositions.get(i);
            Position newPos = exitNewPositions.get(i);

            Assertions.assertNotEquals(oldPos, newPos,
                    "L'exit n°" + i + " n'a pas été téléportée.");
        }
    }

    @Test
    public void stunt_trap_should_paralyze_player_for_5_turns(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.STUN_TRAP.generateTrap();
        for (int i = 0; i<6; i++){
            playerMoving(Direction.RIGHT);
            Assertions.assertEquals(new Position(1, 0), player.getPosition());
        }
        playerMoving(Direction.RIGHT);
        Assertions.assertEquals(new Position(2, 0), player.getPosition());
    }

    @Test
    public void lava_trap_should_kill_player(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.LAVA_TRAP.generateTrap();
        playerMoving(Direction.RIGHT);
        Assertions.assertTrue(maze.getEntityManager().getEntitiesByType(PlayerEntity.class).isEmpty());
    }



    private void playerMoving(Direction direction) {
        playerPosition = player.getPosition().copy();
        player.move(maze, direction);
        maze.trapEffect(player.getID(), playerPosition);
    }

    private void teleportPlayerFrom(Position from) {
        TrapFactory.TELEPORTER_TRAP
                .generateTrap()
                .onUse(player.getID(), from, playerPosition, maze);
    }

    private void markVisited(boolean[][] grid, Position pos) {
        grid[pos.getY()][pos.getX()] = true;
    }

    private boolean[][] expectedFreeCells(ObservableMaze maze) {
        boolean[][] free = new boolean[3][3];
        Trap[][] traps = maze.getTrapManager().getTraps();
        EntityManager em = maze.getEntityManager();

        for (int y = 0; y < traps.length; y++) {
            for (int x = 0; x < traps[y].length; x++) {
                Position pos = new Position(x, y);
                free[y][x] = isCellFree(traps, em, pos);
                if (em.getPlayerEntity().getPosition().equals(pos)) free[y][x]=true;
            }
        }
        return free;
    }

    private boolean isCellFree(Trap[][] traps, EntityManager em, Position pos) {
        return traps[pos.getY()][pos.getX()] instanceof NoneTrap
                && !em.isEntityOnCell(pos);
    }

    private boolean[][] cloneMatrix(boolean[][] matrix) {
        if (matrix == null) return null;
        boolean[][] copy = new boolean[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    private boolean areEqualMatrices(boolean[][] a, boolean[][] b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }


}
