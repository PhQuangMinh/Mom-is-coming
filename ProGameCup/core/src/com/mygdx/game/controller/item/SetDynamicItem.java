package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;

import java.util.ArrayList;

public class SetDynamicItem {
    private float overlap;

    private Vector2 getPosition(String nameImage, float height) {
        overlap = 0;
        float posX = GameConstant.posMapX;
        float posY = GameConstant.posMapY;
        switch (nameImage) {
            case "battery":
                return new Vector2(posX + 240, posY + 140);
            case "blue-sock":
                return new Vector2(posX + 150, posY + 100);
            case "box":
                return new Vector2(posX + 350, posY + 350);
            case "dish":
                return new Vector2(posX + 400, posY + 100);
            case "letter":
                return new Vector2(posX + 140, posY + 295);
            case "paper":
                return new Vector2(posX + 140, posY + 265);
            case "pizza":
                return new Vector2(posX + 200, posY + 200);
            case "box-pizza":
                overlap = 1/3f*height;
                return new Vector2(posX + 100, posY + 200);
            case "red-shirt":
                return new Vector2(posX + 400, posY + 200);
            case "red-sock":
                return new Vector2(posX + 280, posY + 140);
        }
        return new Vector2(posX + 300, posY + 300);//shirt
    }

    private Vector2 getSize(String image, Texture imageTexture) {
        float patio = (float) imageTexture.getHeight() /imageTexture.getWidth();
        float width = GameConstant.dynamicSize, height = GameConstant.dynamicSize;
        if (patio>1) height *= patio;
        else width /= patio;
        if (image.equals("battery") || image.equals("red-sock")){
            width/=1.5f;
            height/=1.5f;
        }
        if (image.equals("box-pizza")){
            width*=1.5f;
            height*=1.5f;
        }
        return  new Vector2(width, height);
    }

    public DynamicItem getItem(String image, boolean isCross) {
        Texture imageItem = new Texture("items/dynamic-items/items/" + image + ".png");
        Texture chosenImageItem = new Texture("items/dynamic-items/pick-items/" + image + ".png");
        Vector2 size = getSize(image, imageItem);
        Vector2 position = getPosition(image, size.y);
        return new DynamicItem(image, imageItem, chosenImageItem, position.x, position.y, size.x, size.y,overlap, isCross);
    }

    public void setDynamic(ArrayList<DynamicItem> items) {
        items.add(getItem("battery", true));
        items.add(getItem("blue-sock", true));
        items.add(getItem("box", false));
        items.add(getItem("box-pizza", false));
        items.add(getItem("dish", true));
        items.add(getItem("letter", true));
        items.add(getItem("paper", true));
        items.add(getItem("pizza", true));
        items.add(getItem("red-shirt", true));
        items.add(getItem("red-sock", true));
        items.add(getItem("shirt", true));
    }
}
