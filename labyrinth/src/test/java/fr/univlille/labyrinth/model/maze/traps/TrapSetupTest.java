package fr.univlille.labyrinth.model.maze.traps;


import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;
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
        map2.put(TrapFactory.HIDE_WALL_TRAP,10);
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
        TrapSetup.generate(new Maze(10,10,10),"R5_F3_P10_L2_S3");
        assertEquals(map1, TrapSetup.trapMap);
        TrapSetup.generate(new Maze(10,10,10),"T5_TE3_H10_L2_S3");
        assertEquals(map2,TrapSetup.trapMap);
        TrapSetup.generate(new Maze(10,10,10),"SGL18");
        assertEquals(map4,TrapSetup.trapMap);
        TrapSetup.generate(new Maze(10,10,10),"S5_T3_P10_TE2_L3");
        assertEquals(map3,TrapSetup.trapMap);
        TrapSetup.generate(new Maze(10,10,10),"S1A8");
        assertEquals(map4,TrapSetup.trapMap);
        TrapSetup.generate(new Maze(10,10,10),"");
        assertEquals(new EnumMap<>(TrapFactory.class),TrapSetup.trapMap);
    }
}
