# Organisation

Notre équipe communique sur un groupe *Discord* et nous faisons une mini-démonstration (petite phrase, ou screen) à chaque commit.

# Membres de l'équipe

- **Bredelle Victor** : [victor.bredelle.etu@univ-lille.fr](mailto:victor.bredelle.etu@univ-lille.fr)  
- **Marouze Antonin** : [antonin.marouze.etu@univ-lille.fr](mailto:antonin.marouze.etu@univ-lille.fr) 
- **Harlaut Romain** : [romain.harlaut.etu@univ-lille.fr](mailto:romain.harlaut.etu@univ-lille.fr)  
- **Angèl Zheng** : [angel.zheng.etu@univ-lille.fr@univ-lille.fr](mailto:angel.zheng.etu@univ-lille.fr)   
- **Baptiste Lavogiez** : [baptiste.lavogiez.etu@univ-lille.fr](mailto:baptiste.lavogiez.etu@univ-lille.fr)  

**Chaque personne complète soi-même sa contribution (vendez-vous bien)**

# Jalon 1

## **Semaine du 22/09 au 29/09**

### **Le groupe** :

### **Antonin** :

### **Angèl** :

### **Baptiste** :

### **Romain** :

### **Victor** :

## **Semaine du 29/09 au 06/10**

### **Le groupe** :
- Création du diagramme UML

### **Antonin** :

### **Angèl** :
- Maquette Figma

### **Baptiste** :
| Développement | Analyse | Algorithmie |
|--------------|---------|-------------|
| Après la fin de l'UML, création de l'environnement du projet avec importation (JDK 17, JavaFX + Maven) | | |
| Génération des classes et méthodes vides depuis l'UML | | |

### **Romain** :
Fiche descriptive du "Déplacer joueur"

### **Victor** :
- Création d'un premier algorithme de labyrinthe
- Fiches descriptives de Lancer partie, Charger partie et Afficher Progression avec Baptiste


## **Semaine du 06/10 au 13/10**

### **Le groupe** :


### **Antonin** :
Création des tests Maze et Position

### **Angèl** :
- Création de pages FXML et leurs controlleurs
- Naviguation entre les pages
- Pages FXML presque responsive avec les outils de sceneBuilder

### **Baptiste** :

| Développement | Analyse | Algorithmie |
|--------------|---------|-------------|
| Code de Player et de sa progression (progression initialisée par défaut, calcul du score) | Rapport d'analyse | |
| Code de PlayerDatabase (sauvegarde par sérialisation) | | |
| Tests : PlayerDatabaseTest (Test de sauvegarde, lecture, effacement...), PlayerTest, PositionTest | | |
| Code start de FreeMode et ProgressionMode (Tour, choix challenge) | | |

### **Romain** :
Création de test pour "PlayerDatabase"
Création de la classe "PlayerDatabase"

### **Victor** :
- Création de la scène Labyrinthe
- Création du controleur de la scène Labyrinthe
- Première Importation du premier algorithme → impossibilité de modifier le pourcentage de mur
- Modification de GameMode (Déplacer joueurs)
- Ajout de la classe SceneManager (Pile des scenes) -> inutilisé/obsolète
- Début d'implémentation de Main en tant que singleton (afin d'accéder aux SceneManager depuis tous les channels sans utiliser de méthode statique, car ce n'est pas très optimisé, mais vu que ça rend la chose bien plus compliquée a utilisé pour les tests, ça sera sûrement retransformé en statique) -> Main remplacé par HelloApplication pour être renommé Main

## **Semaine du 13/10 au 20/10**

### **Le groupe** :

### **Antonin** :
- Création Fxml Progression
- implémentation changement de la couleur du bouton si niveau fini

>Probléme branche 'develop-anto' commit du 15 non publié sur master car conflict donc fichier copier/coller > master

### **Angèl** :
- Integration LabyGridView en FXML
- Page responsive avec des GridPane pour les pages FXML
- Créations de pages FXML et leurs controlleurs
- Modifications de pages, fix de bugs visuelles

### **Baptiste** :

