package com.mygdx.game.view.screens.optionplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.screens.maingame.multiplayer.MultiPlayer;
import com.mygdx.game.view.screens.maingame.singleplayer.SinglePlayer;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;

public class OptionPlayer implements Screen {
    private final SpaceGame game;
    private final SpriteBatch batch;

    ButtonOption buttonOption;

    MainMenuScreen mainMenuScreen;

    SinglePlayer singlePlayer;

    MultiPlayer multiPlayer;

    public OptionPlayer(SpaceGame game, MainMenuScreen mainMenuScreen){
        this.game = game;
        this.batch = game.getBatch();
        this.mainMenuScreen = mainMenuScreen;
        singlePlayer = new SinglePlayer(game, mainMenuScreen);
        multiPlayer = new MultiPlayer(game, mainMenuScreen);
        buttonOption = new ButtonOption(game, mainMenuScreen, singlePlayer, multiPlayer);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        buttonOption.draw();
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
