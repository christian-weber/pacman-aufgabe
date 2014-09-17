package com.infonova.education.pacman.observer;

import com.infonova.education.pacman.*;
import com.infonova.education.pacman.strategy.EnemySupermodeMovable;
import com.infonova.education.pacman.thread.SuperModeThread;

import java.util.Observable;
import java.util.Observer;

public class PointObserver implements Observer {

    private final Level level;
    private final GamePanel gamePanel;

    public PointObserver(Level level, GamePanel gamePanel) {
        this.level = level;
        this.gamePanel = gamePanel;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!(o instanceof Hero)) {
            return;
        }

        GameObject gameObject = (GameObject) o;
        int newX = gameObject.getX();
        int newY = gameObject.getY();

        Hero hero = level.getHero();

        BackgroundElement backgroundElement = level.getBg(newX, newY);
        BackgroundType backgroundType = backgroundElement.getType();

        if (backgroundType == BackgroundType.DOT) {
            hero.setPoints(hero.getPoints() + 1);
            level.increaseScore(1);
            BackgroundElement bg = level.getBg(newX, newY);
            bg.setType(BackgroundType.FREE);
            level.addBg(bg);
        }

        // TODO: hint
        if (backgroundType == BackgroundType.SUPERDOT) {
            hero.setPoints(hero.getPoints() + 1);
            level.increaseScore(2);
            hero.startSupermode();

            for (Enemy enemy : level.getEnemies()) {
                enemy.setMovable(new EnemySupermodeMovable());
            }

            new SuperModeThread(hero).start();

            BackgroundElement bg = level.getBg(newX, newY);
            bg.setType(BackgroundType.FREE);
            level.addBg(bg);
        }

    }



}
