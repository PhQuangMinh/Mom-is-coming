package com.mygdx.game.controller.item.activity.throwitem;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.ActivityStatus;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.effect.MakeSound;

import java.util.ArrayList;

public class ThrowItem {
    ThrowInStatic throwInStatic;
    ThrowFloor throwFloor;

    ThrowToPlayer throwToPlayer;

    public ThrowItem(){
        throwInStatic = new ThrowInStatic();
        throwFloor = new ThrowFloor();
        throwToPlayer = new ThrowToPlayer();
    }
    public void throwDynamicItem(Player player, ArrayList<DynamicItem> dynamicItems,
                                 ArrayList<StaticItem> staticItems){
        if (player.getItemHolding()==null || player.getActivity() == ActivityStatus.CLEANING_DISH ||
                player.getActivity() == ActivityStatus.MOPPING_FLOOR) return;
        throwItem(player.getItemHolding(), dynamicItems, staticItems, player);
    }
    public void throwItem(DynamicItem dynamicItem, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems, Player player) {
        if (player.getContainer() != null) {
            if(!player.getContainer().getName().equals("dish-washing")) {
                if (player.getContainer().getItems().size() < player.getContainer().getNumber()) {
                    throwInStatic.throwStaticItem(dynamicItem, staticItems, dynamicItems, player);
                    MakeSound.makeSound("sounds/soItemStore.ogg", 0.8f);
                } else {
                    player.setValidThrow(false);
                    player.setPositionThrew(new Vector2(player.getX(), player.getY()));
                }
            }
        }
        else {
            throwFloor.throwFloor(dynamicItem, player, staticItems);
            if(player.isValidThrow()) MakeSound.makeSound("sounds/soItemDrop.ogg", 0.8f);
        }
    }
}
