package fr.univlille.labyrinth.model.gamemode;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazePath;
import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

/*
Test du gamemode orienté, ciblé sur le jeu à plusieurs joueurs afin de vérifier les pré/post-conditions indépendantes des joueurs
 */
class MultiplayerGameModeTest {

    private FreeMode gameMode;
    private FreeModeConfig config;

    @BeforeEach
    void setUp() {
        config = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 10, 10, 0.4, 10);
        config.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=EXIT;q=1;m=DEFAULT");
        gameMode = new FreeMode(config);
    }

    private static class MockVictoryObserver implements VictoryObserver<GameMode> {
        private boolean victoryTriggered = false;
        private boolean defeatTriggered = false;

        public void onVictory() {
            this.victoryTriggered = true;
        }

        public void onDefeat(GameMode observable) {
            this.defeatTriggered = true;
        }

        public boolean isVictoryTriggered() {
            return victoryTriggered;
        }

        public boolean isDefeatTriggered() {
            return defeatTriggered;
        }
    }

    @Test
    void should_create_multiple_players_with_unique_ids() {
        gameMode.createMaze();
        ObservableMaze maze = gameMode.getCurrentMaze();
        EntityManager entityManager = maze.getEntityManager();

        PlayerEntity player0 = entityManager.getPlayerEntityByID(0);
        PlayerEntity player1 = entityManager.getPlayerEntityByID(1);

        assertNotNull(player0);
        assertNotNull(player1);
        assertEquals(0, player0.getId());
        assertEquals(1, player1.getId());
        assertNotEquals(player0.getPosition(), player1.getPosition());
    }

    @Test
    void should_move_players_independently() {
        FreeModeConfig largeConfig = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 20, 20, 0.4, 10);
        largeConfig.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=EXIT;q=1;m=DEFAULT");
        FreeMode largeGameMode = new FreeMode(largeConfig);
        largeGameMode.createMaze();

        ObservableMaze maze = largeGameMode.getCurrentMaze();
        EntityManager entityManager = maze.getEntityManager();

        PlayerEntity player0 = entityManager.getPlayerEntityByID(0);
        PlayerEntity player1 = entityManager.getPlayerEntityByID(1);

        Position initialPos1 = player1.getPosition();

        boolean moved = false;
        for (int i = 0; i < 100 && !moved; i++) {
            for (Direction dir : Direction.values()) {
                Position before = player0.getPosition().copy();
                largeGameMode.movePlayerPosition(0, dir);
                if (!player0.getPosition().equals(before)) {
                    moved = true;
                    break;
                }
            }
        }

        assertTrue(moved);
        assertEquals(initialPos1, player1.getPosition());
    }

    @Test
    void should_trigger_victory_when_first_player_reaches_exit() {
        FreeModeConfig simpleConfig = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 10, 10, 0.4, 10);
        simpleConfig.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=EXIT;q=1;m=DEFAULT");
        FreeMode simpleGameMode = new FreeMode(simpleConfig);
        simpleGameMode.createMaze();

        MockVictoryObserver observer = new MockVictoryObserver();
        simpleGameMode.addVictoryObserver(observer);

        ObservableMaze maze = simpleGameMode.getCurrentMaze();
        Position start = maze.getEntryPosition();
        Position exit = maze.getExitPosition();

        List<Position> path = MazePath.pathFinder(maze, start, exit);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        path.add(0, start);

        assertFalse(observer.isVictoryTriggered());

        for (Position targetPosition : path) {
            Position currentPlayerPos = maze.getEntityManager().getPlayerEntityByID(0).getPosition();
            if (currentPlayerPos.equals(targetPosition)) continue;

            Direction direction = determineDirectionStep(currentPlayerPos, targetPosition);
            simpleGameMode.movePlayerPosition(0, direction);

            if (!maze.getEntityManager().getPlayerEntityByID(0).getPosition().equals(exit)) {
                assertFalse(observer.isVictoryTriggered());
            }
        }

        assertTrue(observer.isVictoryTriggered());
        assertEquals(exit, maze.getEntityManager().getPlayerEntityByID(0).getPosition());
        assertEquals(maze.getEntityManager().getPlayerEntityByID(0), simpleGameMode.getWinner());
    }

    @Test
    void should_continue_game_when_one_player_dies() {
        FreeModeConfig configWithMonster = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 10, 10, 0.4, 10);
        configWithMonster.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=MONSTER;q=1;m=MONSTER|t=EXIT;q=1;m=DEFAULT");
        FreeMode monsterGameMode = new FreeMode(configWithMonster);
        monsterGameMode.createMaze();

        MockVictoryObserver observer = new MockVictoryObserver();
        monsterGameMode.addVictoryObserver(observer);

        ObservableMaze maze = monsterGameMode.getCurrentMaze();
        EntityManager entityManager = maze.getEntityManager();

        PlayerEntity player0 = entityManager.getPlayerEntityByID(0);

        Entity monster = entityManager.getEntities().stream()
            .filter(e -> e.getEntityType() == EntityType.MONSTER)
            .findFirst()
            .orElse(null);

        assertNotNull(monster);
        player0.setPosition(monster.getPosition());
        entityManager.checkMonsterOnPlayer();

        assertFalse(entityManager.containsType(PlayerEntity.class) &&
                   entityManager.getPlayerEntityByID(0) != null);
        assertNull(entityManager.getPlayerEntityByID(0));
        assertNotNull(entityManager.getPlayerEntityByID(1));
        assertFalse(observer.isDefeatTriggered());
    }

    @Test
    void should_trigger_defeat_when_all_players_die() {
        FreeModeConfig configMonsters = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 8, 8, 0.4, 8);
        configMonsters.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=MONSTER;q=1;m=MONSTER|t=EXIT;q=1;m=DEFAULT");
        FreeMode monsterMode = new FreeMode(configMonsters);
        monsterMode.createMaze();

        ObservableMaze maze = monsterMode.getCurrentMaze();
        EntityManager entityManager = maze.getEntityManager();

        Entity monster = entityManager.getEntities().stream()
            .filter(e -> e.getEntityType() == EntityType.MONSTER)
            .findFirst()
            .orElse(null);

        assertNotNull(monster);

        entityManager.getEntitiesByType(PlayerEntity.class).forEach(player -> {
            PlayerEntity p = (PlayerEntity) player;
            p.setPosition(monster.getPosition());
        });

        entityManager.checkMonsterOnPlayer();

        assertFalse(entityManager.containsType(PlayerEntity.class));
    }

    @Test
    void should_trap_affect_only_target_player() {
        FreeModeConfig trapConfig = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 10, 10, 0.4, 10);
        trapConfig.setEntitiesConfiguration("t=PLAYER;q=2;m=PLAYER|t=EXIT;q=1;m=DEFAULT");
        trapConfig.setTrapsConfiguration("L5");
        FreeMode trapMode = new FreeMode(trapConfig);
        trapMode.createMaze();

        ObservableMaze maze = trapMode.getCurrentMaze();
        EntityManager entityManager = maze.getEntityManager();

        PlayerEntity player0 = entityManager.getPlayerEntityByID(0);
        PlayerEntity player1 = entityManager.getPlayerEntityByID(1);

        assertNotNull(player0);
        assertNotNull(player1);

        Position player1InitialPos = player1.getPosition();

        Position newPos = new Position(1, 1);
        Position oldPos = player0.getPosition();
        player0.setPosition(newPos);
        maze.getTrapManager().trapEffect(0, oldPos);

        assertNotNull(entityManager.getPlayerEntityByID(1));
        assertEquals(player1InitialPos, player1.getPosition());
    }

    private Direction determineDirectionStep(Position from, Position to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        if (dx == 1 && dy == 0) return Direction.RIGHT;
        if (dx == -1 && dy == 0) return Direction.LEFT;
        if (dx == 0 && dy == 1) return Direction.DOWN;
        if (dx == 0 && dy == -1) return Direction.UP;

        throw new IllegalArgumentException("Positions non adjacentes : " + from + " to " + to);
    }
}
