package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Item;
import com.mygdx.game.model.Player;

import java.util.ArrayList;

public class DrawItems{

    private void drawItem(Item item, SpriteBatch batch) {
        Texture image;
        if (item.getDiscover() && item.getChosenImage()!=null) image = item.getChosenImage();
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawItems(ArrayList<Item> items, SpriteBatch batch, Player player){
        SolveDiscover discover = new SolveDiscover();
        discover.discoverItems(items, player);
        for (Item item : items) {
//            System.out.println(item.getDiscover());
            drawItem(item, batch);
        }
    }
}
