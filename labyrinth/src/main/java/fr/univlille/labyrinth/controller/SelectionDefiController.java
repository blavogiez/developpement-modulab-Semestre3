package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionDefiController {
    @FXML
    private Button buttonEtape1Defi1;
    @FXML
    private Button buttonEtape1Defi2;
    @FXML
    private Button buttonEtape1Defi3;


    @FXML
    private Button buttonEtape2Defi1;
    @FXML
    private Button buttonEtape2Defi2;
    @FXML
    private Button buttonEtape2Defi3;


    @FXML
    private Button buttonEtape3Defi1;
    @FXML
    private Button buttonEtape3Defi2;
    @FXML
    private Button buttonEtape3Defi3;

    @FXML
    private Button boutonRetour;

    @FXML
    private void goTo() throws IOException {
        HelloApplication.goTo("ModeLibre.fxml");
    }

    @FXML
    private void goToAccueil() throws IOException {
        HelloApplication.goTo("AccueilLabyrinth.fxml");
    }

    // private Challenge getCorrespondingChallenge(int levelIndex, int challengeIndex) {
    //     return HelloApplication.getPlayer().getProgress().getStageProgress()[levelIndex].getChallenges()[challengeIndex];
    // }
}