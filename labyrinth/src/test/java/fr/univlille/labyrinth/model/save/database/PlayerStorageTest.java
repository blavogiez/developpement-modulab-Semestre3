package fr.univlille.labyrinth.model.save.database;

import fr.univlille.labyrinth.model.save.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStorageTest {

    @BeforeEach
    void setUp() {
        PlayerStorage.deleteFile();
    }

    @AfterEach
    void tearDown() {
        PlayerStorage.deleteFile();
    }

    @Test
    void shouldReturnEmptyListWhenNoFileExists() {
        List<Player> joueurs = PlayerStorage.readAll();

        assertNotNull(joueurs);
        assertTrue(joueurs.isEmpty());
    }

    @Test
    void shouldWriteAndReadSinglePlayer() {
        Player joueur = new Player("TestPlayer");
        List<Player> joueurs = new ArrayList<>();
        joueurs.add(joueur);

        PlayerStorage.writeAll(joueurs);
        List<Player> joueursCharges = PlayerStorage.readAll();

        assertEquals(1, joueursCharges.size());
        assertEquals("TestPlayer", joueursCharges.get(0).getName());
    }

    @Test
    void shouldWriteAndReadMultiplePlayers() {
        List<Player> joueurs = new ArrayList<>();
        joueurs.add(new Player("Player1"));
        joueurs.add(new Player("Player2"));
        joueurs.add(new Player("Player3"));

        PlayerStorage.writeAll(joueurs);
        List<Player> joueursCharges = PlayerStorage.readAll();

        assertEquals(3, joueursCharges.size());
        assertEquals("Player1", joueursCharges.get(0).getName());
        assertEquals("Player2", joueursCharges.get(1).getName());
        assertEquals("Player3", joueursCharges.get(2).getName());
    }

    @Test
    void shouldHandleEmptyList() {
        List<Player> listeVide = new ArrayList<>();

        PlayerStorage.writeAll(listeVide);
        List<Player> joueursCharges = PlayerStorage.readAll();

        assertNotNull(joueursCharges);
        assertTrue(joueursCharges.isEmpty());
    }

    @Test
    void shouldDeleteFile() {
        List<Player> joueurs = new ArrayList<>();
        joueurs.add(new Player("TestPlayer"));
        PlayerStorage.writeAll(joueurs);

        PlayerStorage.deleteFile();
        List<Player> joueursCharges = PlayerStorage.readAll();

        assertTrue(joueursCharges.isEmpty());
    }

    @Test
    void shouldPreservePlayerProgress() {
        Player joueur = new Player("ProgressPlayer");
        joueur.getProgress().getLevelProgress()[0].getChallenges()[0].setCompleted(true);

        List<Player> joueurs = new ArrayList<>();
        joueurs.add(joueur);

        PlayerStorage.writeAll(joueurs);
        List<Player> joueursCharges = PlayerStorage.readAll();

        // la progression est bien conservée après sauvegarde/chargement
        assertTrue(joueursCharges.get(0).getProgress().getLevelProgress()[0].getChallenges()[0].isCompleted());
    }
}
