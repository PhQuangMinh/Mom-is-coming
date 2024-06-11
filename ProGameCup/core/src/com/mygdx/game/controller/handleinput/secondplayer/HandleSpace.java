package com.mygdx.game.controller.handleinput.secondplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.player.ManagerPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class HandleSpace {

    ManagerPlayer managerPlayer;

    public HandleSpace(){
        managerPlayer = new ManagerPlayer();
    }
    public void handle(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            managerPlayer.handleStatus(player, dynamicItems, staticItems);
        }
    }
}
