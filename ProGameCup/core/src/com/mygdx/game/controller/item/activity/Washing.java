package com.mygdx.game.controller.item.activity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class Washing {
    public void washingDish(DynamicItem dynamicItem, ArrayList<DynamicItem> dynamicItems, Player player
            , Boolean inAnimationFinished){
        player.setStatus(CharacterStatus.CLEANING_DISH);
        dynamicItem.setVisible(false);
        int xKeyPressCount = player.getFrameIndex();

        if (Gdx.input.isKeyPressed(Input.Keys.X) && inAnimationFinished) {
            xKeyPressCount++;
            player.setFrameIndex(xKeyPressCount);
            if (xKeyPressCount == 12) {
                player.setIsCountingXPress(false);
                player.setStatus(CharacterStatus.IDLE);
                player.setValidThrow(true);
                player.setItemHolding(null);
                dynamicItems.remove(dynamicItem);
            }
        }
    }
}
