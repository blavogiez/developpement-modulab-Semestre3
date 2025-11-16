package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        player = maze.getEntityManager().getPlayerEntities().get(0);
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

    //Le teleporter se situe en (1,0), et l'exit en (2,2). Il devrait donc téléporter partout sauf dans ces 2 cases
    @Test
    public void random_teleport_trap_teleport_test(){
        maze.getTrapManager().getTraps()[0][1] = TrapFactory.PUSH_TRAP.generateTrap();

        boolean[][] positionWasUsed = new boolean[3][3];
        for (int i = 0; i<1000; i++){
            TrapFactory.PUSH_TRAP.generateTrap().onUse(player.getID(),new Position(0,1),playerPosition,maze);
            positionWasUsed[player.getPosition().getY()][player.getPosition().getX()] = true;
        }

        boolean[][] shouldBe = new boolean[][]{{true,false,true},{true,true,true},{true,true,false}};

        Assertions.assertArrayEquals(positionWasUsed,shouldBe);



    }

    private void playerMoving(Direction direction) {
        playerPosition = player.getPosition().copy();
        player.move(maze, direction);
        maze.trapEffect(player.getID(), playerPosition);
    }
}
