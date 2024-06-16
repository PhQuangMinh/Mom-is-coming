package com.mygdx.game.controller.discover.discoversingle;

import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.controller.discover.DiscoverStatic;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class DiscoverStaticSingle extends DiscoverStatic {
    public void discoverStatic(ArrayList<StaticItem> items, Player player) {
        resetStatic(items);
        for (StaticItem item : items) {
            if (checkDiscover(item, player)){
                item.setDiscover(true);
                player.setContainer(item);
                return;
            }
        }
        player.setContainer(null);
    }
}
