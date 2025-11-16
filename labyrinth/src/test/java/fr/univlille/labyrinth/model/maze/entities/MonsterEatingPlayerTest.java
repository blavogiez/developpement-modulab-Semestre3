package fr.univlille.labyrinth.model.maze.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;

class MonsterEatingPlayerTest {

    @Test
    void should_monster_eat_player_after_playing_dumbly() {
        String entitiesConfiguration = "t=PLAYER;q=1;m=PLAYER|t=EXIT;q=1;m=MOVING|t=MONSTER;q=3;m=MONSTER";
        ObservableMaze maze = new ObservableMaze(15, 10, 1, entitiesConfiguration);

        assertEquals(1, maze.getEntityManager().getPlayerEntities().size(), "Devrait avoir 1 joueur au départ");

        for (int i = 0 ; i < 100 ; i++) {
            maze.getEntityManager().moveEntities(0, maze, Direction.LEFT);
        }
        maze.getEntityManager().checkMonsterOnPlayer();

        assertEquals(0, maze.getEntityManager().getPlayerEntities().size(), "Le joueur devrait être mangé");
    }

    @Test
    void should_remove_player_when_monster_on_same_position() {
        String entitiesConfiguration = "t=PLAYER;q=1;m=PLAYER|t=MONSTER;q=1;m=MONSTER";
        ObservableMaze maze = new ObservableMaze(10, 10, 1, entitiesConfiguration);

        PlayerEntity player = maze.getEntityManager().getPlayerEntity();
        assertNotNull(player, "Le joueur devrait exister");

        maze.getEntityManager().getEntities().stream()
            .filter(e -> e.getEntityType() == EntityType.MONSTER)
            .findFirst()
            .ifPresent(monster -> monster.setPosition(player.getPosition()));

        int deadCount = maze.getEntityManager().checkMonsterOnPlayer();

        assertEquals(1, deadCount, "Devrait avoir tuer 1 joueur");
        assertEquals(0, maze.getEntityManager().getPlayerEntities().size(), "Le joueur devrait être supprimé");
    }

    @Test
    void should_return_correct_dead_count_with_multiple_players() {
        String entitiesConfiguration = "t=PLAYER;q=2;m=PLAYER|t=MONSTER;q=1;m=MONSTER";
        ObservableMaze maze = new ObservableMaze(10, 10, 1, entitiesConfiguration);

        assertEquals(2, maze.getEntityManager().getPlayerEntities().size(), "Devrait avoir 2 joueurs");

        PlayerEntity firstPlayer = maze.getEntityManager().getPlayerEntities().get(0);
        maze.getEntityManager().getEntities().stream()
            .filter(e -> e.getEntityType() == EntityType.MONSTER)
            .findFirst()
            .ifPresent(monster -> monster.setPosition(firstPlayer.getPosition()));

        int deadCount = maze.getEntityManager().checkMonsterOnPlayer();

        assertEquals(1, deadCount, "Devrait avoir tuer 1 joueur");
        assertEquals(1, maze.getEntityManager().getPlayerEntities().size(), "Devrait rester 1 joueur vivant");
    }
}
