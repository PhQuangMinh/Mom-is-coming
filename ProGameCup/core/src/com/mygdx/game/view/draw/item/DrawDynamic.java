package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class DrawDynamic {
    private void drawNoteDynamic(DynamicItem item, SpriteBatch batch, DrawText drawText) {
        DrawStatic drawStatic = new DrawStatic();
        drawStatic.drawNoteName(item, batch, drawText);
    }
    private void drawDynamicItem(DynamicItem item, SpriteBatch batch, Player player, DrawText drawText){
        if (!item.isVisible()) return;
        Texture image;
        if (player.getItemHolding()!=null){
            image = item.getImage();
            drawText.drawStaticText(batch, player.getItemHolding().getName(), 10, 720, 0.5f);
        }
        else
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            drawNoteDynamic(item, batch, drawText);
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }


    public void drawDynamic(ArrayList<DynamicItem> items, SpriteBatch batch, Player player, DrawText drawText){
        for (DynamicItem item : items) {
            drawDynamicItem(item, batch, player, drawText);
        }
    }
}
