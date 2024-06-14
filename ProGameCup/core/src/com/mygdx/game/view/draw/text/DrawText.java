package com.mygdx.game.view.draw.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.screens.endgame.MainEndStory;

import java.util.ArrayList;

public class DrawText {
    BitmapFont charFont;
    MainEndStory mainEndStory;
    Texture note;

    float noteX;
    float noteY;

    Vector2 sizeItem;
    public DrawText(String path, Color color, MainEndStory mainEndStory){
        sizeItem = new Vector2();
        setCharFont(path, color);
        note = new Texture("alert/note.png");
        this.mainEndStory = mainEndStory;
    }

    public DrawText(String path, Color color){
        sizeItem = new Vector2();
        setCharFont(path, color);
        note = new Texture("alert/note.png");
    }

    public void setCharFont(String path, Color color){
        charFont = new BitmapFont(Gdx.files.internal(path));
        charFont.setColor(color);
    }

    public void drawStaticText(SpriteBatch batch, String text, float x, float y, float size){
        charFont.getData().setScale(size);
        charFont.draw(batch, text, x, y);
    }

    public void drawNoteName(Item item, SpriteBatch batch, DrawText drawText){
        MakeSize makeSize = new MakeSize();
        makeSize.getSize(note, 550, sizeItem);
        noteX = (GameConstant.WINDOW_WIDTH- ItemConstant.NOTE_WIDTH)/2 + 40;
        noteY = MapConstant.POS_MAP_Y + MapConstant.MAP_HEIGHT + ItemConstant.NOTE_HEIGHT + 5;
        batch.draw(note, (GameConstant.WINDOW_WIDTH-note.getWidth())/2 + 30
                , MapConstant.POS_MAP_Y + MapConstant.MAP_HEIGHT + 10, sizeItem.x, sizeItem.y);
        String noteText;
        if (item instanceof StaticItem){
            noteText = "It's the " + item.getName() + ".";
        }
        else{
            noteText = "It's a " + item.getName() + ".";
        }
        System.out.println(noteY);
        drawText.drawStaticText(batch, noteText, noteX, noteY,0.5f);
    }

    public void drawClock(ArrayList<DynamicItem> dynamicItems, SpaceGame game, SpriteBatch batch, float stateTime, int initTime, float x, float y, float size, MainEndStory mainEndStory){
        charFont.getData().setScale(size/2);
        float timeLeft = initTime  - stateTime;
        if(timeLeft <= 0){
            game.setScreen(mainEndStory);
        }
        int remainMinutes = (int) (timeLeft / 60);
        int remainSeconds = (int) (timeLeft % 60);
        charFont.draw(batch, String.format("%02d", remainMinutes) + ":" + String.format("%02d", remainSeconds),x, y );

    }
}
