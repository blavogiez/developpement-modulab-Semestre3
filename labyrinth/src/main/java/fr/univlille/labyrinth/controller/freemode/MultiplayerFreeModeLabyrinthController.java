package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.view.labyrinth.NormalLabyrinthCanvasView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class MultiplayerFreeModeLabyrinthController extends FreeModeLabyrinthController {
    @FXML
    private Button bouttonRetour;

    private NormalLabyrinthCanvasView labyrinth;

    @Override
    protected void initializeGameMode() {
        gameMode = new FreeMode(AppState.getInstance().getFreeModeConfig());
        gameMode.createMaze();

        labyrinth = new NormalLabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getView());
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @Override
    public void handleVictory() {
        try {
            App.goTo("freemode/MultiplayerFreeModeLabyrinth.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeMode.fxml");
    }

    /**
     * Déplace le joueur en fonction de la touche pressée
     */
    @Override
    @FXML
    public void movement(KeyEvent e) throws IOException {
        Direction direction = null;
        KeyCode code = e.getCode();

        if (code == KeyCode.S || code == KeyCode.DOWN) direction = Direction.DOWN;
        else if (code == KeyCode.Z || code == KeyCode.UP) direction = Direction.UP;
        else if (code == KeyCode.Q || code == KeyCode.LEFT) direction = Direction.LEFT;
        else if (code == KeyCode.D || code == KeyCode.RIGHT) direction = Direction.RIGHT;

        if(code==KeyCode.S || code==KeyCode.Z || code==KeyCode.Q || code==KeyCode.D) {
            if (direction != null) {
                gameMode.movePlayerPosition(0, direction);
                e.consume();
            }
        }

        if(code==KeyCode.DOWN || code==KeyCode.UP || code==KeyCode.LEFT || code==KeyCode.RIGHT) {
            if (direction != null) {
                gameMode.movePlayerPosition(1, direction);
                e.consume();
            }
        }



    }
}
