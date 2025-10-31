package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FreeModeAlgorithmSelectionController {
    @FXML
    private ComboBox<MazeAlgorithmFactory> freeModeComboBox;

    @FXML
    private VBox menuBoutons;

    @FXML
    private void initialize() {
        freeModeComboBox.getItems().addAll(MazeAlgorithmFactory.values());
        freeModeComboBox.getSelectionModel().select(0);
        resize();
    }
    @FXML
    private void goToJouer()throws IOException{
        App.goTo("freemode/"+freeModeComboBox.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void goToRetour()throws IOException {
        App.goTo("GameModeSelection.fxml");
    }

    private void resize(){
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizePanesInPane(menuBoutons));
        menuBoutons.heightProperty().addListener((o,oldH,newH) -> ResizeUtil.resizePanesInPane(menuBoutons));
    }
}
