package com.mygdx.game.controller;

import com.mygdx.game.model.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterMovement implements Movable {
    Direction direction;

    @Override
    public void move(Character character) {
        direction = null;
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            direction = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            direction = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) {
            direction = Direction.UPLEFT;
        }
        if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
            direction = Direction.UPRIGHT;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)) {
            direction = Direction.DOWNLEFT;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
            direction = Direction.DOWNRIGHT;
        }

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
    }
}
