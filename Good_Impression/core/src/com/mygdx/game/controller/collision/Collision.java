package com.mygdx.game.controller.collision;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class Collision {

    public void updatePosition(Vector2 position, Vector2 oldPosition, ArrayList<StaticItem> staticItems
            , ArrayList<DynamicItem> dynamicItems, Player player) {
        CollisionFrame collisionFrame = new CollisionFrame();
        collisionFrame.updateFrame(position, player);
        CollisionItem collisionItem = new CollisionItem();
        if (collisionItem.checkStaticItem(position, staticItems)
        || collisionItem.checkDynamicItem(position, dynamicItems)) position.set(oldPosition);
    }

}
