package com.mygdx.game.view.draw.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.effect.MakeSound;

public class DrawPlayer {
    float animationTime;

    public String getAnimationName(Player player, CharacterStatus status) {
        String animationName = "";

        if(status != CharacterStatus.CLEANING_DISH && status != CharacterStatus.MOPPING_FLOOR
                && player.getItemHolding()!=null){
            animationName = "HOLDING_";
        }
        animationName += status.name();
        if(status != CharacterStatus.CLEANING_DISH){
            animationName += "_" + player.getDirection().name();
        }
        return  animationName;
    }

    private void drawIDLE(Player player, Batch batch, String animationName){
        TextureRegion region =  player.getTexture(animationName);
        float width = (float) region.getRegionWidth()/1.5f;
        float height = (float) region.getRegionHeight()/1.5f;
        player.setSize(width, height);
        batch.draw(region, player.getX(), player.getY(), width, height);
    }

    private void updateAnimationTime(Animation animation, CharacterStatus status, boolean pressed) {
        if(animation.isAnimationFinished(animationTime)){
            if(status == CharacterStatus.CLEANING_DISH || status == CharacterStatus.MOPPING_FLOOR){
                if(pressed){
                    animationTime = 0;
                }
            }
            else{
                animationTime = 0;
            }
        }
    }


    private void drawMove(Player player, Batch batch, String animationName, CharacterStatus status,
                          float delta, boolean keyPressed){
        Animation animation = player.getAnimation(animationName);

        float width = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionWidth() / 1.5f;
        float height = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionHeight() / 1.5f;
        player.setSize(width, height);
        batch.draw((TextureRegion) animation.getKeyFrame(animationTime, false), player.getX(), player.getY(), width, height);
        player.getMovement().setAnimationFinished(animation.isAnimationFinished(animationTime));

        updateAnimationTime(animation, status, keyPressed);

        player.getMovement().setAnimationTime(animationTime);

        MakeSound.makeSoundWalk(player, status, delta);
    }

    public void draw(Player player, Batch batch, float delta, boolean keyPressed){
        CharacterStatus status = player.getMovement().getStatus();
        animationTime = player.getMovement().getAnimationTime();
        animationTime += delta;

        String animationName = getAnimationName(player, status);

        if(status == CharacterStatus.IDLE){
            drawIDLE(player, batch, animationName);
        }
        else {
            drawMove(player, batch, animationName, status, delta, keyPressed);
        }
    }
}
