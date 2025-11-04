package fr.univlille.labyrinth.model.algorithmold;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MazeAlgorithmFactoryTest {
    @Test
    public void testGetName() {
        MazeAlgorithmFactory algoCorrespondant = MazeAlgorithmFactory.valueOf("STANDARDLARGEUR");
        assertEquals(MazeAlgorithmFactory.STANDARDLARGEUR, algoCorrespondant);
    }

}
