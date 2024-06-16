package com.mygdx.game.view.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;
import com.mygdx.game.view.screens.optionplayer.OptionPlayer;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    SpaceGame spaceGame;
    ButtonMenu buttonMenu;
    Texture background;

    OptionPlayer optionPlayer;



    public MainMenuScreen(SpaceGame game) {
        this.batch = game.getBatch();
        this.spaceGame = game;
        buttonMenu = new ButtonMenu(game);
        optionPlayer = new OptionPlayer(game, this);
        background = new Texture("otherImage/background.png");
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0 , GameConstant.WINDOW_WIDTH, GameConstant.WINDOW_HEIGHT);
        buttonMenu.draw(optionPlayer);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
