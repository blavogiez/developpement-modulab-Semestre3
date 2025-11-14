package fr.univlille.labyrinth.model.maze.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

/*
 * Ici nous testons si lorsqu'un monstre "mange" un joueur (arrive sur sa case) le joueur est bien enlevé et le jeu est conduit à la défaite.
 */
public class MonsterEatingPlayerTest {
    static ObservableMaze maze ;
    
    @BeforeAll
    private void init() {
       
    }

    @Test
    public void monsterEatsPlayer() {
        String entitiesConfiguration = "t=PLAYER;q=1;m=PLAYER|t=EXIT;q=1;m=MOVING|t=MONSTER;q=3;m=MONSTER";
        ObservableMaze maze = new ObservableMaze(15, 10, 1, entitiesConfiguration);
    
        for (int i = 0 ; i < 100 ; i++) {
            maze.getEntityManager().moveEntities(0, maze, Direction.LEFT);
        }
        maze.getEntityManager().checkMonsterOnPlayer();

        assertEquals(0, maze.getEntityManager().getPlayerEntities().size()) ;
    }
}
