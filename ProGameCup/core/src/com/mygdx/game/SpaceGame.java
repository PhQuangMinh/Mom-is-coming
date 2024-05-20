package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.ui.*;
import com.mygdx.game.view.effect.MakeMusic;

public class SpaceGame extends Game {

	SpriteBatch batch;
	NewButton newButton;

	public SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		newButton = new NewButton(this);
		MakeMusic.playMusic("music/SmoothSailing.mp3");
		this.setScreen(new MainGameScreen(this));
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
