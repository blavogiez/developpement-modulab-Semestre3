package fr.univlille.labyrinth.model;

// Pattern observé / observable
// Cette classe servira à faire observer le mode de jeu lors du changement du labyrinthe, ou même de la victoire par des observeurs (Controleurs)

// le vrai objectif est de faire reposer toutes les actions de mouvement, victoire (i.e : compléter le défi, sauvegarder) sur le modèle et non sur le controleur !
// Soit le S de S O L I D

public interface Observer<T> {
    public void update(T type);
}
