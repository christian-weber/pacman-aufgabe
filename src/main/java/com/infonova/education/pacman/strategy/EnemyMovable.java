package com.infonova.education.pacman.strategy;

import com.infonova.education.pacman.*;

/**
 * Created by christian.weber on 16.09.2014.
 */
public class EnemyMovable implements Movable {

    @Override
    public void move(UserAction userAction, Level level, GameObject gameObject) {

        Hero h = level.getHero();

        final int maxX = level.getMaxX();
        final int maxY = level.getMaxY();

        int newX = gameObject.getX();
        int newY = gameObject.getY();

        if (h.getX() < newX) {
            movePosition(--newX, maxX);
        } else if (h.getX() > newX) {
            movePosition(++newX, maxX);
        } else if (h.getY() < newY) {
            movePosition(--newY, maxY);
        } else if (h.getY() > newY) {
            movePosition(++newY, maxY);
        }

        BackgroundElement backgroundElement = level.getBg(newX, newY);
        BackgroundType backgroundType = backgroundElement.getType();

        if (BackgroundType.WALL.compareTo(backgroundType) != 0) {
            gameObject.setX(newX);
            gameObject.setY(newY);
        }

    }

    private int movePosition(int position, int maxPosition) {
        position = Math.max(0, position);
        position = Math.min(position, maxPosition - 1);
        return position;
    }

}
