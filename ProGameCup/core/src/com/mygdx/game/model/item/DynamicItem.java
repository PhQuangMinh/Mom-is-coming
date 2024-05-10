package com.mygdx.game.model.item;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.item.Item;

public class DynamicItem extends Item {

    public DynamicItem(String name, Texture image, Texture chosenImage, float x, float y, float width, float height, float overlap) {
        super(name, image, chosenImage, x, y, width, height, overlap);
    }
}
