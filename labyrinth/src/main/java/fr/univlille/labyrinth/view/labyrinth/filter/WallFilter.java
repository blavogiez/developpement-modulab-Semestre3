package fr.univlille.labyrinth.view.labyrinth.filter;


/*
 * Interface utilitaire pour décider de si l'on affiche ou non les murs dans les implémentations (vue exploration, locale...)
 */
public interface WallFilter {

    boolean shouldDrawVerticalWall(int y, int x, int height, int width);

    boolean shouldDrawHorizontalWall(int y, int x, int height, int width);
}
