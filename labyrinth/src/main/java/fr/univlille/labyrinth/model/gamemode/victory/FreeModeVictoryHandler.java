package fr.univlille.labyrinth.model.gamemode.victory;

import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public class FreeModeVictoryHandler implements VictoryHandler {

    /** 
     * @param winner
     */
    @Override
    public void handleVictory(PlayerEntity winner) {
        /*Since the controller execute a new page, handle Victory do nothing*/
    }

    @Override
    public void handleLoose() {
        /*Since the controller execute a new page, handle Loose do nothing*/
    }
}
