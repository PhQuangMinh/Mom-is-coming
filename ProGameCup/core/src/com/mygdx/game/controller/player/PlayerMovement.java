package com.mygdx.game.controller.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.collision.Collision;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class PlayerMovement {
    private static Direction direction;
    private static CharacterStatus status;

    private static void controlHandle(Player player, float stateTime){
        boolean isLeftKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT);
        boolean isRightKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean isUpKeyPressed = Gdx.input.isKeyPressed(Keys.UP);
        boolean isDownKeyPressed = Gdx.input.isKeyPressed(Keys.DOWN);

        if(status == CharacterStatus.MOPPING_FLOOR || status == CharacterStatus.CLEANING_DISH){}
        else if(Gdx.input.isKeyPressed(Keys.C)){
            direction = Direction.UP;
            status = CharacterStatus.CLEANING_DISH;
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
        controlHandle(player, stateTime);

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

    public static void draw(Player player, Batch batch, float stateTime){
        boolean isHoldingItem = (player.getItemHolding() != null);
        CharacterStatus status = player.getStatus();
        Direction direction = player.getDirection();

        if(status == CharacterStatus.CLEANING_DISH || status == CharacterStatus.MOPPING_FLOOR){
            String animationName = status.name();
            if(status == CharacterStatus.MOPPING_FLOOR)
                animationName += "_" + direction.name();

            TextureRegion texture = player.getAnimationFrame(animationName, player.getFrameIndex());

            float width = (float) texture.getRegionWidth()/1.5f;
            float height = (float) texture.getRegionHeight()/1.5f;
            batch.draw(texture, player.getX(), player.getY(), width, height);
            return;
        }

        if(status == CharacterStatus.IDLE){
            TextureRegion region =  player.getTexture((isHoldingItem ? "HOLDING_" : "") + status.name() + "_" + direction.name());
            float width = (float) region.getRegionWidth()/1.5f;
            float height = (float) region.getRegionHeight()/1.5f;
            batch.draw(region, player.getX(), player.getY(), width, height);
        }
        else {
            String animationName = "";
            if (status == CharacterStatus.WALKING)
                animationName = (isHoldingItem ? "HOLDING_" : "");
            animationName += status.name();

            if (direction == Direction.LEFT || direction == Direction.UPLEFT || direction == Direction.DOWNLEFT)
                animationName += "_LEFT";
            else if (direction == Direction.RIGHT || direction == Direction.UPRIGHT || direction == Direction.DOWNRIGHT)
                animationName += "_RIGHT";
            else if (direction == Direction.UP)
                animationName += "_UP";
            else if (direction == Direction.DOWN)
                animationName += "_DOWN";
            Animation animation = player.getAnimation(animationName);

            float width = (float) ((TextureRegion) animation.getKeyFrame(stateTime)).getRegionWidth() / 1.5f;
            float height = (float) ((TextureRegion) animation.getKeyFrame(stateTime)).getRegionHeight() / 1.5f;
            batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), player.getX(), player.getY(), width, height);
        }
    }
}