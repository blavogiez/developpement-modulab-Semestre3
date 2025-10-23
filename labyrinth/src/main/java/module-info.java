module fr.univlille.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports fr.univlille.labyrinth.view;

    opens fr.univlille.labyrinth to javafx.fxml, org.junit.platform.commons;
    opens fr.univlille.labyrinth.controller to javafx.fxml;
    exports fr.univlille.labyrinth;
    exports fr.univlille.labyrinth.model;
    exports fr.univlille.labyrinth.utils;
    exports fr.univlille.labyrinth.model.maze;
    exports fr.univlille.labyrinth.model.gamemode;
    exports fr.univlille.labyrinth.model.save;
}