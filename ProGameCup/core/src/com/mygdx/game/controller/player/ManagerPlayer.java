package com.mygdx.game.controller.player;

import com.mygdx.game.controller.item.activity.GetItem;
import com.mygdx.game.controller.item.activity.MoppingFloor;
import com.mygdx.game.controller.item.activity.throwitem.ThrowItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ManagerPlayer {
    GetItem getItem;
    ThrowItem throwItem;

    MoppingFloor moppingFloor;

    public ManagerPlayer(){
        getItem = new GetItem();
        throwItem = new ThrowItem();
        moppingFloor = new MoppingFloor();
    }

    public void updateStatus(Player player){
        if(player.getItemHolding() == null){
            if (player.getContainer() == null || player.getContainer().getNumber() == 0)
                player.setStatusHold(1);
            else player.setStatusHold(3);
        }
        else{
            player.setStatusHold(2);
        }
    }

    public void handleStatus(Player player, ArrayList<DynamicItem> dynamicItems,
                             ArrayList<StaticItem> staticItems) {
        switch (player.getStatusHold()) {
            case 1:
                getItem.pickItemFloor(dynamicItems, player);
                break;
            case 2:
                throwItem.throwDynamicItem(player, dynamicItems, staticItems);
                break;
            case 3:
                getItem.takeItemStatic(player, dynamicItems);
                break;
//            case 4:
//                moppingFloor.mopping(player, dynamicItems);
//                break;
        }
    }
}
