package com.mygdx.game.controller.item;

import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class DeleteItem {
    public void delete(Player player, ArrayList<DynamicItem> dynamicItems){
        if(player.getMovement().getStatus() == CharacterStatus.MOPPING_FLOOR){
            deletePuddle(player, dynamicItems);
        }
        else {
            deleteDish(player, dynamicItems);
        }

        player.getMovement().setStatus(CharacterStatus.IDLE);
        player.setValidThrow(true);
    }

    private void deletePuddle(Player player, ArrayList<DynamicItem> dynamicItems){
        player.setStatusHold(1);
        ((DynamicItem)player.getItemHolding()).setVisible(true);
        DynamicItem puddle = (DynamicItem)player.getItemInRange();// xóa vũng nước
        puddle.setVisible(false);
        dynamicItems.remove(puddle);
        player.setItemInRange(null);
    }

    private void deleteDish(Player player, ArrayList<DynamicItem> dynamicItems){
        player.getMovement().setDirection(Direction.UP);
        dynamicItems.remove((DynamicItem) player.getItemHolding());// xóa đĩa đã được rửa xong
        player.setItemHolding(null);
    }
}
