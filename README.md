# SAÉ S3.02 - Labyrinthe

## Membres de l'équipe
Réalisé par :
- **Bredelle Victor** : [victor.bredelle.etu@univ-lille.fr](mailto:victor.bredelle.etu@univ-lille.fr.fr)  
- **Marouze Antonin** : [antonin.marouze.etu@univ-lille.fr](mailto:antonin.marouze.etu@univ-lille.fr) 
- **Harlaut Romain** : [romain.harlaut.etu@univ-lille.fr](mailto:romain.harlaut.etu@univ-lille.fr)  
- **Angèl Zheng** : [angel.zheng.etu@univ-lille.fr@univ-lille.fr](mailto:angel.zheng.etu@univ-lille.fr@univ-lille.fr)   
- **Baptiste Lavogiez** : [baptiste.lavogiez.etu@univ-lille.fr](mailto:baptiste.lavogiez.etu@univ-lille.fr)  

## Liens utiles

Mme Boneva : [Rapport d'analyse](rapports/analyse/main.pdf)

Mr Delecroix : [Rapport qualité](rapports/qualite/main.pdf) | [Suivi](labyrinth/suivi.md)

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
```


## Présentation
Le but de cette SAE est de créer une application de jeu de labyrinthe proposant différents modes de jeu.
Le joueur doit rejoindre la sortie du labyrinthe, généré de manière aléatoire, tout en faisant face à diverses contraintes selon le mode choisi.

L’application comporte deux modes de jeu principaux :

1. **Mode libre** : 

    - le joueur peut choisir la taille du labyrinthe (largeur, hauteur) ainsi que le pourcentage de murs.


2. **Mode progression** : 

    - le joueur avance à travers plusieurs niveaux de difficulté croissante.

    - Chaque étape comporte trois défis : facile, moyen et difficile.

    - Certains niveaux imposent une restriction de vision (le joueur ne voit qu’autour de lui).

    - D’autres incluent une limite de temps pour réussir le niveau.

Un système de sauvegarde enregistre la progression à partir du pseudo saisi par le joueur.

## Démonstrations