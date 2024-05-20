package com.mygdx.game.controller.item.activity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import java.util.ArrayList;

public class MoppingFloor {
    public void moppingFloor(DynamicItem dynamicItem, ArrayList<DynamicItem> dynamicItems, Player player, Boolean inAnimationFinished){
        player.setStatus(CharacterStatus.MOPPING_FLOOR);
        ((DynamicItem)player.getItemHolding()).setVisible(false);
        int xKeyPressCount = player.getFrameIndex();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && inAnimationFinished) {
            xKeyPressCount++;
            player.setFrameIndex(xKeyPressCount);
            System.out.println(player.getFrameIndex());
            if (xKeyPressCount == 12) {
                player.setIsCountingXPress(false);
                player.setStatus(CharacterStatus.IDLE);
                player.setValidThrow(true);
                dynamicItem.setVisible(false);
                dynamicItems.remove(dynamicItem);
                dynamicItems.remove((DynamicItem) player.getItemHolding());
                player.setItemHolding(null);
            }
        }
    }
}
