package com.mygdx.game.view.screens.mainstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.effect.MakeSound;
import com.mygdx.game.view.screens.maingame.MainGameScreen;

public class Message {
    private int countMessages = 1;
    private final Texture press;
    private final Texture[] messages;
    private float countDes = 1f;

    Message (String path){
        press = new Texture(path);
        messages = new Texture[10];
        for (int i = 1; i <= 8; i++) {
            messages[i] = new Texture("story/message" + i + ".png");
        }
    }

    public void update(float stateTime){
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) && stateTime >= 3.5){
            if (countMessages<8) countMessages++;
            MakeSound.makeSound("music/press.mp3");
        }
    }

    private void setColor(SpriteBatch batch, float stateTime){
        if (countMessages==8){
            batch.setColor(1f, 1f, 1f, Math.max(countDes / 2f, 0f));
        }
        else batch.setColor(1f, 1f, 1f, Math.min((stateTime - 2) / 2f, 1f));
    }

    private void drawPress(SpriteBatch batch, float stateTime){
        if (stateTime >= 3.5 && Math.abs(stateTime - (int) stateTime) <= 0.5 && countMessages<8) {
            batch.draw(press, (GameConstant.windowWidth - press.getWidth()) / 2, 2);
        }
    }

    public void draw(float delta, SpaceGame game, SpriteBatch batch, float stateTime) {
        if (stateTime < 2) return;
        setColor(batch, stateTime);
        batch.begin();
        batch.draw(messages[countMessages], (GameConstant.windowWidth - messages[countMessages].getWidth()) / 2, 0);
        drawPress(batch, stateTime);

        if (countMessages==8){
            countDes -= delta;
            if (countDes<=0){
                batch.end();
                game.setScreen(new MainGameScreen(game));
                return;
            }
        }
        batch.end();
    }
}
