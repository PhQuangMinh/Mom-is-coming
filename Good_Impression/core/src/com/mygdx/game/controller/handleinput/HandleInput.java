package com.mygdx.game.controller.handleinput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.handleinput.firstplayer.HandleArrow;
import com.mygdx.game.controller.handleinput.firstplayer.HandleEnter;
import com.mygdx.game.controller.handleinput.firstplayer.HandleP;
import com.mygdx.game.controller.handleinput.secondplayer.HandleADWS;
import com.mygdx.game.controller.handleinput.secondplayer.HandleM;
import com.mygdx.game.controller.handleinput.secondplayer.HandleSpace;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;
import java.util.Random;

public class HandleInput {
    HandleEnter handleEnter;

    HandleArrow handleArrow;

    HandleADWS handleADWS;

    HandleSpace handleSpace;

    HandleP handleP;

    HandleM handleM;
    public HandleInput(){
        handleArrow = new HandleArrow();
        handleEnter = new HandleEnter();
        handleADWS = new HandleADWS();
        handleSpace = new HandleSpace();
        handleP = new HandleP();
        handleM = new HandleM();
    }


    public void firstPlayer(Player player, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems){
        handleArrow.handle(player);
        handleEnter.handle(player, dynamicItems, staticItems);
    }

    public void handleMove(Player firstPlayer, Player secondPlayer){
        handleArrow.handle(firstPlayer);
        handleADWS.handle(secondPlayer);
    }

    public void handleActivity(Player firstPlayer, Player secondPlayer,
                               ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems){
        handleEnter.handle(firstPlayer, dynamicItems, staticItems);
        handleSpace.handle(secondPlayer, dynamicItems, staticItems);
        handleP.handle(firstPlayer, secondPlayer);
        handleM.handle(firstPlayer, secondPlayer);
    }
}
