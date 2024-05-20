package com.mygdx.game.controller.player;

import com.mygdx.game.model.Player;

public class ManagerPlayer {
    public void updateStatus(Player player){
        if(player.getStatusHold() == 4);
        else if(player.getItemHolding()==null){
            if (player.getContainer()==null || player.getContainer().getNumber()==0)
                player.setStatusHold(1);
            else player.setStatusHold(3);
        }
        else player.setStatusHold(2);
    }
}
