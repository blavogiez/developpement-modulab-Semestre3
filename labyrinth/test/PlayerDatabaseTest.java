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

    }

    testLoadPlayer(){

    }

    testPlayerExists(){

    }

    testVerifyData(){
        
    }
}