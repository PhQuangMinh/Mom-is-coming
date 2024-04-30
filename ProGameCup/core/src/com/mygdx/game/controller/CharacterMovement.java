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
        //up left
        if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) {
            direction = Direction.UPLEFT;
        }
        //up Gdx.input.isKeyPressed(Keys.RIGHT)
        if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
            direction = Direction.UPRIGHT;
        }
        //down left
        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)) {
            direction = Direction.DOWNLEFT;
        }
        //down right
        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
            direction = Direction.DOWNRIGHT;
        }


        float x = character.getX();
        float y = character.getY();
        if(direction == Direction.UP){
            y += character.getSTRAIGHT_SPEED();
        }
        if(direction == Direction.DOWN){
            y -= character.getSTRAIGHT_SPEED();
        }
        if(direction == Direction.LEFT){
            x -= character.getSTRAIGHT_SPEED();
        }
        if(direction == Direction.RIGHT){
            x += character.getSTRAIGHT_SPEED();
        }
        if(direction == Direction.UPLEFT){
            y += character.getDIAGONAL_SPEED();
            x -= character.getDIAGONAL_SPEED();
        }
        if(direction == Direction.UPRIGHT){
            y += character.getDIAGONAL_SPEED();
            x += character.getDIAGONAL_SPEED();
        }
        if(direction == Direction.DOWNLEFT){
            y -= character.getDIAGONAL_SPEED();
            x -= character.getDIAGONAL_SPEED();
        }
        if(direction == Direction.DOWNRIGHT){
            y -= character.getDIAGONAL_SPEED();
            x += character.getDIAGONAL_SPEED();
        }
//        switch (direction) {
//            case UP:
//                x += character.getSTRAIGHT_SPEED();
//                break;
//            case DOWN:
//                x -= character.getSTRAIGHT_SPEED();
//                break;
//            case LEFT:
//                y -= character.getSTRAIGHT_SPEED();
//                break;
//            case RIGHT:
//                y += character.getSTRAIGHT_SPEED();
//                break;
//            case UPLEFT:
//                x += character.getDIAGONAL_SPEED();
//                y -= character.getDIAGONAL_SPEED();
//                break;
//            case UPRIGHT:
//                x += character.getDIAGONAL_SPEED();
//                y += character.getDIAGONAL_SPEED();
//                break;
//            case DOWNLEFT:
//                x -= character.getDIAGONAL_SPEED();
//                y -= character.getDIAGONAL_SPEED();
//                break;
//            case DOWNRIGHT:
//                x -= character.getDIAGONAL_SPEED();
//                y += character.getDIAGONAL_SPEED();
//                break;
//        }
        character.setPosition(x, y);
    }
}
