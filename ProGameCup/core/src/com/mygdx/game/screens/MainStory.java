package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;

public class MainStory implements Screen {
    private SpaceGame game;
    private SpriteBatch batch;
    private Texture mainStory;

    public MainStory(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
    }
    @Override
    public void show() {
        mainStory = new Texture("mainstory/mainstory.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(mainStory, 0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.X)){
            game.setScreen(new MainMenuScreen(game));
        }
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
