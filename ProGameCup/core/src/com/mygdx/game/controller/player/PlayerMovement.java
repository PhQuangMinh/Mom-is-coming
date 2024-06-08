package com.mygdx.game.controller.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.collision.Collision;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.effect.MakeSound;

import java.util.ArrayList;

public class PlayerMovement {
    private static Direction direction;
    private static CharacterStatus status;
    private static Animation animation;
    private static float animationTime = 0;
    private static int soundIndex = 0;
    public static int actionCount;
    private static boolean isAnimationFinished = true;
    private static int cleanTime = 12;
    private static final Texture loading_bar_background = new Texture("otherImage/loading_bar_background.png");
    private static final Texture loading_bar_progress = new Texture("otherImage/loading_bar_progress.png");
    private static final TextureRegion loading_bar_start = new TextureRegion(loading_bar_progress, 0, 0, 15, 32);
    private static final TextureRegion loading_bar_body = new TextureRegion(loading_bar_progress, 15, 0, 226, 32);
    private static final TextureRegion loading_bar_end = new TextureRegion(loading_bar_progress, 241, 0, 15, 32);

    private static void controlHandle(Player player, ArrayList<StaticItem> staticItems, ArrayList<DynamicItem> dynamicItems){
        boolean isLeftKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT);
        boolean isRightKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean isUpKeyPressed = Gdx.input.isKeyPressed(Keys.UP);
        boolean isDownKeyPressed = Gdx.input.isKeyPressed(Keys.DOWN);

