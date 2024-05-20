package com.mygdx.game.view.draw.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.FrameStatus;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.Player;


public class DrawMap {
    private final Texture map;
    private final Texture verticalColumn;
    private final Texture rightHorizontal;
    private final Texture leftHorizontal;
    private final Texture overlapsLeft;
    public DrawMap() {
        map = new Texture("maps/map.png");
        verticalColumn = new Texture("maps/verticalColumn.png");
        rightHorizontal = new Texture("maps/rightHorizontal.png");
        leftHorizontal = new Texture("maps/leftHorizontal.png");
        overlapsLeft = new Texture("maps/overlapsLeft.png");
    }
    public void drawMap(SpriteBatch batch){
        MakeSize makeSize = new MakeSize();
        Vector2 sizeMap = makeSize.getSize(map, (int) MapConstant.MAP_SIZE);
        MapConstant.MAP_WIDTH = sizeMap.x;
        MapConstant.MAP_HEIGHT = sizeMap.y;
        batch.draw(map, MapConstant.POS_MAP_X, MapConstant.POS_MAP_Y,
                sizeMap.x, sizeMap.y);
    }

    private void drawOverlapsMap(SpriteBatch batch, Texture texture, int posX, int posY, int sizeMap){
        MakeSize makeSize = new MakeSize();
        Vector2 size = makeSize.getSize(texture, sizeMap);
        batch.draw(texture, posX, posY, size.x, size.y);
    }

    public void drawBars(SpriteBatch batch, Player player){
        drawOverlapsMap(batch, verticalColumn, 207, 209, 263);
        drawOverlapsMap(batch, leftHorizontal, 29, 185, 179);
        drawOverlapsMap(batch, rightHorizontal, 267, 179, 524);
        if (player.getFrameStatus() == FrameStatus.RIGHT_FRAME){
            drawOverlapsMap(batch, overlapsLeft, 10, 190, 370);
        }
    }
}
