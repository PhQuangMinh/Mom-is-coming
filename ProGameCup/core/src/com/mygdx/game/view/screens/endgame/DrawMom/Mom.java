package com.mygdx.game.view.screens.endgame.DrawMom;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.map.DrawMap;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.effect.MakeSound;
import com.mygdx.game.view.screens.endgame.ResultScreen;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class Mom extends Sprite {
    ArrayList<DynamicItem> dynamicItems;
    private Animation<TextureRegion>[] mom_walking;
    private final Texture verticalColumn;
    private int MOM_WIDTH = 13;
    private int MOM_HEIGHT = 26;
    private float stateTime;
    private float currentX;
    private static final float stepSize = 30f;
    private int stepCount = 0;
    private float noteX, noteY;
    private int noteState = 0;
    private float noteTime = 0;
    private float columnStartX = 110;
    private float columnWidth;
    DrawText drawText;
    DrawMap drawMap;
    MakeSize makeSize;
    MainStory mainStory;
     public Mom(Texture texture, ArrayList<DynamicItem> dynamicItems, MainStory mainStory){
         this.dynamicItems = dynamicItems;
         this.mainStory = mainStory;
         drawText = new DrawText("fonts/char.fnt", Color.BLACK);
         verticalColumn = new Texture("maps/verticalColumn.png");
         setAnimation(texture);
        stateTime = 0f;
        currentX = 130;
        drawMap = new DrawMap();
        makeSize = new MakeSize();

         Vector2 sizeItem = new Vector2();
         makeSize.getSize(verticalColumn, 263, sizeItem);
         columnWidth = sizeItem.x;
     }
    public void setAnimation(Texture texture){
        mom_walking = new Animation[10];
        TextureRegion[][] region = TextureRegion.split(texture, MOM_WIDTH, MOM_HEIGHT);
        for(int i = 0; i < 1; i++){
           mom_walking[i] = new Animation(0.2f, region[i]);
        }
    }
    public boolean isBehindVerticalColumn() {
        if(currentX >= columnStartX && currentX <= columnStartX + columnWidth) return true;
        return false;
    }

    public void draw(Texture chat, SpaceGame game, SpriteBatch batch, float delta){
        stateTime += delta;
        for (int i = 0 ; i < 1; i++) {
            currentX += stepSize * delta;
            TextureRegion currentFrame = mom_walking[i].getKeyFrame(stateTime, true);
            if (currentX > MapConstant.POS_MAP_Y + 30 + stepSize * 2) {
                currentX = MapConstant.POS_MAP_Y + 30
                        + stepSize * 2;
                currentFrame = mom_walking[0].getKeyFrame(stateTime);
                stepCount = 2;
            }
            if (!isBehindVerticalColumn()) {
                batch.draw(currentFrame, currentX, MapConstant.POS_MAP_Y + 230, 32, 58);
            }
            drawMap.drawOverlap(batch, verticalColumn, 207, 209, 263);
            if (isBehindVerticalColumn()) {
                batch.draw(currentFrame, currentX, MapConstant.POS_MAP_Y + 230, 32, 58);
            }
        }

        if(stepCount == 2) drawChat(chat, game, batch, delta);
    }

    public void drawChat(Texture chat,SpaceGame game, SpriteBatch batch, float delta){
        batch.draw(chat, (GameConstant.WINDOW_WIDTH-chat.getWidth())/2
                , MapConstant.POS_MAP_Y + MapConstant.MAP_HEIGHT + 10);
        noteX = (GameConstant.WINDOW_WIDTH-chat.getWidth())/2 + 10;
        noteY = MapConstant.POS_MAP_Y + MapConstant.MAP_HEIGHT + chat.getHeight() - 5;
        switch (noteState) {
            case 0:
                drawText.drawStaticText(batch, "Hello darling! How's your new place ?", noteX, noteY, 0.4f);
                break;
            case 1:
                drawText.drawStaticText(batch, "Can I have a look around ?", noteX, noteY, 0.4f);
                break;
            case 2:
                drawText.drawStaticText(batch, "O-Of course, mom", noteX, noteY, 0.4f);
                break;
        }
        noteTime += delta;
        if (noteTime >= 1) {
            noteState++;
            noteTime = 0;
        }
        if(noteState >= 3) game.setScreen(new ResultScreen(game, dynamicItems, mainStory));
    }
}
