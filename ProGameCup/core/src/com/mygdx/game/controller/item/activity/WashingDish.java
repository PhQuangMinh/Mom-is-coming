package com.mygdx.game.controller.item.activity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;

public class WashingDish {
    Vector2 position;
    public WashingDish(){
        position = new Vector2();
    }

    private boolean checkItem(Player player, boolean keyPressed){
        return keyPressed && player.getContainer() != null && player.getItemHolding() != null &&
                player.getContainer().getName().equals("dish-washing") &&
                player.getMovement().getStatus() != CharacterStatus.CLEANING_DISH;
    }
    public void washingDishSingle(Player player, boolean keyPressed){
        if (checkItem(player, keyPressed)) {
            washSingle(player, player.getItemHolding());
        }
    }

    private void setPlayer(Player player){
        player.getMovement().setStatus(CharacterStatus.CLEANING_DISH);
        player.getItemHolding().setVisible(false);
        player.getItemHolding().setActionCount(0);
    }

    private void setInValid(Player player){
        player.setValidThrow(false);
        position.set(player.getX(), player.getY());
        player.setPositionThrew(position);
    }

    public void washSingle(Player player, DynamicItem dynamicItem){
        if(dynamicItem.getName().equals("dish")){
            setPlayer(player);
        }
        else{
            setInValid(player);
        }
    }

    private void washMulti(Player firstPlayer, Player secondPlayer, DynamicItem dynamicItem){
        if (dynamicItem.getName().equals("dish") &&
            secondPlayer.getMovement().getStatus() != CharacterStatus.CLEANING_DISH){
            setPlayer(firstPlayer);
        }
        else{
            setInValid(firstPlayer);
        }
    }

    public void washingDishMulti(Player firstPlayer, Player secondPlayer, boolean keyPressed){
        if (checkItem(firstPlayer, keyPressed)){
            washMulti(firstPlayer, secondPlayer, firstPlayer.getItemHolding());
        }
    }
}
