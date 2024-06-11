package com.mygdx.game.controller.item.activity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class MoppingFloor {
    public void mopping(Player player, ArrayList<DynamicItem> dynamicItems){
        for (DynamicItem item : dynamicItems) {
            if (item.getDiscover()){
                if(item.getName().equals("puddle")){
                    if(player.getItemHolding() != null && ((DynamicItem)player.getItemHolding()).isClothes()) {
                        if(player.getMovement().getStatus() != CharacterStatus.MOPPING_FLOOR){
                            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                                player.setStatusHold(4);
                                player.getMovement().setStatus(CharacterStatus.MOPPING_FLOOR);
                                player.getMovement().setActionCount(0);
                                ((DynamicItem)player.getItemHolding()).setVisible(false);
                                player.setItemInRange(item);
                            }
                        }
                    }
                }
                return;
            }
        }
    }
}
