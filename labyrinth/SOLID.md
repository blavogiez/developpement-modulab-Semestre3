# Organisation

Ce fichier répertorie ici toutes les améliorations SOLID possibles

# S

- Créer des exceptions

- + O : Créer une classe static MazeWallChecker avec méthodes static de vérifications de murs (on reprend les méthodes de Maze de vérification et on met en static avec Maze en argument) 

- Encore simplifier GameMode (bouger les check ailleurs, en realite ca peut aller avec MazeWallChecker ?)

# O

# L

# I

- Créer l'interface Observable (simple avec notify) et la faire implémenter par ObservableMaze

- Créer VictoryObserver<T extends GameMode> avec la méthode onVictory(bouger les controlleurs)

# D



# Autre :

- Fix les magic numbers
- Fix les nommages
- Javadoc

- Plus de tests sur la vue / utilitaires (ProgressionLoader par ex)