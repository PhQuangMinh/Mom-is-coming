package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.NewButton;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class ButtonGame {
    NewButton newButton;
    Texture resume, pause, home, homePress, replay, replayPress, musicOn, musicOff
            , buttonSpace, menuBar, menuBarPress;

    ButtonGame(SpaceGame game){
        newButton = new NewButton(game);
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

    public void draw(SpaceGame game, SpriteBatch batch, float stateTime, DrawText drawText
            , ArrayList<DynamicItem> dynamicItems, MainMenuScreen mainMenuScreen, MainStory mainStory){
        newButton.drawButton(menuBar, menuBarPress, (int) GameConstant.WINDOW_WIDTH - 70, 900
                , ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 3, mainMenuScreen, mainStory);
        drawMenuBar(mainMenuScreen, mainStory);
        drawText.drawClock(dynamicItems, game, batch, stateTime, 10, 0, 460, 930, 1.2f);
    }

    public void drawMenuBar(MainMenuScreen mainMenuScreen, MainStory mainStory){
        if(NewButton.isMenuBarOpen) {
            newButton.drawMusicButton(musicOn, musicOff, (int) GameConstant.WINDOW_WIDTH - 70, 840, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            newButton.drawButton(home, homePress, (int) GameConstant.WINDOW_WIDTH - 70, 780, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 5, mainMenuScreen, mainStory);
            newButton.drawButton(replay, replayPress, (int) GameConstant.WINDOW_WIDTH - 70, 720, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT, 1, mainMenuScreen, mainStory);
            newButton.drawPauseButton(resume, pause, (int) GameConstant.WINDOW_WIDTH - 70, 660, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
        }
    }
}
