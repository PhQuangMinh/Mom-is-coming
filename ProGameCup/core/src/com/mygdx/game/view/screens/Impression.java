package com.mygdx.game.view.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;

public class Impression {
    Texture impression;
    private int countImpress = 0;

    private Texture[] impressions;
    public Impression(String path){
        impression = new Texture(path);
    }

    public Impression(){
        impressions = new Texture[6];
        for (int i = 0; i < 5; i++){
            impressions[i] = new Texture("story/impression" + (i + 1) + ".png");
        }
    }
    private Vector2 getSize(Texture impression){
        float ratio = (float)impression.getWidth() / impression.getHeight();
        float width, height;
        if (ratio>1) {
            width = GameConstant.impressionSize;
            height = GameConstant.impressionSize / ratio;
        }
        else{
            height = GameConstant.impressionSize;
            width = GameConstant.impressionSize * ratio;
        }
        return new Vector2(width, height);
    }

    public void drawStory (SpriteBatch batch, float stateTime){
        float alpha = Math.min(1f, stateTime / 2f);
        batch.setColor(1, 1, 1, alpha);
        Vector2 size = getSize(impression);
        batch.begin();
        batch.draw(impression, 80,110, size.x, size.y);
        batch.end();
    }

    public void drawGame (SpriteBatch batch, float stateTime){
        if (countImpress>=5) return;
        batch.setColor(1, 1, 1, 1);
        Vector2 size = getSize(impressions[countImpress]);
        int[] a = {110, 110, 110, 500, 680};
        batch.draw(impressions[countImpress], 80, a[countImpress], size.x, size.y);
        countImpress = (int) (1 + stateTime * 4);
    }
}
