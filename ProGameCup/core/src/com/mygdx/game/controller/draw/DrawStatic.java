package com.mygdx.game.controller.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.ui.DrawText;

import java.util.ArrayList;

public class DrawStatic {
    private float noteX, noteY;
    Texture note = new Texture("alert/note.png");
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

    public void drawNoteName(Item item, SpriteBatch batch, DrawText drawText){
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
        drawText.drawStaticText(batch, noteText, noteX, noteY,0.5f);
    }

    private void drawNoteStatic(StaticItem item, SpriteBatch batch, DrawText drawText, Player player){
        drawNoteName(item, batch, drawText);
        if (item.getName().equals("dish-washing")){
            return;
        }
        String noteContains;
        if (item.getItems().isEmpty()) {
            noteContains = "It's empty.";
            drawText.drawStaticText(batch, noteContains, noteX, noteY - 20, 0.5f);
        }
        else if (item.getItems().size() == item.getNumber()) {
            noteContains = "It's full.";
            drawText.drawStaticText(batch, noteContains, noteX, noteY - 20, 0.5f);
        }
        else
            if (player.getItemHolding()==null){
                noteContains = "You can take " + item.getItems().get(item.getItems().size() - 1).getName() + ".";
                drawText.drawStaticText(batch, noteContains, noteX, noteY - 20, 0.5f);
            }
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
    private void drawStaticItem(StaticItem item, SpriteBatch batch, DrawText drawText, Player player) {
        Texture image;
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            drawNoteStatic(item, batch, drawText, player);
            if (!item.getName().equals("dish-washing")){
                drawContains(item, batch);
            }
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawStatic(ArrayList<StaticItem> items, SpriteBatch batch, Player player, DrawText drawText){
        for (StaticItem item : items) {
            drawStaticItem(item, batch, drawText, player);
        }
    }

}
