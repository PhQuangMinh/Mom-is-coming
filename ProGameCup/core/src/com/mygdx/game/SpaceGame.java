package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;

public class SpaceGame extends Game {

	SpriteBatch batch;

	public SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		MakeMusic.playMusic("music/SmoothSailing.mp3");
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
