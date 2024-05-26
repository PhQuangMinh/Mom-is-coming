package com.mygdx.game.view.screens.mainstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.view.screens.Impression;

public class MainStory implements Screen {
    private final SpaceGame game;
    private final SpriteBatch batch;
    Impression impression;

    private float stateTime = 0;
    Message message;

    public MainStory(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
    }
    @Override
    public void show() {
        impression = new Impression("story/impression1.png");
        message = new Message("story/press.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        message.update(stateTime);
        impression.drawStory(batch, stateTime);
        message.draw(delta, game, batch, stateTime);
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
