package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MakeSize {
    public void getSize(Texture texture, int size, Vector2 sizeItem){
        float ratio = (float)texture.getWidth() / texture.getHeight();
        float width, height;
        if (ratio>1) {
            width = size;
            height = size / ratio;
        } else {
            width = size * ratio;
            height = size;
        }
        sizeItem.x = width;
        sizeItem.y = height;
    }
}
