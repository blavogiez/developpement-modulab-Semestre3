# Rapport : UML & Choix de Conception

L'architecture de l'application se compose sous la forme suivante, en plusieurs diagrammes à granularités différentes.

Les choix de conception seront abstraits et évoquent la logique générale plutôt que celle des élements ciblés.

## Navigation de l'application

[Diagramme de l'application](assets/uml_app_navigation.png)

### Choix de conception

Le package `app` sert à naviguer dans l'application ; il s'agit de manipuler les paramètres et de changer de scène. Puisque nous avons une vidéo de fond, un `BackgroundManager` existe. D'un autre côté, la classe `App` est le point d'entrée de l'application pour **Maven**.

## Controllers
[Diagramme des controllers](assets/uml_controllers.png)

### Clarifications

Notre application utilise, pour toutes ses vues, des pages FXML, stockées dans le dossier `ressources`. Le nommage se doit alors d'être constant ; la classe de controlleur a le même nom + `Controlleur` que sa page FXML référencée. Cela permet de rendre le code plus maintenable.

### Choix de conception

La logique **SOLID** et particulièrement le **DRY** implique ici une abstraction ; les différents controlleurs d'étapes, responsables de l'initialisation des parties du modèle, de la vue et de leur liaison, réalisent beaucoup de comportements communs. La classe `LabyrinthController` est parent de tous les controlleurs de labyrinthe. De ce fait, il est très simple d'ajouter une nouvelle vue ; on peut noter, par exemple, que des controlleurs comme `ExplorationViewProgressionModeLabyrinthController` font moins de 20 lignes de code ; en l'occurrence, ce controleur s'occupe uniquement de *Override* la méthode `setupView`, dont le retour est mis dans le conteneur `StackPane`

### Rapport avec le MVC

Les `LabyrinthController` sont des `VictoryObserver<GameMode>` qui, lorsque notifiés par le `GameMode` qu'un joueur est sur une sortie, invoque leur méthode `handleVictory`. Il en est de même pour la logique de défaite (plus aucun joueur restant), invoquant `handleDefeat`.

Ces méthodes peuvent être surchargées : par exemple, lors de la victoire, un `ProgressionModeLabyrinthController` redirige vers la sélection de niveaux, tandis qu'un `FreeModeLabyrinthController` redirige vers un nouveau niveau (donc sa propre page). Le désabonnement se réalise alors par le *Garbage Collector*, puisque l'ancienne page n'est plus référencée. 

C'est là l'intérêt d'avoir conceptualisé par l'abstraction ; décider d'implémentations, de comportements différents plus facilement grâce à la modularité acquise.

## Couverture totale
[Diagramme de toutes les classes](assets/uml_granularite_complete.png)

Les relations sont ici entièrement visibles, si l'on navigue bien à l'intérieur.

### Choix de conception

Les choix sont identiques aux sections précedemment décrites ; le seul choix dont l'on peut ici parler réside dans les packages. Nous avons privilégié cette approche afin de bien séparer les classes et de les réunifier en package lorsque leurs responsabilités sont similaires.

## Persistence, configuration
[Diagramme des persistences et configurations](assets/uml_persistence.png)

### Clarifications

### Choix de conception

Il a été fait le choix de la sérialisation de la classe `Player`, contenant des `Challenge` (défi). Un défi contient donc toutes les caractéristiques du labyrinthe qui lui sera correspondant. Puisqu'il contient un algorithme de labyrinthe, il sera stocké une valeur `String` qui sera convertie dans la Factory.

## Vue et rendu
[Diagramme de la vue et du rendu](assets/uml_vue_rendu.png)

### Choix de conception

La logique **SOLID** et particulièrement le **DRY** implique, à la manière des controlleurs, une abstraction ; les différentes vues sont en réalité toutes des `Canvas`.
Puisque notre application utilise des pages FXML, nos vues, à proprement parler en fichier java sont alors des parties intégrées à la page, par leur controlleur (nommage identique + `Controlleur`). 



### Rapport avec le MVC

Une vue enfant de `LabyrinthCanvasView` retourne donc un objet `Canvas` observant le modèle. Cet objet est intégré par le controlleur dans son conteneur `StackPane`. Les vues sont donc abonnées par le controlleur.

