package fr.univlille.labyrinth.model.maze;

import fr.univlille.labyrinth.model.algorithm.Trap;

public class TrapMaze extends PlayerMaze {

    public TrapMaze(int width, int height, int distanceBetweenEntryAndExit) {
        super(width, height, distanceBetweenEntryAndExit);
    }


    //TODO à mettre dans un runnable dans leurs enums si possible évitant un switch
    @Override
    public void trapEffect(Position playerPosition) {
        Trap trap = grid[entryPosition.getY()][entryPosition.getX()];
        switch (trap) {
            case PUSH -> {
                Direction direction = Direction.getDirection(playerPosition, this.playerPosition);
                boolean moving;
                do {
                    moving = movePlayer(direction);
                } while (moving);
                revealTrap(playerPosition);
            }
            case FAKE -> {
                revealTrap(playerPosition);
            }
            case STUN ->  {
                //besoin des codes, execute PvETurn()
            }
            case TELEPORTER -> {

            }
        }
    }

    private void revealTrap(Position playerPosition) {
        this.grid[playerPosition.getY()][playerPosition.getX()] = Trap.USED;
    }


}
