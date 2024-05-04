package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.controller.CharacterMovement;
import com.mygdx.game.controller.Direction;
import com.mygdx.game.controller.CharacterStatus;
import java.lang.Math;

public class Character extends Entity{
    private float STRAIGHT_SPEED = 4; // 4 pixels per frame
    private float DIAGONAL_SPEED = 2.8F;
    private Direction direction;
    private CharacterStatus status;

    public Character(){
        direction = null;
        setPosition(0, 0);
        status = CharacterStatus.IDLE;
    }

    public Character(float x, float y){
        this();
        setPosition(x, y);
    }

    public Character(float speed){
        this();
        setSpeed(speed);
        System.out.printf("%.2f %.2f\n", STRAIGHT_SPEED, DIAGONAL_SPEED);
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

    public float getDIAGONAL_SPEED() {
        return DIAGONAL_SPEED;
    }

    public void setSpeed(float speed) {
        this.STRAIGHT_SPEED = speed;
        this.DIAGONAL_SPEED = (float) Math.sqrt(speed * speed/2);
    }

    public void update(){
        CharacterMovement movement = new CharacterMovement();
        movement.move(this);
    }

    public CharacterStatus getStatus() {
        return status;
    }

    public void setStatus(CharacterStatus status) {
        this.status = status;
    }

    public void draw(Batch batch){
        batch.draw(this.getTexture(), x, y);
    }
}
