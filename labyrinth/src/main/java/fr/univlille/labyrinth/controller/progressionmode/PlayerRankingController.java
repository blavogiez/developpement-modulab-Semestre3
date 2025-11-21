package fr.univlille.labyrinth.controller.progressionmode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerRankingController {

    private final List<Player> ranking= PlayerDatabase.loadAllPlayers();

    @FXML
    private TableView<Player> tableView;

    @FXML
    private TableColumn<Player, String> colName;

    @FXML
    private TableColumn<Player, Integer> colScore;


    @FXML
    public void initialize(){
        colName.getStyleClass().add("nameCol");
        colScore.getStyleClass().add("scoreCol");
        Collections.sort(ranking);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        colScore.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        tableView.setItems(FXCollections.observableArrayList(ranking));
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToLevelSelection() throws IOException {
        App.goTo("progressionmode/LevelSelection.fxml");
    }
    
}