        if(status == CharacterStatus.CLEANING_DISH || status == CharacterStatus.MOPPING_FLOOR){
            if(Gdx.input.isKeyJustPressed(Keys.X) && isAnimationFinished){
                actionCount++;

                MakeSound.makeSound("sounds/soSqueak" + (actionCount % 5 + 1) + ".ogg", 1f);
                if(actionCount == cleanTime){
                    if(status == CharacterStatus.MOPPING_FLOOR){
                        player.setStatusHold(1);
                        ((DynamicItem)player.getItemHolding()).setVisible(true);
                        // xóa vũng nước
                        DynamicItem puddle = (DynamicItem)player.getItemInRange();
                        puddle.setVisible(false);
                        dynamicItems.remove(puddle);
                        player.setItemInRange(null);
                    }
                    else {
                        direction = Direction.UP;
                        // xóa đĩa đã được rửa xong
                        dynamicItems.remove((DynamicItem) player.getItemHolding());
                        player.setItemHolding(null);
                    }

                    status = CharacterStatus.IDLE;
                    player.setValidThrow(true);
                }
            }
        }
        else if(!isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && !isDownKeyPressed) {
            status = CharacterStatus.IDLE;
        }
        else {
            status = CharacterStatus.WALKING;
            if(isLeftKeyPressed && !isRightKeyPressed && isUpKeyPressed && !isDownKeyPressed) {
                direction = Direction.UPLEFT;
            }
            else if(isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && isDownKeyPressed) {
                direction = Direction.DOWNLEFT;
            }
            else if(!isLeftKeyPressed && isRightKeyPressed && isUpKeyPressed && !isDownKeyPressed) {
                direction = Direction.UPRIGHT;
            }
            else if(!isLeftKeyPressed && isRightKeyPressed && !isUpKeyPressed && isDownKeyPressed) {
                direction = Direction.DOWNRIGHT;
            }
            else if(isLeftKeyPressed) {
                direction = Direction.LEFT;
            }
            else if(isRightKeyPressed) {
                direction = Direction.RIGHT;
            }
            else if(isUpKeyPressed) {
                direction = Direction.UP;
            }
            else {
                direction = Direction.DOWN;
            }
        }
    }

    private static Vector2 getNewPosition(float x, float y, Player player) {
        float straight = player.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        float diagonal = player.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();

        if(direction == Direction.UP){
            y += straight;
        }
        if(direction == Direction.DOWN){
            y -= straight;
        }
        if(direction == Direction.LEFT){
            x -= straight;
        }
        if(direction == Direction.RIGHT){
            x += straight;
        }
        if(direction == Direction.UPLEFT){
            y += diagonal;
            x -= diagonal;
        }
        if(direction == Direction.UPRIGHT){
            y += diagonal;
            x += diagonal;
        }
        if(direction == Direction.DOWNLEFT){
            y -= diagonal;
            x -= diagonal;
        }
        if(direction == Direction.DOWNRIGHT){
            y -= diagonal;
            x += diagonal;
        }
        return new Vector2(x, y);
    }

    private static void setDirection(Player player){
        if (direction == Direction.UP || direction == Direction.DOWN
                || direction == Direction.LEFT || direction == Direction.RIGHT)
            player.setDirection(direction);
        if (direction == Direction.UPLEFT || direction == Direction.DOWNLEFT)
            player.setDirection(Direction.LEFT);
        if (direction == Direction.UPRIGHT || direction == Direction.DOWNRIGHT)
            player.setDirection(Direction.RIGHT);
    }

    public static void move(Player player, ArrayList<StaticItem> staticItems
            , ArrayList<DynamicItem> dynamicItems, float stateTime) {
        direction = player.getDirection();
        status = player.getStatus();
        controlHandle(player, staticItems, dynamicItems);

        Vector2 oldPosition = new Vector2(player.getX(), player.getY());
        Vector2 newPosition = getNewPosition(player.getX(), player.getY(), player);

        Collision collision = new Collision();
        collision.updatePosition(newPosition, oldPosition, staticItems, dynamicItems, player);

        if(status == CharacterStatus.WALKING){
            player.setPosition(newPosition.x, newPosition.y);
        }
        player.setStatus(status);
        setDirection(player);
    }

    public static void draw(Player player, Batch batch, float delta){
        boolean isHoldingItem = (player.getItemHolding() != null);
        CharacterStatus status = player.getStatus();
        Direction direction = player.getDirection();
        float soundRepeatTime = animationTime % 0.3f;
        animationTime += delta;

        String animationName = "";

        if(status == CharacterStatus.MOPPING_FLOOR || status == CharacterStatus.CLEANING_DISH){
            float x, y;
            if(status == CharacterStatus.MOPPING_FLOOR){
                x = player.getItemInRange().getX() + (player.getItemInRange().getWidth() - loading_bar_background.getWidth() * GameConstant.loading_bar_ratio)/2;
                y = player.getItemInRange().getY() + 70;
            }
            else{
                x = player.getContainer().getX() + (player.getContainer().getWidth() - loading_bar_background.getWidth() * GameConstant.loading_bar_ratio)/2;
                y = player.getContainer().getY() + 70;
            }
            batch.draw(loading_bar_background, x, y,
                    loading_bar_background.getWidth() * GameConstant.loading_bar_ratio, loading_bar_background.getHeight() * GameConstant.loading_bar_ratio);

            batch.draw(loading_bar_start, x, y,
                    loading_bar_start.getRegionWidth() * GameConstant.loading_bar_ratio, loading_bar_start.getRegionHeight() * GameConstant.loading_bar_ratio);

            x += loading_bar_start.getRegionWidth() * GameConstant.loading_bar_ratio;
            batch.draw(loading_bar_body, x, y,
                    loading_bar_body.getRegionWidth() * GameConstant.loading_bar_ratio * actionCount / 12, loading_bar_start.getRegionHeight() * GameConstant.loading_bar_ratio);

            x += loading_bar_body.getRegionWidth() * GameConstant.loading_bar_ratio * actionCount / 12;
            batch.draw(loading_bar_end, x, y,
                    loading_bar_end.getRegionWidth() * GameConstant.loading_bar_ratio, loading_bar_end.getRegionHeight() * GameConstant.loading_bar_ratio);
        }

        if(status != CharacterStatus.CLEANING_DISH && status != CharacterStatus.MOPPING_FLOOR && isHoldingItem){
            animationName = "HOLDING_";
        }
        animationName += status.name();
        if(status != CharacterStatus.CLEANING_DISH){
            animationName += "_" + direction.name();
        }

        if(status == CharacterStatus.IDLE){
            TextureRegion region =  player.getTexture(animationName);
            float width = (float) region.getRegionWidth()/1.5f;
            float height = (float) region.getRegionHeight()/1.5f;
            batch.draw(region, player.getX(), player.getY(), width, height);
        }
        else {
            animation = player.getAnimation(animationName);

            float width = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionWidth() / 1.5f;
            float height = (float) ((TextureRegion) animation.getKeyFrame(animationTime)).getRegionHeight() / 1.5f;
            batch.draw((TextureRegion) animation.getKeyFrame(animationTime, false), player.getX(), player.getY(), width, height);
            isAnimationFinished = animation.isAnimationFinished(animationTime);

            if(animation.isAnimationFinished(animationTime)){
                if(status == CharacterStatus.CLEANING_DISH || status == CharacterStatus.MOPPING_FLOOR){
                    if(Gdx.input.isKeyJustPressed(Keys.X)){
                        animationTime = 0;
                    }
                }
                else{
                    animationTime = 0;
                }
            }

            if(status == CharacterStatus.WALKING){
                soundRepeatTime += delta;
                if(soundRepeatTime > 0.3f) {
                    soundIndex = soundIndex % 5;
                    soundIndex++;
                    MakeSound.makeSound("sounds/soStep" + soundIndex + ".ogg", 0.6f);
                }
            }
        }
    }
}