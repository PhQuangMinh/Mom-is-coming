package com.mygdx.game.controller.item.activity.throwitem;

import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ThrowInStatic {
    public void throwStaticItem(DynamicItem dynamicItem, ArrayList<StaticItem> staticItems
            , ArrayList<DynamicItem> dynamicItems, Player player) {
//<<<<<<< HEAD
        for (StaticItem item : staticItems) {
            if (item.getName().equals(player.getContainer().getName())) {
//                player.setStatusHold(1);
                dynamicItem.setVisible(false);
                item.getItems().add(dynamicItem);
                player.setValidThrow(true);
                player.setItemHolding(null);
                dynamicItems.remove(dynamicItem);
//=======
//        if(dynamicItem.getName().equals("dish_fish") || dynamicItem.getName().equals("dish_meat")){
//            for(StaticItem item : staticItems){
//                if(item.getName().equals(player.getContainer().getName())){
//                    player.setValidThrow(false);
//                    player.setPositionThrew(new Vector2(player.getX(), player.getY()));
//                }
//            }
//        }
//       else{
//            if (player.getContainer().getItems().size() < player.getContainer().getNumber()) {
//                for (StaticItem item : staticItems) {
//                    if (item.getName().equals(player.getContainer().getName())) {
//                        dynamicItem.setVisible(false);
//                        item.getItems().add(dynamicItem);
//                        player.setValidThrow(true);
//                        player.setItemHolding(null);
//                        dynamicItems.remove(dynamicItem);
//                    }
//                }
//>>>>>>> develop
            }
        }
        System.out.println(player.getItemHolding());
    }
}
