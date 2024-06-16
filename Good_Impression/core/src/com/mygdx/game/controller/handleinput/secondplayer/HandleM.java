package com.mygdx.game.controller.handleinput.secondplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.item.activity.throwitem.ThrowToPlayer;
import com.mygdx.game.model.Player;

public class HandleM {
    ThrowToPlayer throwToPlayer;

    public HandleM(){
        throwToPlayer = new ThrowToPlayer();
    }
    public void handle(Player firstPlayer, Player secondPlayer){
        if (!Gdx.input.isKeyJustPressed(Input.Keys.M)) return;
        throwToPlayer.throwTo(secondPlayer, firstPlayer);
    }
}
