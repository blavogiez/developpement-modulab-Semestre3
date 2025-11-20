package fr.univlille.labyrinth.controller.freemode;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class MazeThreatsListCellController<T extends MazeThreatConfiguration> extends ListCell<T> {
    private final HBox content;
    private final Label label;

    public MazeThreatsListCellController(ObservableList<T> components) {
        label = new Label();
        Button deleteButton = new Button("X");
        deleteButton.getStyleClass().add("delete-button");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        content = new HBox(10, label, spacer, deleteButton);
        content.setAlignment(Pos.CENTER_LEFT);

        deleteButton.setOnAction(e -> {
            int index = getIndex();
            if (index >= 0 && index < components.size()) {
                components.remove(index);
            }
        });
    }

    @Override
    protected void updateItem(T item, boolean empty) {

        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            label.setText(String.format("%s x%d", item.type(), item.quantity()));
            setGraphic(content);
        }
    }
}
