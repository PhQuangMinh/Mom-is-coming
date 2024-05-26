package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.common.constant.GameConstant;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Good Impressions");
		config.setResizable(false);
		config.setWindowedMode((int) GameConstant.WINDOW_WIDTH, (int)GameConstant.WINDOW_HEIGHT);
		config.setWindowIcon("logo.png");
		new Lwjgl3Application(new SpaceGame(), config);
	}
}
