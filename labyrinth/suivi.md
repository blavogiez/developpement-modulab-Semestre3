# Organisation

Notre équipe communique sur un groupe *Discord* et nous faisons une mini-démonstration (petite phrase, ou screen) à chaque commit

# Membres de l'équipe

- **Bredelle Victor** : [victor.bredelle.etu@univ-lille.fr](mailto:victor.bredelle.etu@univ-lille.fr.fr)  
- **Marouze Antonin** : [antonin.marouze.etu@univ-lille.fr](mailto:antonin.marouze.etu@univ-lille.fr) 
- **Harlaut Romain** : [romain.harlaut.etu@univ-lille.fr](mailto:romain.harlaut.etu@univ-lille.fr)  
- **Angèl Zheng** : [angel.zheng.etu@univ-lille.fr@univ-lille.fr](mailto:angel.zheng.etu@univ-lille.fr@univ-lille.fr)   
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
| Après la fin de l'UML, création du corps du projet avec importation (JDK 17, JavaFX + Maven) | | |
| Génération des classes et méthodes vides de l'UML | | |

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
| Intégration des fichiers FXML et de leurs contrôleurs respectifs afin de respecter la maquette d'interface établie | | |
| Intégration du composant chronomètre développé par Romain pour son affichage dans la vue du labyrinthe | | |
| Implémentation de la suite de tests pour les algorithmes de génération / labyrinthe | | |
| Implémentation du singleton AppState pour simplifier les traitements (attributs du joueur accessibles aux controlleurs plutot que du static) | | |

(On peut noter qu'une approche TDD fut particulièrement pertinente pour le cas d'un labyrinthe)

En parallèle, j'ai remis en question et simplifié le code que j'ai produit, avec les bonnes pratiques, pour permettre une meilleure compréhension par les autres.

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
|Contrôle de saisie du mode libre (Avertissement de 2 secondes si erreur)|
|Contrôle de saisie du mode progression (saisie des noms vérifiée)||
|Couverture de tests pour les controlleurs / la distance entrée/sortie ||
|Amélioration de la qualité de code globale du projet (petits refactor, faire respecter la logique métier...)||
|Script bash pour créer la javadoc et la placer dans `doc` ||
|Création des UML à différentes granularités et explication dans README ||
|Ajout d'une suite de tests complète pour ProgressionMode afin de garantir une couverture maximale avant le rendu ||
|Renommage de tous les fichiers en anglais avec des noms explicites et restructuration des packages selon les conventions Java ||

La qualité du code est extrêmement importante et au rendu du jalon 1, notre dette technique est très faible. Je fais tout pour la réduire au maximum, en l'assurant par la non-régression des tests.


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


### **Baptiste** :

| Développement | Algorithmie |
|--------------|-------------|
| Refactor de la sélection de défis pour permettre une initialisation dynamique (autant de défis que dans le CSV)| |
| Refactor du controlleur de mode de jeu pour simplifier le code | |
| Strategy Pattern pour le score des défis (speedrun, temps) | |
| Couverture de tests augmentée pour les controlleurs | |

J'ai profité de cette semaine de vacances pour faire des petites retouches sur le code afin de faciliter les futures implémentations, en les communiquant sur le groupe.

Comme c'était une semaine de vacances et que l'objectif n'était pas de travailler au maximum, je n'ai pas touché aux nouvelles *features* ni à l'algorithme pour ne pas prendre des tâches aux autres.

### **Romain** :


### **Victor** :


## **Semaine du 3/11 au 9/11**

**Semaine de rentrée.**

### **Antonin** :

- Ajout Classement TableView

### **Angèl** :


### **Baptiste** :

| Développement | Algorithmie |
|--------------|-------------|
| Avec Victor, refactor des vues pour correspondre au nouvel algorithme (mur entre les cases)| |
| Implémentation de la vue Exploration (Étape 6) | |
| Refactor de la classe Maze en deux parties : Maze et ObservableMaze pour mieux respecter le DIP et SRP.*   | |
| Implémentation d'un système d'entités pour permettre des composants dynamiques **| |

*(Un `Maze` est un labyrinthe qui contient uniquement les murs et position entrée / sortie (L'algorithme ne voit que les Maze) ; un ObservableMaze hérite du labyrinthe et gère les positions d'entités )

**Le système d'entités a emergé après la volonté de chacun d'implémenter des modes de jeux originaux ; Antonin a pensé à un mode à deux joueurs, Angèl à un monstre (Minotaure-like), Romain à une sortie qui bouge... J'ai implémenté ce système pour que tout le monde puisse rapidement créer l'entité qu'il veut. De plus, elles peuvent s'assembler (on peut mettre 15 monstres, 4 sorties...)


### **Romain** :

-code du classement
-ajout mode movingexit
-ajout d'un movement fluide pour le joueur

### **Victor** :

