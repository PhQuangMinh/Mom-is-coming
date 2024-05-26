package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MakeSize {
    public Vector2 getSize(Texture texture, int size){
        float ratio = (float)texture.getWidth() / texture.getHeight();
        float width, height;
        if (ratio>1) {
            width = size;
            height = size / ratio;
        } else {
            width = size * ratio;
            height = size;
        }
        return new Vector2(width, height);
    }
}
