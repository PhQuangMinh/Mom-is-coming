package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.discover.Discover;
import com.mygdx.game.view.draw.item.Draw;
import com.mygdx.game.controller.item.activity.GetItem;
import com.mygdx.game.controller.item.activity.throwitem.ThrowItem;
import com.mygdx.game.controller.player.ManagerPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.map.DrawMap;
import com.mygdx.game.view.screens.Impression;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.Holding;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class ManagerGame {
    Impression impression;
    ButtonGame buttonGame;
    SpaceGame game;
    Holding holding;
    Draw draw;
    DrawMap drawMap;
    public ManagerGame(SpaceGame spaceGame){
           impression = new Impression();
           buttonGame = new ButtonGame(spaceGame);
           this.game = spaceGame;
           holding = new Holding();
           draw = new Draw();
           drawMap = new DrawMap();
    }
    public void update(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems
            , SpriteBatch batch, float stateTime, float delta, MainMenuScreen mainMenuScreen,MainStory mainStory, MainEndStory mainEndStory, DrawText drawText, int initTime) {
        updateItem(player, dynamicItems, staticItems);
        updatePlayer(player);
        draw(batch, stateTime, delta, player, dynamicItems, staticItems, mainMenuScreen, mainStory, mainEndStory, drawText, initTime);
    }

    public void updatePlayer(Player player){
        ManagerPlayer managerPlayer = new ManagerPlayer();
        managerPlayer.updateStatus(player);
        managerPlayer = null;
    }

    public void updateItem(Player player, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems){
        Discover discover = new Discover();
        discover.updateDiscover(dynamicItems, staticItems, player);
        ThrowItem throwItem = new ThrowItem();
        throwItem.updatePosition(dynamicItems, staticItems, player);
        GetItem getItem = new GetItem();
        getItem.takeItemStatic(player, dynamicItems);
    }

    public void draw(SpriteBatch batch, float stateTime, float delta, Player player, ArrayList<DynamicItem>dynamicItems,
                     ArrayList<StaticItem> staticItems, MainMenuScreen mainMenuScreen, MainStory mainStory, MainEndStory mainEndStory, DrawText drawText, int initTime){
        drawMap.drawMap(batch);
        if (impression.getCountImpress()>=5){
            buttonGame.draw(game, batch, stateTime, drawText, dynamicItems, mainMenuScreen, mainStory, mainEndStory, initTime);
            holding.drawHold(batch, player);
            draw.drawInGame(dynamicItems, staticItems, player, batch, delta, drawText);
        }
        drawMap.drawBars(batch, player);
        impression.drawGame(batch, stateTime);
    }
}
