package com.mygdx.game.view.draw.screengame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.item.DrawDynamic;
import com.mygdx.game.view.draw.item.DrawStatic;
import com.mygdx.game.view.draw.map.DrawMap;
import com.mygdx.game.view.draw.player.DrawPlayer;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.Holding;
import com.mygdx.game.view.screens.Impression;
import com.mygdx.game.view.screens.maingame.ButtonGame;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class InitDraw {
    public ArrayList<DynamicItem> dynamicFloor;
    public ArrayList<DynamicItem> dynamicTable;
    public ArrayList<DynamicItem> dynamicTop;
    public ArrayList<StaticItem> staticBottom;

    public ArrayList<StaticItem> staticMiddle;
    public ArrayList<StaticItem> staticTop;
    public DrawStatic drawStatic;
    public DrawDynamic drawDynamic;
    DrawPlayer drawPlayer;

    Impression impression;
    ButtonGame buttonGame;
    SpaceGame game;
    DrawText drawText;
    Holding holding;
    DrawMap drawMap;

    public InitDraw(SpaceGame spaceGame){
        impression = new Impression();
        buttonGame = new ButtonGame(spaceGame);
        this.game = spaceGame;
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        holding = new Holding();
        drawMap = new DrawMap();
        dynamicFloor = new ArrayList<>();
        dynamicTable = new ArrayList<>();
        dynamicTop = new ArrayList<>();
        staticBottom = new ArrayList<>();
        staticTop = new ArrayList<>();
        drawDynamic = new DrawDynamic();
        drawStatic = new DrawStatic();
        drawPlayer = new DrawPlayer();
        staticMiddle = new ArrayList<>();
    }

    public boolean checkObscure(StaticItem item, Player player){
        return player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() >= item.getY() + item.getHeight() - item.getOverlap()
                && player.getY() <= item.getY() + item.getHeight();
    }

}
