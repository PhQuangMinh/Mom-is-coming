package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class DrawDynamic {
    private void drawDynamicItemInGame(DynamicItem item, SpriteBatch batch, Player player, DrawText drawText){
        if (!item.isVisible()) return;
        Texture image;
        if (player.getItemHolding()!=null){
            image = item.getImage();
            drawText.drawStaticText(batch, player.getItemHolding().getName(), 180, 870, 0.5f);
        }
        else
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            drawText.drawNoteName(item, batch, drawText);
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    private void drawDynamicItemEndGame(DynamicItem item, SpriteBatch batch){
        Texture image;
        image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }
    public void drawDynamicEndGame(ArrayList<DynamicItem> items, SpriteBatch batch){
        for (DynamicItem item : items) {
            drawDynamicItemEndGame(item, batch);
        }
    }

    public void drawDynamicInGame(ArrayList<DynamicItem> items, SpriteBatch batch, Player player, DrawText drawText){
        for (DynamicItem item : items) {
            drawDynamicItemInGame(item, batch, player, drawText);
        }
    }
}
