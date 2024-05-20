package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.FrameStatus;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.lang.Math;
import java.util.LinkedHashMap;

public class Player extends Sprite {
    private float STRAIGHT_SPEED = 4;
    private float DIAGONAL_SPEED = 2.8F;
    private Direction direction;
    private CharacterStatus status;

    private boolean overlap, validThrow;
    private Item itemHolding;
    private StaticItem container;
    private Vector2 positionThrew;
    private int statusHold;
    // statusHold = 4: đang cầm đồ vật bằng vải đứng trên vũng nước, chuẩn bị lau
    private int frameIndex;
    private boolean isCountingXPress;

    private LinkedHashMap<String, Animation> animations;
    private LinkedHashMap<String, TextureRegion> textures;

    private FrameStatus frameStatus;

    public Player(){
        direction = Direction.DOWN;
        status = CharacterStatus.IDLE;
        setPosition(0, 0);
    }

    public Player(TextureAtlas atlas, String[] animationNames, String[] textureNames, float x, float y, float width, float height, float speed){
        this();
        setAnimation(atlas, animationNames);
        setTextures(atlas, textureNames);
        setPosition(x, y);
        setSpeed(speed);
        setSize(width, height);
        frameStatus = FrameStatus.RIGHT_FRAME;
    }

    public void setAnimation(TextureAtlas atlas, String[] animationNames){
        animations = new LinkedHashMap<>();
        for(String s : animationNames){
            TextureRegion[] frames = atlas.findRegions(s).toArray();
            Animation ani = new Animation(0.15f, frames);
            animations.put(s, ani);
        }
    }

    public void setTextures(TextureAtlas atlas, String[] textureNames){
        textures = new LinkedHashMap<>();
        for(String s : textureNames){
            TextureRegion texture = atlas.findRegion(s);
            textures.put(s, texture);
        }
    }

    public Animation getAnimation(String animationName){
        if(animations.containsKey(animationName)){
            Animation animation = animations.get(animationName);
            return animation;
        }
        System.out.println("Animation not found");
        return null;
    }

    public TextureRegion getTexture(String textureName){
        if(textures.containsKey(textureName))
            return textures.get(textureName);
        System.out.println("Texture not found");
        return null;
    }

    public TextureRegion getAnimationFrame(String animationName, int index){
        if(animations.containsKey(animationName)){
            TextureRegion[] region = (TextureRegion[]) getAnimation(animationName).getKeyFrames();
            index %= region.length;
            return region[index];
        }
        System.out.println("Animation not found");
        return null;
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }

    public void setFrameStatus(FrameStatus frameStatus) {
        this.frameStatus = frameStatus;
    }
    public Vector2 getPositionThrew() {
        return positionThrew;
    }

    public void setPositionThrew(Vector2 positionThrew) {
        this.positionThrew = positionThrew;
    }

    public boolean isValidThrow() {
        return validThrow;
    }
    public void setValidThrow(boolean validThrow) {
        this.validThrow = validThrow;
    }

    public int getStatusHold() {
        return statusHold;
    }

    public void setStatusHold(int statusHold) {
        this.statusHold = statusHold;
    }

    public StaticItem getContainer() {
        return container;
    }

    public void setContainer(StaticItem container) {
        this.container = container;
    }

    public Item getItemHolding() {
        return itemHolding;
    }

    public void setItemHolding(Item itemHolding) {
        this.itemHolding = itemHolding;
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

    public int getFrameIndex() {
        return frameIndex;
    }

    public void setFrameIndex(int frameIndex) {
        this.frameIndex = frameIndex;
    }

    public boolean getIsCountingXPress() {
        return isCountingXPress;
    }

    public void setIsCountingXPress(boolean countingXPress) {
        isCountingXPress = countingXPress;
    }
}
