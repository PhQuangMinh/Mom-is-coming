package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class SetStaticItem {
    private float overlap;
    private Vector2 getPosition(String nameImage, float width, float height){
        overlap = 0;
        float x = GameConstant.posMapX, y = GameConstant.posMapY;
        float mapSize = GameConstant.mapHeight;
        float itemSize = GameConstant.staticSize;
        float tileSize = GameConstant.tileSize;
        float patioTop = 2/3f;
        float patioLow = 1/4f*height;
        float patioMedium = 1/3f*height;
        float patioHigh = 1/2f*height;
        switch (nameImage){
            case "bed":
                overlap = patioMedium;
                x += mapSize - width - tileSize;
                y += 1/2.2f * mapSize;
                break;
            case "box":
                overlap = patioMedium;
                x += mapSize - itemSize - tileSize - 5;
                y += tileSize + 10;
                break;
            case "wardrobe":
                x += mapSize - itemSize*3;
                y += patioTop*mapSize;
                break;
            case "night-stand":
                x += mapSize - itemSize - tileSize;
                y += 3/7.2f*mapSize;
                break;
            case "chair":
                overlap = patioMedium;
                x += mapSize/2 - width/2;
                y += 1.1f/3f*mapSize;
                break;
            case "computer-desk":
                overlap = patioHigh + 10;
                x += tileSize + 5;
                y += 1/4f*mapSize;
                break;
            case "dish-washing":
                x += width*1.5f;
                y += patioTop*mapSize;
                break;
            case "fridge":
                x += tileSize + 5;
                y += patioTop*mapSize;
                break;
            case "oven":
                x += 4*width;
                y += patioTop*mapSize;
                break;
            case "table1":
                overlap = patioLow;
                x += 2.3f*width;
                y += 3/7f*mapSize;
                break;
            case "table2":
                overlap = patioLow;
                x += mapSize/2 - width/2;
                y += 1/5f*mapSize;
                break;
            case "tivi":
                overlap = patioHigh;
                x += mapSize/2 - width/2;
                y += tileSize;
                break;
            case "trash":
                overlap = patioMedium;
                x += tileSize + 5;
                y += tileSize + 5;
                break;
            case "washing-machine":
                overlap = patioMedium;
                x += mapSize - itemSize - tileSize - 5;
                y += tileSize + 10 + height;
                break;
        }
        return new Vector2(x, y);
    }

    private Vector2 getSize(String image, Texture imageTexture) {
        float width = GameConstant.staticSize;
        float height = GameConstant.staticSize;
        float patio = (float) imageTexture.getHeight() /imageTexture.getWidth();
        if (patio>1) height*=patio;
        else width/=patio;
        if (image.equals("bed") || image.equals("computer-desk")){
            height*=1.5f;
            width*=1.5f;
        }
        if (image.equals("trash")){
            height/=1.5f;
            width/=1.5f;
        }
        return new Vector2(width, height);
    }
    public StaticItem getItem(String image, boolean check, int number){
        Texture imageItem = new Texture("items/static-items/items/" + image + ".png");
        Texture chosenImageItem, containItem;
        Vector2 size = getSize(image, imageItem);
        Vector2 position = getPosition(image, size.x, size.y);
        if (!check){
            chosenImageItem = null;
            containItem = null;
        }
        else{
            if (image.equals("dish-washing")) containItem = null;
            else containItem = new Texture("items/static-items/storage/" + image + ".png");
            chosenImageItem = new Texture("items/static-items/pick-items/" + image + ".png");
        }
        return new StaticItem(image, imageItem, chosenImageItem, position.x, position.y, size.x,
                 size.y, overlap, containItem, number);
    }
    public void setStatic(ArrayList<StaticItem> items){
        items.add(getItem("bed", true, 4));
        items.add(getItem("box", true, 2));
        items.add(getItem("wardrobe", true, 3));
        items.add(getItem("night-stand", true, 1));
        items.add(getItem("chair", false, 0));
        items.add(getItem("computer-desk", true, 1));
        items.add(getItem("dish-washing", true, 0));
        items.add(getItem("fridge", true, 3));
        items.add(getItem("oven", true, 1));
        items.add(getItem("table1", false, 0));
        items.add(getItem("table2", false, 0));
        items.add(getItem("tivi", false, 0));
        items.add(getItem("trash", true, 6));
        items.add(getItem("washing-machine", true, 3));
    }
}
