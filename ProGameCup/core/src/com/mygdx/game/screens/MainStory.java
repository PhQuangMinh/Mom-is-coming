package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;

public class MainStory implements Screen {
    private final SpaceGame game;
    private final SpriteBatch batch;
    private Texture impression;
    private Texture press;

    private float elapsedTime = 0;

    private int countMessages = 1;

    public MainStory(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
    }
    @Override
    public void show() {
        impression = new Texture("mainstory/impression.png");
        press = new Texture("mainstory/press.png");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) && elapsedTime>=3.5){
            countMessages++;
            if (countMessages==9) {
                game.setScreen(new MainGameScreen(game));
                return;
            }
        }
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        float alpha = Math.min(1f, elapsedTime / 2f);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(impression, (float) (SpaceGame.WINDOW_WIDTH-impression.getWidth())/2, (float)(SpaceGame.WINDOW_HEIGHT-impression.getHeight())/2+50);
        if (elapsedTime>=2){
            batch.setColor(1f, 1f, 1f, Math.min((elapsedTime-2)/2f, 1f));
            Texture message = new Texture("mainstory/message" + countMessages + ".png");
            batch.draw(message, (float) (SpaceGame.WINDOW_WIDTH- message.getWidth())/2, 0);
            if (elapsedTime>=3.5 && Math.abs(elapsedTime-(int)elapsedTime)<=0.5){
                batch.draw(press, (float)(SpaceGame.WINDOW_WIDTH-press.getWidth())/2, 2);
            }
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
