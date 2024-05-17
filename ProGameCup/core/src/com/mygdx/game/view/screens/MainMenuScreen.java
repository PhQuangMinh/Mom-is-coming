package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.uiingame.*;
//import com.mygdx.game.view.effect.PlaySound;

public class MainMenuScreen implements Screen {
    Texture play, playPress, leaderboard, leaderboardPress, back, backPress, github, githubPress, musicOn, musicOff, musicOnPress, musicOffPress;
    private  SpaceGame game;
    private  SpriteBatch batch;
//    PlaySound playSound;
    DrawText drawText;
    private NewButton newButton;
    int posX = (int)(GameConstant.windowWidth- GameConstant.buttonWidth)/2;

    private void createTexture(){
        play = new Texture("button/play.png");
        playPress = new Texture("button/playPress.png");
        leaderboard = new Texture("button/leaderboard.png");
        leaderboardPress = new Texture("button/leaderboardPress.png");
        back = new Texture("button/back.png");
        backPress = new Texture("button/backPress.png");
        github = new Texture("button/github.png");
        githubPress = new Texture("button/githubPress.png");
        musicOn = new Texture("button/musicOn.png");
        musicOff = new Texture("button/musicOff.png");
    }
    public MainMenuScreen(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        drawText = new DrawText();
        newButton = new NewButton(game);
//        playSound = PlaySound.getInstance(batch);
        createTexture();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        if(newButton.isStopMusic) playSound.stopMusic();
//        else playSound.playMusic();
        batch.begin();
        newButton.drawButton(play, playPress, posX,500, GameConstant.buttonWidth, GameConstant.buttonHeight,1);
        newButton.drawMusicButton(musicOn, musicOff, 800, 800, GameConstant.iconWidth, GameConstant.iconHeight);
        newButton.drawButton(leaderboard, leaderboardPress, posX, 400, GameConstant.buttonWidth, GameConstant.buttonHeight,4);
        newButton.drawButton(back, backPress, posX,  300, GameConstant.buttonWidth, GameConstant.buttonHeight,4);
        newButton.drawButton(github, githubPress, 15,15,GameConstant.iconWidth, GameConstant.iconHeight, 6);
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
