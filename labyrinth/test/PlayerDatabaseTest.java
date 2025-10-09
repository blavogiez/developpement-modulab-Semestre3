import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerDatabaseTest {
    PlayerDatabase playerDatabase;
    String[] data;
    String file;

    @BeforeEach
    public void initialisation() {

        data = new String[] {""};
        playerDatabase = new PlayerDatabaseTest();
        file = "./res/testPlayerDatabase.csv";
    }

    testSavePlayer(){
        boolean result = false;
        try {
            playerDatabase.SavePlayer(data, file);
            result = true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(result);
    }

}

    testLoadPlayer(){
        String[] result = new String[] {};
        try {
            result = playerDatabase.LoadPlayer(" ");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (InvalidStructureException e) {
            System.err.println(e.getMessage());
        }
        assertNotEquals(result, data);
        try {
            result = playerDatabase.LoadPlayer(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (InvalidStructureException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(Arrays.equals(result, data));

    }

    testPlayerExists(){
        Player p1=new Player();
        Player p2=new Player();
        boolean result;
        try {
            result = playerDatabase.playerExists(p1,file);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(result);
        try {
            result = playerDatabase.playerExists(p2,file);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertFalse(result);



        assertFalse(notExists);
    }

    testVerifyData(){
        boolean result = false;
        try {
            playerDatabase.verifyData(data);
            result = true;
        } catch (InvalidStructureException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(result);
        result = false;
        data = new String[] {};
        try {
            playerDatabase.verifyData(data);
            result = true;
        } catch (InvalidStructureException e) {
            System.err.println(e.getMessage());
        }
        assertFalse(result);
        data = new String[] { "" };
        try {
            playerDatabase.verifyData(data);
            result = true;
        } catch (InvalidStructureException e) {
            System.err.println(e.getMessage());

        }
}