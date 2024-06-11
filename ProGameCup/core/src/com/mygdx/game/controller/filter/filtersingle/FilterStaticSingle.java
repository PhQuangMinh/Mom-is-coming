package com.mygdx.game.controller.filter.filtersingle;

import com.mygdx.game.controller.filter.FilterStatic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterStaticSingle extends FilterStatic{

    private int checkStatic(Player player, StaticItem staticItem){
        if (checkObscure(staticItem, player)) return 1;
        return 2;
    }
    public void filter(Player player, ArrayList<StaticItem> staticItems, ArrayList<StaticItem> staticTop,
                       ArrayList<StaticItem> staticMiddle, ArrayList<StaticItem> staticBottom,
                       ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop) {
        for (StaticItem staticItem : staticItems){
            if (checkStatic(player, staticItem)==1) {
                staticTop.add(staticItem);
                updateDynamic(dynamicTable, dynamicTop, staticItem);
            }
            else staticBottom.add(staticItem);
        }
        super.updateNightStand(staticBottom, staticMiddle, staticTop);
    }
}
