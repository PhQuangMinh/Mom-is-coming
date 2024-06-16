package com.mygdx.game.controller.discover;

import com.mygdx.game.controller.discover.discovermulti.DiscoverDynamicMulti;
import com.mygdx.game.controller.discover.discovermulti.DiscoverStaticMulti;
import com.mygdx.game.controller.discover.discoversingle.DiscoverDynamicSingle;
import com.mygdx.game.controller.discover.discoversingle.DiscoverStaticSingle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class Discover {
    DiscoverDynamicSingle discoverDynamicSingle;

    DiscoverStaticSingle discoverStaticSingle;

    DiscoverDynamicMulti discoverDynamicMulti;

    DiscoverStaticMulti discoverStaticMulti;

    public Discover() {
        discoverDynamicSingle = new DiscoverDynamicSingle();
        discoverStaticSingle = new DiscoverStaticSingle();

        discoverDynamicMulti = new DiscoverDynamicMulti();

        discoverStaticMulti = new DiscoverStaticMulti();
    }
    public void updateDiscoverSingle(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems
            , Player player) {
        discoverDynamicSingle.discoverDynamic(dynamicItems, player);
        discoverStaticSingle.discoverStatic(staticItems, player);
    }

    public void updateDiscoverMulti(ArrayList<DynamicItem> dynamicItems,
                                    ArrayList<StaticItem> staticItems
            , Player firstPlayer, Player secondPlayer) {
        discoverDynamicMulti.discoverDynamic(dynamicItems, firstPlayer, secondPlayer);
        discoverStaticMulti.discoverStatic(staticItems, firstPlayer, secondPlayer);
    }
}
