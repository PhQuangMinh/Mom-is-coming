package com.mygdx.game.controller.filter.filterendgame;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterStaticEndGame {

    public boolean dynamicInStatic(StaticItem staticItem, DynamicItem dynamicItem){
        Rectangle rectStatic = staticItem.getBoundingRectangle();
        Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
        return rectStatic.contains(rectDynamic);
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
    public void filter( ArrayList<StaticItem> staticItems,
                       ArrayList<StaticItem> staticTop,
                       ArrayList<StaticItem> staticBottom,
                       ArrayList<DynamicItem> dynamicTable,
                       ArrayList<DynamicItem> dynamicTop) {
        for (StaticItem staticItem : staticItems){
            staticTop.add(staticItem);
            updateDynamic(dynamicTable, dynamicTop, staticItem);
            staticBottom.add(staticItem);
        }
    }
}
