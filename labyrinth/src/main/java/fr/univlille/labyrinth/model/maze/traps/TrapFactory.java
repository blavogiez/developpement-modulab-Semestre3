package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.traps.trap.*;

public enum TrapFactory {
    NONE,
    RANDOM_TRAP{

    },
    USED{
        @Override
        public Trap generateTrap() {
            return new UsedTrap();
        }
    },
    TELEPORTER_TRAP {
        @Override
        public Trap generateTrap() {
            return new TeleportTrap();
        }
    },
    FAKE_EXIT_TRAP{
        @Override
        public Trap generateTrap() {
            return new FakeTrap();
        }
    },
    PUSH_TRAP {
        @Override
        public Trap generateTrap() {
            return new PushTrap();
        }
    },
    STUN_TRAP {
        @Override
        public Trap generateTrap() {
            return new StunTrap();
        }
    },
    HIDE_WALL_TRAP {
        @Override
        public Trap generateTrap() {
            return new HideWallTrap();
        }
    },
    TELEPORT_EXIT_TRAP{
        @Override
        public Trap generateTrap() {
            return new TeleportExitTrap();
        }
    },
    LAVA_TRAP{
        @Override
        public Trap generateTrap() {
            return new LavaTrap();
        }
    };

    public Trap generateTrap(){
        return new NoneTrap();
    }



}
