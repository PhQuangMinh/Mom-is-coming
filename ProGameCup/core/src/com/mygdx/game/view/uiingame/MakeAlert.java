package com.mygdx.game.view.uiingame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;

public class MakeAlert {
    public void drawAlert(SpriteBatch batch, float x, float stateTime, Player player){
        Texture redBlock = new Texture("alert/red-block.png");
        Texture blackBlock = new Texture("alert/black-block.png");
        float alpha = Math.min(2f, stateTime-x);
        if (alpha<=1){
            batch.setColor(1, 1, 1, alpha*1.5f);
            batch.draw(blackBlock, player.getPositionThrew().x, player.getPositionThrew().y
                    , 25, 25);
        }
        else
        if (alpha<=2){
            batch.setColor(1, 1, 1, (alpha-1)*1.5f);
            batch.draw(redBlock, player.getPositionThrew().x, player.getPositionThrew().y
                    ,25, 25);
        }
        batch.setColor(1, 1, 1, 1);
    }
}
