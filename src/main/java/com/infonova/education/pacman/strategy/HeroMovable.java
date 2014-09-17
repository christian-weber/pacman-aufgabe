package com.infonova.education.pacman.strategy;

import com.infonova.education.pacman.*;

/**
 * Default Movable strategy implementation.
 */
public class HeroMovable implements Movable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(UserAction userAction, Level level, GameObject gameObject) {

        final int maxX = level.getMaxX();
        final int maxY = level.getMaxY();

        int newX = gameObject.getX();
        int newY = gameObject.getY();

        switch (userAction) {
            case LEFT:
                newX = movePosition(newX - 1, maxX);
                break;
            case RIGHT:
                newX = movePosition(newX + 1, maxX);
                break;
            case UP:
                newY = movePosition(--newY, maxY);
                break;
            case DOWN:
                newY = movePosition(++newY, maxY);
                break;
        }

        BackgroundElement backgroundElement = level.getBg(newX, newY);
        BackgroundType backgroundType = backgroundElement.getType();

        // TODO: REMOVE
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
