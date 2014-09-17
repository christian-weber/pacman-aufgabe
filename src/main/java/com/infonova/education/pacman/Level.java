package com.infonova.education.pacman;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private Hero hero;
    private List<Enemy> enemies;
    private BackgroundElement[][] backgroundElements;
    private int maxX;
    private int maxY;
    private int points;
    private int score = 0;

    public Level(int maxX, int maxY) {
        backgroundElements = new BackgroundElement[maxX][maxY];
        this.maxX = maxX;
        this.maxY = maxY;

        enemies = new ArrayList<Enemy>();
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public int getPoints() {
        return points;
    }

    public int getScore() {
        return score;
    }

    public Hero getHero() {
        return hero;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Returns the Element
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return BackgroundElement
     */
    public BackgroundElement getBg(int x, int y) {
        return backgroundElements[x][y];
    }

    public void addBg(BackgroundElement bg) {
        backgroundElements[bg.getX()][bg.getY()] = bg;

        // check if the element we load is a Dot or Superdot
        if (bg.getType().equals(BackgroundType.DOT)
                || bg.getType().equals(BackgroundType.SUPERDOT)) {
            // if so we can earn points
            this.points++;
        }
    }
}
