package com.mygdx.game.view.draw.screengame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.filter.filterendgame.FilterDynamicEndGame;
import com.mygdx.game.controller.filter.filterendgame.FilterStaticEndGame;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.item.DrawDynamic;
import com.mygdx.game.view.draw.item.DrawStatic;
import com.mygdx.game.view.draw.player.DrawPlayer;

import java.util.ArrayList;

public class DrawEndGame extends InitDraw{
    FilterDynamicEndGame filterDynamicEndGame;

    FilterStaticEndGame filterStaticEndGame;

    public DrawEndGame(SpaceGame spaceGame){
        super(spaceGame);
        dynamicFloor.clear();
        dynamicTable.clear();
        dynamicTop.clear();
        staticBottom.clear();
        staticTop.clear();
        filterDynamicEndGame = new FilterDynamicEndGame();
        filterStaticEndGame = new FilterStaticEndGame();
    }


    public void filterEndGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems) {
        filterDynamicEndGame.filter(dynamicItems, staticItems, dynamicFloor, dynamicTable, dynamicTop);
        filterStaticEndGame.filter( staticItems, staticTop, staticBottom, dynamicTable, dynamicTop);
    }

    public void drawEndGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems,
                     SpriteBatch batch){
        filterEndGame(dynamicItems, staticItems);
        drawDynamic.drawDynamicEndGame(dynamicFloor, batch);
        drawStatic.drawStaticEndGame(staticBottom, batch);
        drawDynamic.drawDynamicEndGame(dynamicTable, batch);
        drawStatic.drawStaticEndGame(staticTop, batch);
        drawDynamic.drawDynamicEndGame(dynamicTop, batch);
    }

}
