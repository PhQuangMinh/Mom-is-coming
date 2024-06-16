package com.mygdx.game.controller.handleinput.firstplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.player.DirectionPlayer;
import com.mygdx.game.model.Player;

public class HandleArrow {

    DirectionPlayer directionPlayer;

    public HandleArrow(){
        directionPlayer = new DirectionPlayer();
    }
    public void handle(Player player){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        if (!left && !right && !up && !down) return;
        directionPlayer.updateDirection(player, left, right, up, down);
    }
}
