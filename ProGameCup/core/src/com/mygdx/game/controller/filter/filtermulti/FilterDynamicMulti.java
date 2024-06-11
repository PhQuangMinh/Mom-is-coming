package com.mygdx.game.controller.filter.filtermulti;

import com.mygdx.game.controller.filter.FilterDynamic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.sun.tools.javac.jvm.PoolConstant;

import java.util.ArrayList;

public class FilterDynamicMulti extends FilterDynamic {
    public int checkDynamic(Player firstPlayer, Player secondPlayer, DynamicItem dynamicItem,
                            ArrayList<StaticItem> staticItems){
        if (firstPlayer.getItemHolding()!=null && firstPlayer.getItemHolding().equals(dynamicItem) ||
                secondPlayer.getItemHolding()!=null && secondPlayer.getItemHolding().equals(dynamicItem))
            return 3;
        return super.checkDynamic(dynamicItem, staticItems);
    }

    public void filter(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                       ArrayList<StaticItem> staticItems, ArrayList<DynamicItem> dynamicFloor,
                       ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop){
        for (DynamicItem dynamicItem : dynamicItems){
            int check = checkDynamic(firstPlayer, secondPlayer, dynamicItem, staticItems);
            if (check == 1) dynamicFloor.add(dynamicItem);
            else if (check == 2) dynamicTable.add(dynamicItem);
            else dynamicTop.add(dynamicItem);
        }
    }
}
