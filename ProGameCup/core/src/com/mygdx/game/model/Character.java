package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.controller.CharacterMovement;
import com.mygdx.game.controller.Direction;

public class Character extends Entity{
    private float STRAIGHT_SPEED = 4; // 4 pixels per frame
    private float DIAGONAL_SPEED = 2.8F;
    private Direction direction;

    public Character(){
        direction = null;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getSTRAIGHT_SPEED() {
        return STRAIGHT_SPEED;
    }

    public void setSTRAIGHT_SPEED(float STRAIGHT_SPEED) {
        this.STRAIGHT_SPEED = STRAIGHT_SPEED;
    }

    public float getDIAGONAL_SPEED() {
        return DIAGONAL_SPEED;
    }

    public void setDIAGONAL_SPEED(float DIAGONAL_SPEED) {
        this.DIAGONAL_SPEED = DIAGONAL_SPEED;
    }

    public void update(){
        CharacterMovement movement = new CharacterMovement();
        movement.move(this);
    }

    public void draw(Batch batch){
        batch.draw(this.getTexture(), x, y);
    }
}
