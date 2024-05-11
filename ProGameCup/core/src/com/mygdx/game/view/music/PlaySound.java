package com.mygdx.game.view.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.NewButton;
import com.mygdx.game.view.screens.MainMenuScreen;

public class PlaySound {
    private static PlaySound instance;
    AssetManager assetManager;
    Music music;
    boolean isMusicOn;
    SpriteBatch batch;
    public PlaySound(SpriteBatch batch){
        this.batch = batch;
        assetManager = new AssetManager();
        loadMusic();
    }
    public static PlaySound getInstance(SpriteBatch batch) {
        if (instance == null) {
            instance = new PlaySound(batch);
        }
        return instance;
    }

    public void loadMusic() {
        assetManager.load("Smooth Sailing.mp3", Music.class);
        assetManager.finishLoading();
        music = assetManager.get("Smooth Sailing.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();
        isMusicOn = true;
    }

    public void stopMusic(){
        music.stop();
    }
    public void playMusic(){
        music.play();
    }
//    public void checkMusic(Texture musicOn, Texture musicOff, int x, int y, int width, int height) {
//        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + width && GameConstant.windowHeight - Gdx.input.getY() >= y && GameConstant.windowHeight - Gdx.input.getY() <= y + height) {
////            if(isMusicOn) batch.draw(musicOnPress, x, y ,width, height);
////            else batch.draw(musicOffPress,x,y,width,height);
//            if (Gdx.input.isTouched()) {
//                if (isMusicOn) {
////                    batch.draw(musicOff,x,y,width,height);
//                    music.stop();
//                    isMusicOn = false;
//                }
//                else {
////                    batch.draw(musicOn, x, y, width, height);
//                    music.play();
//
//                    isMusicOn = true;
//                }
//            }
//        }
//    }

    public void dispose(){
        music.dispose();
        assetManager.dispose();
    }
}
