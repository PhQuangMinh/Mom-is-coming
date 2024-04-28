package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    protected float x, y;
    protected float width, height;
    protected Texture texture;
    protected Boolean passable;

    public Entity(){
        width = 0; height = 0;
        passable = false;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Boolean getPassable() {
        return passable;
    }

    public void setPassable(Boolean passable) {
        this.passable = passable;
    }
}
