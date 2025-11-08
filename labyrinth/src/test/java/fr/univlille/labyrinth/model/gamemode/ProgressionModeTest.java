package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.utils.Timer;
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
    void testConstructorAndGetPlayer() {
        assertNotNull(progressionMode.getPlayer());
        assertEquals("TestPlayer", progressionMode.getPlayer().getName());
        assertSame(player, progressionMode.getPlayer());
    }

    @Test
    void testHandleVictoryMarksChallenge() throws Exception {
        assertFalse(challenge.isCompleted());

        Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory");
        handleVictory.setAccessible(true);
        handleVictory.invoke(progressionMode);

        assertTrue(challenge.isCompleted());
    }

    @Test
    void testHandleVictoryRecordsTime() throws Exception {
        Timer timer = new Timer();
        timer.start();
        Thread.sleep(100);
        timer.stop();

        progressionMode.setChronometre(timer);
        long recordedTime = timer.getChrono();

        Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory");
        handleVictory.setAccessible(true);
        handleVictory.invoke(progressionMode);

        assertTrue(challenge.getTimeCompleted() > 0);
        assertEquals(recordedTime, challenge.getTimeCompleted());
    }

    @Test
    void testHandleVictoryKeepsBestTime() throws Exception {
        Timer timer1 = new Timer();
        timer1.start();
        Thread.sleep(200);
        timer1.stop();

        progressionMode.setChronometre(timer1);
        Method handleVictory = GameMode.class.getDeclaredMethod("handleVictory");
        handleVictory.setAccessible(true);
        handleVictory.invoke(progressionMode);

        long firstTime = challenge.getTimeCompleted();
        assertTrue(firstTime >= 200);

        Timer timer2 = new Timer();
        timer2.start();
        Thread.sleep(50);
        timer2.stop();

        progressionMode.setChronometre(timer2);
        handleVictory.invoke(progressionMode);

        assertTrue(challenge.getTimeCompleted() < firstTime);
        assertTrue(challenge.getTimeCompleted() >= 50);
    }
}
