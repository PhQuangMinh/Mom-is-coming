package com.mygdx.game.controller.handleinput.firstplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.item.activity.throwitem.ThrowToPlayer;
import com.mygdx.game.model.Player;

public class HandleP {

    ThrowToPlayer throwToPlayer;

    public HandleP(){
        throwToPlayer = new ThrowToPlayer();
    }
    public void handle(Player firstPlayer, Player secondPlayer){
        if (!Gdx.input.isKeyJustPressed(Input.Keys.P)) return;
        System.out.println("HIHI");
        throwToPlayer.throwTo(firstPlayer, secondPlayer);
    }
}
