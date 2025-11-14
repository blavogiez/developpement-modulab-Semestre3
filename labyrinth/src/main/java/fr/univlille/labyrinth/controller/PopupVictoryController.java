package fr.univlille.labyrinth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupVictoryController {

    @FXML
    private Label winnerLabel;

    @FXML
    public void closePopup(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
        stage.close();
    }

    @FXML
    public static void openPopup(String winner) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                PopupVictoryController.class.getResource("/fr/univlille/labyrinth/PopUpVictory.fxml"));
        AnchorPane pane = loader.load();

        PopupVictoryController controller = loader.getController();
        controller.setWinnerName(winner);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public void setWinnerName(String name) {
        winnerLabel.setText("Le gagnant est : " + name);
    }
}