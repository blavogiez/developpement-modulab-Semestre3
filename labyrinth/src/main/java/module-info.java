module fr.univlille.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens fr.univlille.labyrinth to javafx.fxml;
    exports fr.univlille.labyrinth;
    exports fr.univlille.labyrinth.controller;
    opens fr.univlille.labyrinth.controller to javafx.fxml;
}