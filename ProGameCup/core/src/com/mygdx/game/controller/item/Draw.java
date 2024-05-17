package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.PlayerMovement;
import com.mygdx.game.controller.discover.DiscoverDynamic;
import com.mygdx.game.controller.discover.DiscoverStatic;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class Draw {

    private int getFirstPosition(int quantity){
        switch (quantity){
            case 1:
                return 210;
            case 2:
                return 170;
            case 3:
                return 130;
            case 4:
                return 90;
        }
        return 10;
    }

    private void drawStaticItem(StaticItem item, SpriteBatch batch) {
        Texture image;
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            if (!item.getName().equals("dish-washing")){
                float containX = (GameConstant.windowWidth-item.getContainItem().getWidth())/2;
                batch.draw(item.getContainItem(), containX, 50);
                float firstPos = containX + getFirstPosition(item.getNumber());
                for (DynamicItem dynamicItem:item.getItems()){
                    batch.draw(dynamicItem.getImage(), firstPos + (60-dynamicItem.getWidth())/2
                            , 60 + (60-dynamicItem.getHeight())/2
                            , dynamicItem.getWidth(), dynamicItem.getHeight());
                    firstPos += 80;
                }
            }
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    private void drawDynamicItem(DynamicItem item, SpriteBatch batch, Player player){
        if (!item.isVisible()) return;
        Texture image;
        if (player.getItemHolding()!=null) image = item.getImage();
        else
            if (item.getDiscover() && item.getChosenImage()!=null) image = item.getChosenImage();
            else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawStatic(ArrayList<StaticItem> items, SpriteBatch batch, Player player){
        DiscoverStatic discover = new DiscoverStatic();
        discover.discoverStatic(items, player);
        for (StaticItem item : items) {
            drawStaticItem(item, batch);
        }
    }

    public void drawDynamic(ArrayList<DynamicItem> items, SpriteBatch batch, Player player){
        DiscoverDynamic discover = new DiscoverDynamic();
        discover.discoverDynamic(items, player);
        for (DynamicItem item : items) {
            drawDynamicItem(item, batch, player);
        }
    }

    public boolean checkTop(DynamicItem dynamicItem, Player player, ArrayList<StaticItem> staticItems){
        if (player.getItemHolding()!=null && player.getItemHolding().equals(dynamicItem)) return true;
        for (StaticItem item : staticItems){
            Rectangle rectStatic = item.getBoundingRectangle();
            Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
            if (rectStatic.contains(rectDynamic)) {
                return true;
            }
        }
        return false;
    }

    public void filterDynamic(ArrayList<DynamicItem> items, ArrayList<DynamicItem> dynamicTop,
                              ArrayList<DynamicItem> dynamicBottom, Player player, ArrayList<StaticItem> staticItems){
        for (DynamicItem item : items){
            if (checkTop(item, player, staticItems)) dynamicTop.add(item);
            else dynamicBottom.add(item);
        }
    }

    public void draw(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems, Player player,
                     SpriteBatch batch, float stateTime){
        ArrayList<DynamicItem> dynamicTop = new ArrayList<>();
        ArrayList<DynamicItem> dynamicBottom = new ArrayList<>();
        filterDynamic(dynamicItems, dynamicTop, dynamicBottom, player, staticItems);
        drawDynamic(dynamicBottom, batch, player);
        if (player.getOverlap()){
            PlayerMovement.draw(player, batch, stateTime);
            drawStatic(staticItems, batch, player);
        }
        else{
            drawStatic(staticItems, batch, player);
            PlayerMovement.draw(player, batch, stateTime);
        }
        drawDynamic(dynamicTop, batch, player);
    }
}
