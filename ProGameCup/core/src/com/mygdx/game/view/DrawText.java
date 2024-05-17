package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.screens.MainGameScreen;
import com.mygdx.game.view.screens.MainMenuScreen;

public class DrawText {
    BitmapFont charFont;
    public void setCharFont(String path, Color color){
        charFont = new BitmapFont(Gdx.files.internal(path));
        charFont.setColor(color);
    }
    public void drawStaticText(SpriteBatch batch, String text, float x, float y, float size, String path, Color color) {
        setCharFont(path, color);
        charFont.getData().setScale(size);
        charFont.draw(batch,text, x, y);
    }


    public void drawClock(SpaceGame game, SpriteBatch batch, float stateTime, int minutes
            , int seconds, float x, float y, float size, String path, Color color){
        setCharFont(path, color);
        charFont.getData().setScale(size);

        float countdownTime = minutes * 60 + seconds;
        float timeLeft = countdownTime - stateTime;
        if(timeLeft <= 0){
            game.setScreen(new MainMenuScreen(game));
        }

        int remainMinutes = (int) (timeLeft / 60);
        int remainSeconds = (int) (timeLeft % 60);

        charFont.draw(batch, String.format("%02d", remainMinutes) + ":" + String.format("%02d", remainSeconds),x, y );

    }
}
