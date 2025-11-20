package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.controller.VictoryNotification;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.FreeModeVictoryHandler;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.view.labyrinth.LabyrinthCanvasView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller des labyrinthes spécifiquement du mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeModeLabyrinthController extends LabyrinthController<FreeMode> {
    @FXML
    private Button bouttonRetour;

    @FXML
    private TableView<Entity> tableView;

    @FXML
    private TableColumn<Entity, EntityType> colSymbole;

    @FXML
    private TableColumn<Entity, String> colDef;



    @Override
    public void initialize() {
        super.initialize();
        String message = VictoryNotification.getPendingWinner();
        if (message != null) {
            VictoryNotification.show(pane1, message, VictoryNotification.getPendingIsVictory());
        }
    }

    @Override
    protected void initializeGameMode() {
        LabyrinthCanvasView labyrinth;

        colSymbole.getStyleClass().add("Symbole");
        colDef.getStyleClass().add("Def");

        colSymbole.setCellValueFactory(new PropertyValueFactory<>("entityType"));

        colDef.setCellValueFactory(new PropertyValueFactory<>("defType"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colSymbole.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        colDef.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));

        MazeManager mazeManager = new MazeManager();

        

        FreeModeVictoryHandler victoryHandler = new FreeModeVictoryHandler();

        gameMode = new FreeMode(mazeManager, victoryHandler, AppState.getInstance().getFreeModeConfig());
        gameMode.createMaze();

        labyrinth = new LabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getView());
        labyrinth.update(gameMode.getCurrentMaze());

        tableView.setItems(FXCollections.observableArrayList(gameMode.getCurrentMaze().getEntityManager().getEntities()));
    }

    @Override
    public void handleVictory() {
        try {
            String message = "Joueur " + (gameMode.getWinner().getID() + 1);
            VictoryNotification.setPendingWinner(message, true);
            App.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleDefeat() {
        try {
            VictoryNotification.setPendingWinner("Défaite !", false);
            App.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeMode.fxml");
    }

}
