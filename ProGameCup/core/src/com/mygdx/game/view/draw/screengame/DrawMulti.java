package com.mygdx.game.view.draw.screengame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.filter.filtermulti.FilterDynamicMulti;
import com.mygdx.game.controller.filter.filtermulti.FilterStaticMulti;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.text.DrawText;
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
        drawDynamic.drawDynamicInGame(dynamicFloor, batch, firstPlayer, drawText);
        drawStatic.drawStaticInGame(staticBottom, batch, firstPlayer, drawText);
        drawDynamic.drawDynamicInGame(dynamicTable, batch, firstPlayer, drawText);
        if (!checkMiddle(firstPlayer, secondPlayer)){
            drawPlayer.draw(firstPlayer, batch, delta);
            drawStatic.drawStaticInGame(staticMiddle, batch, firstPlayer, drawText);
            drawPlayer.draw(secondPlayer, batch, delta);
        }
        else{
            drawPlayer.draw(secondPlayer, batch, delta);
            drawStatic.drawStaticInGame(staticMiddle, batch, secondPlayer, drawText);
            drawPlayer.draw(firstPlayer, batch, delta);
        }
        for (StaticItem staticItem : staticMiddle) System.out.println(staticItem.getName());
        drawStatic.drawStaticInGame(staticTop, batch, firstPlayer, drawText);
        drawDynamic.drawDynamicInGame(dynamicTop, batch, firstPlayer, drawText);
    }

    public void draw(Player firstPlayer, Player secondPlayer, ArrayList<DynamicItem> dynamicItems,
                     ArrayList<StaticItem> staticItems, MainMenuScreen mainMenuScreen, MainStory mainStory,
                     SpriteBatch batch, float stateTime, float delta){
        drawMap.drawMap(batch);
        if (impression.getCountImpress()>=5){
            buttonGame.draw(game, batch, stateTime, drawText, dynamicItems, mainMenuScreen, mainStory);
            holding.drawHold(batch, firstPlayer);
            drawMultiPlayer(dynamicItems, staticItems, firstPlayer, batch, delta, drawText, secondPlayer);
        }
        drawMap.drawBars(batch, firstPlayer);
        impression.drawGame(batch, stateTime);
    }
}
