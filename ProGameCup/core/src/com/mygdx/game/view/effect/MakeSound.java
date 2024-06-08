package com.mygdx.game.view.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MakeSound {
    public static void makeSound(String pathSound, float volume){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(pathSound));
        sound.play(volume);
        sound = null;
    }
}
