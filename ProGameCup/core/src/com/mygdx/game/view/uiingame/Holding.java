package com.mygdx.game.view.uiingame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;

public class Holding {
    public void drawHold(SpriteBatch batch, Player player){
        Texture holdingImage = new Texture("items/static-items/items/holding.png");
        batch.draw(holdingImage, 10, 720, 120, 120);
        if (player.getItemHolding()!=null){
            batch.draw(player.getItemHolding().getImage(), 60, 750, player.getItemHolding().getWidth()*1.5f
                    , player.getItemHolding().getHeight()*1.5f);
        }
    }
}
