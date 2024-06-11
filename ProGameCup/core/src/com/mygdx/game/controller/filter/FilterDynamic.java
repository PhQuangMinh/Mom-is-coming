package com.mygdx.game.controller.filter;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterDynamic {
    public int checkDynamic(DynamicItem dynamicItem, ArrayList<StaticItem> staticItems){
        for (StaticItem item : staticItems){
            Rectangle rectStatic = item.getBoundingRectangle();
            Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
            if (rectStatic.contains(rectDynamic)) {
                return 2;
            }
        }
        return 1;
    }
}
