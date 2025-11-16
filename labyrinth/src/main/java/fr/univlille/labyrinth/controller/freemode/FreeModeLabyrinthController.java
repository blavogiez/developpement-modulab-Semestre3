package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.controller.PopupVictoryController;
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
    //private TableColumn<Entity, EntityType> colDef;

    private LabyrinthCanvasView labyrinth;

    @Override
    protected void initializeGameMode() {
        colSymbole.getStyleClass().add("Symbole"); //TODO a fix
        //colDef.getStyleClass().add("Def"); //TODO a fix

        colSymbole.setCellValueFactory(new PropertyValueFactory<>("entityType"));//TODO 

        //colDef.setCellValueFactory(new PropertyValueFactory<>("def")); //TODO

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colSymbole.prefWidthProperty().bind(tableView.widthProperty().multiply(1));
        //colDef.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));

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
            PopupVictoryController.openPopup("toi");
            App.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (Exception e) {
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

}
