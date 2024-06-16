package com.mygdx.game.controller.filter.filtersingle;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.controller.filter.FilterDynamic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterDynamicSingle extends FilterDynamic {
    public int checkDynamic(DynamicItem dynamicItem, Player player, ArrayList<StaticItem> staticItems){
        if (player.getItemHolding()!=null && player.getItemHolding().equals(dynamicItem)) return 4;
        if (checkObscure(dynamicItem, player)) return 3;
        return super.checkDynamic(dynamicItem, staticItems);
    }
    public void filter(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                       ArrayList<DynamicItem> dynamicFloor, ArrayList<DynamicItem> dynamicTable,
                       ArrayList<DynamicItem> dynamicTop, ArrayList<DynamicItem> dynamicMiddle){
        for (DynamicItem dynamicItem : dynamicItems){
            int check = checkDynamic(dynamicItem, player, staticItems);
            if (check == 1) dynamicFloor.add(dynamicItem);
            else if (check == 2) dynamicTable.add(dynamicItem);
            else if (check == 3) dynamicMiddle.add(dynamicItem);
            else dynamicTop.add(dynamicItem);
        }
    }
}
