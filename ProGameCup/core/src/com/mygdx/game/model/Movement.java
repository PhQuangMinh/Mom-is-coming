package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;

import java.util.ArrayList;

public class Movement {
    private Direction direction;
    private CharacterStatus status;
    private Animation animation;
    private float animationTime = 0;
    private int soundIndex = 0;
    private int actionCount;
    private boolean isAnimationFinished = true;
    private int cleanTime = 12;

    public Movement() {
        direction = Direction.LEFT;
        status = CharacterStatus.IDLE;
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

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }

    public int getSoundIndex() {
        return soundIndex;
    }

    public void setSoundIndex(int soundIndex) {
        this.soundIndex = soundIndex;
    }

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public boolean isAnimationFinished() {
        return isAnimationFinished;
    }

    public void setAnimationFinished(boolean animationFinished) {
        isAnimationFinished = animationFinished;
    }

    public int getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(int cleanTime) {
        this.cleanTime = cleanTime;
    }
}
