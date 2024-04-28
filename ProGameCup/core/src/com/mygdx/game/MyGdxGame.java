package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.Character;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 80;
	private static final int PLAY_BUTTON_X = 250;
	private static final int PLAY_BUTTON_Y = 250;
	private static final int OPTIONS_BUTTON_X = 250;
	private static final int OPTIONS_BUTTON_Y = 250;
	private static final int QUIT_BUTTON_X = 250;
	private static final int QUIT_BUTTON_Y = 250;

	SpriteBatch batch;
	Texture play, playPress, options, optionsPress, quit, quitPress, pl;
	Character player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		play = new Texture("button/play.png");
		playPress = new Texture("button/playpress.png");
		options = new Texture("button/options.png");
		optionsPress = new Texture("button/optionspress.png");
		quit = new Texture("button/quit.png");
		quitPress = new Texture("button/quitpress.png");
		pl = new Texture("character/example.png");

		player = new Character();
		float x = (float) WINDOW_WIDTH / 2;
		float y = (float) WINDOW_HEIGHT / 2;
		player.setPosition(x, y);
		player.setTexture(pl);
	}

	private void drawButton(Texture button, Texture buttonPress, int y, int width, int height){
		int x = (WINDOW_WIDTH-width)/2;
		if (Gdx.input.getX()>=x && Gdx.input.getX()<=x+width && WINDOW_HEIGHT-Gdx.input.getY()>=y && WINDOW_HEIGHT-Gdx.input.getY()<=y+height){
			batch.draw(buttonPress, (float) (WINDOW_WIDTH - width) /2, y, width, height);
		}
		else{
            batch.draw(button, (float) (WINDOW_WIDTH - width) /2, y, width, height);
        }
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		drawButton(play, playPress, 500, BUTTON_WIDTH, BUTTON_HEIGHT);
		drawButton(options, optionsPress, 400, BUTTON_WIDTH, BUTTON_HEIGHT);
		drawButton(quit, quitPress, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		player.draw(batch);
		batch.end();

		player.update();
	}

	@Override
	public void dispose () {
		batch.dispose();
		play.dispose();
	}
}
