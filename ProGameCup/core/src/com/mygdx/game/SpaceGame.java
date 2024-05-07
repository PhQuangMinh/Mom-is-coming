package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.screens.MainGameScreen;
import com.mygdx.game.view.screens.MainMenuScreen;

public class SpaceGame extends Game {

	SpriteBatch batch;

	public SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
