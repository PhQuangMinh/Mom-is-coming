package com.mygdx.game.view.ui.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.view.effect.MakeMusic;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ManagerButton {
    public void drawMenu(ArrayList<Button> listButton, SpaceGame game, SpriteBatch batch){
        for (Button button : listButton) {
            button.update();
            button.draw(batch);
            if (button.isPressed()) {
                onClick(button, game);
            }
        }
    }
    public void onClick(Button button, SpaceGame game) {
        if (button.getFunction() == 1){
            game.setScreen(new MainStory(game));
        }
        else if (button.getFunction() == 2){

        }
        else if (button.getFunction() == 3) {
            Gdx.app.exit();
        }
        else if (button.getFunction() == 4){
            openLink("https://github.com/Hecker-Chuoi/BTCK2");
        }
        else if (button.getFunction() == 5){

        }
        else{
            music();
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

    private void music(){
        if (MakeMusic.music.isPlaying()){
            MakeMusic.stopMusic();
        }
        else {
            MakeMusic.resumeMusic();
        }
    }
}
