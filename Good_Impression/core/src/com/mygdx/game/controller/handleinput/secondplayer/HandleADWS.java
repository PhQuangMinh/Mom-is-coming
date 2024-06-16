package com.mygdx.game.controller.handleinput.secondplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.player.DirectionPlayer;
import com.mygdx.game.model.Player;

public class HandleADWS {
    DirectionPlayer directionPlayer;

    public HandleADWS(){
        directionPlayer = new DirectionPlayer();
    }
    public void handle(Player player){
        boolean pressA = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean pressD = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean pressS = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean pressW = Gdx.input.isKeyPressed(Input.Keys.W);
        if (!pressA && !pressD && !pressS && !pressW) return;
        directionPlayer.updateDirection(player, pressA, pressD, pressW, pressS);
    }
}
