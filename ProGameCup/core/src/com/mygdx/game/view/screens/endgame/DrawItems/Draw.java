package com.mygdx.game.view.screens.endgame.DrawItems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.screens.endgame.DrawItems.filter.FilterDynamic;
import com.mygdx.game.view.screens.endgame.DrawItems.filter.FilterStatic;

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
    public void filter(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems) {
        init();
        FilterDynamic filterDynamic = new FilterDynamic();
        filterDynamic.filter(dynamicItems, staticItems, dynamicFloor, dynamicTable, dynamicTop);
        FilterStatic filterStatic = new FilterStatic();
        filterStatic.filter( staticItems, staticTop, staticBottom, dynamicTable, dynamicTop);
    }

    public void draw(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems,
                     SpriteBatch batch){
        filter(dynamicItems, staticItems);
        DrawDynamic drawDynamic = new DrawDynamic();
        DrawStatic drawStatic = new DrawStatic();
        drawDynamic.drawDynamic(dynamicFloor, batch);
        drawStatic.drawStatic(staticBottom, batch);
        drawDynamic.drawDynamic(dynamicTable, batch);
        drawStatic.drawStatic(staticTop, batch);
        drawDynamic.drawDynamic(dynamicTop, batch);
    }
}
