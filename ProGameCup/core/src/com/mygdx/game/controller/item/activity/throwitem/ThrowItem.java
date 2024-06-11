package com.mygdx.game.controller.item.activity.throwitem;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.effect.MakeSound;

import java.util.ArrayList;

public class ThrowItem {
    ThrowInStatic throwInStatic;
    ThrowFloor throwFloor;

    public ThrowItem(){
        throwInStatic = new ThrowInStatic();
        throwFloor = new ThrowFloor();
    }
    public void throwDynamicItem(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems){
        if (player.getItemHolding()==null || player.getStatusHold() == 4) return;
        for (DynamicItem item : dynamicItems) {
            if (item.getName().equals(player.getItemHolding().getName())) {
                throwItem(item, dynamicItems, staticItems, player);
                return;
            }
        }
    }
    public void throwItem(DynamicItem dynamicItem, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems, Player player) {
        System.out.println("Throw");
        if (player.getContainer() != null) {
            if(player.getContainer().getName().equals("dish-washing")){
                throwDishWashing(player, dynamicItem);
            }
            else
                if(player.getContainer().getItems().size() < player.getContainer().getNumber()){
                    throwInStatic.throwStaticItem(dynamicItem, staticItems, dynamicItems, player);
                    MakeSound.makeSound("sounds/soItemStore.ogg", 0.8f);
                }
                else{
                    player.setValidThrow(false);
                    player.setPositionThrew(new Vector2(player.getX(), player.getY()));
                }
        }
        else {
            throwFloor.throwFloor(dynamicItem, player, staticItems);
            if(player.isValidThrow()) MakeSound.makeSound("sounds/soItemDrop.ogg", 0.8f);
        }
    }
    public void throwDishWashing(Player player, DynamicItem dynamicItem){
        if(dynamicItem.getName().equals("dish")){
            if(player.getMovement().getStatus() != CharacterStatus.CLEANING_DISH){
                player.getMovement().setStatus(CharacterStatus.CLEANING_DISH);
                dynamicItem.setVisible(false);
                player.getMovement().setActionCount(0);
            }
        }
        else{
            player.setValidThrow(false);
            player.setPositionThrew(new Vector2(player.getX(), player.getY()));
        }
    }
}
