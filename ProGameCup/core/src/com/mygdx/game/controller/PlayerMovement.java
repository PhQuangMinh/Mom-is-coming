package com.mygdx.game.controller;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.constant.CharacterStatus;
import com.mygdx.game.controller.constant.Direction;
import com.mygdx.game.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class PlayerMovement {
    Direction direction;
    CharacterStatus status;
    boolean isLeftKeyPressed;
    boolean isRightKeyPressed;
    boolean isUpKeyPressed;
    boolean isDownKeyPressed;

    private void moveDirection(){
        direction = null;
        status = null;

        isLeftKeyPressed = Gdx.input.isKeyPressed(Keys.LEFT);
        isRightKeyPressed = Gdx.input.isKeyPressed(Keys.RIGHT);
        isUpKeyPressed = Gdx.input.isKeyPressed(Keys.UP);
        isDownKeyPressed = Gdx.input.isKeyPressed(Keys.DOWN);

        if(!isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && !isDownKeyPressed) {
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

    private Vector2 getNewPosition(float x, float y, Player player) {
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

    private void setDirection(Player player){
        if (direction == Direction.UP || direction == Direction.DOWN
                || direction == Direction.LEFT || direction == Direction.RIGHT)
            player.setDirection(direction);
        if (direction == Direction.UPLEFT || direction == Direction.DOWNLEFT)
            player.setDirection(Direction.LEFT);
        if (direction == Direction.UPRIGHT || direction == Direction.DOWNRIGHT)
            player.setDirection(Direction.RIGHT);
    }

    public void move(Player player, MapObjects mapObjects, ArrayList<StaticItem> staticItems) {
        moveDirection();
        Vector2 oldPosition = new Vector2(player.getX(), player.getY());
        Vector2 newPosition = getNewPosition(player.getX(), player.getY(), player);

        CheckCollision checkCollision = new CheckCollision();
        checkCollision.updatePosition(newPosition, oldPosition, mapObjects, staticItems);

        setDirection(player);

        player.setPosition(newPosition.x, newPosition.y);
        player.setStatus(status);
//        if(status != CharacterStatus.IDLE)
//            player.setDirection(direction);
    }
}
