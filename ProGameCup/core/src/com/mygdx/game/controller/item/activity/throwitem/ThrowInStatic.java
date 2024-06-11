package com.mygdx.game.controller.item.activity.throwitem;

import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ThrowInStatic {
    public void throwStaticItem(DynamicItem dynamicItem, ArrayList<StaticItem> staticItems
            , ArrayList<DynamicItem> dynamicItems, Player player) {
        System.out.println("HAHA");
        for (StaticItem item : staticItems) {
            if (item.getName().equals(player.getContainer().getName())) {
//                player.setStatusHold(1);
                dynamicItem.setVisible(false);
                item.getItems().add(dynamicItem);
                player.setValidThrow(true);
                player.setItemHolding(null);
                dynamicItems.remove(dynamicItem);
            }
        }
        System.out.println(player.getItemHolding());
    }
}
