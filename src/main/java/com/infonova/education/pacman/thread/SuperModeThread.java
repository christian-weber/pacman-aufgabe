package com.infonova.education.pacman.thread;

import com.infonova.education.pacman.Hero;

public class SuperModeThread extends Thread {

    private final Hero hero;

    public SuperModeThread(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void run() {

        if (hero.isSuperMode()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hero.endSupermode();
        }
    }
}
