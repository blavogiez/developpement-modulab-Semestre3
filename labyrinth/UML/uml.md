# Rapport : UML & Choix de Conception

L'architecture de l'application se compose sous la forme suivante, en plusieurs diagrammes à différentes granularités.

Les choix de conception évoqués seront globaux et évoqueront la logique générale plutôt que celle des éléments cibles. Ces choix seront guidés par :

- Les principes SOLID, DRY, KISS, YAGNI...
- La volonté d'éviter *l'over-engineering*

**Il est conseillé de lire la présentation au préalable afin de comprendre toutes les fonctionnalités référencées.**

# Sommaire

**[Modèle](#modèle)**

**[Controllers](#controllers)**

**[Persistance et configuration](#persistance-et-configuration)**

**[Vue et rendu](#vue-et-rendu)**

**[Navigation de l'application](#navigation-de-lapplication)**

**[Couverture totale](#couverture-totale)**

**[Tests](#tests)**

**[Conclusion](#conclusion)**


## Modèle
[Diagramme du modèle](assets/uml_model_core.png)

### Choix de conception

La classe `GameMode` mutualise les comportements communs aux deux modes de jeu : `FreeMode` et `ProgressionMode`.

Un labyrinthe immobile est un `Maze`. Il contient uniquement les murs. Cette distinction est importante puisqu'elle permet l'inversion des dépendances vis-à-vis de l'algorithme, qui ne voit que le `Maze` pour ce dont il a besoin. `Maze` expose maintenant ses attributs principaux (dimensions, entrée/sortie, distance, pourcentage, murs) et ses constructeurs, ce qui rend explicite la dépendance aux algorithmes (`MazeAlgorithm`).

`ObservableMaze` hérite de `Maze` et permet d'ajouter des composants, s'agissant de pièges ou d'entités. Les entités sont des agents capables de changer de position dans le labyrinthe. Il s'agira ici de sorties, de joueur et de monstres. Les pièges sont immobiles et, à leur déclenchement, déplacent le joueur. Les joueurs sont des entités spéciales, étant la seule entité capable de déclencher le mouvement. Ainsi, ils comportent un `ID`, dans le mode solo étant toujours à 0, et dans le mode multijoueur désignant l'identifiant du joueur joué. Les gestionnaires (`EntityManager`, `TrapManager`) et les observateurs du labyrinthe sont affichés pour refléter le code.

`MazeManager` porte la validation des dimensions et gère la création de `ObservableMaze`. `MazeAlgorithmFactory` liste l'ensemble des algorithmes disponibles avec `getAlgorithm()`. Le pattern Factory permet notamment une création simple.

De cette façon, lors du jeu dans le contrôleur, notre joueur 1 se déplace avec les flèches, tandis que le 2ème peut avec ZQSD, le troisième avec IJKL... et ce sur un joueur distinct.

### Clarifications

Par strict respect du MVC, seul le modèle réalise les actions de sauvegarde.

### Controllers
[Diagramme des contrôleurs](assets/uml_controllers.png)

### Clarifications

Notre application utilise, pour toutes ses vues, des pages FXML, stockées dans le dossier `ressources`. Le nommage se doit alors d'être constant ; la classe de contrôleur a le même nom + `Contrôleur` que sa page FXML référencée. Cela permet de rendre le code plus maintenable.

### Choix de conception

La logique **SOLID** et particulièrement le **DRY** implique ici une abstraction ; les différents contrôleurs d'étapes, responsables de l'initialisation des parties du modèle, de la vue et de leur liaison, réalisent beaucoup de comportements communs. La classe `LabyrinthController` est parente de tous les contrôleurs de labyrinthe. De ce fait, il est très simple d'ajouter une nouvelle vue ; on peut noter, par exemple, que des contrôleurs comme `ExplorationViewProgressionModeLabyrinthController` font moins de 20 lignes de code ; en l'occurrence, ce contrôleur s'occupe uniquement de *override* la méthode `setupView`, dont le retour est mis dans le conteneur `StackPane`.

`AppState` reflète l'état applicatif complet (joueur, niveau/défi sélectionné, configuration du mode libre, notification en attente), et `PlayerMovementHandler` est placé dans le sous-package `controller.input` pour clarifier son rôle.

### Rapport avec le MVC

Les `LabyrinthController` sont des `VictoryObserver<GameMode>` qui, lorsque notifiés par le `GameMode` qu'un joueur est sur une sortie, invoquent leur méthode `handleVictory`. Il en est de même pour la logique de défaite (plus aucun joueur restant), invoquant `handleDefeat`.

Ces méthodes peuvent être surchargées : par exemple, lors de la victoire, un `ProgressionModeLabyrinthController` redirige vers la sélection de niveaux, tandis qu'un `FreeModeLabyrinthController` redirige vers un nouveau niveau (donc sa propre page). Le désabonnement se réalise alors par le *Garbage Collector*, puisque l'ancienne page n'est plus référencée.

C'est là l'intérêt d'avoir conceptualisé par l'abstraction ; décider d'implémentations, de comportements différents plus facilement grâce à la modularité acquise.

## Persistance et configuration
[Diagramme des persistences et configurations](assets/uml_persistence.png)

### Choix de conception

Il a été fait le choix de la sérialisation de la classe `Player`, contenant des `Challenge` (défis). Un défi contient donc toutes les caractéristiques du labyrinthe qui lui sera correspondant. Puisqu'il contient un algorithme de labyrinthe, il sera stocké sous forme de valeur `String`, qui sera convertie dans la Factory.

## Vue et rendu
[Diagramme de la vue et du rendu](assets/uml_vue_rendu.png)

### Choix de conception

La logique **SOLID** et particulièrement le **DRY** implique, à la manière des contrôleurs, une abstraction ; les différentes vues sont en réalité toutes des `Canvas`.  
Puisque notre application utilise des pages FXML, nos vues, à proprement parler en fichiers Java, sont alors des parties intégrées à la page, par leur contrôleur (nommage identique + `Contrôleur`).

### Rapport avec le MVC

Une vue enfant de `LabyrinthCanvasView` retourne donc un objet `Canvas` observant le modèle. Cet objet est intégré par le contrôleur dans son conteneur `StackPane`. Les vues sont donc abonnées par le contrôleur.

## Navigation de l'application

[Diagramme de l'application](assets/uml_app_navigation.png)

### Choix de conception

Le package `app` sert à naviguer dans l'application ; il s'agit de manipuler les paramètres et de changer de scène. Puisque nous avons une vidéo de fond, un `BackgroundManager` existe. D'un autre côté, la classe `App` est le point d'entrée de l'application pour **Maven**.

## Couverture totale
[Diagramme de toutes les classes](assets/uml_granularite_complete.png)

Les relations sont ici entièrement visibles, si l'on navigue bien à l'intérieur.

### Choix de conception

Les choix sont identiques aux sections précédemment décrites ; le seul choix dont l'on peut ici parler concerne les packages. Nous avons privilégié une approche modulaire afin de bien séparer les classes et de les réunifier en packages lorsque leurs responsabilités sont similaires.

## Tests

Les tests suivent exactement la même arborescence afin de s'y retrouver facilement. Le modèle est couvert au maximum.

## Conclusion

Nous avons tâché d'être concis dans les explications. Nos choix de conception peuvent également être retrouvés dans :

- Le rapport d'analyse  
- Le rapport d'algorithmie  
- La Javadoc  
- La soutenance
