package com.mygdx.game.controller.discover;

import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class Discover {
    public void updateDiscover(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems
            , Player player) {
        DiscoverDynamic discover = new DiscoverDynamic();
        discover.discoverDynamic(dynamicItems, player);
        DiscoverStatic discover1 = new DiscoverStatic();
        discover1.discoverStatic(staticItems, player);
    }
}
