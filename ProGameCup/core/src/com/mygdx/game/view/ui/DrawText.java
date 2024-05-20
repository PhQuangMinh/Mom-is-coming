package com.mygdx.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;

public class DrawText {
    BitmapFont charFont;

    public DrawText(String path, Color color){
        setCharFont(path, color);
    }

    public void setCharFont(String path, Color color){
        charFont = new BitmapFont(Gdx.files.internal(path));
        charFont.setColor(color);
    }

    public void drawStaticText(SpriteBatch batch, String text, float x, float y, float size){
        charFont.getData().setScale(size);
        charFont.draw(batch,text, x, y);
    }


    public void drawClock(SpaceGame game, SpriteBatch batch, float stateTime, int minutes, int seconds, float x, float y, float size){
        charFont.getData().setScale(size/2);

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
