package com.mygdx.game.view.screens.maingame.singleplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.discover.Discover;
import com.mygdx.game.controller.item.ManagerItem;
import com.mygdx.game.controller.item.activity.MoppingFloor;
import com.mygdx.game.controller.item.activity.WashingDish;
import com.mygdx.game.controller.player.ManagerPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class ManagerSingle {
    protected ManagerItem managerItem;

    ManagerPlayer managerPlayer;

    protected Discover discover;

    protected MoppingFloor moppingFloor;

    protected WashingDish washingDish;
    public ManagerSingle(){
           managerItem = new ManagerItem();
           managerPlayer = new ManagerPlayer();
           discover = new Discover();
           moppingFloor = new MoppingFloor();
           washingDish = new WashingDish();
    }

    public void update(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems) {
        updateItem(player, dynamicItems, staticItems);
        updatePlayer(player);
        moppingFloor.moppingSingle(player, dynamicItems, Gdx.input.isKeyJustPressed(Input.Keys.ENTER));
        washingDish.washingDishSingle(player, Gdx.input.isKeyJustPressed(Input.Keys.ENTER));
    }

    public void updatePlayer(Player player){
        managerPlayer.updateStatus(player);
    }

    public void updateItem(Player player, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems){
        discover.updateDiscoverSingle(dynamicItems, staticItems, player);
        managerItem.updatePosition(player);
    }

}
