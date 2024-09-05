package com.mygdx.game.controller.filter.filtermulti;

import com.mygdx.game.controller.filter.FilterDynamic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterDynamicMulti extends FilterDynamic {
    public int checkDynamic(Player firstPlayer, Player secondPlayer, DynamicItem dynamicItem,
                            ArrayList<StaticItem> staticItems){
        if (firstPlayer.getItemHolding()!=null && firstPlayer.getItemHolding().equals(dynamicItem) ||
                secondPlayer.getItemHolding()!=null && secondPlayer.getItemHolding().equals(dynamicItem)
         || checkObscure(dynamicItem, firstPlayer) && checkObscure(dynamicItem, secondPlayer))
            return 4;
        if (checkObscure(dynamicItem, firstPlayer) || checkObscure(dynamicItem, secondPlayer))
            return 3;
        return super.checkDynamic(dynamicItem, staticItems);
    }

    public void filter(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                       ArrayList<StaticItem> staticItems, ArrayList<DynamicItem> dynamicFloor,
                       ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop,
                       ArrayList<DynamicItem> dynamicMiddle){
        for (DynamicItem dynamicItem : dynamicItems){
            int check = checkDynamic(firstPlayer, secondPlayer, dynamicItem, staticItems);
            if (check == 1) dynamicFloor.add(dynamicItem);
            else if (check == 2) dynamicTable.add(dynamicItem);
            else if (check == 3) dynamicMiddle.add(dynamicItem);
            else dynamicTop.add(dynamicItem);
        }
    }
}
