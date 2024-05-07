package com.mygdx.game.controller.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Item;
import com.mygdx.game.model.Character;

import java.util.ArrayList;

public class DrawItems{

    private boolean check(Item item, Character character){
        return true;
    }

    private void drawItem(Item item, SpriteBatch batch, Character character) {
        if (check(item, character)) batch.draw(item.getImage(), item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawItems(ArrayList<Item> items, SpriteBatch batch, Character character){
        for (Item item : items) {
//            System.out.println(item.getWidth() + " " + item.getHeight());
            drawItem(item, batch, character);
        }
    }
}
