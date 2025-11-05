package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.algorithmold.MazeAlgorithmFactory;

/**
 * Freemode est une extension de GameMode pour le mode libre (la plus simple possible).
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeMode extends GameMode {
    //ces attributs peuvent être amenés à être changés par le controlleur afin d'assurer une persistance par session (le joueur n'a pas à resaisir plein de fois son entrée !)
    public static MazeAlgorithmFactory algorithm = MazeAlgorithmFactory.STANDARDLARGEUR ;
    public static int mazeWidth = 20;
    public static int mazeHeight = 20;
    public static double mazeWallPercentage = 0.4 ;
    public static int distanceBetweenEntryAndExit = 10 ;

    /*
     * Conformément au sujet :
     * si l'algorithme est parfait, on crée le labyrinthe avec une distance entrée / sortie et on ne prend pas en compte le pourcentage de murs
     * si l'algorithme est aléatoire, on crée le labyrinthe avec un pourcentage de murs et on ne prend pas en compte la distance entrée / sortie

     */
    public void createMaze() {
        if(algorithm.isPerfect()) createMaze(algorithm, mazeWidth, mazeHeight, distanceBetweenEntryAndExit);
        else createMaze(algorithm, mazeWidth, mazeHeight, mazeWallPercentage);
    }

    public String toString() {
        String info = "Labyrinthe d'algorithme " + algorithm.name() + " ; \n" ;
        info+= "Dimensions : " + FreeMode.mazeWidth + "x" + FreeMode.mazeHeight;
        if (algorithm.isPerfect()) {
            info+= ", Distance : "+ FreeMode.distanceBetweenEntryAndExit ;
        }else{
            info += ", Pourcentage : " + (int) (FreeMode.mazeWallPercentage * 100) + "%";
        }
        return info ;
    }

    /* Donne la distance maximale entre l'entrée et la sortie (Hauteur -3, Largeur -3) selon les dimensions du mode libre
     * Utile pour mettre cette valeur maximum si la sélection de distance entrée / sortie est trop élevée
     * @return int
     */
    public static int getMaxDistanceBetweenEntryAndExit() {
        return (FreeMode.mazeHeight - 3) + (FreeMode.mazeWidth - 3) ;
    }
}
