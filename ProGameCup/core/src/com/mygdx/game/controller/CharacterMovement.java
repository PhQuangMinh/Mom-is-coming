package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterMovement implements Movable {
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

    @Override
    public void move(Character character) {
        moveDirection();

        float x = character.getX();
        float y = character.getY();
        if(direction == Direction.UP){
            y += character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.DOWN){
            y -= character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.LEFT){
            x -= character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.RIGHT){
            x += character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.UPLEFT){
            y += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            x -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.UPRIGHT){
            y += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            x += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.DOWNLEFT){
            y -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            x -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if(direction == Direction.DOWNRIGHT){
            y -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            x += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        character.setPosition(x, y);
        character.setStatus(status);
        if(status != CharacterStatus.IDLE)
            character.setDirection(direction);
    }
}
