package com.mygdx.game.controller.draw.filter;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterDynamic {
    public int checkDynamic(DynamicItem dynamicItem, Player player, ArrayList<StaticItem> staticItems){
        if (player.getItemHolding()!=null && player.getItemHolding().equals(dynamicItem)) return 3;
        for (StaticItem item : staticItems){
            Rectangle rectStatic = item.getBoundingRectangle();
            Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
            if (rectStatic.contains(rectDynamic)) {
                return 2;
            }
        }
        return 1;
    }
    public void filter(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                       ArrayList<DynamicItem> dynamicFloor,
                       ArrayList<DynamicItem> dynamicTable,
                       ArrayList<DynamicItem> dynamicTop){
        for (DynamicItem dynamicItem : dynamicItems){
            int check = checkDynamic(dynamicItem, player, staticItems);
            if (check == 1) dynamicFloor.add(dynamicItem);
            else if (check == 2) dynamicTable.add(dynamicItem);
            else dynamicTop.add(dynamicItem);
        }
    }
}
