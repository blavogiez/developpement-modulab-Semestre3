package fr.univlille.labyrinth.model;

/**
 * GameMode est la classe qui gère le mode de jeu choisi par le joueur. Elle sera intermédiaire entre Labyrinthe et Joueur.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class GameMode {

    private Maze currentMaze;

    /**
     * Cette méthode est appelé lorsque le GameMode est généré
     */
    public abstract void start();

    /**
     * Cette méthode est appelé lorsque le joueur fini un labyrinthe
     */
    public abstract void playerWin();

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

    public void setCurrentMaze(Maze currentMaze) {
        this.currentMaze = currentMaze;
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }


}
