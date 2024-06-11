package com.mygdx.game.controller.player;

import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.Player;

public class DirectionPlayer {
    public void updateDirection(Player player, boolean left, boolean right, boolean up, boolean down){
        if (player.getMovement().getStatus() != CharacterStatus.CLEANING_DISH
                && player.getMovement().getStatus() != CharacterStatus.MOPPING_FLOOR){
            if(left && !right && up && !down) {
                player.getMovement().setDirection(Direction.UPLEFT);
            }
            else if(left && !right && !up && down) {
                player.getMovement().setDirection(Direction.DOWNLEFT);
            }
            else if(!left && right && up && !down) {
                player.getMovement().setDirection(Direction.UPRIGHT);
            }
            else if(!left && right && !up && down) {
                player.getMovement().setDirection(Direction.DOWNRIGHT);
            }
            else if(left) {
                player.getMovement().setDirection(Direction.LEFT);
            }
            else if(right) {
                player.getMovement().setDirection(Direction.RIGHT);
            }
            else if(up) {
                player.getMovement().setDirection(Direction.UP);
            }
            else {
                player.getMovement().setDirection(Direction.DOWN);
            }
            setDirection(player);
        }
    }

    private void setDirection(Player player){
        Direction direction = player.getMovement().getDirection();
        if (direction == Direction.UP || direction == Direction.DOWN
                || direction == Direction.LEFT || direction == Direction.RIGHT)
            player.setDirection(direction);
        if (direction == Direction.UPLEFT || direction == Direction.DOWNLEFT)
            player.setDirection(Direction.LEFT);
        if (direction == Direction.UPRIGHT || direction == Direction.DOWNRIGHT)
            player.setDirection(Direction.RIGHT);
    }
}
