package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import javafx.animation.AnimationTimer;

public class PlayerAnimation extends AnimationTimer {

    private boolean enabled = true ;
    private LabyrinthCanvasView view;
    private double speed;

    public PlayerAnimation(LabyrinthCanvasView view,double speed) {
        this.view = view;
        this.speed=speed;
    }

    public PlayerAnimation(LabyrinthCanvasView view) {
        this(view,0.2);
    }

    public void disable() {
        enabled=false;
    }

    @Override
    public void handle(long now) {
        if(!enabled) return;
        ObservableMaze maze = view.getCurrentMaze();
        int targetX = maze.getPlayerPosition().getX();
        int targetY = maze.getPlayerPosition().getY();

        view.setPlayerX( view.getPlayerX() + (targetX - view.getPlayerX()) * speed);
        view.setPlayerY( view.getPlayerY() + (targetY - view.getPlayerY()) * speed);


        if (Math.abs(view.getPlayerX() - targetX) < 0.01) view.setPlayerX(targetX);
        if (Math.abs(view.getPlayerY() - targetY) < 0.01) view.setPlayerY(targetY);

        view.drawMazeOnly();
        view.dessinerJoueur(view.getCanvas().getGraphicsContext2D(), maze);
    }
}
