package com.mygdx.game.view.screens.maingame.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        updateActivity(firstPlayer, secondPlayer, dynamicItems);

    }

    public void updateItem(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                           ArrayList<StaticItem> staticItems) {
        managerItem.updatePosition(firstPlayer);
        managerItem.updatePosition(secondPlayer);
        discover.updateDiscoverMulti(dynamicItems, staticItems, firstPlayer, secondPlayer);
    }

    public void updateActivity(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> items){
        moppingFloor.moppingMulti(firstPlayer, secondPlayer, items,
                Gdx.input.isKeyJustPressed(Input.Keys.ENTER));
        moppingFloor.moppingMulti(secondPlayer, firstPlayer, items, Gdx.input.isKeyJustPressed(Input.Keys.SPACE));
        washingDish.washingDishMulti(firstPlayer, secondPlayer, Gdx.input.isKeyJustPressed(Input.Keys.ENTER));
        washingDish.washingDishMulti(secondPlayer, firstPlayer, Gdx.input.isKeyJustPressed(Input.Keys.SPACE));
    }

    public void updatePlayer(Player firstPlayer, Player secondPlayer) {
        super.updatePlayer(firstPlayer);
        super.updatePlayer(secondPlayer);
    }
}
