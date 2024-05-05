package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;

public class MainMenuScreen implements Screen {
    Texture play, playPress, options, optionsPress, quit, quitPress;
    private final SpaceGame game;

    private final SpriteBatch batch;

    private void drawButton(Texture button, Texture buttonPress, int y, int choice){
        int x = (int)(GameConstant.windowWidth- GameConstant.buttonWidth)/2;
        if (Gdx.input.getX()>=x && Gdx.input.getX()<=x+ GameConstant.buttonWidth && GameConstant.windowHeight-Gdx.input.getY()>=y && GameConstant.windowHeight-Gdx.input.getY()<=y+ GameConstant.buttonHeight){
            batch.draw(buttonPress, (GameConstant.windowWidth - GameConstant.buttonWidth) /2, y, GameConstant.buttonWidth, GameConstant.buttonHeight);
            if (Gdx.input.isTouched()){
                if (choice==1){
                    game.setScreen(new MainGameScreen(game));
                }
                else{
                    if (choice==2){
                        //options
                    }
                    else{
                        Gdx.app.exit();
                    }
                }
            }
        }
        else{
            batch.draw(button, (GameConstant.windowWidth - GameConstant.buttonWidth) /2, y, GameConstant.buttonWidth, GameConstant.buttonHeight);
        }
    }

    private void createTexture(){
        play = new Texture("button/play.png");
        playPress = new Texture("button/playpress.png");
        options = new Texture("button/options.png");
        optionsPress = new Texture("button/optionspress.png");
        quit = new Texture("button/quit.png");
        quitPress = new Texture("button/quitpress.png");
    }

    public MainMenuScreen(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        createTexture();

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawButton(play, playPress, 500, 1);
        drawButton(options, optionsPress, 400, 2);
        drawButton(quit, quitPress, 300, 3);
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
