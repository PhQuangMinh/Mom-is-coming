package com.mygdx.game.view.draw.screengame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.filter.filtersingle.FilterDynamicSingle;
import com.mygdx.game.controller.filter.filtersingle.FilterStaticSingle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.screens.maingame.singleplayer.SinglePlayer;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class DrawSingle extends InitDraw{

    FilterDynamicSingle filterDynamicSingle;

    FilterStaticSingle filterStaticSingle;

    public DrawSingle(SpaceGame spaceGame){
        super(spaceGame);
        filterDynamicSingle = new FilterDynamicSingle();
        filterStaticSingle = new FilterStaticSingle();
    }
    public void filterInGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player) {
        dynamicFloor.clear();
        dynamicTable.clear();
        dynamicTop.clear();
        staticBottom.clear();
        staticTop.clear();
        filterDynamicSingle.filter(dynamicItems, staticItems, player, dynamicFloor, dynamicTable, dynamicTop);
        filterStaticSingle.filter(player, staticItems, staticTop, staticMiddle, staticBottom, dynamicTable, dynamicTop);
    }


    public void drawSinglePlayer(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                                 SpriteBatch batch, float delta, DrawText drawText){
        filterInGame(dynamicItems, staticItems, player);
        drawDynamic.drawDynamicSingle(dynamicFloor, batch, player, drawText);
        drawStatic.drawStaticInGame(staticBottom, batch, player, drawText);
        drawDynamic.drawDynamicSingle(dynamicTable, batch, player, drawText);
        boolean pressedEnter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        drawPlayer.draw(player, batch, delta, pressedEnter);
        drawStatic.drawStaticInGame(staticTop, batch, player, drawText);
        drawDynamic.drawDynamicSingle(dynamicTop, batch, player, drawText);
    }

    public void draw(SpriteBatch batch, float stateTime, float delta, Player player, ArrayList<DynamicItem>dynamicItems,
                     ArrayList<StaticItem> staticItems, MainMenuScreen mainMenuScreen, SinglePlayer singlePlayer){
        drawMap.drawMap(batch);
        if (impression.getCountImpress()>=5){
            buttonGame.drawSingle(game, batch, stateTime, drawText, dynamicItems, mainMenuScreen, singlePlayer);
            holding.drawHoldSingle(batch, player, 10, 65);
            drawSinglePlayer(dynamicItems, staticItems, player, batch, delta, drawText);
        }
        drawMap.drawBarsSingle(batch, player);
        impression.drawGame(batch, stateTime);
    }
}
