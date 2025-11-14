package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.factory.EntityConfiguration;
import fr.univlille.labyrinth.model.maze.entities.factory.TrapConfiguration;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import fr.univlille.labyrinth.model.maze.traps.TrapManager;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreeModeEntityConfigurationController {
    @FXML
    private ListView<EntityConfiguration> listView;
    @FXML
    private TextField textField;
    @FXML
    private Button buttonAdd;
    @FXML
    private ComboBox<EntityType> comboBox1;
    @FXML
    private ComboBox<String> comboBox2;
    @FXML
    private Label errorLabel;
    @FXML
    private ListView<TrapConfiguration> listView2;
    @FXML
    private TextField textField2;
    @FXML
    private Button buttonAdd2;
    @FXML
    private ComboBox<TrapFactory> comboBox3;
    @FXML
    private Label errorLabel2;


    private ObservableList<EntityConfiguration> entities;

    private ObservableList<TrapConfiguration> traps;


    @FXML
    public void initialize() {
        initializeEntityMenu();
        initializeTrapMenu();
        String existingConfig = AppState.getInstance().getFreeModeConfig().getEntitiesConfiguration();
        if (existingConfig != null && !existingConfig.isEmpty() && !existingConfig.equals("DEFAULT")) {
            loadConfiguration(existingConfig);
        }
    }

    public void initializeEntityMenu(){
        entities = FXCollections.observableArrayList();
        listView.setItems(entities);
        listView.setCellFactory(lv -> new EntityConfigCell());

        comboBox1.getItems().setAll(EntityType.values());
        comboBox1.getSelectionModel().selectFirst();

        comboBox2.getItems().setAll("PLAYER", "MOVING", "MONSTER", "DEFAULT");
        comboBox2.getSelectionModel().selectFirst();

        textField.setText("1");

        buttonAdd.setOnAction(e -> onAddEntity());
    }

    public void initializeTrapMenu(){
        entities = FXCollections.observableArrayList();
        listView2.setItems(traps);
        listView2.setCellFactory(lv -> new TrapConfigCell());
        comboBox3.getItems().setAll(TrapFactory.values());
        comboBox3.getSelectionModel().selectFirst();

        textField.setText("1");

        buttonAdd.setOnAction(e -> onAddTrap());
    }

    @FXML
    private void onAddEntity() {
        EntityType selectedType = comboBox1.getValue();
        String selectedBehavior = comboBox2.getValue();
        String quantityText = textField.getText();

        if (selectedType == null || selectedBehavior == null || quantityText.isEmpty()) {
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                return;
            }

            EntityConfiguration config = new EntityConfiguration(selectedType, quantity, selectedBehavior);
            entities.add(config);

            textField.setText("1");
        } catch (NumberFormatException ex) {
        }
    }

    private void onAddTrap() {
        TrapFactory selectedType = comboBox3.getValue();
        String quantityText = textField.getText();

        if (selectedType == null || quantityText.isEmpty()) {
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                return;
            }

            TrapConfiguration config = new TrapConfiguration(selectedType,Integer.parseInt(quantityText));
            traps.add(config);

            textField.setText("1");
        } catch (NumberFormatException ex) {
        }
    }


    @FXML
    private void onValidate() throws IOException {
        if (!isValid()) {
            showError();
            return;
        }

        String config = buildConfigurationString();
        AppState.getInstance().getFreeModeConfig().setEntitiesConfiguration(config);
        AppState.getInstance().getFreeModeConfig().setTrapsConfiguration("DEFAULT");
        App.goTo("freemode/FreeMode.fxml");
    }

    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeMode.fxml");
    }

    /*
     * de la liste d'objets de configuration, on créé un String pour être interprété par la Factory d'entités plus tard
     */
    private String buildConfigurationString() {
        if (entities.isEmpty()) {
            return "DEFAULT";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < entities.size(); i++) {
            EntityConfiguration e = entities.get(i);
            result.append(String.format("t=%s;q=%d;m=%s", e.type(), e.quantity(), e.moveBehaviorName()));
            if (i < entities.size() - 1) {
                result.append("|");
            }
        }
        return result.toString();
    }

    /*
     * Un labyrinthe doit avoir au moins 1 joueur, au moins 1 sortie.
     */
    private boolean isValid() {
        boolean hasPlayer = false;
        boolean hasExit = false;

        for (EntityConfiguration e : entities) {
            if (e.type() == EntityType.PLAYER) {
                hasPlayer = true;
            }
            if (e.type() == EntityType.EXIT) {
                hasExit = true;
            }
        }

        return hasPlayer && hasExit;
    }

    private void showError() {
        errorLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> errorLabel.setVisible(false));
        pause.play();
    }

    private void loadConfiguration(String config) {
        String[] parts = config.split("\\|");
        for (String part : parts) {
            String[] params = part.split(";");
            EntityType type = null;
            int quantity = 1;
            String behavior = "DEFAULT";

            for (String param : params) {
                String[] kv = param.split("=");
                if (kv.length == 2) {
                    if (kv[0].equals("t")) {
                        type = EntityType.valueOf(kv[1]);
                    }
                    if (kv[0].equals("q")) {
                        quantity = Integer.parseInt(kv[1]);
                    }
                    if (kv[0].equals("m")) {
                        behavior = kv[1];
                    }
                }
            }

            if (type != null) {
                entities.add(new EntityConfiguration(type, quantity, behavior));
            }
        }
    }

    /*
     * petit objet pour faire apparaître une cellule d'entité de configuratoin
     */
    private class EntityConfigCell extends ListCell<EntityConfiguration> {
        private final HBox content;
        private final Label label;
        private final Button deleteButton;

        public EntityConfigCell() {
            label = new Label();
            deleteButton = new Button("X");
            deleteButton.getStyleClass().add("delete-button");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            content = new HBox(10, label, spacer, deleteButton);
            content.setAlignment(Pos.CENTER_LEFT);

            deleteButton.setOnAction(e -> {
                int index = getIndex();
                if (index >= 0 && index < entities.size()) {
                    entities.remove(index);
                }
            });
        }

        @Override
        protected void updateItem(EntityConfiguration item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                label.setText(String.format("%s x%d (%s)", item.type(), item.quantity(), item.moveBehaviorName()));
                setGraphic(content);
            }
        }
    }

    private class TrapConfigCell extends ListCell<TrapFactory> {
        private final HBox content;
        private final Label label;
        private final Button deleteButton;

        public TrapConfigCell() {
            label = new Label();
            deleteButton = new Button("X");
            deleteButton.getStyleClass().add("delete-button");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            content = new HBox(10, label, spacer, deleteButton);
            content.setAlignment(Pos.CENTER_LEFT);

            deleteButton.setOnAction(e -> {
                int index = getIndex();
                if (index >= 0 && index < entities.size()) {
                    entities.remove(index);
                }
            });
        }

        @Override
        protected void updateItem(TrapConfiguration item, boolean empty) {
            super.updateItem(item.trap(), empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                label.setText(String.format("%s x%d (%s)", item, item.quantity()));
                setGraphic(content);
            }
        }
    }
}
