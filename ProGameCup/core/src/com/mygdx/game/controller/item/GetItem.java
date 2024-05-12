package com.mygdx.game.controller.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class GetItem {
    public void takeItemStatic(Player player, ArrayList<DynamicItem> dynamicItems) {
        if (player.getContainer() == null || player.getItemHolding() != null
                || player.getContainer().getNumber()==0) return;
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) && player.getStatusHold()==3){
            ArrayList<DynamicItem> dynamic = player.getContainer().getItems();
            DynamicItem dynamicItem = dynamic.get(dynamic.size()-1);
            player.setItemHolding(dynamicItem);
            dynamicItem.setVisible(true);
            dynamicItem.setPosition(player.getX() + (player.getWidth() - dynamicItem.getWidth()) / 2
                    , player.getY() + 0.6f * player.getHeight());
            dynamic.remove(dynamicItem);
            dynamicItems.add(dynamicItem);
        }

    }
}
