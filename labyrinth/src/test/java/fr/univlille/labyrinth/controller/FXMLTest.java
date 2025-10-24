package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

// cette classe teste l'existence de tous les fichiers FXML utilisés par les méthodes goTo des contrôleurs
// chaque test vérifie qu'un fichier FXML est accessible via Main.class.getResource()

// Nous avons eu des problèmes de FXML ; cette classe sert désormais à la non régression des FXML !
public class FXMLTest {

    @Test
    public void testHomeMenuFxmlExists() {
        // Utilisé par: HomeMenuController.goToQuitter(), SettingsController.goToAccueil()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/HomeMenu.fxml");
        assertNotNull(resource, "Le fichier HomeMenu.fxml devrait exister");
    }

    @Test
    public void testSettingsFxmlExists() {
        // Utilisé par: HomeMenuController.goToParametres()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/Settings.fxml");
        assertNotNull(resource, "Le fichier Settings.fxml devrait exister");
    }

    @Test
    public void testGameModeSelectionFxmlExists() {
        // Utilisé par: HomeMenuController.goToJouer(), GameModeSelectionController.goToQuitter()
        // FreeModeLabyrinthController.goToAccueil(), FreeModeController.goToAccueil()
        // PlayerNameEntryController.goToAccueil(), LevelSelectionController.goToAccueil()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/GameModeSelection.fxml");
        assertNotNull(resource, "Le fichier GameModeSelection.fxml devrait exister");
    }

    @Test
    public void testFreeModeFxmlExists() {
        // Utilisé par: GameModeSelectionController.goToModeLibre()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/freemode/FreeMode.fxml");
        assertNotNull(resource, "Le fichier freemode/FreeMode.fxml devrait exister");
    }

    @Test
    public void testFreeModeLabyrinthFxmlExists() {
        // Utilisé par: FreeModeLabyrinthController.update(), FreeModeController.goToModeLaby()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/freemode/FreeModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier freemode/FreeModeLabyrinth.fxml devrait exister");
    }

    @Test
    public void testPlayerNameEntryFxmlExists() {
        // Utilisé par: GameModeSelectionController.goToModeProgression()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/PlayerNameEntry.fxml");
        assertNotNull(resource, "Le fichier progressionmode/PlayerNameEntry.fxml devrait exister");
    }

    @Test
    public void testPlayerNameEntryFxmlExistsWithCapitalM() {
        // Utilisé par: ExistingProfileController.goToProgressionEntreNom()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/PlayerNameEntry.fxml");
        assertNotNull(resource, "Le fichier progressionmode/PlayerNameEntry.fxml devrait exister");
    }

    @Test
    public void testExistingProfileFxmlExists() {
        // Utilisé par: PlayerNameEntryController.goToProgression() (si joueur existe)
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/ExistingProfile.fxml");
        assertNotNull(resource, "Le fichier progressionmode/ExistingProfile.fxml devrait exister");
    }

    @Test
    public void testLevelSelectionFxmlExistsLowercase() {
        // Utilisé par: ExistingProfileController.goToNewProgression(), AbstractProgressionModeLabyrinthController.goToProgression()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/LevelSelection.fxml");
        assertNotNull(resource, "Le fichier progressionmode/LevelSelection.fxml devrait exister");
    }

    @Test
    public void testLevelSelectionFxmlExistsWithCapitalM() {
        // Utilisé par: PlayerNameEntryController.goToProgression() (si joueur n'existe pas)
        // ExistingProfileController.goToProgression()
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/LevelSelection.fxml");
        assertNotNull(resource, "Le fichier progressionmode/LevelSelection.fxml devrait exister");
    }

    @Test
    public void testProgressionModeLabyrinthFxmlExists() {
        // Utilisé par: LevelSelectionController.goToChallenge() (mode normal)
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/ProgressionModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier progressionmode/ProgressionModeLabyrinth.fxml devrait exister");
    }

    @Test
    public void testLimitedViewProgressionModeLabyrinthFxmlExists() {
        // Utilisé par: LevelSelectionController.goToChallenge() (mode vue limitée)
        URL resource = Main.class.getResource("/fr/univlille/labyrinth/progressionmode/LimitedViewProgressionModeLabyrinth.fxml");
        assertNotNull(resource, "Le fichier progressionmode/LimitedViewProgressionModeLabyrinth.fxml devrait exister");
    }
}
