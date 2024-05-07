package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item extends Sprite{
    private Texture image;
    private Texture chosenImage;

    private String name;

    public Item(String name, Texture image, Texture chosenImage, float x, float y, float width, float height) {
        this.name = name;
        this.image = image;
        this.chosenImage = chosenImage;
        this.setX(x);
        this.setY(y);
        this.setSize(width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Texture getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(Texture chosenImage) {
        this.chosenImage = chosenImage;
    }
}
