package com.mygdx.game.controller.item.activity.throwitem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ThrowFloor {
    public Vector2 throwTop(DynamicItem dynamicItem, Player player){
        return new Vector2(player.getX() + (player.getWidth() - dynamicItem.getWidth()) / 2
                , player.getY() + player.getHeight()/2);
    }

    public Vector2 throwBottom(DynamicItem dynamicItem, Player player){
        return new Vector2(player.getX() + (player.getWidth() - dynamicItem.getWidth()) / 2
                , player.getY() - dynamicItem.getHeight());
    }

    public Vector2 throwLeft(DynamicItem dynamicItem, Player player){
        return new Vector2(player.getX() - dynamicItem.getWidth()
                , player.getY() - dynamicItem.getHeight() / 2);
    }

    public Vector2 throwRight(DynamicItem dynamicItem, Player player){
        return new Vector2(player.getX() + player.getWidth()
                , player.getY() - dynamicItem.getHeight() / 2);
    }

    public Vector2 getPosition(DynamicItem dynamicItem, Player player){
        if (player.getDirection()== Direction.UP)
            return throwTop(dynamicItem, player);
        else if (player.getDirection()== Direction.DOWN)
            return throwBottom(dynamicItem, player);
        else if (player.getDirection()== Direction.LEFT)
            return throwLeft(dynamicItem, player);
        return throwRight(dynamicItem, player);
    }

    public boolean checkFrame(DynamicItem dynamic){
        return dynamic.getX() >= GameConstant.posMapX + GameConstant.tileSize*2
                && dynamic.getX() + dynamic.getWidth() <= GameConstant.posMapX + GameConstant.mapWidth - 2*GameConstant.tileSize
                && dynamic.getY() >= GameConstant.posMapY + 2.5f*GameConstant.tileSize
                && dynamic.getY() + 0.7f*dynamic.getHeight() <= GameConstant.posMapY + 13*GameConstant.tileSize;
    }

    public boolean validPosition(DynamicItem dynamicItem, Vector2 position
            , ArrayList<StaticItem> staticItems){
        Rectangle rectDynamic = new Rectangle(position.x, position.y, dynamicItem.getWidth()
                , dynamicItem.getHeight());
        if (!checkFrame(dynamicItem)) return false;
        for (StaticItem item: staticItems){
            Rectangle rectStatic = new Rectangle(item.getX(), item.getY(), item.getWidth()
                    , item.getHeight() - item.getOverlap());
            if (rectDynamic.overlaps(rectStatic)) {
                return false;
            }
        }
        return true;
    }

    public void throwFloor(DynamicItem dynamicItem, Player player, ArrayList<StaticItem> staticItems) {
        Vector2 position = getPosition(dynamicItem, player);
        if (player.getDirection() == Direction.RIGHT || player.getDirection() == Direction.LEFT){
            player.setPositionThrew(new Vector2(position.x, player.getY()));
        }
        else player.setPositionThrew(position);
        if (validPosition(dynamicItem, position, staticItems)) {
            dynamicItem.setPosition(position.x, position.y);
            player.setValidThrow(true);
            player.setItemHolding(null);
        }
        else{
            player.setValidThrow(false);
        }
    }
}
