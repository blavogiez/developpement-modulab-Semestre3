package fr.univlille.labyrinth.view.labyrinth.filter;

public interface WallFilter {

    boolean shouldDrawVerticalWall(int y, int x, int height, int width);

    boolean shouldDrawHorizontalWall(int y, int x, int height, int width);
}
