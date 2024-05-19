package com.mygdx.game.view.uiingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewButton {
    Texture resume, pause, home, homePress, replay, replayPress, musicOn, musicOff, buttonSpace;
    SpriteBatch batch;
    DrawText drawText;
    SpaceGame game;
    public boolean isPause = false;
    public boolean isStopMusic = false;
    public static boolean isHowToPlayOpen = false;
    public static boolean isMenuBarOpen = false;
    public NewButton(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        createTexture();
        drawText = new DrawText();

    }

    public void createTexture(){
        resume = new Texture("button/resume.png");
        pause = new Texture("button/pause.png");
        home = new Texture("button/home.png");
        homePress = new Texture("button/homePress.png");
        replay = new Texture("button/replay.png");
        replayPress = new Texture("button/replayPress.png");
        musicOn = new Texture("button/musicOn.png");
        musicOff = new Texture("button/musicOff.png");
        buttonSpace = new Texture("otherImage/ButtonSpace.png");
    }
    public void drawButton( Texture button, Texture buttonPress, int x, int y, int buttonWidth, int buttonHeight, int choice){
        if (Gdx.input.getX()>=x && Gdx.input.getX()<=x+ buttonWidth && GameConstant.windowHeight-Gdx.input.getY()>=y && GameConstant.windowHeight-Gdx.input.getY()<=y+ buttonHeight){
            batch.draw(buttonPress, x, y, buttonWidth, buttonHeight);
            if(choice == 6) drawText.drawStaticText(batch, "Link Github", 80, 40, 0.6f);
            if (Gdx.input.isTouched()){
                if (choice==1){
                    game.setScreen(new MainGameScreen(game));
                }
                else if(choice == 2){
                     isHowToPlayOpen = true;
                }
                else if(choice == 3){
                    isMenuBarOpen = true;
                }
                else if(choice == 4){
                    Gdx.app.exit();
                }
                else if(choice == 5){
                    game.setScreen(new MainMenuScreen(game));
                }
                else{
                    openLink("https://github.com/Hecker-Chuoi/BTCK2");
                }
            }
        }
        else{
            batch.draw(button, x, y, buttonWidth, buttonHeight);
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

    public void drawPauseButton(Texture resumeButton, Texture pauseButton, int x, int y, int buttonWidth, int buttonHeight) {
        boolean isTouched = Gdx.input.justTouched();
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + buttonWidth && GameConstant.windowHeight - Gdx.input.getY() >= y && GameConstant.windowHeight - Gdx.input.getY() <= y + buttonHeight) {
            if (isTouched) {
                if(isPause) isPause = false;
                else isPause = true;
            }
        }
        if(isPause){
            batch.draw(pauseButton, x, y, buttonWidth, buttonHeight);
        }
        else batch.draw(resumeButton, x, y, buttonWidth, buttonHeight);
    }
    public void drawMusicButton(Texture musicOnButton, Texture musicOffButton, int x, int y, int buttonWidth, int buttonHeight) {
        boolean isTouched = Gdx.input.justTouched();
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + buttonWidth && GameConstant.windowHeight - Gdx.input.getY() >= y && GameConstant.windowHeight - Gdx.input.getY() <= y + buttonHeight) {
            if (isTouched) {
                if(isStopMusic) isStopMusic= false;
                else isStopMusic= true;
            }
        }
        if(isStopMusic){
            batch.draw(musicOffButton, x, y, buttonWidth, buttonHeight);
            MakeMusic.stopMusic();
        }
        else{
            batch.draw(musicOnButton, x, y, buttonWidth, buttonHeight);
            MakeMusic.resumeMusic();
        }
    }

    public void drawHowToPlayButton(int x, int y, int width, int height){
        if(isHowToPlayOpen){
            Texture close = new Texture("button/close.png");
            Texture closePress = new Texture("button/closePress.png");
            Texture howToPlay = new Texture("otherImage/HowtoPlay.png");

            batch.draw(howToPlay, x, y, width, height);
            int closeButtonX = x + width - 50;
            int closeButtonY = y + height - 50;

            if (Gdx.input.getX() >= closeButtonX && Gdx.input.getX() <= closeButtonX + GameConstant.iconWidth
                    && GameConstant.windowHeight - Gdx.input.getY() >= closeButtonY
                    && GameConstant.windowHeight - Gdx.input.getY() <= closeButtonY + GameConstant.iconHeight) {
                batch.draw(closePress, closeButtonX, closeButtonY, GameConstant.iconWidth, GameConstant.iconHeight);
                if (Gdx.input.justTouched()) {
                    isHowToPlayOpen = false;
                }
            }
            else batch.draw(close, closeButtonX, closeButtonY, GameConstant.iconWidth, GameConstant.iconHeight);
        }
    }
    public void drawMenuBar(){
        if(isMenuBarOpen) {
            drawMusicButton(musicOn, musicOff, (int) GameConstant.windowWidth - 70, 740, GameConstant.iconWidth, GameConstant.iconHeight);
            drawButton(home, homePress, (int) GameConstant.windowWidth - 70, 680, GameConstant.iconWidth, GameConstant.iconHeight, 5);
            drawButton(replay, replayPress, (int) GameConstant.windowWidth - 70, 620, GameConstant.iconWidth, GameConstant.iconHeight, 1);
            drawPauseButton(resume, pause, (int) GameConstant.windowWidth - 70, 560, GameConstant.iconWidth, GameConstant.iconHeight);
        }
    }
}
