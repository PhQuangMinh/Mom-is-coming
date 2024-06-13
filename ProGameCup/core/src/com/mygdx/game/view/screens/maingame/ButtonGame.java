package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.DrawButton;
import com.mygdx.game.view.screens.maingame.multiplayer.MultiPlayer;
import com.mygdx.game.view.screens.maingame.singleplayer.SinglePlayer;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class ButtonGame {
    DrawButton drawButton;
    Texture resume, pause, home, homePress, replay, replayPress, musicOn, musicOff
            , buttonSpace, menuBar, menuBarPress;

    public ButtonGame(SpaceGame game){
        drawButton = new DrawButton(game);
        init();
    }
    public void init(){
        resume = new Texture("button/game/resume.png");
        pause = new Texture("button/game/pause.png");
        home = new Texture("button/game/home.png");
        homePress = new Texture("button/game/homePress.png");
        replay = new Texture("button/game/replay.png");
        replayPress = new Texture("button/game/replayPress.png");
        musicOn = new Texture("button/menu/musicOn.png");
        musicOff = new Texture("button/menu/musicOff.png");
        buttonSpace = new Texture("otherImage/ButtonSpace.png");
        menuBar = new Texture("button/game/menuBar.png");
        menuBarPress = new Texture("button/game/menuBarPress.png");
    }

    public void drawSingle(SpaceGame game, SpriteBatch batch, float stateTime, DrawText drawText
            , ArrayList<DynamicItem> dynamicItems, MainMenuScreen mainMenuScreen, SinglePlayer singlePlayer){
        drawButton.drawButtonGameSingle(menuBar, menuBarPress, (int) GameConstant.WINDOW_WIDTH - 70,
                900, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 3,
                mainMenuScreen, singlePlayer);
        drawMenuBarSingle(mainMenuScreen, singlePlayer);
        drawText.drawClock(dynamicItems, game, batch, stateTime, 10, 0, 460, 930, 2f);
        drawText.drawStaticText(batch, "X - Interact Items", 10, 30, 0.5f);

    }

    public void drawMulti(SpaceGame game, SpriteBatch batch, float stateTime, DrawText drawText
            , ArrayList<DynamicItem> dynamicItems, MainMenuScreen mainMenuScreen, MultiPlayer multiPlayer){
        drawButton.drawButtonGameMulti(menuBar, menuBarPress, (int) GameConstant.WINDOW_WIDTH - 70, 900
                , ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 1, mainMenuScreen, multiPlayer);
        drawMenuBarMulti(mainMenuScreen, multiPlayer);
        drawText.drawClock(dynamicItems, game, batch, stateTime, 10, 0, 460, 930, 2f);
        drawText.drawStaticText(batch, "X - Interact Items", 10, 30, 0.5f);

    }

    public void drawMenuBarSingle(MainMenuScreen mainMenuScreen, SinglePlayer singlePlayer){
        if (!DrawButton.isMenuBarOpen) return;
        drawButton.drawMusicButton(musicOn, musicOff, (int)GameConstant.WINDOW_WIDTH - 70, 840);
        drawButton.drawButtonGameSingle(home, homePress, (int)GameConstant.WINDOW_WIDTH - 70,
                780, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 2, mainMenuScreen,
                singlePlayer);
        drawButton.drawButtonGameSingle(replay, replayPress, (int)GameConstant.WINDOW_WIDTH - 70, 720,
                ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 3, mainMenuScreen, singlePlayer);
        drawButton.drawPauseButton(resume, pause, (int) GameConstant.WINDOW_WIDTH - 70, 660);
    }

    public void drawMenuBarMulti(MainMenuScreen mainMenuScreen, MultiPlayer multiPlayer){
        if(!DrawButton.isMenuBarOpen) return;
        drawButton.drawMusicButton(musicOn, musicOff, (int) GameConstant.WINDOW_WIDTH - 70, 840);

        drawButton.drawButtonGameMulti(home, homePress, (int) GameConstant.WINDOW_WIDTH - 70, 780,
                ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 2, mainMenuScreen, multiPlayer);

        drawButton.drawButtonGameMulti(replay, replayPress, (int) GameConstant.WINDOW_WIDTH - 70, 720,
                ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 3, mainMenuScreen, multiPlayer);

        drawButton.drawPauseButton(resume, pause, (int) GameConstant.WINDOW_WIDTH - 70, 660);
    }
}
