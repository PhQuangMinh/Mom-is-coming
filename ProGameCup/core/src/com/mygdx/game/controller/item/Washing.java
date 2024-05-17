package com.mygdx.game.controller.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.uiingame.DrawText;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Washing {
    private int xKeyPressCount = -1;
    public void washingDish(DynamicItem dynamicItem, ArrayList<DynamicItem> dynamicItems, Player player, Boolean inAnimationFinished){
        player.setStatus(CharacterStatus.CLEANING_DISH);
        dynamicItem.setVisible(false);
        xKeyPressCount = player.getWashingIndex();
        if (Gdx.input.isKeyPressed(Input.Keys.X) && inAnimationFinished) {
            xKeyPressCount++;
            player.setWashingIndex(xKeyPressCount);
            if (xKeyPressCount == 5) {
                player.setIsCountingXPress(false);
                player.setStatus(CharacterStatus.IDLE);
                player.setValidThrow(true);
                player.setItemHolding(null);
                dynamicItems.remove(dynamicItem);
                xKeyPressCount = -1;
            }
        }
    }
}
