package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.MainMenuScreen;
import com.mygdx.game.view.screens.MainStory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewButton {
    SpriteBatch batch;
    DrawText drawText;
    SpaceGame game;
    public boolean isPause = false;
    public boolean isStopMusic = false;
    public NewButton(SpaceGame game) {
        this.game = game;
        this.batch = game.getBatch();
        drawText = new DrawText();
    }
    public void drawButton( Texture button, Texture buttonPress, int x, int y, int buttonWidth, int buttonHeight, int choice){
        if (Gdx.input.getX()>=x && Gdx.input.getX()<=x+ buttonWidth && GameConstant.windowHeight-Gdx.input.getY()>=y && GameConstant.windowHeight-Gdx.input.getY()<=y+ buttonHeight){
            batch.draw(buttonPress, x, y, buttonWidth, buttonHeight);
            if(choice == 6) drawText.drawStaticText(batch, "Link Github", 80, 40, 0.6f);
            if (Gdx.input.isTouched()){
                if (choice==1){
                    game.setScreen(new MainStory(game));
                }
//                else if(choice == 2){
//                   playSound.checkMusic(musicOn, musicOff, x, y, buttonWidth, buttonHeight);
//                }
                else if(choice == 3){

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
}
