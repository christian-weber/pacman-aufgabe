package com.infonova.education.pacman.support;

import com.infonova.education.pacman.BackgroundElement;
import com.infonova.education.pacman.BackgroundType;
import com.infonova.education.pacman.Hero;
import com.infonova.education.pacman.Level;
import com.infonova.education.pacman.util.IOUtils;


public class LevelFactory {
	
	/**
	 * Die Start-X-Koordinate der Spielfigur.
	 * In der entgültigen Versio sollte diese
	 * Information aus der Level-Datei ausgelesen
	 * werden.
	 */
	private static final int HERO_START_X = 0;
	
	/**
	 * Die Start-X-Koordinate der Spielfigur.
	 * In der entgültigen Versio sollte diese
	 * Information aus der Level-Datei ausgelesen
	 * werden.
	 */
	private static final int HERO_START_Y = 0;
	
	private static int maxX;
	private static int maxY;
	
	/**
	 * Erzeugt das Level
	 * @return
	 */
	public static Level createLevel(String fileName) {
		
		BackgroundType[][] levelMap = loadLevel(fileName);
		
		BackgroundType type;
		BackgroundElement bg;
		Level level = new Level(maxX, maxY);
		
		// Hintergrund erzeugen
		for (int x=0; x<maxX; x++) {
			for (int y=0; y < maxY; y++) {
				type = levelMap[y][x];
				bg = createBackgroundElement(type, x, y);
				level.addBg(bg);
			}
		}

		// Hero erzeugen
		Hero hero = new Hero(HERO_START_X, HERO_START_Y);
		level.setHero(hero);
		
		return level;
	}

	/**
	 * Erzeugt ein BackgroundElement
	 * @param c
	 * @param x
	 * @param y
	 * @return
	 */
	private static BackgroundElement createBackgroundElement(BackgroundType type, int x, int y) {
		return new BackgroundElement(type, x, y);
	}
	
	/**
	 * loads the level.
	 * @param level
	 * @return char[][]
	 */
	static BackgroundType[][]loadLevel(final String level) {
		final String[]lines = IOUtils.readLines("level/" + level);
		
		final int columns = lines[0].length();
		
		final BackgroundType[][]array  = new BackgroundType[lines.length][columns];
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for (int j = 0; j < line.length(); j++) {
				array[i][j] = BackgroundType.fromChar(line.charAt(j));
			}
		}
				
		LevelFactory.maxX = columns;
		LevelFactory.maxY = lines.length;
				
		return array;
	}
}
