package com.mygdx.game.view.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;

public class MakeSound {
    public static void makeSound(String pathSound, float volume){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(pathSound));
        sound.play(volume);
        sound = null;
    }

    public static void makeSoundWalk(Player player, CharacterStatus status, float delta){
        float soundRepeatTime = player.getMovement().getAnimationTime() % 0.3f;
        if(status == CharacterStatus.WALKING){
            soundRepeatTime += delta;
            if(soundRepeatTime > 0.3f) {
                int soundIndex = player.getMovement().getSoundIndex();
                soundIndex = soundIndex % 5;
                soundIndex++;
                MakeSound.makeSound("sounds/soStep" + soundIndex + ".ogg", 0.6f);
                player.getMovement().setSoundIndex(soundIndex);
            }
        }
    }
}
