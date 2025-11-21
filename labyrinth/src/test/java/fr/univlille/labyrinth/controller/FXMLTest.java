package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

// cette classe teste l'existence de tous les fichiers FXML utilisés par les méthodes goTo des contrôleurs
// chaque test vérifie qu'un fichier FXML est accessible via Main.class.getResource()

// Nous avons eu des problèmes de nommage de fichiers FXML différents ; cette classe sert désormais à la non régression des nommages / chemins
class FXMLTest {

    @Test
    void testHomeMenuFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/HomeMenu.fxml");
        assertNotNull(resource, "Le fichier HomeMenu.fxml devrait exister");
    }

    @Test
    void testSettingsFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/Settings.fxml");
        assertNotNull(resource, "Le fichier Settings.fxml devrait exister");
    }

    @Test
    void testGameModeSelectionFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/GameModeSelection.fxml");
        assertNotNull(resource, "Le fichier GameModeSelection.fxml devrait exister");
    }

    @Test
    void testFreeModeFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/freemode/FreeMode.fxml");
        assertNotNull(resource, "Le fichier freemode/FreeMode.fxml devrait exister");
    }

    @Test
    void testFreeModeLabyrinthFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/freemode/FreeModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier freemode/FreeModeLabyrinth.fxml devrait exister");
    }

    @Test
    void testPlayerNameEntryFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/progressionmode/PlayerNameEntry.fxml");
        assertNotNull(resource, "Le fichier progressionmode/PlayerNameEntry.fxml devrait exister");
    }

    @Test
    void testExistingProfileFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/progressionmode/ExistingProfile.fxml");
        assertNotNull(resource, "Le fichier progressionmode/ExistingProfile.fxml devrait exister");
    }

    @Test
    void testLevelSelectionFxmlExistsLowercase() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/progressionmode/LevelSelection.fxml");
        assertNotNull(resource, "Le fichier progressionmode/LevelSelection.fxml devrait exister");
    }

    @Test
    void testProgressionModeLabyrinthFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/progressionmode/labyrinthviewtype/NormalViewProgressionModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier progressionmode/ProgressionModeLabyrinth.fxml devrait exister");
    }

    @Test
    void testLimitedViewProgressionModeLabyrinthFxmlExists() {
        URL resource = App.class.getResource("/fr/univlille/labyrinth/progressionmode/labyrinthviewtype/LocalViewProgressionModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier progressionmode/LimitedViewProgressionModeLabyrinth.fxml devrait exister");
    }
}
