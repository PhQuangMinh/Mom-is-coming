package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.Player;

public class MakeAlert {
    public float firstValue = -1;

    public void update(SpriteBatch batch, float stateTime, Player player){
        if (!player.isValidThrow()){
            if (firstValue == -1) firstValue = stateTime;
            drawAlert(batch, stateTime, player);
            if (stateTime - firstValue > 2){
                player.setValidThrow(true);
                firstValue = -1;
            }
        }
    }

    private void drawBlock(String path, float alpha, SpriteBatch batch, Player player){
        batch.setColor(1, 1, 1, alpha*1.5f);
        Texture block = new Texture(path);
        MakeSize makeSize = new MakeSize();
        Vector2 size = makeSize.getSize(block, 30);
        if (player.getPositionThrew()!=null)
            batch.draw(block, player.getPositionThrew().x, player.getPositionThrew().y
                , size.x, size.y);
    }
    public void drawAlert(SpriteBatch batch, float stateTime, Player player){
        float alpha = Math.min(2f, stateTime - firstValue);
        if (alpha<=1){
            drawBlock("alert/black-block.png", alpha, batch, player);
        }
        else
            if (alpha<=2){
                drawBlock("alert/red-block.png", alpha, batch, player);
            }
        batch.setColor(1, 1, 1, 1);
    }
}
