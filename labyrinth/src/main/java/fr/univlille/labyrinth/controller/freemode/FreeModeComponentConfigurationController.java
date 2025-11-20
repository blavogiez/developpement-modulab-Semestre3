package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.maze.MazeThreats;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.factory.EntityConfiguration;
import fr.univlille.labyrinth.model.maze.traps.TrapConfig;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public class FreeModeComponentConfigurationController {
    private static final int MAX_MONSTERS = 5;
    private static final int MAX_PLAYERS = 3;
    private static final String DEFAULT = "DEFAULT";

    @FXML
    private ListView<EntityConfiguration> listView;
    @FXML
    private TextField textField;
    @FXML
    private Button buttonAdd;
    @FXML
    private ComboBox<EntityType> comboBox1;
    @FXML
    private Label errorLabel;
    @FXML
    private ListView<TrapConfig> trapListView;
    @FXML
    private TextField trapTextField;
    @FXML
    private Button buttonAddTrap;
    @FXML
    private ComboBox<TrapFactory> trapComboBox;

    private ObservableList<EntityConfiguration> entities;
    private ObservableList<TrapConfig> traps;

    @FXML
    public void initialize() {
        initializeEntitySection();
        initializeTrapSection();
    }

    private void initializeEntitySection() {
        entities = FXCollections.observableArrayList();
        listView.setItems(entities);
        listView.setCellFactory(lv -> new MazeThreatsListCellController<>(entities));

        comboBox1.getItems().setAll(EntityType.values());
        comboBox1.getSelectionModel().selectFirst();
        textField.setText("1");
        buttonAdd.setOnAction(e -> onAddEntity());

        String existingConfig = AppState.getInstance().getFreeModeConfig().getEntitiesConfiguration();
        if (existingConfig != null && !existingConfig.isEmpty() && !existingConfig.equals(DEFAULT)) {
            loadEntityConfiguration(existingConfig);
        } else {
            addDefaultEntities();
        }
    }

    /*
     * tous les pièges sauf none / used 
     */
    private void initializeTrapSection() {
        traps = FXCollections.observableArrayList();
        trapListView.setItems(traps);
        trapListView.setCellFactory(lv -> new MazeThreatsListCellController<>(traps));

        trapComboBox.getItems().setAll(Arrays.stream(TrapFactory.values())
                .filter(t -> !t.getCompactedName().isEmpty() && t != TrapFactory.NONE && t != TrapFactory.USED)
                .toArray(TrapFactory[]::new));
        if (!trapComboBox.getItems().isEmpty()) {
            trapComboBox.getSelectionModel().selectFirst();
        }

        trapTextField.setText("1");
        buttonAddTrap.setOnAction(e -> onAddTrap());

        String existingTrapConfig = AppState.getInstance().getFreeModeConfig().getTrapsConfiguration();
        if (existingTrapConfig != null && !existingTrapConfig.isEmpty() && !existingTrapConfig.equals(DEFAULT)) {
            loadTrapConfiguration(existingTrapConfig);
        }
    }

    @FXML
    private void onAddEntity() {
        EntityType selectedType = comboBox1.getValue();
        if (selectedType == null) return;

        try {
            int quantity = Integer.parseInt(textField.getText());
            if (quantity <= 0) return;

            int currentTotal = getTotalQuantityByType(selectedType);
            int maxAllowed = getMaxAllowed(selectedType);

            if (maxAllowed > 0 && currentTotal >= maxAllowed) {
                errorLabel.setText("Maximum " + maxAllowed + " " + selectedType.name().toLowerCase() + "(s) autorisé(s)");
                showError();
                return;
            }

            if (maxAllowed > 0 && currentTotal + quantity > maxAllowed) {
                quantity = maxAllowed - currentTotal;
            }

            EntityConfiguration existing =(EntityConfiguration) findMazeThreatConfigurationByType(selectedType, entities);

            if (existing != null) {
                int index = entities.indexOf(existing);
                int newQuantity = existing.quantity() + quantity;
                entities.set(index, new EntityConfiguration(
                    selectedType,
                    newQuantity,
                    existing.moveBehaviorName()
                ));
            } else {
                entities.add(new EntityConfiguration(selectedType, quantity, DEFAULT));
            }
            textField.setText("1");
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void onAddTrap() {
        TrapFactory selectedTrap = trapComboBox.getValue();
        if (selectedTrap == null) return;

        try {
            int quantity = Integer.parseInt(trapTextField.getText());
            if (quantity <= 0) return;

            TrapConfig existing =(TrapConfig) findMazeThreatConfigurationByType(selectedTrap, traps);

            if (existing != null) {
                int index = traps.indexOf(existing);
                int newQuantity = existing.quantity() + quantity;
                traps.set(index, new TrapConfig(selectedTrap, newQuantity));
            } else {
                traps.add(new TrapConfig(selectedTrap, quantity));
            }
            trapTextField.setText("1");
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void onValidate() throws IOException {
        if (!isValid()) {
            showError();
            return;
        }

        AppState.getInstance().getFreeModeConfig().setEntitiesConfiguration(buildConfigurationString());
        AppState.getInstance().getFreeModeConfig().setTrapsConfiguration(buildTrapConfigurationString());
        App.goTo("freemode/FreeMode.fxml");
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeMode.fxml");
    }

    /** 
     * @return String
     */
    /*
     * joiner utile pour compacter code
     */
    private String buildConfigurationString() {
        if (entities.isEmpty()) return DEFAULT;

        StringJoiner joiner = new StringJoiner("|");
        for (EntityConfiguration e : entities) {
            joiner.add(String.format("t=%s;q=%d;m=%s", e.type(), e.quantity(), e.moveBehaviorName()));
        }
        return joiner.toString();
    }

    /** 
     * @return String
     */
    private String buildTrapConfigurationString() {
        if (traps.isEmpty()) return DEFAULT;

        StringJoiner joiner = new StringJoiner("_");
        for (TrapConfig t : traps) {
            joiner.add(t.getType().getCompactedName() + t.quantity());
        }
        return joiner.toString();
    }

    /** 
     * @return boolean
     */
    /*
     * au moins 1 joueur / 1 sortie
     */
    private boolean isValid() {
        boolean hasPlayer = entities.stream().anyMatch(e -> e.getType() == EntityType.PLAYER);
        boolean hasExit = entities.stream().anyMatch(e -> e.getType() == EntityType.EXIT);
        return hasPlayer && hasExit;
    }

    /*
     * avertissement si non valid 
     */
    private void showError() {
        errorLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            errorLabel.setVisible(false);
            errorLabel.setText("Au moins 1 joueur et 1 sortie requis");
        });
        pause.play();
    }

    /** 
     * @param type
     * @return int
     */
    private int getTotalQuantityByType(EntityType type) {
        return entities.stream()
                .filter(e -> e.getType() == type)
                .mapToInt(EntityConfiguration::quantity)
                .sum();
    }

    /** 
     * @param type
     * @return int
     */
    private int getMaxAllowed(EntityType type) {
        return switch (type) {
            case MONSTER -> MAX_MONSTERS;
            case PLAYER -> MAX_PLAYERS;
            default -> 0;
        };
    }

    /** 
     * @param type
     * @return EntityConfiguration
     */
    private MazeThreatConfiguration findMazeThreatConfigurationByType(MazeThreats type, ObservableList<? extends MazeThreatConfiguration> list) {
        return list.stream()
                .filter(e -> e.getType() == type)
                .findFirst()
                .orElse(null);
    }

    private void addDefaultEntities() {
        entities.add(new EntityConfiguration(EntityType.PLAYER, 1, DEFAULT));
        entities.add(new EntityConfiguration(EntityType.EXIT, 1, DEFAULT));
    }

    /** 
     * @param config
     */
    private void loadEntityConfiguration(String config) {
        String[] parts = config.split("\\|");
        for (String part : parts) {
            String[] params = part.split(";");
            EntityType type = null;
            int quantity = 1;

            EntityData entityData = getEntityData(params, type, quantity, DEFAULT);

            if (entityData.type() != null) {
                entities.add(new EntityConfiguration(entityData.type(), entityData.quantity(), entityData.behavior()));
            }
        }
    }

    /**
     * @param config
     */
    private void loadTrapConfiguration(String config) {
        String[] parts = config.split("_");
        for (String part : parts) {
            String compactedName = part.replaceAll("\\d+$", "");
            String quantityStr = part.replaceAll("^\\D+", "");

            try {
                TrapFactory type = TrapFactory.compactedValueOf(compactedName);
                int quantity = Integer.parseInt(quantityStr);
                traps.add(new TrapConfig(type, quantity));
            } catch (Exception ignored) {
                //Do nothing
            }
        }
    }

    private static EntityData getEntityData(String[] params, EntityType type, int quantity, String behavior) {
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
        return new EntityData(type, quantity, behavior);
    }

    private record EntityData(EntityType type, int quantity, String behavior) {
    }


}
