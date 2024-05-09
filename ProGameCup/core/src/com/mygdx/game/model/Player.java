package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.PlayerMovement;
import com.mygdx.game.controller.constant.Direction;
import com.mygdx.game.controller.constant.CharacterStatus;
import java.lang.Math;
import java.util.ArrayList;

public class Player extends Sprite {
    private float STRAIGHT_SPEED = 4; // 4 pixels per frame
    private float DIAGONAL_SPEED = 2.8F;
    public static int CHARACTER_WIDTH = 16;
    public static int CHARACTER_HEIGHT = 20;
    private Direction direction;
    private CharacterStatus status;
    private Animation[] walking;
    private TextureRegion[] idleTexture;

    private boolean overlap;

    public Player(){
        direction = Direction.DOWN;
        status = CharacterStatus.IDLE;
        setPosition(0, 0);
    }

    public Player(Texture texture, float x, float y, float width, float height, float speed){
        this();
        setTexture(texture);
        setPosition(x, y);
        setSpeed(speed);
        setAnimations(texture);
        setSize(width, height);
    }

    public void setAnimations(Texture texture) {
        walking = new Animation[10];
        idleTexture = new TextureRegion[10];
        TextureRegion[][] region = TextureRegion.split(texture, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        for(int i = 0; i < 4; ++i){
            walking[i] = new Animation(0.2f, region[i]);
            idleTexture[i] = region[i][1];
        }
    }

    public boolean getOverlap() {
        return overlap;
    }

    public void setOverlap(boolean overlap) {
        this.overlap = overlap;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CharacterStatus getStatus() {
        return status;
    }

    public void setStatus(CharacterStatus status) {
        this.status = status;
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

    public void update(MapObjects mapObjects, ArrayList<Item> items){
        PlayerMovement movement = new PlayerMovement();
        movement.move(this, mapObjects, items);
    }

    public void draw(Batch batch, float stateTime){
        int index;

        if(direction == Direction.DOWN) index = 0;
        else if(direction == Direction.LEFT || direction == Direction.DOWNLEFT || direction == Direction.UPLEFT) index = 1;
        else if(direction == Direction.RIGHT || direction == Direction.DOWNRIGHT || direction == Direction.UPRIGHT) index = 2;
        else index = 3;

        if(status == CharacterStatus.IDLE){
            batch.draw(idleTexture[index], this.getX(), this.getY(), GameConstant.playerWidth, GameConstant.playerHeight);
        }
        else if(status == CharacterStatus.WALKING){
            batch.draw((TextureRegion) walking[index].getKeyFrame(stateTime, true), this.getX(), this.getY(), GameConstant.playerWidth, GameConstant.playerHeight);
        }
    }
}