| Développement | Analyse | Algorithmie |
|--------------|---------|-------------|
| Implémentation de la vue à visibilité restreinte autour du joueur (conformément aux spécifications des diapositives) pour la troisième étape | Rédaction du rapport d'analyse | Implémentation du parcours en profondeur/largeur du labyrinthe (Package parcours) pour les tests |
| Collecte et validation des paramètres saisis par l'utilisateur pour la génération en mode libre | | |
| Utilisation du composant chronomètre de Romain dans la vue |
| Implémentation de la suite de tests pour les algorithmes de génération / labyrinthe | | |
| Implémentation du singleton AppState pour simplifier les traitements (attributs du joueur accessibles aux controlleurs plutot que du static) | | |

### **Romain** :
- création du fxml pour un joueur déjà existant
- ajout du contrôleur du fxml pour un joueur déjà existant
- création d'un chronomètre

### **Victor** :
- Implémentation d'un nouvel algorithme utilisant l'algorithme de parcours en profondeur
- Modification de Scene pour détecter les touches directionnelles au clavier
- Ajout d'une méthode activé et envoyé à GameMode lorsqu'un joueur termine un labyrinthe
- Ajout des Javadocs et suppressions des warnings de GameMode, Maze, LabyrinthScene, AlgoLabyNew et LabyrinthControler
- Rendre LabyrinthScene dynamique (:D, j'ai pété mon crâne) -> complètement modifié

## **Semaine du 20/10 au 24/10**

**Rendu du jalon 1.**

### **Le groupe** :

### **Antonin** :

- ReadMe
- Création du `run.sh` et `compile.sh`
- Un peut de Java Doc
- Ajout de Progress Bar
- Ajout de test

### **Angèl** :
- Pages FXML Responsives avec la class ResizeUtil
- Modifications de pages, fix de bugs visuelles

### **Baptiste** :

*Fin de la section Analyse.*

| Développement| Algorithmie |
|--------------|-------------|
|Implémentation d'une fonctionnalité de "tooltip" quand on survole un bouton, affichant ses informations|Test de la distance entrée/sortie correcte et debug du placement adéquat de la case de départ|
|Contrôle de saisie des modes de jeu (Avertissement de 2 secondes si erreur)||
|Script bash pour créer la javadoc et la placer dans `doc` ||
|Création des UML à différentes granularités et explication dans README ||
|Ajout d'une suite de tests complète pour ProgressionMode afin de garantir une couverture maximale avant le rendu ||

### **Romain** :
- J'ai fait de la javadoc
- Modification du chronomètre

### **Victor** :

- Ajout des différents algorithmes
- Ajout du paramètre facultatif mode nuit
- modification légère du CSS
- Ajout de Javadoc

## **Semaine du 27/10 au 2/11**

**Sujet du jalon 2 rendu disponible**

**Semaine de vacances**

### **Antonin** :

- Ajout choix différents type d'algo possible dans freeMode
- Fxml choix type d'algo freeMode

### **Angèl** :
- Amélioration de resizeUtil, application du resize sur toutes les pages qui en ont besoin.
- Fixe de plusieurs fxml.

### **Baptiste** :

| Développement | Algorithmie |
|--------------|-------------|
| Amélioration de la sélection de défis pour permettre une initialisation dynamique (autant de défis que dans le CSV)|  |
| Refactor du controlleur de mode de jeu pour simplifier le code | |
| Petite couverture de tests pour les controlleurs | |
| Fonctionnalité originale : Strategy Pattern pour permettre différents calculs de score pour les défis (speedrun, temps...) | |


### **Romain** :
- animation


### **Victor** :
- Je n'ai rien branlé pendant les vacs


## **Semaine du 3/11 au 9/11**

**Semaine de rentrée.**

### **Antonin** :

- Ajout Classement TableView

### **Angèl** :

- Refactor de certaines fonctions dans ResizeUtil pour plus de clareté et de modularité.
- Ajout de la fonction pathFinder dans classe BreadthFirstSearch.
- Début ajout de MonsterEntity.


### **Baptiste** :

| Développement | Algorithmie |
|--------------|-------------|
| Avec Victor, refactor des vues en Canvas + responsive pour correspondre au nouvel algorithme (murs entre les cases)| Début du rapport d'algorithmie|
| Implémentation de la vue Exploration (Étape 6) | Algorithme pour la distance entrée / sortie |
| Création de ObservableMaze héritant de Maze | Création de la classe `Benchmark` pour mesurer le temps d'exécution des différents algorithmes avec résultats dans un fichier CSV + modélisation `matplotlib`|
| Fonctionnalité originale : Implémentation d'un système d'entités pour permettre des composants dynamiques **| |
| Fonctionnalité originale : Implémentation d'une Factory de liste d'entités par chaine textuelle depuis le CSV des défis pour une configuration facile | |
| Fonctionnalité originale : Gestion de plusieurs sorties de labyrinthe (Accepter n'importe quelle sortie en vérifiant la liste d'entités) | |
| Fonctionnalité originale : Implémentation d'une gestion de Formes / Couleurs dans la vue pour dessiner et configurer facilement les composants du labyrinthe (Pièges, entités) | |


### **Romain** :

-code du classement
-ajout mode movingexit
-ajout d'un movement fluide pour le joueur

### **Victor** :

- Création de l'algorithme de fusion aléatoire
- Création de javadoc
- Création de test pour l'algorithme de fusion aléatoire
- Ajout test de positon
- Ajout test de direction
- Modification de MazeAlgorithm de interface à classe abstract
- Test de MazeAlgorithm en général

## **Semaine du 10/11 au 16/11**


### **Antonin** :
- Fxml entity
- Création des classes exception et implémentation
- Ajout de test
- Mode 2 player

### **Angèl** :

- Développement et intégration de MonsterEntity.
- Ajout classe wallRemover.
- Refactor de certaines parties du code pour respecter les principes SOLID.


### **Baptiste** :


| Développement | Algorithmie |
|--------------|-------------|
| Fonctionnalité originale : Avec Antonin, implémentation du mode multijoueur (gestion des ID joueur et touches) | |
| Fonctionnalité originale : Algorithmes de positionnement initial d'entité | |
| Fonctionnalité originale : Piège de regénération du labyrinthe | |
| Style : Vidéo de fond dans les menus avec transitions | |
| Tests : MonsterEatingPlayer, LiskovMaze + mise à jour des tests pour supporter le mode multijoueur | |


### **Romain** :
- popup de victoire
- SOLID (Observable victory et mazewallchecker et magic number)
- javadoc


### **Victor** :
- Améliorations des traps (Factory, Méthode en OnUse, etc..)
- Créations des tests des traps afin d'atteindre un coverage de ~85%


## **Semaine du 17/11 au 21/11**

**Cette semaine, nous avons principalement testé et finalisé l'application pour un rendu sans bugs / crash. Nous avons également tous discuté de la meilleure approche pour maximiser la qualité de code.**

### **Antonin** :
- Ajout Exception

>Probléme branche 'develop-antonin' commit non publié sur master car probléme de merge



### **Angèl** :

- Fix de la sélection des paramètres, fix de divers problèmes d'affichage.
- Refactor de certaines parties du code pour respecter les principes SOLID.

### **Baptiste** :


| Développement | Algorithmie |
|--------------|-------------|
| Refactor / extraction du package "view" avec un système de filtres pour alléger les classes | Optimisation de l'algorithme de fusion (Union-Find) avec tests correspondants |
| Fonctionnalité originale : Avec Romain, affichage en panneau d'information des entités/pièges contenus dans le labyrinthe | |
| Fonctionnalité originale : Affichage du panneau d'information des entités/pièges dynamique avec pattern Observer / Observable | |
| Test : MultiplayerGameMode | |


### **Romain** :
- javadoc
- affichage entite dans le mode libre
- fix run.sh pour absence de dependance


### **Victor** :

- Amélioration générale de tout le programme, factorisation afin d'éviter du DRY
- Résolution de bug utilisateur / Crash
