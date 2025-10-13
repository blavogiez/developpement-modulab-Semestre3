module fr.univlille.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.univlille.labyrinth to javafx.fxml, org.junit.platform.commons;
    exports fr.univlille.labyrinth;
    exports fr.univlille.labyrinth.model;
}