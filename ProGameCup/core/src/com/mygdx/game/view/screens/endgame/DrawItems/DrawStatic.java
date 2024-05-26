package com.mygdx.game.view.screens.endgame.DrawItems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class DrawStatic {
    private void drawStaticItem(StaticItem item, SpriteBatch batch) {
        Texture image;
        image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawStatic(ArrayList<StaticItem> items, SpriteBatch batch){
        for (StaticItem item : items) {
            drawStaticItem(item, batch);
        }
    }

}
