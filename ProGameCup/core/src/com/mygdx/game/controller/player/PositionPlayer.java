package com.mygdx.game.controller.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.controller.collision.Collision;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class PositionPlayer {
    Vector2 oldPosition;

    Vector2 newPosition;

    PositionPlayer(){
        oldPosition = new Vector2();
        newPosition = new Vector2();
    }

    private void setNewPosition(Player player) {
        float straight = player.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime()*2;
        float diagonal = player.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime()*2;

        if(player.getDirection() == Direction.UP){
            newPosition.y += straight;
        }
        if(player.getDirection() == Direction.DOWN){
            newPosition.y -= straight;
        }
        if(player.getDirection() == Direction.LEFT){
            newPosition.x -= straight;
        }
        if(player.getDirection() == Direction.RIGHT){
            newPosition.x += straight;
        }
        if(player.getDirection() == Direction.UPLEFT){
            newPosition.y += diagonal;
            newPosition.x -= diagonal;
        }
        if(player.getDirection() == Direction.UPRIGHT){
            newPosition.y += diagonal;
            newPosition.x += diagonal;
        }
        if(player.getDirection() == Direction.DOWNLEFT){
            newPosition.y -= diagonal;
            newPosition.x -= diagonal;
        }
        if(player.getDirection() == Direction.DOWNRIGHT){
            newPosition.y -= diagonal;
            newPosition.x += diagonal;
        }
    }

    public void update(Player player, ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems){
        oldPosition.set(player.getX(), player.getY());
        newPosition.set(player.getX(), player.getY());
        setNewPosition(player);

        Collision collision = new Collision();
        collision.updatePosition(newPosition, oldPosition, staticItems, dynamicItems, player);

        if(player.getMovement().getStatus() == CharacterStatus.WALKING){
            player.setPosition(newPosition.x, newPosition.y);
        }
    }
}
