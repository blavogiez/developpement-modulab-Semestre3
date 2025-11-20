package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ProgressionModeTest {

    private Player player;
    private Challenge challenge;
    private ProgressionMode progressionMode;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
        challenge = player.getProgress().getLevelProgress()[0].getChallenges()[2];
        progressionMode = new ProgressionMode(player, challenge);
    }

    @Test
    void game_mode_should_initialize_correctly() {
        assertNotNull(progressionMode.getPlayer());
        assertEquals("TestPlayer", progressionMode.getPlayer().getName());
        assertSame(player, progressionMode.getPlayer());
    }

    /** 
     * @throws Exception
     */
    @Test
    void test_challenge_should_be_completed_after_being_completed() throws Exception {
        assertFalse(challenge.isCompleted());

        Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory", PlayerEntity.class);
        handleVictory.setAccessible(true);
        handleVictory.invoke(progressionMode, (PlayerEntity) null);

        assertTrue(challenge.isCompleted());
    }

    /** 
     * @throws Exception
     */
    @Test
    void test_should_have_best_time() throws Exception {
        Timer timer = new Timer();
        timer.start();
        synchronized (timer){
            timer.wait(100);
            timer.stop();

            progressionMode.setChronometre(timer);
            long recordedTime = timer.getChrono();

            Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory", PlayerEntity.class);
            handleVictory.setAccessible(true);
            handleVictory.invoke(progressionMode, (PlayerEntity) null);

            assertTrue(challenge.getTimeCompleted() > 0);
            assertEquals(recordedTime, challenge.getTimeCompleted());
        }

    }

    /** 
     * @throws Exception
     */
    @Test
    void test_should_keep_best_time() throws Exception {
        Timer timer1 = new Timer();
        long firstTime;
        Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory", PlayerEntity.class);
        synchronized (timer1){
            timer1.start();
            timer1.wait(200);
            timer1.stop();
            progressionMode.setChronometre(timer1);
            handleVictory.setAccessible(true);
            handleVictory.invoke(progressionMode, (PlayerEntity) null);
            firstTime = challenge.getTimeCompleted();
            assertTrue(firstTime >= 200);
        }

        Timer timer2 = new Timer();
        synchronized (timer2){
            timer2.start();
            timer2.wait(50);
            timer2.stop();
            progressionMode.setChronometre(timer2);
            handleVictory.invoke(progressionMode, (PlayerEntity) null);
            assertTrue(challenge.getTimeCompleted() < firstTime);
            assertTrue(challenge.getTimeCompleted() >= 50);
        }
    }
}
