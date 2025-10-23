package fr.univlille.labyrinth.model;
import fr.univlille.labyrinth.model.save.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    static Player p1,p2,p3;

    @BeforeAll
    public static void initialization(){
        p1=new Player("toto");
        p2=new Player("tutu");
        p3=new Player("tata");
    }

    @Test
    public void testGetName() {
        assertEquals("toto", p1.getName());
        assertEquals("tutu", p2.getName());
        assertEquals("tata", p3.getName());
    }


    // tests if default progress is correctly initialized
    @Test
    public void testProgressNotNull() {
        assertNotNull(p1.getProgress());
        assertNotNull(p2.getProgress());
        assertNotNull(p3.getProgress());
    }

    // if correctly initialized, then there are existing challenges that return a score (even 0 as none is done)

    @Test
    public void testGetHighestLevel() {
        assertTrue(p1.getHighestLevel() >= 0);
        assertTrue(p2.getHighestLevel() >= 0);
        assertTrue(p3.getHighestLevel() >= 0);
    }

    @Test
    public void testGetScore() {
        assertTrue(p1.getScore() >= 0);
        assertTrue(p2.getScore() >= 0);
        assertTrue(p3.getScore() >= 0);
    }
}
