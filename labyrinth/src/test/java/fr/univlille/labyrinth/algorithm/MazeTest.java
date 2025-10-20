package fr.univlille.labyrinth.algorithm;

import fr.univlille.labyrinth.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Cette classe sert à tester que le labyrinthe s'initialise bien selon la taille et pourcentage de mur demandé !
// Les tests d'entrée et de fin concernent la partie algorithm :)


// On fait également un test sur le respect du pourcentage de mur, vérifiant que la moyenne des murs de 100 labyrinthes générés tend vers le pourcentage de mur (différence (donc absolue) inférieure à 0.05)
public class MazeTest {

    static Maze m1,m2,m3,m4;
    @BeforeAll
    public static void initialization(){
        m1 = new Maze(10, 10, 0.5);
        m2 = new Maze(20, 20, 0.3);
        m3 = new Maze(30, 30, 0.5);
        m4=m1;
    }
    @Test
    public void testGridInitialization() {
        assertNotNull(m1.getGrid());
        assertEquals(10, m1.getGrid().length);
        assertEquals(10, m1.getGrid()[0].length);
        assertNotNull(m2.getGrid());
        assertEquals(20, m2.getGrid().length);
        assertEquals(20, m2.getGrid()[0].length);
        assertNotNull(m3.getGrid());
        assertEquals(30, m3.getGrid().length);
        assertEquals(30, m3.getGrid()[0].length);
    }

    @Test
    public void testWidth(){
        assertEquals(10, m1.getWidth());
        assertEquals(m1.getWidth(),m4.getWidth());
    }
    @Test
    public void testHeight(){
        assertEquals(10, m1.getHeight());
        assertEquals(20, m2.getHeight());
        assertEquals(30, m3.getHeight());
        assertEquals(m4.getHeight(),m1.getHeight());

    }

    @Test
    public void testWallPercentage(){
        // On vérifie que sur 100 labyrinthes, le pourcentage de mur moyen tend vers celui demandé
        // Peu coûteux car le labyrinthe est petit (execution du test sub 0.5sec)
        int totalWalls = 0;
        int totalCells = 0;
        for(int i=0;i<100;i++){
            Maze m = new Maze(20,20,0.4);
            int walls = 0;
            for(int x=1;x<m.getWidth() - 1 ; x++){
                for(int y=1;y<m.getHeight() - 1 ; y++){
                    if(!m.getGrid()[x][y]){
                        walls++;
                    }
                }
            }
            totalWalls += walls;
            // on calcule le total (on ne compte pas les murs sur les côtés !)
            totalCells += (m.getWidth() - 2) * (m.getHeight() - 2);
        }
        double actualPercentage = (double)totalWalls / totalCells;
        assertTrue(Math.abs(actualPercentage - 0.4) < 0.05);
    }

}
