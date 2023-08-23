package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.TankStars;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("TankStars");
		config.useVsync(true);
		config.setWindowedMode(1000,550);
		config.setForegroundFPS(60);
		config.setTitle("TankStars");
		new Lwjgl3Application(TankStars.getInstance(), config);
	}
}

