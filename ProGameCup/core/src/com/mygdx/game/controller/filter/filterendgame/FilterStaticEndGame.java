package com.mygdx.game.controller.filter.filterendgame;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.controller.filter.FilterStatic;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterStaticEndGame extends FilterStatic {

    public boolean dynamicInStatic(StaticItem staticItem, DynamicItem dynamicItem){
        return super.dynamicInStatic(staticItem, dynamicItem);
    }
    public void updateDynamic(ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop,
                               StaticItem staticItem){
        super.updateDynamic(dynamicTable, dynamicTop, staticItem);
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
