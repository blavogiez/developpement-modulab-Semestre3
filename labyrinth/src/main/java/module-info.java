module fr.univlille.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.univlille.labyrinth to javafx.fxml;
    exports fr.univlille.labyrinth;
}