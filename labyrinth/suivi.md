Attendus pour le jalon 1, commit dépôt git taggé [V1] pour le vendredi 24 octobre 23h :
▶ un document suivi.md dans lequel est indiqué pour chaque semaine ce qui a été réalisé par chacun des membres depuis le début du projet.

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
- Ajout de la classe SceneManager (Pile des scenes)
- Début d'implémentation de Main en tant que singleton (afin d'accéder aux SceneManager depuis tous les channels sans utiliser de méthode statique, car ce n'est pas très optimisé, mais vu que ça rend la chose bien plus compliquée a utilisé pour les tests, ça sera sûrement retransformé en statique)

## **Semaine du 13/10 au 20/10**

### **Le groupe** :

### **Antonin** :
- Création Fxml Progression
- implémentation changement de la couleur du bouton si niveau fini

### **Angèl** :

### **Baptiste** :

| Développement | Analyse | Algorithmie |
|--------------|---------|-------------|
| Parcours en profondeur du labyrinthe (DepthStackSearch) avec test correspondant | Rapport d'analyse | Parcours en profondeur du labyrinthe (DepthStackSearch) avec test correspondant |
| Debug de LabyrinthModeLibre (Collecte des valeurs entrées pour la génération effective) et de son FXML | | |
| Intégration des FXML / Controlleurs entre eux pour correspondre au prototype d'interface réalisé | | |

### **Romain** :
- création du fxml pour un joueur déjà existant
- ajout du contrôleur du fxml pour un joueur déjà existant
- création d'un chronomètre

### **Victor** :
- Implémentation d'un nouvel algorithme utilisant l'algorithme de parcours en profondeur
- Modification de Scene pour détecter les touches directionnelles au clavier
- Ajout d'une méthode activé et envoyé à GameMode lorsqu'un joueur termine un labyrinthe
- Ajout des Javadocs et suppressions des warnings de GameMode, Maze, LabyrinthScene, AlgoLabyNew et LabyrinthControler
- Rendre LabyrinthScene dynamique (:D, j'ai pété mon crâne)

## **Semaine du 20/10 au 24/10**

### **Le groupe** :

### **Antonin** :

### **Angèl** :

### **Baptiste** :

### **Romain** :

### **Victor** :
