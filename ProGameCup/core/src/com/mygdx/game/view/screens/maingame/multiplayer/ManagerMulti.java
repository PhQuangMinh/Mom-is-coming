package com.mygdx.game.view.screens.maingame.multiplayer;

import com.mygdx.game.controller.discover.Discover;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.screens.maingame.singleplayer.ManagerSingle;

import java.util.ArrayList;

public class ManagerMulti extends ManagerSingle {

    public ManagerMulti() {
        super();
    }

    public void update(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                       ArrayList<StaticItem> staticItems){
        updateItem(firstPlayer, secondPlayer, dynamicItems, staticItems);
        updatePlayer(firstPlayer, secondPlayer);
    }

    public void updateItem(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                           ArrayList<StaticItem> staticItems) {
        super.updateItem(firstPlayer, dynamicItems, staticItems);
        super.updateItem(secondPlayer, dynamicItems, staticItems);
        discover.updateDiscoverMulti(dynamicItems, staticItems, firstPlayer, secondPlayer);
        discover.updateDiscoverMulti(dynamicItems, staticItems, firstPlayer, secondPlayer);
    }

    public void updatePlayer(Player firstPlayer, Player secondPlayer) {
        super.updatePlayer(firstPlayer);
        super.updatePlayer(secondPlayer);
    }
}
