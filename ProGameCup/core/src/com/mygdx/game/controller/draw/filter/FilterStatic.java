package com.mygdx.game.controller.draw.filter;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterStatic {
    public boolean checkObscure(StaticItem item, Player player){
        return player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() >= item.getY() + item.getHeight() - item.getOverlap()
                && player.getY() <= item.getY() + item.getHeight();
    }

    public boolean dynamicInStatic(StaticItem staticItem, DynamicItem dynamicItem){
        Rectangle rectStatic = staticItem.getBoundingRectangle();
        Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
        return rectStatic.contains(rectDynamic);
    }
    private int checkStatic(Player player, StaticItem staticItem){
        if (checkObscure(staticItem, player)) return 1;
        return 2;
    }
    private void updateDynamic(ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop,
                               StaticItem staticItem){
        ArrayList<DynamicItem> listDynamic = new ArrayList<>();
        for (DynamicItem dynamicItem : dynamicTable){
            if (dynamicInStatic(staticItem, dynamicItem)){
                listDynamic.add(dynamicItem);
            }
        }
        dynamicTable.removeAll(listDynamic);
        dynamicTop.addAll(listDynamic);
    }
    public void filter(Player player, ArrayList<StaticItem> staticItems,
                       ArrayList<StaticItem> staticTop,
                       ArrayList<StaticItem> staticBottom,
                       ArrayList<DynamicItem> dynamicTable,
                       ArrayList<DynamicItem> dynamicTop) {
        for (StaticItem staticItem : staticItems){
            if (checkStatic(player, staticItem)==1) {
                staticTop.add(staticItem);
                updateDynamic(dynamicTable, dynamicTop, staticItem);
            }
            else staticBottom.add(staticItem);
        }
    }
}
