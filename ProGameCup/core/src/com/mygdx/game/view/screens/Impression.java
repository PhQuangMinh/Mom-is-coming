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

    private Texture[] impressions;
    public Impression(String path){
        impression = new Texture(path);
    }

    public int getCountImpress() {
        return countImpress;
    }

    public Impression(){
        impressions = new Texture[6];
        for (int i = 0; i < 5; i++){
            impressions[i] = new Texture("story/impression" + (i + 1) + ".png");
        }
    }

    public void drawStory (SpriteBatch batch, float stateTime){
        float alpha = Math.min(1f, stateTime / 2f);
        batch.setColor(1, 1, 1, alpha);
        MakeSize makeSize = new MakeSize();
        Vector2 size = makeSize.getSize(impression, ItemConstant.IMPRESSION_SIZE);
        batch.begin();
        batch.draw(impression, 150,110, size.x, size.y);
        batch.end();
    }

    public void drawGame (SpriteBatch batch, float stateTime){
        if (countImpress>=5) return;
        batch.setColor(1, 1, 1, 1);
        MakeSize makeSize = new MakeSize();
        Vector2 size = makeSize.getSize(impressions[countImpress], ItemConstant.IMPRESSION_SIZE);
        int[] a = {110, 110, 110, 500, 680};
        batch.draw(impressions[countImpress], 150, a[countImpress], size.x, size.y);
        countImpress = (int) (1 + stateTime * 4);
    }
}
