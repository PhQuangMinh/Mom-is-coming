package com.mygdx.game.view.screens.endgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.common.constant.GameConstant;

public class Knock extends Sprite {

    public static boolean isNextMapEndGame = false;
    private Animation<TextureRegion>[] knocking;

    private TextureRegion[] knock;
    private int KNOCK_WIDTH = 52;
    private int KNOCK_HEIGHT = 16;
    private float stateTime;
    public Knock(Texture texture){
        setAnimation(texture);
        stateTime = 0f;
    }
    public void setAnimation(Texture texture){
        knocking = new Animation[5];
        TextureRegion[][] region = TextureRegion.split(texture, KNOCK_WIDTH, KNOCK_HEIGHT);
        for(int i = 0; i < 3; i++){
            knocking[i] = new Animation(0.1f, region[i]);
        }
    }

    public void draw(SpriteBatch batch, float delta){
        stateTime += delta;

        for (int i = 0 ; i < 3; i++) {
            TextureRegion currentFrame = knocking[i].getKeyFrame(stateTime);
            batch.draw(currentFrame, 100, GameConstant.WINDOW_WIDTH / 2 + 30 * (i + 1), KNOCK_WIDTH * 3, KNOCK_HEIGHT * 3);
            if (knocking[i].isAnimationFinished(stateTime)) {
                isNextMapEndGame = true;
            }
        }
    }

}
