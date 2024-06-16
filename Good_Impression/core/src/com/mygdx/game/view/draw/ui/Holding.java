package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.Player;

public class Holding {
    Texture holdingImage;

    Vector2 sizeItem;

    MakeSize makeSize;

    public Holding(){
        holdingImage = new Texture("items/static-items/items/holding.png");
        sizeItem = new Vector2();
        makeSize = new MakeSize();
        makeSize.getSize(holdingImage, 170, sizeItem);
    }

    public void initHold(SpriteBatch batch, float posX){
        batch.draw(holdingImage, posX, GameConstant.WINDOW_HEIGHT - sizeItem.y * 1.1f
                , sizeItem.x, sizeItem.y);
    }


    public void drawHoldSingle(SpriteBatch batch, Player player, float posXHold, float posXItem){
        initHold(batch, posXHold);
        if (player.getItemHolding()!=null){
            batch.draw(player.getItemHolding().getImage(), posXItem
                    , GameConstant.WINDOW_HEIGHT - 110
                    , player.getItemHolding().getWidth()*1.5f
                    , player.getItemHolding().getHeight()*1.5f);
        }
    }

    public void drawHoldMulti(Player firstPlayer, Player secondPlayer, SpriteBatch batch){
        drawHoldSingle(batch, firstPlayer, 10, 65);
        drawHoldSingle(batch, secondPlayer, 20 + sizeItem.x, sizeItem.x*1.5f);
    }
}
