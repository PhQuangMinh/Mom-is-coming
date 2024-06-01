package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.filter.filterendgame.FilterDynamicEndGame;
import com.mygdx.game.controller.filter.filterendgame.FilterStaticEndGame;
import com.mygdx.game.controller.filter.filteringame.FilterDynamicInGame;
import com.mygdx.game.controller.player.PlayerMovement;
import com.mygdx.game.controller.filter.filteringame.FilterStaticInGame;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class Draw {
    public ArrayList<DynamicItem> dynamicFloor;
    public ArrayList<DynamicItem> dynamicTable;
    public ArrayList<DynamicItem> dynamicTop;
    public ArrayList<StaticItem> staticBottom;
    public ArrayList<StaticItem> staticTop;

    public DrawStatic drawStatic;

    public DrawDynamic drawDynamic;

    private void init() {
        dynamicFloor = new ArrayList<>();
        dynamicTable = new ArrayList<>();
        dynamicTop = new ArrayList<>();
        staticBottom = new ArrayList<>();
        staticTop = new ArrayList<>();
        drawDynamic = new DrawDynamic();
        drawStatic = new DrawStatic();
    }
    public void filterInGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player) {
        init();
        FilterDynamicInGame filterDynamicInGame = new FilterDynamicInGame();
        filterDynamicInGame.filter(dynamicItems, staticItems, player, dynamicFloor, dynamicTable, dynamicTop);
        FilterStaticInGame filterStaticInGame = new FilterStaticInGame();
        filterStaticInGame.filter(player, staticItems, staticTop, staticBottom, dynamicTable, dynamicTop);
    }

    public void drawInGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                     SpriteBatch batch, float delta, DrawText drawText){
        filterInGame(dynamicItems, staticItems, player);
        drawDynamic.drawDynamicInGame(dynamicFloor, batch, player, drawText);
        drawStatic.drawStaticInGame(staticBottom, batch, player, drawText);
        drawDynamic.drawDynamicInGame(dynamicTable, batch, player, drawText);
        PlayerMovement.draw(player, batch, delta);
        drawStatic.drawStaticInGame(staticTop, batch, player, drawText);
        drawDynamic.drawDynamicInGame(dynamicTop, batch, player, drawText);
    }

    public void filterEndGame(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems) {
        init();
        FilterDynamicEndGame filterDynamicEndGame = new FilterDynamicEndGame();
        filterDynamicEndGame.filter(dynamicItems, staticItems, dynamicFloor, dynamicTable, dynamicTop);
        FilterStaticEndGame filterStaticEndGame = new FilterStaticEndGame();
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
