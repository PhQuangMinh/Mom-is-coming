package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.player.PlayerMovement;
import com.mygdx.game.controller.filter.FilterDynamic;
import com.mygdx.game.controller.filter.FilterStatic;
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

    private void init() {
        dynamicFloor = new ArrayList<>();
        dynamicTable = new ArrayList<>();
        dynamicTop = new ArrayList<>();
        staticBottom = new ArrayList<>();
        staticTop = new ArrayList<>();
    }
    public void filter(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player) {
        init();
        FilterDynamic filterDynamic = new FilterDynamic();
        filterDynamic.filter(dynamicItems, staticItems, player, dynamicFloor, dynamicTable, dynamicTop);
        FilterStatic filterStatic = new FilterStatic();
        filterStatic.filter(player, staticItems, staticTop, staticBottom, dynamicTable, dynamicTop);
    }

    public void draw(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                     SpriteBatch batch, float stateTime, DrawText drawText){
        filter(dynamicItems, staticItems, player);
        DrawDynamic drawDynamic = new DrawDynamic();
        DrawStatic drawStatic = new DrawStatic();
        drawDynamic.drawDynamic(dynamicFloor, batch, player, drawText);
        drawStatic.drawStatic(staticBottom, batch, player, drawText);
        drawDynamic.drawDynamic(dynamicTable, batch, player, drawText);
        PlayerMovement.draw(player, batch, stateTime);
        drawStatic.drawStatic(staticTop, batch, player, drawText);
        drawDynamic.drawDynamic(dynamicTop, batch, player, drawText);
    }
}
