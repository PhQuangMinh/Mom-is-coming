package com.mygdx.game.controller;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;

public class CheckCollision {
    private Rectangle getRectangle(MapObject mapObject){
        float xObject = (GameConstant.windowWidth-GameConstant.mapWidth)/2 + mapObject.getProperties().get("x", Float.class) + 0.2f*GameConstant.playerWidth;
        float yObject = (GameConstant.windowHeight-GameConstant.mapHeight)/2+ 0.8f* GameConstant.playerHeight +mapObject.getProperties().get("y", Float.class);
        float widthObject = mapObject.getProperties().get("width", Float.class) - 0.4f*GameConstant.playerWidth;
        float heightObject = Math.max(mapObject.getProperties().get("height", Float.class)-0.8f*GameConstant.playerHeight, 1f);
        return new Rectangle(xObject, yObject, widthObject, heightObject);
    }

    private void updateFrame(Vector2 position){
        float pos= (GameConstant.windowWidth-GameConstant.mapHeight)/2;
        if (position.x<pos + GameConstant.tileWidth)
            position.x = pos + GameConstant.tileWidth;
        if (position.x>pos + GameConstant.mapWidth - GameConstant.tileWidth*2)
            position.x = pos + GameConstant.mapWidth - GameConstant.tileWidth*2;
        if (position.y<pos + GameConstant.tileHeight)
            position.y = pos + GameConstant.tileHeight;
        if (position.y>pos + GameConstant.mapHeight - GameConstant.tileHeight-10)
            position.y = pos + GameConstant.mapHeight - GameConstant.tileHeight-10;
        if (position.y > pos + 12*GameConstant.tileHeight+0.7f*GameConstant.playerHeight)
            position.y = pos + 12*GameConstant.tileHeight+0.7f*GameConstant.playerHeight;
    }
    public Vector2 updatePosition(Vector2 position, Vector2 oldPosition, MapObjects mapObjects) {
        updateFrame(position);

        for (MapObject mapObject:mapObjects){
            Rectangle characterRect = new Rectangle(position.x, position.y, GameConstant.playerWidth, GameConstant.playerHeight);
            Rectangle blockRect = getRectangle(mapObject);
            if (characterRect.overlaps(blockRect) || blockRect.overlaps(characterRect)){
                System.out.println("aaaa" + mapObject.getName() + " " + blockRect.getX() + " " + blockRect.getY());
                return oldPosition;
            }
        }
        return position;
    }
}
