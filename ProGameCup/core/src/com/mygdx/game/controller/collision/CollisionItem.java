package com.mygdx.game.controller.collision;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class CollisionItem {
    private Rectangle getRectItem(Item item) {
        float width = item.getWidth() - 0.4f * GameConstant.PLAYER_WIDTH;
        float height = item.getHeight() - 0.8f * GameConstant.PLAYER_HEIGHT - item.getOverlap();
        float xObject = item.getX() + 0.2f * GameConstant.PLAYER_WIDTH;
        float yObject = item.getY() + 0.8f * GameConstant.PLAYER_HEIGHT;
        return new Rectangle(xObject, yObject, width, height);
    }

    boolean checkStaticItem(Vector2 position, ArrayList<StaticItem> staticItems) {
        for (Item item : staticItems) {
            Rectangle characterRect = new Rectangle(position.x, position.y, GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT);
            Rectangle itemRect = getRectItem(item);
            if (characterRect.overlaps(itemRect)) {
                return true;
            }
        }
        return false;
    }

    boolean checkDynamicItem(Vector2 position, ArrayList<DynamicItem> dynamicItems){
        for (DynamicItem item : dynamicItems) {
            if (item.isCross()) continue;
            Rectangle characterRect = new Rectangle(position.x, position.y, GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT);
            Rectangle itemRect = getRectItem(item);
            if (characterRect.overlaps(itemRect)) {
                return true;
            }
        }
        return false;
    }
}
