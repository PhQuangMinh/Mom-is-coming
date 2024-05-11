package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class SetUpItem {
    private float overlap;
    private Vector2 getPosition(String nameImage, float width, float height){
        overlap = 0;
        float x = GameConstant.posMap, y = GameConstant.posMap;
        float mapSize = GameConstant.mapHeight;
        float itemSize = GameConstant.itemSize;
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
                x += mapSize - itemSize - tileSize - 5;
                y += tileSize + 10;
                break;
            case "cabinet1":
                x += mapSize - itemSize*3;
                y += patioTop*mapSize;
                break;
            case "cabinet2":
                x += mapSize - itemSize - tileSize;
                y += 3/7.2f*mapSize;
                break;
            case "chair":
                overlap = patioMedium;
                x += mapSize/2 - width/2;
                y += 1.1f/3f*mapSize;
                break;
            case "computer-desk":
                overlap = patioHigh;
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
            case "trash-can":
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
        float width = GameConstant.itemSize;
        float height = GameConstant.itemSize;
        float patio = (float) imageTexture.getHeight() /imageTexture.getWidth();
        if (patio>1) height*=patio;
        else width/=patio;
        if (image.equals("bed") || image.equals("computer-desk")){
            height*=1.5f;
            width*=1.5f;
        }
        if (image.equals("trash-can")){
            height/=1.5f;
            width/=1.5f;
        }
        return new Vector2(width, height);
    }
    public StaticItem getItem(String image, boolean check){
        Texture imageItem = new Texture("items/static-items/items/" + image + ".png");
        Texture chosenImageItem;
        Vector2 size = getSize(image, imageItem);
        Vector2 position = getPosition(image, size.x, size.y);
        if (!check) chosenImageItem = null;
        else chosenImageItem = new Texture("items/static-items/pick-items/" + image + ".png");
        return new StaticItem(image, imageItem, chosenImageItem, position.x, position.y, size.x, size.y, overlap);
    }
    public void setUpItems(ArrayList<StaticItem> items){
        items.add(getItem("bed", true));
        items.add(getItem("box", true));
        items.add(getItem("cabinet1", true));
        items.add(getItem("cabinet2", true));
        items.add(getItem("chair", false));
        items.add(getItem("computer-desk", true));
        items.add(getItem("dish-washing", true));
        items.add(getItem("fridge", true));
        items.add(getItem("oven", true));
        items.add(getItem("table1", false));
        items.add(getItem("table2", false));
        items.add(getItem("tivi", false));
        items.add(getItem("trash-can", true));
        items.add(getItem("washing-machine", true));
    }
}
