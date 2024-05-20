package com.mygdx.game.controller.item.activity.throwitem;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ThrowInStatic {
    public void throwStaticItem(DynamicItem dynamicItem, ArrayList<StaticItem> staticItems
            , ArrayList<DynamicItem> dynamicItems, Player player) {
        if(dynamicItem.getName().equals("dish")){
            for(StaticItem item : staticItems){
                if(item.getName().equals(player.getContainer().getName())){
                    player.setValidThrow(false);
                    player.setPositionThrew(new Vector2(player.getX(), player.getY()));
                }
            }
        }
        else {
            if (player.getContainer().getItems().size() < player.getContainer().getNumber()) {
                for (StaticItem item : staticItems) {
                    if (item.getName().equals(player.getContainer().getName())) {
                        dynamicItem.setVisible(false);
                        item.getItems().add(dynamicItem);
                        player.setValidThrow(true);
                        player.setItemHolding(null);
                        dynamicItems.remove(dynamicItem);
                    }
                }
            }
        }
    }
}
