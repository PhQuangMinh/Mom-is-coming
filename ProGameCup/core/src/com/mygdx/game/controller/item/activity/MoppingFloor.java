package com.mygdx.game.controller.item.activity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class MoppingFloor {
    public boolean checkMoppingSingle(Player player, DynamicItem item, boolean keyPress){
        return item.getDiscover() && item.getName().equals("puddle") &&
                player.getItemHolding() != null && player.getItemHolding().isClothes() &&
                player.getMovement().getStatus() != CharacterStatus.MOPPING_FLOOR && keyPress;
    }

    private void updatePlayer(Player player, DynamicItem item) {
        player.getMovement().setStatus(CharacterStatus.MOPPING_FLOOR);
        player.setItemMopping(item);
        player.getItemHolding().setVisible(false);
        player.getItemHolding().setActionCount(0);
        player.setItemInRange(item);
    }

    public void moppingSingle(Player player, ArrayList<DynamicItem> dynamicItems, boolean keyPressed){
        for (DynamicItem item : dynamicItems) {
            if (checkMoppingSingle(player, item, keyPressed)){
                updatePlayer(player, item);
                return;
            }
        }
    }

    public void moppingMulti(Player firstPlayer, Player secondPlayer,
                             ArrayList<DynamicItem> dynamicItems, boolean keyPressed){
        for (DynamicItem item : dynamicItems) {
            if (checkMoppingSingle(firstPlayer, item, keyPressed)){
                if (secondPlayer.getItemMopping() == null || !secondPlayer.getItemMopping().equals(item)){
                    updatePlayer(firstPlayer, item);
                    firstPlayer.setValidThrow(true);
                    return;
                }
                else{
                    firstPlayer.setValidThrow(false);
                }
            }
        }
    }
}
