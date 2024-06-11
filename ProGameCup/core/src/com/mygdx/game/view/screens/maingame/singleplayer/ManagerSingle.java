package com.mygdx.game.view.screens.maingame.singleplayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.discover.Discover;
import com.mygdx.game.controller.item.ManagerItem;
import com.mygdx.game.controller.player.ManagerPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.map.DrawMap;
import com.mygdx.game.view.draw.screengame.DrawSingle;
import com.mygdx.game.view.screens.Impression;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.Holding;
import com.mygdx.game.view.screens.maingame.ButtonGame;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class ManagerSingle {
    ManagerItem managerItem;

    ManagerPlayer managerPlayer;

    protected Discover discover;
    public ManagerSingle(){
           managerItem = new ManagerItem();
           managerPlayer = new ManagerPlayer();
           discover = new Discover();
    }
    public void update(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems) {
        updateItem(player, dynamicItems, staticItems);
        updatePlayer(player);
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
