package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainmenu.ButtonMenu;
import com.mygdx.game.view.screens.mainmenu.Leaderboard;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewButton {
    SpriteBatch batch;
    DrawText drawText;
    Leaderboard leaderboard;
    SpaceGame game;
    public static boolean isPause = false;
    public static boolean isStopMusic = false;
    public static boolean isMenuBarOpen = false;
    Texture close, closePress, howToPlay;

    public NewButton(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        leaderboard = new Leaderboard();
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        close = new Texture("button/game/close.png");
        closePress = new Texture("button/game/closePress.png");
        howToPlay = new Texture("otherImage/HowtoPlay.png");
    }
    public void drawButton( Texture button, Texture buttonPress, int x, int y, int BUTTON_WIDTH
            , int BUTTON_HEIGHT, int choice, MainMenuScreen mainMenuScreen, MainStory mainStory){
        if(!ButtonMenu.isLeaderboardOpen && !ButtonMenu.isHowToPlayOpen) {
            if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + BUTTON_WIDTH && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() >= y && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() <= y + BUTTON_HEIGHT) {
                batch.draw(buttonPress, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if (choice == 5) drawText.drawStaticText(batch, "Link Github", 80, 40, 0.6f);
                if (Gdx.input.isTouched()) {
                    if (choice == 1) {
                        MainGameScreen.stateTime = 0f;
                        game.setScreen(new MainStory(game, mainMenuScreen));
                    } else if (choice == 2) {
                        ButtonMenu.isLeaderboardOpen = true;
                    } else if (choice == 3) {
                        Gdx.app.exit();
                    } else if (choice == 4) {
                        ButtonMenu.isHowToPlayOpen = true;
                    } else if (choice == 5) {
                        openLink("https://github.com/Hecker-Chuoi/BTCK2");
                    }
                }
            }
            else{
                batch.draw(button, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
            }
        }
        else{
            batch.draw(button, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    private void openLink(String link) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(link));
            }
        } catch (IOException | URISyntaxException ignored) {
        }
    }

    public void drawPauseButton(Texture resumeButton, Texture pauseButton, int x, int y, int BUTTON_WIDTH, int BUTTON_HEIGHT) {
        boolean isTouched = Gdx.input.justTouched();
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + BUTTON_WIDTH && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() >= y && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() <= y + BUTTON_HEIGHT) {
            if (isTouched) {
                isPause = !isPause;
            }
        }
        if(isPause){
            batch.draw(pauseButton, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        else batch.draw(resumeButton, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public void drawMusicButton(Texture musicOnButton, Texture musicOffButton, int x, int y, int BUTTON_WIDTH, int BUTTON_HEIGHT) {
        boolean isTouched = Gdx.input.justTouched();
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + BUTTON_WIDTH && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() >= y && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() <= y + BUTTON_HEIGHT) {
            if (isTouched) {
                isStopMusic= !isStopMusic;
            }
        }
        if(isStopMusic){
            batch.draw(musicOffButton, x, y, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            MakeMusic.pauseMusic();
        }
        else{
            batch.draw(musicOnButton, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
            MakeMusic.resumeMusic();
        }
    }

    public void drawHowToPlay(){
        if(ButtonMenu.isHowToPlayOpen){
            batch.draw(howToPlay, 0, 0, 960, 960);
            float x = Gdx.input.getX();
            float y = 960 - Gdx.input.getY();

            if((x >= 784 && x <= 784 + ItemConstant.ICON_WIDTH) && (y >= 816 && y <= 816 + ItemConstant.ICON_HEIGHT)){
                batch.draw(closePress, 784, 816, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
                if(Gdx.input.isTouched()){
                    ButtonMenu.isHowToPlayOpen = false;
                }
            }
            else {
                batch.draw(close, 784, 816, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            }
        }
    }
}
