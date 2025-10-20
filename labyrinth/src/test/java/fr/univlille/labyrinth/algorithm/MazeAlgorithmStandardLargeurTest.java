package fr.univlille.labyrinth.algorithm;

import fr.univlille.labyrinth.model.Position;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmStandardLargeur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeAlgorithmStandardLargeurTest {

    @Test
    void testCreateMazeWithMinimumDistance() {
        // Given
        MazeAlgorithmStandardLargeur algorithm = MazeAlgorithmStandardLargeur.getInstance();
        int width = 11; // Utilisation d'une taille impaire pour s'assurer d'avoir des chemins clairs
        int height = 11;
        double percentageOfWall = 0.3;
        int minimumPathLength = 8;

        // When
        boolean[][] maze = algorithm.createMaze(width, height, percentageOfWall, minimumPathLength);
        Position start = algorithm.getStart();
        Position end = algorithm.getEnd();

        // Then
        assertNotNull(maze);
        assertNotNull(start);
        assertNotNull(end);
        
        // Vérification que la distance entre le départ et l'arrivée est au moins égale à minimumPathLength
        // La distance réelle est calculée comme la distance Manhattan dans ce contexte
        int actualDistance = Math.abs(end.getX() - start.getX()) + Math.abs(end.getY() - start.getY());
        
        // On vérifie que la distance est au moins égale à la longueur de chemin spécifiée
        assertTrue(actualDistance >= minimumPathLength, 
            "La distance entre l'entrée et la sortie devrait être d'au moins " + minimumPathLength + 
            ", mais est de " + actualDistance);
    }

    @Test
    void testCreateMazeWithSmallMinimumDistance() {
        // Given
        MazeAlgorithmStandardLargeur algorithm = MazeAlgorithmStandardLargeur.getInstance();
        int width = 5;
        int height = 5;
        double percentageOfWall = 0.3;
        int minimumPathLength = 3;

        // When
        boolean[][] maze = algorithm.createMaze(width, height, percentageOfWall, minimumPathLength);
        Position start = algorithm.getStart();
        Position end = algorithm.getEnd();

        // Then
        assertNotNull(maze);
        assertNotNull(start);
        assertNotNull(end);
    }

    @Test
    void testCreateMazeWithLargeMinimumDistance() {
        // Test avec une distance minimale grande par rapport à la taille du labyrinthe
        MazeAlgorithmStandardLargeur algorithm = MazeAlgorithmStandardLargeur.getInstance();
        int width = 11;
        int height = 11;
        double percentageOfWall = 0.1; // Peu de murs pour permettre un long chemin
        int minimumPathLength = 8;

        // On s'attend à ce que le labyrinthe soit créé avec succès
        assertDoesNotThrow(() -> {
            boolean[][] maze = algorithm.createMaze(width, height, percentageOfWall, minimumPathLength);
            Position start = algorithm.getStart();
            Position end = algorithm.getEnd();
            
            assertNotNull(maze);
            assertNotNull(start);
            assertNotNull(end);
        });
    }
}