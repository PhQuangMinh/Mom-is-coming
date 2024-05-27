package com.mygdx.game.view.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.controller.MakeSize;

public class Impression {
    Texture impression;
    private int countImpress = 0;

    Vector2 sizeItem;

    private Texture[] impressions;
    public Impression(String path){
        sizeItem = new Vector2();
        impression = new Texture(path);
    }

    public int getCountImpress() {
        return countImpress;
    }

    public Impression(){
        sizeItem = new Vector2();
        impressions = new Texture[6];
        for (int i = 0; i < 5; i++){
            impressions[i] = new Texture("story/impression" + (i + 1) + ".png");
        }
    }

    public void drawStory (SpriteBatch batch, float stateTime){
        float alpha = Math.min(1f, stateTime / 2f);
        batch.setColor(1, 1, 1, alpha);
        MakeSize makeSize = new MakeSize();
        makeSize.getSize(impression, ItemConstant.IMPRESSION_SIZE, sizeItem);
        batch.begin();
        batch.draw(impression, 115,110, sizeItem.x, sizeItem.y);
        batch.end();
    }

    public void drawGame (SpriteBatch batch, float stateTime){
        if (countImpress>=5) return;
        batch.setColor(1, 1, 1, 1);
        MakeSize makeSize = new MakeSize();
        makeSize.getSize(impressions[countImpress], ItemConstant.IMPRESSION_SIZE, sizeItem);
        int[] a = {110, 110, 110, 500, 680};
        batch.draw(impressions[countImpress], 115, a[countImpress], sizeItem.x, sizeItem.y);
        countImpress = (int) (1 + stateTime * 4);
    }
}
