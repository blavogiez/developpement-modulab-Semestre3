package fr.univlille.labyrinth.model;

/**
 * GameMode est la classe qui gère le mode de jeu choisi par le joueur. Elle sera l'intermédiaire entre Labyrinthe et Joueur.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class GameMode {

    private Maze currentMaze;

    /** 
     * @param width
     * @param height
     * @param wallPercentage
     */
    /**

    /**
     * Cette méthode permet de générer un labyrinthe, afin de le stocker en paramètre.
     *
     * @param width la largeur du labyrinthe.
     * @param height la hauteur du labyrinthe.
     * @param wallPercentage le taux de mur entre 0 et 0.5.
     */
    public void createMaze(int width, int height, double wallPercentage) {
        this.currentMaze = new Maze(width,height,wallPercentage);
    }

    // Surcharge pour prendre en compte une distance entre l'entrée et la sortie (Le sujet ne mentionne que pour la progression)
    public void createMaze(int width, int height, double wallPercentage, int distanceBetweenEntryAndExit) {
        this.currentMaze = new Maze(width,height,wallPercentage,distanceBetweenEntryAndExit);
    }

    /**
     * Cette méthode vérifie si le joueur peut se déplacer à l'endroit demander, et envoie au labyrinthe de le déplacer si c'est le cas.
     *
     * @param direction la largeur du labyrinthe.
     */
    public void movePlayerPosition(Direction direction) {
        if (currentMaze!=null && currentMaze.getPlayerPosition()!=null){
            Position playerPosition = currentMaze.getPlayerPosition();
            if ( !currentMaze.isWall(playerPosition.getX()+direction.x,playerPosition.getY()+direction.y)){
                currentMaze.movePlayer(direction);
            }
        }
    }

    /** 
     * @return boolean
     */
    public boolean isPlayerAtEnd() {
        return currentMaze != null && currentMaze.getPlayerPosition().equals(currentMaze.getExitPosition());
    }

    /** 
     * @param currentMaze
     */
    public void setCurrentMaze(Maze currentMaze) {
        this.currentMaze = currentMaze;
    }

    /** 
     * @return Maze
     */
    public Maze getCurrentMaze() {
        return currentMaze;
    }
}
