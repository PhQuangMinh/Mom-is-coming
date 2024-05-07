package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Item;

import java.util.ArrayList;

public class SetUpItem {
    private Vector2 getPosition(String nameImage, float width, float height){
        float x = GameConstant.posMap, y = GameConstant.posMap;
        float mapSize = GameConstant.mapHeight;
        float itemSize = GameConstant.itemSize;
        float tileSize = GameConstant.tileSize;
        float patioTop = 2/3f;
        switch (nameImage){
            case "bed":
                x += mapSize - width - tileSize;
                y += 1/2f * mapSize;
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
                y += 3/7f*mapSize;
                break;
            case "chair":
                x += mapSize/2 - width/2;
                y += 1/3f*mapSize;
                break;
            case "computer-desk":
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
                x += 2.3f*width;
                y += 3/7f*mapSize;
                break;
            case "table2":
                x += mapSize/2 - width/2;
                y += 1/5f*mapSize;
                break;
            case "tivi":
                x += mapSize/2 - width/2;
                y += tileSize;
                break;
            case "trash-can":
                x += tileSize + 5;
                y += tileSize + 5;
                break;
            case "washing-machine":
                x += mapSize - itemSize - tileSize - 5;
                y += tileSize + 10 + height;
                break;
        }
        return new Vector2(x, y);
    }
    public Item getItem(String image){
        Texture imageItem = new Texture("items/" + image + ".png");
        Texture chosenImageItem = new Texture("chosen-items/" + image + ".png");
        float width = GameConstant.itemSize;
        float height = GameConstant.itemSize;
        float patio = (float) imageItem.getHeight() /imageItem.getWidth();
        if (patio>1) height*=patio;
        else width/=patio;
        if (image.equals("bed") || image.equals("computer-desk")){
            height*=1.5f;
            width*=1.5f;
        }
        Vector2 position = getPosition(image, width, height);
        return new Item(image, imageItem, chosenImageItem, position.x, position.y, width, height);
    }
    public void setUpItems(ArrayList<Item> items){
        items.add(getItem("bed"));
        items.add(getItem("box"));
        items.add(getItem("cabinet1"));
        items.add(getItem("cabinet2"));
        items.add(getItem("chair"));
        items.add(getItem("computer-desk"));
        items.add(getItem("dish-washing"));
        items.add(getItem("fridge"));
        items.add(getItem("oven"));
        items.add(getItem("table1"));
        items.add(getItem("table2"));
        items.add(getItem("tivi"));
        items.add(getItem("trash-can"));
        items.add(getItem("washing-machine"));
    }
}
