package com.mygdx.game.view.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MakeSound {
    public static void makeSound(String pathSound){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(pathSound));
        sound.play(1.0f);
    }
}
