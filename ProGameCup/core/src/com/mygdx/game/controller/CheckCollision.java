package com.mygdx.game.controller;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class CheckCollision {
    private Rectangle getRectObject(MapObject mapObject) {
        float width = mapObject.getProperties().get("width", Float.class);
        float height = mapObject.getProperties().get("height", Float.class);
        float xObject = GameConstant.posMap + 0.2f * GameConstant.playerWidth;
        float yObject = GameConstant.posMap + 0.8f * GameConstant.playerHeight;
        float widthObject = width - 0.4f * GameConstant.playerWidth;
        float heightObject = Math.max(height - 0.8f * GameConstant.playerHeight, 1f);
        return new Rectangle(xObject, yObject, widthObject, heightObject);
    }

    private void updateFrame(Vector2 position) {
        float tileSize = GameConstant.tileSize;
        float pos = (GameConstant.windowWidth - GameConstant.mapHeight) / 2;
        if (position.x < pos + tileSize - 0.2f * GameConstant.playerWidth)
            position.x = pos + tileSize - 0.2f * GameConstant.playerWidth;
        if (position.x > pos + GameConstant.mapWidth - tileSize * 2)
            position.x = pos + GameConstant.mapWidth - tileSize * 2;
        if (position.y < pos + tileSize)
            position.y = pos + tileSize;
        if (position.y > pos + GameConstant.mapHeight - tileSize - 10)
            position.y = pos + GameConstant.mapHeight - tileSize - 10;
        if (position.y > pos + 12 * tileSize + 0.7f * GameConstant.playerHeight)
            position.y = pos + 12 * tileSize + 0.7f * GameConstant.playerHeight;
    }

    private boolean checkMapObject(Vector2 position, MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            Rectangle characterRect = new Rectangle(position.x, position.y, GameConstant.playerWidth, GameConstant.playerHeight);
            Rectangle blockRect = getRectObject(mapObject);
            if (characterRect.overlaps(blockRect) || blockRect.overlaps(characterRect)) {
//                System.out.println("aaaa" + mapObject.getName() + " " + blockRect.getX() + " " + blockRect.getY());
                return true;
            }
        }
        return false;
    }

    private Rectangle getRectItem(Item item) {
        float width = item.getWidth() - 0.4f * GameConstant.playerWidth;
        float height = item.getHeight() - 0.8f * GameConstant.playerHeight - item.getOverlap();
        float xObject = item.getX() + 0.2f * GameConstant.playerWidth;
        float yObject = item.getY() + 0.8f * GameConstant.playerHeight;
        return new Rectangle(xObject, yObject, width, height);
    }

    private boolean checkItem(Vector2 position, ArrayList<StaticItem> staticItems) {
        for (Item item : staticItems) {
            Rectangle characterRect = new Rectangle(position.x, position.y, GameConstant.playerWidth, GameConstant.playerHeight);
            Rectangle itemRect = getRectItem(item);
            if (characterRect.overlaps(itemRect)) {
                return true;
            }
        }
        return false;
    }

    public void updatePosition(Vector2 position, Vector2 oldPosition, MapObjects mapObjects, ArrayList<StaticItem> staticItems) {
        updateFrame(position);
        if (checkMapObject(position, mapObjects)
                || checkItem(position, staticItems)) position.set(oldPosition);
    }

    public boolean checkObscure(Item item, Player player){
        return player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() >= item.getY() + item.getHeight() - item.getOverlap()
                && player.getY() <= item.getY() + item.getHeight();
    }

    public boolean checkFull(ArrayList<StaticItem> items, Player player) {
        for (Item item : items) {
            if (checkObscure(item, player)) return true;
        }
        return false;
    }

}
