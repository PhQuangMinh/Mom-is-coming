package com.mygdx.game.controller.player;

import com.mygdx.game.common.constant.ActivityStatus;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.controller.item.activity.GetItem;
import com.mygdx.game.controller.item.activity.MoppingFloor;
import com.mygdx.game.controller.item.activity.throwitem.ThrowItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.ui.BarProcess;

import java.util.ArrayList;

public class ManagerPlayer {
    GetItem getItem;
    ThrowItem throwItem;

    MoppingFloor moppingFloor;

    BarProcess barProcess;

    public ManagerPlayer(){
        getItem = new GetItem();
        throwItem = new ThrowItem();
        moppingFloor = new MoppingFloor();
        barProcess = new BarProcess();
    }

    public void updateStatus(Player player){
        if (player.getMovement().getStatus() == CharacterStatus.MOPPING_FLOOR){
            player.setActivity(ActivityStatus.MOPPING_FLOOR);
        }
        else{
            if (player.getMovement().getStatus() == CharacterStatus.CLEANING_DISH){
                player.setActivity(ActivityStatus.CLEANING_DISH);
            }
            else{
                if(player.getItemHolding() == null){
                    if (player.getContainer() == null || player.getContainer().getNumber() == 0)
                        player.setActivity(ActivityStatus.GET_ITEM_FLOOR);
                    else player.setActivity(ActivityStatus.GET_ITEM_STATIC);
                }
                else{
                    player.setActivity(ActivityStatus.THROW_ITEM);
                }
            }
        }
    }

    public void handleStatus(Player player, ArrayList<DynamicItem> dynamicItems,
                             ArrayList<StaticItem> staticItems) {
        if (player.getMovement().getStatus() == CharacterStatus.MOPPING_FLOOR ||
                player.getMovement().getStatus() == CharacterStatus.CLEANING_DISH) return;
        switch (player.getActivity()) {
            case GET_ITEM_FLOOR:
                getItem.pickItemFloor(dynamicItems, player);
                break;
            case THROW_ITEM:
                throwItem.throwDynamicItem(player, dynamicItems, staticItems);
                break;
            case GET_ITEM_STATIC:
                getItem.takeItemStatic(player, dynamicItems);
                break;
        }
    }
}
