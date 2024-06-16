package com.mygdx.game.controller.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ManagerItem {
    public void updatePosition(Player player){
        if (player.getItemHolding()==null || player.getStatusHold() == 4) return;
        player.getItemHolding().setPosition(player.getX() + (player.getWidth() - player.getItemHolding().getWidth()) / 2
                , player.getY() + player.getHeight());
    }
}
