package com.mygdx.game.controller.discover.discovermulti;

import com.mygdx.game.controller.discover.DiscoverStatic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class DiscoverStaticMulti extends DiscoverStatic {
    public void discoverStatic(ArrayList<StaticItem> items, Player firstPlayer, Player secondPlayer) {
        resetStatic(items);
        firstPlayer.setContainer(null);
        secondPlayer.setContainer(null);
        for (StaticItem item : items) {
            if (firstPlayer.getContainer()== null && checkDiscover(item, firstPlayer)){
                item.setDiscover(true);
                firstPlayer.setContainer(item);
            }
            if (secondPlayer.getContainer()== null && checkDiscover(item, secondPlayer)){
                item.setDiscover(true);
                secondPlayer.setContainer(item);
            }
        }
    }
}
