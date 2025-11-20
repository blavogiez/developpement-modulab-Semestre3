package fr.univlille.labyrinth.controller;

import java.io.IOException;

import fr.univlille.labyrinth.controller.input.PlayerMovementHandler;
import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.Timer;
import fr.univlille.labyrinth.view.labyrinth.legend.LegendPanel;
import fr.univlille.labyrinth.view.utils.TimerFX;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * Classe abstraite générique pour les contrôleurs de labyrinthe
 * Regroupe les fonctionnalités communes comme le mouvement du joueur, les panels d'informations et le chronomètre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class LabyrinthController<T extends GameMode> implements VictoryObserver<GameMode> {
    
    @FXML
    protected BorderPane pane1;

    @FXML
    protected Label chronoLabel;

    @FXML
    private Label mazeInfoLabel;

    protected T gameMode;
    protected Timer chrono;
    protected Timeline chronoTimeline;
    protected PlayerMovementHandler movementHandler;

    private static final double LEGEND_PANEL_WIDTH = 200;
    private static final double LEGEND_PANEL_MARGIN = 30;
    private static final double LEGEND_MIN_SCREEN_RATIO = 0.8;

    protected LegendPanel leftLegendPanel;
    protected LegendPanel rightLegendPanel;
    protected HBox centerContainer;

    /**
     * Méthode d'initialisation commune à tous les contrôleurs de labyrinthe
     */
    @FXML
    public void initialize() {
        chrono = new Timer();
        chrono.start();
        chronoTimeline = TimerFX.initChrono(chrono, chronoLabel);
        movementHandler = new PlayerMovementHandler();
        initializeGameMode();
        
        mazeInfoLabel.setText(gameMode.toString());
        
        pane1.requestFocus();
        pane1.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            try {
                movement(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        gameMode.addVictoryObserver(this);
    }


    /**
     * Méthode abstraite pour initialiser le mode de jeu spécifique
     * 
     * @param e
     * @throws IOException
     */
    protected abstract void initializeGameMode();

    /** 
     * @param e
     * @throws IOException
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        movementHandler.handleMovement(e, gameMode);
    }

    /**
     * Arrête le chronomètre
     */
    protected void stopChrono() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
    }

    @Override
    public void onVictory() {
        stopChrono();
        handleVictory();
    }

    /** 
     * @param observable
     */
    @Override
    public void onDefeat(GameMode observable) {
        stopChrono();
        handleDefeat();
    }

    public abstract void handleVictory();
    public abstract void handleDefeat();

    /*
    Setup de panneaux de légende pour indiquer les entités et pièges présents dans le labyrinthe
    Le centerContent en argument sera les vues injectées par les classes enfants de LabyrinthController
    */
    protected void setupLegendPanels(Node centerContent) {
        leftLegendPanel = new LegendPanel("Entities", LegendPanel.LegendType.ENTITIES);
        rightLegendPanel = new LegendPanel("Traps", LegendPanel.LegendType.TRAPS);

        StackPane centerWrapper = createCenterWrapper(centerContent);
        centerContainer = createCenterContainer(centerWrapper);

        configureLegendPanels();
        registerLegendObservers();
        pane1.setCenter(centerContainer);

        bindLegendVisibility();
    }

    private StackPane createCenterWrapper(Node centerContent) {
        StackPane centerWrapper = new StackPane(centerContent);
        centerWrapper.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        centerWrapper.setMinWidth(0);
        centerWrapper.maxWidthProperty().bind(
            Bindings.max(0, pane1.widthProperty().subtract(2 * (LEGEND_PANEL_WIDTH + LEGEND_PANEL_MARGIN)))
        );
        HBox.setHgrow(centerWrapper, Priority.ALWAYS);
        return centerWrapper;
    }

    private HBox createCenterContainer(StackPane centerWrapper) {
        HBox container = new HBox(LEGEND_PANEL_MARGIN);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(leftLegendPanel, centerWrapper, rightLegendPanel);
        BorderPane.setAlignment(container, Pos.CENTER);
        return container;
    }

    private void configureLegendPanels() {
        leftLegendPanel.setMaxWidth(LEGEND_PANEL_WIDTH);
        rightLegendPanel.setMaxWidth(LEGEND_PANEL_WIDTH);
        leftLegendPanel.setPrefWidth(LEGEND_PANEL_WIDTH);
        rightLegendPanel.setPrefWidth(LEGEND_PANEL_WIDTH);
        leftLegendPanel.setMinWidth(LEGEND_PANEL_WIDTH);
        rightLegendPanel.setMinWidth(LEGEND_PANEL_WIDTH);
        rightLegendPanel.setTranslateX(-LEGEND_PANEL_MARGIN);

        BorderPane.setMargin(leftLegendPanel, new Insets(0, LEGEND_PANEL_MARGIN, 0, 0));
        BorderPane.setMargin(rightLegendPanel, new Insets(0, 0, 0, LEGEND_PANEL_MARGIN));
    }

    private void registerLegendObservers() {
        gameMode.getCurrentMaze().add(leftLegendPanel);
        gameMode.getCurrentMaze().add(rightLegendPanel);

        leftLegendPanel.update(gameMode.getCurrentMaze());
        rightLegendPanel.update(gameMode.getCurrentMaze());
    }

    /*
    Responsive : cache les panels en dessous de 80% de taille d'écran occupée 
     */
    private void bindLegendVisibility() {
        var bounds = Screen.getPrimary().getVisualBounds();
        double minWidth = bounds.getWidth() * LEGEND_MIN_SCREEN_RATIO;
        double minHeight = bounds.getHeight() * LEGEND_MIN_SCREEN_RATIO;

        pane1.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                updateLegendVisibility(newScene.getWidth(), newScene.getHeight(), minWidth, minHeight);
                newScene.widthProperty().addListener((o, ov, nv) -> updateLegendVisibility(nv.doubleValue(), newScene.getHeight(), minWidth, minHeight));
                newScene.heightProperty().addListener((o, ov, nv) -> updateLegendVisibility(newScene.getWidth(), nv.doubleValue(), minWidth, minHeight));
            }
        });
    }

    private void updateLegendVisibility(double width, double height, double minWidth, double minHeight) {
        boolean show = width >= minWidth && height >= minHeight;
        leftLegendPanel.setVisible(show);
        rightLegendPanel.setVisible(show);
        leftLegendPanel.setManaged(show);
        rightLegendPanel.setManaged(show);
    }
}
