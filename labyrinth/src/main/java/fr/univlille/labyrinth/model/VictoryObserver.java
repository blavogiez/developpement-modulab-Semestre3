package fr.univlille.labyrinth.model;

// Pattern observé / observable
// Cette classe servira à faire observer le mode de jeu lors de la victoire par des observeurs (Controleurs)
// Classe distincte car il ne s'agit pas d'un "update" en continu ; il s'agit d'une action déclenchée en une seule fois

// le vrai objectif est de faire reposer toutes les actions de victoire (i.e : compléter le défi, sauvegarder) sur le modèle et non sur le controleur !
public interface VictoryObserver {
    void onVictory();
}
