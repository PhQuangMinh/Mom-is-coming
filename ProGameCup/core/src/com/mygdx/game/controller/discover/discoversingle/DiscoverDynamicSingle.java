package com.mygdx.game.controller.discover.discoversingle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.controller.discover.DiscoverDynamic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;

import java.util.ArrayList;

public class DiscoverDynamicSingle extends DiscoverDynamic {
    public void discoverDynamic(ArrayList<DynamicItem> items, Player player) {
        resetDynamic(items);
        for (DynamicItem item : items) {
            if (checkDiscover(item, player)){
                item.setDiscover(true);
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
                    return;
                }
                return;
            }
        }
    }


}
