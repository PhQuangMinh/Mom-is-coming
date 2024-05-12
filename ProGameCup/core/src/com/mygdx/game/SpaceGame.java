package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.NewButton;
import com.mygdx.game.view.music.PlaySound;
import com.mygdx.game.view.screens.MainMenuScreen;

public class SpaceGame extends Game {

	SpriteBatch batch;
	PlaySound playSound;
	NewButton newButton;

	public SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		newButton = new NewButton(this);

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
