package com.mygdx.game.view.screens.endgame.DrawItems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class DrawDynamic {
    private void drawDynamicItem(DynamicItem item, SpriteBatch batch){
        Texture image;
        image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }
    public void drawDynamic(ArrayList<DynamicItem> items, SpriteBatch batch){
        for (DynamicItem item : items) {
            drawDynamicItem(item, batch);
        }
    }
}
