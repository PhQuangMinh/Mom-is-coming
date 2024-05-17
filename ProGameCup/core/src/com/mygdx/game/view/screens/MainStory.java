package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.effect.MakeSound;
import com.mygdx.game.view.screens.MainGameScreen;

public class MainStory implements Screen {
    private final SpaceGame game;
    private final SpriteBatch batch;
    private Texture impression, press;

    private float elapsedTime = 0;

    private int countMessages = 1;

    private float countDes = 1f;

    public MainStory(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
        countDes = 1f;
    }
    @Override
    public void show() {
        impression = new Texture("mainstory/impression1.png");
        press = new Texture("mainstory/press.png");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) && elapsedTime >= 3.5){
            if (countMessages<8) countMessages++;
            MakeSound.makeSound("music/press.mp3");
        }
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        float alpha = Math.min(1f, elapsedTime / 2f);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        float ratio = (float)impression.getWidth() / impression.getHeight();
        float width, height;
        float t = 720;
        if (ratio>1) {
            width = t;
            height = t / ratio;
        }
        else{
            height = t;
            width = t * ratio;
        }
        batch.draw(impression, 80
                , 110, width, height);
        if (elapsedTime >= 2) {
            if (countMessages==8){
                countDes -= delta;
                batch.setColor(1f, 1f, 1f, Math.max(countDes / 2f, 0f));
                if (countDes<=0){
                    batch.end();
                    game.setScreen(new MainGameScreen(game));
                    return;
                }
            }
            else batch.setColor(1f, 1f, 1f, Math.min((elapsedTime - 2) / 2f, 1f));
            Texture message = new Texture("mainstory/message" + countMessages + ".png");
            batch.draw(message, (GameConstant.windowWidth - message.getWidth()) / 2, 0);
            if (elapsedTime >= 3.5 && Math.abs(elapsedTime - (int) elapsedTime) <= 0.5 && countMessages<8) {
                batch.draw(press, (GameConstant.windowWidth - press.getWidth()) / 2, 2);
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
        impression.dispose();
        press.dispose();
    }
}
