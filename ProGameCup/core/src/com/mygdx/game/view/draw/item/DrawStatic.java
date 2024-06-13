package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class DrawStatic {
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

    private void drawNoteStatic(StaticItem item, SpriteBatch batch, DrawText drawText, Player player){
        drawText.drawNoteName(item, batch, drawText);
        float noteX = (GameConstant.WINDOW_WIDTH - note.getWidth()) / 2 + 40;
        float noteY = MapConstant.POS_MAP_Y + MapConstant.MAP_HEIGHT + note.getHeight();
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
        float containX = (MapConstant.UPPER_LIMIT_RIGHT_X + MapConstant.LOWER_LIMIT_RIGHT_X
                + GameConstant.PLAYER_WIDTH - item.getContainItem().getWidth()) / 2;
        batch.draw(item.getContainItem(), containX, 20);
        float firstPos = containX + getFirstPosition(item.getNumber());
        for (DynamicItem dynamicItem:item.getItems()){
            batch.draw(dynamicItem.getImage(), firstPos + (60-dynamicItem.getWidth())/2
                    , 30 + (60-dynamicItem.getHeight())/2
                    , dynamicItem.getWidth(), dynamicItem.getHeight());
            firstPos += 80;
        }
    }
    private void drawStaticItemInGame(StaticItem item, SpriteBatch batch, DrawText drawText, Player player) {
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

    private void drawStaticItemEndGame(StaticItem item, SpriteBatch batch) {
        Texture image;
        image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawStaticEndGame(ArrayList<StaticItem> items, SpriteBatch batch){
        for (StaticItem item : items) {
            drawStaticItemEndGame(item, batch);
        }
    }

    public void drawStaticInGame(ArrayList<StaticItem> items, SpriteBatch batch, Player player, DrawText drawText){
        if (items.isEmpty()) return;
        for (StaticItem item : items) {
            drawStaticItemInGame(item, batch, drawText, player);
        }
    }

}
