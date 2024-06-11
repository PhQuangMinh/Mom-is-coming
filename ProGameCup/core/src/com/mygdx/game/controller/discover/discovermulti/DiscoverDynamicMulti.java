package com.mygdx.game.controller.discover.discovermulti;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.controller.discover.DiscoverDynamic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class DiscoverDynamicMulti extends DiscoverDynamic {
    public void discoverDynamic(ArrayList<DynamicItem> items, Player firstPlayer, Player secondPlayer) {
        resetDynamic(items);
        for (DynamicItem item : items) {
            if (checkDiscover(item, firstPlayer) || checkDiscover(item, secondPlayer)) {
                item.setDiscover(true);
//                if(item.getName().equals("puddle")){
//                    if(player.getItemHolding() != null && ((DynamicItem)player.getItemHolding()).isClothes()) {
//                        if(player.getMovement().getStatus() != CharacterStatus.MOPPING_FLOOR){
//                            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//                                player.setStatusHold(4);
//                                player.getMovement().setStatus(CharacterStatus.MOPPING_FLOOR);
//                                player.getMovement().setActionCount(0);
//                                ((DynamicItem)player.getItemHolding()).setVisible(false);
//                                player.setItemInRange(item);
//                            }
//                        }
//                    }
//                    return;
//                }
//                return;
            }
        }
    }
}
