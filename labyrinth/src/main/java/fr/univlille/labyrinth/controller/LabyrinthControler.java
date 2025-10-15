package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.GameMode;

/**
 * LabyrinthControler est le controleur de LabyrinthGridView. C'est un controleur.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthControler {

    private final GameMode gameMode;

    /**
     * Cette méthode génère un controler
     *
     * @param gameMode le gameMode qui recevra les ordres de la scène
     */
    public LabyrinthControler(GameMode gameMode){
        this.gameMode=gameMode;
    }

    /**
     * Alerte le GameMode que le joueur veut se déplacer
     *
     * @param direction la direction que le joueur souhaite aller
     */
    public void movePlayer(Direction direction){
        gameMode.movePlayerPosition(direction);
    }

    /**
     * Alerte GameMode que le joueur a réussi le labyrinthe
     */
    public void playerWin() {
        gameMode.playerWin();
    }
}
