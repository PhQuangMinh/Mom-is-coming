package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.discover.Discover;
import com.mygdx.game.controller.draw.Draw;
import com.mygdx.game.controller.item.activity.GetItem;
import com.mygdx.game.controller.item.activity.throwitem.ThrowItem;
import com.mygdx.game.controller.player.ManagerPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.screens.Impression;
import com.mygdx.game.view.ui.DrawText;
import com.mygdx.game.view.ui.Holding;

import java.util.ArrayList;

public class ManagerGame {
    Impression impression;
    ButtonGame buttonGame;
    SpaceGame game;
    DrawText drawText;
    Holding holding;
    Draw draw;
    public ManagerGame(SpaceGame spaceGame){
           impression = new Impression();
           buttonGame = new ButtonGame(spaceGame);
           this.game = spaceGame;
           drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
           holding = new Holding();
           draw = new Draw();

    }
    public void update(Player player, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems, SpriteBatch batch, float stateTime) {
        updateItem(player, dynamicItems, staticItems);
        updatePlayer(player);
        draw(batch, stateTime, player, dynamicItems, staticItems);
    }

    public void updatePlayer(Player player){
        ManagerPlayer managerPlayer = new ManagerPlayer();
        managerPlayer.updateStatus(player);
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

    public void draw(SpriteBatch batch, float stateTime, Player player, ArrayList<DynamicItem>dynamicItems,
                     ArrayList<StaticItem> staticItems){
        buttonGame.draw(game, batch, stateTime, drawText);
        holding.drawHold(batch, player);
        draw.draw(dynamicItems, staticItems, player, batch, stateTime, drawText);
        impression.drawGame(batch, stateTime);
    }
}
