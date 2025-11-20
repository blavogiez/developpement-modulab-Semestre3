package fr.univlille.labyrinth.model.maze.traps;


import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.traps.trap.NoneTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.RandomTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
import fr.univlille.labyrinth.model.maze.traps.trap.UsedTrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class TrapSetupTest {
    Map<TrapFactory, Integer> map1;
    Map<TrapFactory, Integer> map2;
    Map<TrapFactory, Integer> map3;
    Map<TrapFactory, Integer> map4;


    @BeforeEach
    public void init(){
        map1 = new EnumMap<>(TrapFactory.class);
        map1.put(TrapFactory.RANDOM_TRAP,5);
        map1.put(TrapFactory.FAKE_EXIT_TRAP,3);
        map1.put(TrapFactory.PUSH_TRAP,10);
        map1.put(TrapFactory.LAVA_TRAP,2);
        map1.put(TrapFactory.STUN_TRAP,3);


        map2 = new EnumMap<>(TrapFactory.class);
        map2.put(TrapFactory.TELEPORTER_TRAP,5);
        map2.put(TrapFactory.TELEPORT_EXIT_TRAP,3);
        map2.put(TrapFactory.LAVA_TRAP,2);
        map2.put(TrapFactory.STUN_TRAP,3);

        map3 = new EnumMap<>(TrapFactory.class);
        map3.put(TrapFactory.STUN_TRAP,5);
        map3.put(TrapFactory.TELEPORTER_TRAP,3);
        map3.put(TrapFactory.PUSH_TRAP,10);
        map3.put(TrapFactory.TELEPORT_EXIT_TRAP,2);
        map3.put(TrapFactory.LAVA_TRAP,3);

        map4 = new EnumMap<>(TrapFactory.class);
        map4.put(TrapFactory.NONE, 0);


    }

    @Test
    public void generateTrapsWithStringTest(){
        TrapSetup trapSetup = TrapSetup.getInstance();
        trapSetup.generate(new ObservableMaze(10,10,10),"R5_F3_P10_L2_S3");
        assertEquals(map1, trapSetup.trapMap);
        trapSetup.generate(new ObservableMaze(10,10,10),"T5_TE3_L2_S3");
        assertEquals(map2,trapSetup.trapMap);
        trapSetup.generate(new ObservableMaze(10,10,10),"SGL18");
        assertEquals(map4,trapSetup.trapMap);
        trapSetup.generate(new ObservableMaze(10,10,10),"S5_T3_P10_TE2_L3");
        assertEquals(map3,trapSetup.trapMap);
        trapSetup.generate(new ObservableMaze(10,10,10),"S1A8");
        assertEquals(map4,trapSetup.trapMap);
        trapSetup.generate(new ObservableMaze(10,10,10),"");
        assertEquals(new EnumMap<>(TrapFactory.class),trapSetup.trapMap);
    }

    @Test
    public void trap_setup_random_should_generate_one_real_random_trap() {
        for (int i = 0;i<1000;i++){
        ObservableMaze maze = new ObservableMaze(2, 2, 2,"DEFAULT", MazeAlgorithmFactory.PERFECT.getAlgorithm(),"R1");
        Trap[][] traps = maze.getTrapManager().getTraps();

        int noneCount = 0;
        int realTrapCount = 0;

            for (int y = 0; y < traps.length; y++) {
                for (int x = 0; x < traps[y].length; x++) {

                    Trap t = traps[y][x];

                    if (t instanceof NoneTrap) {
                        noneCount++;
                    }
                    else if (
                            !(t instanceof UsedTrap) && !(t instanceof RandomTrap)
                    ) {
                        realTrapCount++;
                    }
                }
            }

            Assertions.assertEquals(3, noneCount,
                    "Il doit y avoir exactement 3 NoneTrap dans une grille 2x2.");

            Assertions.assertEquals(1, realTrapCount,
                    "Il doit y avoir exactement 1 trap généré aléatoirement, " +
                            "ni NoneTrap, ni UsedTrap, ni RandomTrap.");
        }

    }


}
