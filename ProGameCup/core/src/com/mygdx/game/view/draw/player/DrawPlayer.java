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
    private static final Texture loading_bar_background = new Texture("otherImage/loading_bar_background.png");
    private static final Texture loading_bar_progress = new Texture("otherImage/loading_bar_progress.png");
    private static final TextureRegion loading_bar_start = new TextureRegion(loading_bar_progress, 0, 0, 15, 32);
    private static final TextureRegion loading_bar_body = new TextureRegion(loading_bar_progress, 15, 0, 226, 32);
    private static final TextureRegion loading_bar_end = new TextureRegion(loading_bar_progress, 241, 0, 15, 32);

    float x, y;

    float animationTime;
    float ratio = GameConstant.LOADING_BAR_RATIO;

    private void updateXY(Player player, CharacterStatus status){
        if(status == CharacterStatus.MOPPING_FLOOR){
            x = player.getItemInRange().getX() + (player.getItemInRange().getWidth() - loading_bar_background.getWidth() * ratio)/2;
            y = player.getItemInRange().getY() + 70;
        }
        else{
            x = player.getContainer().getX() + (player.getContainer().getWidth() - loading_bar_background.getWidth() * ratio)/2;
            y = player.getContainer().getY() + 70;
        }
    }

    public void drawBars(Player player, CharacterStatus status, Batch batch){
        updateXY(player, status);
        batch.draw(loading_bar_background, x, y, loading_bar_background.getWidth() * ratio
                , loading_bar_background.getHeight() * ratio);

        batch.draw(loading_bar_start, x, y, loading_bar_start.getRegionWidth() * ratio
                , loading_bar_start.getRegionHeight() * ratio);

        x += loading_bar_start.getRegionWidth() * ratio;
        batch.draw(loading_bar_body, x, y, loading_bar_body.getRegionWidth() * ratio * player.getMovement().getActionCount() / 12
                , loading_bar_start.getRegionHeight() * ratio);

        x += loading_bar_body.getRegionWidth() * ratio * player.getMovement().getActionCount() / 12;
        batch.draw(loading_bar_end, x, y,
                loading_bar_end.getRegionWidth() * ratio, loading_bar_end.getRegionHeight() * ratio);
    }

    public String getAnimationName(Player player, CharacterStatus status, Batch batch) {
        String animationName = "";

        if(status == CharacterStatus.MOPPING_FLOOR || status == CharacterStatus.CLEANING_DISH){
            drawBars(player, status, batch);
        }

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

    private void updateAnimationTime(Animation animation, CharacterStatus status){
        if(animation.isAnimationFinished(animationTime)){
            if(status == CharacterStatus.CLEANING_DISH || status == CharacterStatus.MOPPING_FLOOR){
                if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    animationTime = 0;
                }
            }
            else{
                animationTime = 0;
            }
        }
    }


    private void drawMove(Player player, Batch batch, String animationName, CharacterStatus status, float delta){
        Animation animation = player.getAnimation(animationName);

        float width = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionWidth() / 1.5f;
        float height = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionHeight() / 1.5f;
        player.setSize(width, height);
        batch.draw((TextureRegion) animation.getKeyFrame(animationTime, false), player.getX(), player.getY(), width, height);
        player.getMovement().setAnimationFinished(animation.isAnimationFinished(animationTime));

        updateAnimationTime(animation, status);

        player.getMovement().setAnimationTime(animationTime);

        MakeSound.makeSoundWalk(player, status, delta);
    }

    public void draw(Player player, Batch batch, float delta){
        CharacterStatus status = player.getMovement().getStatus();
        animationTime = player.getMovement().getAnimationTime();
        animationTime += delta;

        String animationName = getAnimationName(player, status, batch);

        if(status == CharacterStatus.IDLE){
            drawIDLE(player, batch, animationName);
        }
        else {
            drawMove(player, batch, animationName, status, delta);
        }
    }
}
