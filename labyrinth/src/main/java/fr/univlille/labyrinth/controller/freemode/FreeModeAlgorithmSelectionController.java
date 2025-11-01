package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
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
        freeModeComboBox.getSelectionModel().select(FreeMode.algorithm);
        resize();
    }
    @FXML
    private void goToJouer()throws IOException{
        FreeMode.algorithm=freeModeComboBox.getSelectionModel().getSelectedItem();
        App.goTo("freemode/FreeMode.fxml");
    }
    @FXML
    private void goToRetour()throws IOException {
        App.goTo("GameModeSelection.fxml");
    }

    private void resize(){
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizePanesInPane(menuBoutons));
        menuBoutons.heightProperty().addListener((o,oldH,newH) -> ResizeUtil.resizePanesInPane(menuBoutons));

        for (Node ligne : menuBoutons.getChildren()) {
            if(ligne instanceof Pane pane){
                pane.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsInPane( pane));
                pane.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsInPane( pane));
            }
        }
    }
}
