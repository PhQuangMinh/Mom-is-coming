package com.mygdx.game.view.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MakeMusic{
    public static Music music;
    public static void playMusic(String pathMusic){
        music = Gdx.audio.newMusic(Gdx.files.internal(pathMusic));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();
    }

    public static void stopMusic(){
        music.stop();
    }
    public static void pauseMusic(){
        music.pause();
    }
    public static void resumeMusic(){
        music.play();
    }
}
