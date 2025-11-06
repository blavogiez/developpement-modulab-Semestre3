module fr.univlille.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires jdk.compiler;

    opens fr.univlille.labyrinth to javafx.fxml, org.junit.platform.commons;
    opens fr.univlille.labyrinth.controller to javafx.fxml;
    opens fr.univlille.labyrinth.controller.freemode to javafx.fxml;
    opens fr.univlille.labyrinth.controller.progressionmode to javafx.fxml;
    opens fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype to javafx.fxml;
    exports fr.univlille.labyrinth;
    exports fr.univlille.labyrinth.view;
    exports fr.univlille.labyrinth.model;
    exports fr.univlille.labyrinth.utils;
    exports fr.univlille.labyrinth.model.maze;
    exports fr.univlille.labyrinth.model.gamemode;
    exports fr.univlille.labyrinth.model.save;
    exports fr.univlille.labyrinth.model.algorithm;
}