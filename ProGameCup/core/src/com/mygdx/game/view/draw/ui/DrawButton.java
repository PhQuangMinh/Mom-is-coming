package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.maingame.multiplayer.MultiPlayer;
import com.mygdx.game.view.screens.maingame.singleplayer.SinglePlayer;
import com.mygdx.game.view.screens.mainmenu.ButtonMenu;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;
import com.mygdx.game.view.screens.optionplayer.OptionPlayer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DrawButton {
    SpriteBatch batch;
    DrawText drawText;
    SpaceGame game;
    public static boolean isPause = false;
    public static boolean isStopMusic = false;
    public static boolean isMenuBarOpen = false;
    Texture close, closePress, howToPlay;

    MakeSize makeSize;

    Vector2 size;

    public DrawButton(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        close = new Texture("button/game/close.png");
        closePress = new Texture("button/game/closePress.png");
        howToPlay = new Texture("otherImage/HowtoPlay.png");
        makeSize = new MakeSize();
        size = new Vector2();
    }

    private boolean checkPress(int x, int y, int width, int height) {
        return Gdx.input.getX() >= x && Gdx.input.getX() <= x + width &&
                GameConstant.WINDOW_HEIGHT - Gdx.input.getY() >= y &&
                GameConstant.WINDOW_HEIGHT - Gdx.input.getY() <= y + height;
    }
    private void openLink() {
        try {
            if (Desktop.isDesktopSupported() &&
                    Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://github.com/Hecker-Chuoi/BTCK2"));
            }
        } catch (IOException | URISyntaxException ignored) {
        }
    }

    public void drawPauseButton(Texture resumeButton, Texture pauseButton, int x, int y) {
        boolean isTouched = Gdx.input.justTouched();
        if (checkPress(x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT)) {
            if (isTouched) {
                isPause = !isPause;
            }
        }
        if(isPause){
            batch.draw(pauseButton, x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
        }
        else batch.draw(resumeButton, x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
    }
    public void drawMusicButton(Texture musicOnButton, Texture musicOffButton, int x, int y) {
        boolean isTouched = Gdx.input.justTouched();
        if (checkPress(x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT)) {
            if (isTouched) {
                isStopMusic= !isStopMusic;
            }
        }
        if(isStopMusic){
            batch.draw(musicOffButton, x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            MakeMusic.pauseMusic();
        }
        else{
            batch.draw(musicOnButton, x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            MakeMusic.resumeMusic();
        }
    }

    public void drawHowToPlayButton(){
        if(ButtonMenu.isHowToPlayOpen){
            makeSize.getSize(howToPlay, 850, size);
            float posX = (GameConstant.WINDOW_WIDTH - size.x)/2;
            float posY = (GameConstant.WINDOW_HEIGHT - size.y)/2;
            batch.draw(howToPlay, posX, posY, size.x, size.y);
            float closeButtonX = posX + size.x - 50;
            float closeButtonY = posY + size.y - 50;

            if (checkPress((int)closeButtonX, (int)closeButtonY, ItemConstant.ICON_WIDTH,
                    ItemConstant.ICON_HEIGHT)) {
                batch.draw(closePress, closeButtonX, closeButtonY, ItemConstant.ICON_WIDTH,
                        ItemConstant.ICON_HEIGHT);
                if (Gdx.input.justTouched()) {
                    ButtonMenu.isHowToPlayOpen = false;
                }
            }
            else batch.draw(close, closeButtonX, closeButtonY, ItemConstant.ICON_WIDTH,
                    ItemConstant.ICON_HEIGHT);
        }
    }

    public void drawButtonGameSingle(Texture button, Texture buttonPress, int x, int y, int width, int height,
                                     int choice, MainMenuScreen mainMenuScreen, SinglePlayer singlePlayer){
        if (checkPress(x, y, width, height)){
            batch.draw(buttonPress, x, y, width, height);
            if (Gdx.input.isTouched()){
                switch (choice){
                    case 1:
                        isMenuBarOpen = true;
                        break;
                    case 2:
                        game.setScreen(mainMenuScreen);
                        break;
                    case 3:
                        game.setScreen(singlePlayer);
                        break;
                    default:
                        openLink();
                        break;
                }
            }
        }
        else{
            batch.draw(button, x, y, width, height);
        }
    }

    public void drawButtonGameMulti(Texture button, Texture buttonPress, int x, int y, int width, int height,
                                     int choice, MainMenuScreen mainMenuScreen, MultiPlayer multiPlayer){
        if (checkPress(x, y, width, height)){
            batch.draw(buttonPress, x, y, width, height);
            if (Gdx.input.isTouched()){
                switch (choice){
                    case 1:
                        isMenuBarOpen = true;
                        break;
                    case 2:
                        game.setScreen(mainMenuScreen);
                        break;
                    case 3:
                        game.setScreen(multiPlayer);
                        break;
                    default:
                        openLink();
                        break;
                }
            }
        }
        else{
            batch.draw(button, x, y, width, height);
        }
    }

    public void drawButtonMenu(Texture button, Texture buttonPress, int x, int y, int width, int height
            , int choice, OptionPlayer optionPlayer){
        if (ButtonMenu.isHowToPlayOpen || ButtonMenu.isLeaderboardOpen ||
                !checkPress(x, y, width, height)){
            batch.draw(button, x, y, width, height);
            return;
        }
        batch.draw(buttonPress, x, y, width, height);
        if (choice == 6) drawText.drawStaticText(batch, "Link Github", 80, 40, 0.6f);
        if (Gdx.input.isTouched()) {
            switch (choice) {
                case 1:
                    MainGameScreen.stateTime = 0f;
                    game.setScreen(optionPlayer);
                    break;
                case 2:
                    ButtonMenu.isHowToPlayOpen = true;
                    break;
                case 3:
                    ButtonMenu.isLeaderboardOpen = true;
                    break;
                case 4:
                    Gdx.app.exit();
                    break;
                default:
                    openLink();
                    break;
            }
        }
    }

    public void drawButtonOption(Texture button, Texture pressedButton, int x, int y, int choice,
                                 MainStory mainStory){
        if (checkPress(x, y, ItemConstant.BUTTON_WIDTH, ItemConstant.BUTTON_HEIGHT)){
            batch.draw(pressedButton, x, y, ItemConstant.BUTTON_WIDTH, ItemConstant.BUTTON_HEIGHT);
            if (Gdx.input.justTouched()){
                switch (choice){
                    case 1:
                        GameConstant.FORMAT_PLAYER = 1;
                        break;
                    case 2:
                        GameConstant.FORMAT_PLAYER = 2;
                        break;
                }
                game.setScreen(mainStory);
            }
        }
        else{
            batch.draw(button, x, y, ItemConstant.BUTTON_WIDTH, ItemConstant.BUTTON_HEIGHT);
        }
    }
}
