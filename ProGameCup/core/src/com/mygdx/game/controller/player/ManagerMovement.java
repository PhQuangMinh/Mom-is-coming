package com.mygdx.game.controller.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.item.DeleteItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.ui.BarProcess;
import com.mygdx.game.view.effect.MakeSound;

import java.util.ArrayList;

public class ManagerMovement {
    DeleteItem deleteItem;

    PositionPlayer positionPlayer;

    BarProcess barProcess;
    public ManagerMovement(){
        positionPlayer = new PositionPlayer();
        deleteItem  = new DeleteItem();
        barProcess = new BarProcess();

    }
    public void statusFirstPlayer(Player player, ArrayList<DynamicItem> dynamicItems,
                                  ArrayList<StaticItem> staticItems, SpriteBatch batch){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean enter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        updateStatus(player, dynamicItems, left, right, up, down, enter, batch);
        positionPlayer.update(player, dynamicItems, staticItems);
    }

    public void statusSecondPlayer(Player player, ArrayList<DynamicItem> dynamicItems,
                                    ArrayList<StaticItem> staticItems, SpriteBatch batch){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean space = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        updateStatus(player, dynamicItems, left, right, up, down, space, batch);
        positionPlayer.update(player, dynamicItems, staticItems);
    }

    private void updateStatus(Player player, ArrayList<DynamicItem> dynamicItems, boolean left,
                              boolean right, boolean up, boolean down, boolean keyPressed, SpriteBatch batch) {
        if(player.getMovement().getStatus() == CharacterStatus.CLEANING_DISH
                || player.getMovement().getStatus() == CharacterStatus.MOPPING_FLOOR){
            barProcess.drawBars(player, player.getMovement().getStatus(), batch);
            if(keyPressed && player.getMovement().isAnimationFinished()){
                player.getItemHolding().setActionCount(player.getItemHolding().getActionCount() + 1);

                MakeSound.makeSound("sounds/soSqueak" + (player.getItemHolding().getActionCount() % 5 + 1) + ".ogg", 1f);
                if(player.getItemHolding().getActionCount() == player.getItemHolding().getCleanTime()){
                    deleteItem.delete(player, dynamicItems);
                }
            }
        }
        else if(!left && !right && !up && !down) {
            player.getMovement().setStatus(CharacterStatus.IDLE);
        }
        else {
            player.getMovement().setStatus(CharacterStatus.WALKING);
        }
    }

}
