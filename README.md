<div align="center">

# SAÉ S3.02 - Labyrinthe

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-17-blue?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.8+-red?style=for-the-badge&logo=apachemaven)
![License](https://img.shields.io/badge/License-Université%20de%20Lille-green?style=for-the-badge)

**[Équipe](#membres-de-léquipe)** • **[Liens utiles](#liens-utiles)** • **[Arborescence](#arborescence)** • **[Diagramme de classe](#diagramme-de-classe)** • **[Présentation](#présentation)** • **[Démonstrations](#démonstrations)** • **[Déploiement](#déploiement)**

</div>

## Membres de l'équipe
Réalisé par :
- **Victor Bredelle** : [victor.bredelle.etu@univ-lille.fr](mailto:victor.bredelle.etu@univ-lille.fr.fr)  
- **Antonin Marouze** : [antonin.marouze.etu@univ-lille.fr](mailto:antonin.marouze.etu@univ-lille.fr) 
- **Romain Harlaut** : [romain.harlaut.etu@univ-lille.fr](mailto:romain.harlaut.etu@univ-lille.fr)  
- **Angèl Zheng** : [angel.zheng.etu@univ-lille.fr@univ-lille.fr](mailto:angel.zheng.etu@univ-lille.fr@univ-lille.fr)   
- **Baptiste Lavogiez** : [baptiste.lavogiez.etu@univ-lille.fr](mailto:baptiste.lavogiez.etu@univ-lille.fr)  

## Liens utiles

Mme Boneva : [Rapport d'analyse](rapports/analyse/main.pdf)

Mr Delecroix :

| Documents | Code |
|-----------|------|
| [Rapport qualité](rapports/qualite/main.pdf) | [Répertoire principal](labyrinth/src/main/java/fr/univlille/labyrinth) |
| [Suivi](labyrinth/suivi.md) | [Répertoire de tests](labyrinth/src/test/java/fr/univlille/labyrinth) |
| [Issues](https://gitlab.univ-lille.fr/sae302/2025/G2_SAE3.3/-/boards) | |

Mme Everaere : [Rapport algorithmique](rapports/algo/main.pdf)

## Arborescence
```
.
├── analyse
│   ├── documents
│   │   └── plan.md
│   └── fiches_desc
│       ├── AfficherProgression.md
│       ├── ChargerProfil.md
│       ├── DeplacerJoueur.md
│       └── LancerModeProgression.md
├── attentes
│   ├── Algo.md
│   ├── Presentation_SAE_Labyrinthes.pdf
│   ├── Qdev.md
│   ├── Sujet.md
│   └── Sujet SAE S3 Labyrinthe.pdf
├── labyrinth
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── reflexion3.puml
│   ├── res
│   │   └── saves
│   │       └── players.dat
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── fr
│   │   │   │   │   └── univlille
│   │   │   │   │       └── labyrinth
│   │   │   │   │           ├── controller
│   │   │   │   │           │   └── Contient tous les controllers de l’application
│   │   │   │   │           ├── Main.java
│   │   │   │   │           ├── model
│   │   │   │   │           │   └── Contient les models de l’application
│   │   │   │   │           ├── parcours
│   │   │   │   │           │   └── Contient le programme permettant de parcourir le labyrinthe
│   │   │   │   │           ├── utils
│   │   │   │   │           │   └── Contient les fichiers displayDataBase et chronoUtil
│   │   │   │   │           └── view
│   │   │   │   │               └── Contient toutes les vues de l’application
│   │   │   │   └── module-info.java
│   │   │   └── resources
│   │   │       └── Contient les ressources nécessaires au fonctionnement de l’application, dont les fichiers FXML
│   │   └── test
│   │       └── Contient tout les tests
│   ├── suivi.md
│   └── target
│       └── Contient les fichiers compiler
├── rapports
│   ├── algo
│   │   └── Rapport Algo
│   ├── analyse
│   │   ├── Rapport Analyse
│   │   └── Sujet SAE S3 Labyrinthe.pdf
│   ├── qualite
│   │   └── Rapport Qualité
│   └── template
├── README.md
└── rendus Analyse
    └── G2_SAE3.3-Diagramme_Classes_V1.png

(update arborescence à chaque rendu taggé)
```

## Diagramme de classe

L'architecture de l'application se compose sous la forme suivante :

[Diagramme UML](labyrinth/uml.png)

*Ne sont concernés que les modules "model", "algorithm" et classes utilitaires associées.*

### Clarifications

insérer explications..

## Présentation
Le but de cette SAÉ est de créer une application de jeu de labyrinthe proposant différents modes de jeu.
Le joueur doit rejoindre la sortie du labyrinthe, généré de manière aléatoire, tout en faisant face à diverses contraintes selon le mode choisi.

L’application comporte deux modes de jeu principaux :

1. **Mode libre** : 

    - le joueur peut choisir la taille du labyrinthe (largeur, hauteur) ainsi que le pourcentage de murs.

2. **Mode progression** : 

    - Le joueur avance à travers plusieurs niveaux de difficulté croissante.

    - Chaque étape comporte trois défis : facile, moyen et difficile.

    - Certains niveaux imposent une restriction de vision (le joueur ne voit qu’autour de lui).

Un système de sauvegarde enregistre la progression à partir du pseudo saisi par le joueur.

## Lancer le projet
### Prérequis

Pour exécuter le projet, vous aurez besoin de :

- **Java 17** installé
  ```bash
  java -version

* **Maven** installé

  ```bash
  mvn -v
 
* installé **JavaFX 17** sur votre machine :

  ```bash
  sudo apt install openjfx
  ```

  > JavaFX sera installé dans `/usr/share/openjfx/lib`.

## Compilation du projet

Depuis la racine du projet, compilez et packagez l’application :
```bash
cd labyrinth
mvn clean package
```

Le JAR exécutable sera généré dans :

```
target/labyrinth-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Exécution du projet

Il existe plusieurs façons de lancer le jeu Labyrinth :

### Avec Maven

```bash
cd labyrinth
mvn javafx:run
```

> Attention cette méthode utilise Maven pour gérer JavaFX automatiquement.

---

### Avec le script `run.sh` (Linux/Mac)

Si JavaFX n’est pas encore installé, faites :

```bash
sudo apt install openjfx
```

Ensuite, lancez le script :

```bash
cd labyrinth
./run.sh
```

> Le script configure automatiquement le chemin vers JavaFX et démarre le jeu.

---

## Démonstrations

[Vue d'un labyrinthe](assets/vueLaby.png)

[Vue limitée d'un labyrinthe](assets/vueLimiteeLaby.png)

## Déploiement

Dernière version **déployable** de l'application :

[Archive exécutable](labyrinth/labyrinth.jar)

instr exec...