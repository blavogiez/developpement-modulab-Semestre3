package fr.univlille.labyrinth.algorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Player;
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
