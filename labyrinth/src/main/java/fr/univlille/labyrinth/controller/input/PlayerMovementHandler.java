package fr.univlille.labyrinth.controller.input;

import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.maze.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
 * Classe spécifique pour gérer le mouvement d'une touche, utilisant la classe InputConfig.
 */
public class PlayerMovementHandler {

    /** 
     * @param event
     * @param gameMode
     */
    /*
     * Pour une touche pressée, cherche le joueur associé.
     * Puis, si il y a correspondance, le joueur est déplacé dans le mode de jeu passé en paramètre.
     */
    public void handleMovement(KeyEvent event, GameMode gameMode) {
        KeyCode keyCode = event.getCode();

        if (PlayerInputConfig.isValidKey(keyCode)) {
            Integer playerId = PlayerInputConfig.getPlayerIdForKey(keyCode);
            Direction direction = PlayerInputConfig.getDirectionForKey(keyCode);

            if (playerId != null && direction != null) {
                gameMode.movePlayerPosition(playerId, direction);
                event.consume();
            }
        }
    }
}
