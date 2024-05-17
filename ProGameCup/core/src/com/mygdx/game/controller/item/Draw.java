package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.discover.DiscoverDynamic;
import com.mygdx.game.controller.discover.DiscoverStatic;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.DrawText;

import java.util.ArrayList;

public class Draw {
    private float noteX, noteY;

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

    private void drawNoteName(Item item, SpriteBatch batch, DrawText drawText){
        Texture note = new Texture("alert/note.png");
        batch.draw(note, (GameConstant.windowWidth-note.getWidth())/2
                , GameConstant.posMapY + GameConstant.mapHeight + 10);
        String noteText;
        if (item instanceof StaticItem){
            noteText = "It's the " + item.getName() + ".";
        }
        else{
            noteText = "It's a " + item.getName() + ".";
        }
        noteX = (GameConstant.windowWidth-note.getWidth())/2 + 10;
        noteY = GameConstant.posMapY + GameConstant.mapHeight + note.getHeight() - 5;
        drawText.drawStaticText(batch, noteText, noteX, noteY,0.5f, "fonts/char.fnt"
                , Color.BLACK);
    }

    private void drawNoteStatic(StaticItem item, SpriteBatch batch){
        DrawText drawText = new DrawText();
        drawNoteName(item, batch, drawText);
        if (item.getName().equals("dish-washing")){
            return;
        }
        String noteContains;
        if (item.getItems().isEmpty()) {
            noteContains = "It's empty.";
        }
        else if (item.getItems().size() == item.getNumber()) {
            noteContains = "It's full.";
        }
        else noteContains = "You can take " + item.getItems().get(item.getItems().size() - 1).getName() + ".";
        drawText.drawStaticText(batch, noteContains, noteX, noteY - 20, 0.5f
                , "fonts/char.fnt", Color.BLACK);
    }

    private void drawContains(StaticItem item, SpriteBatch batch){
        float containX = (GameConstant.windowWidth-item.getContainItem().getWidth())/2;
        batch.draw(item.getContainItem(), containX, 20);
        float firstPos = containX + getFirstPosition(item.getNumber());
        for (DynamicItem dynamicItem:item.getItems()){
            batch.draw(dynamicItem.getImage(), firstPos + (60-dynamicItem.getWidth())/2
                    , 30 + (60-dynamicItem.getHeight())/2
                    , dynamicItem.getWidth(), dynamicItem.getHeight());
            firstPos += 80;
        }
    }
    private void drawStaticItem(StaticItem item, SpriteBatch batch) {
        Texture image;
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            drawNoteStatic(item, batch);
            if (!item.getName().equals("dish-washing")){
                drawContains(item, batch);
            }
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    private void drawNoteDynamic(DynamicItem item, SpriteBatch batch) {
        DrawText drawText = new DrawText();
        drawNoteName(item, batch, drawText);
    }
    private void drawDynamicItem(DynamicItem item, SpriteBatch batch, Player player){
        if (!item.isVisible()) return;
        Texture image;
        if (player.getItemHolding()!=null){
            image = item.getImage();
            DrawText drawText = new DrawText();
            drawText.drawStaticText(batch, player.getItemHolding().getName(), 10, 720, 0.5f
                    , "fonts/char.fnt", Color.BROWN);
        }
        else
            if (item.getDiscover() && item.getChosenImage()!=null){
                image = item.getChosenImage();
                drawNoteDynamic(item, batch);
            }
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
            player.draw(batch, stateTime);
            drawStatic(staticItems, batch, player);
        }
        else{
            drawStatic(staticItems, batch, player);
            player.draw(batch, stateTime);
        }
        drawDynamic(dynamicTop, batch, player);
    }
}
