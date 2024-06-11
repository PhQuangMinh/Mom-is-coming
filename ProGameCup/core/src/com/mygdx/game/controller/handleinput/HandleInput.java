package com.mygdx.game.controller.handleinput;

import com.mygdx.game.controller.handleinput.firstplayer.HandleArrow;
import com.mygdx.game.controller.handleinput.firstplayer.HandleEnter;
import com.mygdx.game.controller.handleinput.secondplayer.HandleADWS;
import com.mygdx.game.controller.handleinput.secondplayer.HandleSpace;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class HandleInput {
    HandleEnter handleEnter;

    HandleArrow handleArrow;

    HandleADWS handleADWS;

    HandleSpace handleSpace;
    public HandleInput(){
        handleArrow = new HandleArrow();
        handleEnter = new HandleEnter();
        handleADWS = new HandleADWS();
        handleSpace = new HandleSpace();
    }

    public void firstPlayer(Player player, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems){
        handleEnter.handle(player, dynamicItems, staticItems);
        handleArrow.handle(player);
    }

    public void secondPlayer(Player player, ArrayList<DynamicItem> dynamicItems
           , ArrayList<StaticItem> staticItems){
        handleADWS.handle(player);
        handleSpace.handle(player, dynamicItems, staticItems);
    }
}
