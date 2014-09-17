package com.infonova.education.pacman.observer;

import com.infonova.education.pacman.Enemy;
import com.infonova.education.pacman.GamePanel;
import com.infonova.education.pacman.Hero;
import com.infonova.education.pacman.Level;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameObjectCollisionObserver implements Observer {

    private final Level level;
    private final GamePanel gamePanel;

    public GameObjectCollisionObserver(Level level, GamePanel gamePanel) {
        this.level = level;
        this.gamePanel = gamePanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Enemy> enemies = level.getEnemies();
        Hero hero = level.getHero();

        for (Enemy enemy : enemies) {

            if (enemy.getX() == hero.getX() && enemy.getY() == hero.getY()) {

                if (hero.isSuperMode()) {
                    if (enemy.isAlive()) {
                        level.increaseScore(3);
                    }
                    enemy.setAlive(false);
                } else {
                    gamePanel.endGame();
                    gamePanel.update(gamePanel.getGraphics());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    gamePanel.startLevel();

                }
            }

        }
    }

}
