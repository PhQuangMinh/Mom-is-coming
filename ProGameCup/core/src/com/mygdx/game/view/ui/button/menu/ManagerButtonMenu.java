//package com.mygdx.game.view.ui.button.menu;
//
//import com.badlogic.gdx.Gdx;
//import com.mygdx.game.SpaceGame;
//import com.mygdx.game.view.effect.MakeMusic;
//import com.mygdx.game.view.screens.mainstory.MainStory;
//import com.mygdx.game.view.ui.button.Button;
//
//import java.awt.*;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//public class ManagerButtonMenu {
//    public void onClick(Button button, SpaceGame game) {
//        if (button.getName().equals("play")){
//            game.setScreen(new MainStory(game));
//        }
//        else if (button.getName().equals("leaderboard")){
//
//        }
//        else if (button.getName().equals("back")) {
//            Gdx.app.exit();
//        }
//        else if (button.getName().equals("github")){
//            openLink("https://github.com/Hecker-Chuoi/BTCK2");
//        }
//        else if (button.getName().equals("howToPlay")){
//
//        }
//        else{
//            music();
//        }
//    }
//
//    private void openLink(String link) {
//        try {
//            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//                Desktop.getDesktop().browse(new URI(link));
//            }
//        } catch (IOException | URISyntaxException ignored) {
//        }
//    }
//
//    private void music(){
//        if (MakeMusic.music.isPlaying()){
//            MakeMusic.stopMusic();
//        }
//        else {
//            MakeMusic.resumeMusic();
//        }
//    }
//}
