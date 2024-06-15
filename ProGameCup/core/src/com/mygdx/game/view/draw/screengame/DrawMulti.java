package com.mygdx.game.view.draw.screengame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.filter.filtermulti.FilterDynamicMulti;
import com.mygdx.game.controller.filter.filtermulti.FilterStaticMulti;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.screens.maingame.multiplayer.MultiPlayer;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class DrawMulti extends InitDraw{
    FilterDynamicMulti filterDynamicMulti;

    FilterStaticMulti filterStaticMulti;


    public DrawMulti(SpaceGame spaceGame) {
        super(spaceGame);
        filterDynamicMulti = new FilterDynamicMulti();
        filterStaticMulti  = new FilterStaticMulti();
    }

    public boolean checkMiddle(Player firstPlayer, Player secondPlayer) {
        if (staticMiddle.isEmpty()) return true;
        for (StaticItem staticItem : staticMiddle) {
            if (checkObscure(staticItem, firstPlayer)){
                return false;
            }
            else {
                if (checkObscure(staticItem, secondPlayer)){
                    return true;
                }
            }
        }
        return true;
    }

    public void filterMulti(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                            ArrayList<StaticItem> staticItems){
        dynamicFloor.clear();
        dynamicTable.clear();
        dynamicTop.clear();
        staticBottom.clear();
        staticMiddle.clear();
        staticTop.clear();
        filterDynamicMulti.filter(firstPlayer, secondPlayer, dynamicItems, staticItems, dynamicFloor,
                dynamicTable, dynamicTop);
        filterStaticMulti.filter(firstPlayer, secondPlayer, staticItems, staticTop, staticBottom,
                dynamicTable, dynamicTop, staticMiddle);
    }



    public void drawMultiPlayer(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems,
                                Player firstPlayer, SpriteBatch batch, float delta, DrawText drawText,
                                Player secondPlayer){
        filterMulti(firstPlayer, secondPlayer, dynamicItems, staticItems);
        drawDynamic.drawDynamicMulti(firstPlayer, secondPlayer, drawText, dynamicFloor, batch);
        drawStatic.drawStaticInGame(staticBottom, batch, firstPlayer, drawText);
        drawDynamic.drawDynamicMulti(firstPlayer, secondPlayer, drawText, dynamicTable, batch);
        boolean pressEnter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        boolean pressSpace = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if (!checkMiddle(firstPlayer, secondPlayer)){
            drawPlayer.draw(firstPlayer, batch, delta, pressEnter);
            drawStatic.drawStaticInGame(staticMiddle, batch, firstPlayer, drawText);
            drawPlayer.draw(secondPlayer, batch, delta, pressSpace);
        }
        else{
            drawPlayer.draw(secondPlayer, batch, delta, pressSpace);
            drawStatic.drawStaticInGame(staticMiddle, batch, secondPlayer, drawText);
            drawPlayer.draw(firstPlayer, batch, delta, pressEnter);
        }
        drawStatic.drawStaticInGame(staticTop, batch, firstPlayer, drawText);
        drawDynamic.drawDynamicMulti(firstPlayer, secondPlayer, drawText, dynamicTop, batch);
    }

    public void draw(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                     ArrayList<StaticItem> staticItems, SpriteBatch batch, float stateTime,
                     float delta, MainEndStory mainEndStory){
        drawMap.drawMap(batch);
        System.out.println("HIHI" + impression.getCountImpress());
        if (impression.getCountImpress()>=5){
            buttonGame.drawPauseGame(game, batch, stateTime, drawText, mainEndStory);
            holding.drawHoldMulti(firstPlayer, secondPlayer, batch);
            drawMultiPlayer(dynamicItems, staticItems, firstPlayer, batch, delta, drawText, secondPlayer);
        }
        drawMap.drawBarsMulti(batch, firstPlayer, secondPlayer);
        impression.drawGame(batch, stateTime);
    }
}
