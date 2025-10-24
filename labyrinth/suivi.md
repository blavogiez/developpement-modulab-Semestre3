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

### **Angèl** :

### **Baptiste** :

| Développement | Analyse | Algorithmie |
|--------------|---------|-------------|
| Implémentation de la vue à visibilité restreinte autour du joueur (conformément aux spécifications des diapositives) pour la troisième étape | Rédaction du rapport d'analyse | Implémentation du parcours en profondeur/largeur du labyrinthe (Package parcours) pour les tests |
| Collecte et validation des paramètres saisis par l'utilisateur pour la génération en mode libre | | |
| Intégration des fichiers FXML et de leurs contrôleurs respectifs afin de respecter la maquette d'interface établie | | |
| Intégration du composant chronomètre développé par Romain pour son affichage dans la vue du labyrinthe | | |
| Implémentation de la suite de tests pour les algorithmes de génération / labyrinthe | | |
| Implémentation du singleton AppState pour simplifier les traitements | | |

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

### **Le groupe** :

### **Antonin** :

### **Angèl** :

### **Baptiste** :

*Fin de la section Analyse.*

| Développement| Algorithmie |
|--------------|-------------|
|Implémentation d'une fonctionnalité de "tooltip" quand on survole un bouton, affichant ses informations|Test de la distance entrée/sortie correcte et debug du placement adéquat de la case de départ|
|Contrôle de saisie du mode libre (Avertissement de 2 secondes si erreur)|
|Contrôle de saisie du mode progression (saisie des noms vérifiée)||
|Couverture de tests pour les controlleurs / la distance entrée/sortie ||
|Amélioration de la qualité de code ||
|Script bash pour créer la javadoc et la placer dans `doc` ||
|Création des UML à différentes granularités et explication dans README ||

La qualité du code est extrêmement importante et au rendu du jalon 1, notre dette technique est très faible et je fais tout pour la réduire au maximum, en l'assurant pas la non-régression des tests.


### **Romain** :

### **Victor** :

- Ajout des différents algorithmes
- Ajout du paramètre facultatif mode nuit
- modification légère du CSS
- Ajout de Javadoc
