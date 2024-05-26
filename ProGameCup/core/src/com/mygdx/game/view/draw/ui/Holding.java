package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.Player;

public class Holding {
    Texture holdingImage;

    public Holding(){
        holdingImage = new Texture("items/static-items/items/holding.png");
    }

    public void drawHold(SpriteBatch batch, Player player){
        MakeSize makeSize = new MakeSize();
        Vector2 sizeHold = makeSize.getSize(holdingImage, 170);
        batch.draw(holdingImage, 10, GameConstant.WINDOW_HEIGHT - sizeHold.y * 1.1f
                , sizeHold.x, sizeHold.y);
        if (player.getItemHolding()!=null){
            batch.draw(player.getItemHolding().getImage(), 65
                    , GameConstant.WINDOW_HEIGHT - 110
                    , player.getItemHolding().getWidth()*1.5f
                    , player.getItemHolding().getHeight()*1.5f);
        }
    }
}
