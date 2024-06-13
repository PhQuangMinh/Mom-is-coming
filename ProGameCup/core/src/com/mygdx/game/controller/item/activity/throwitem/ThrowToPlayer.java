package com.mygdx.game.controller.item.activity.throwitem;

import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.Item;

public class ThrowToPlayer {

    public boolean checkUP(Player firstPlayer, Player secondPlayer){
        return firstPlayer.getMovement().getDirection() == Direction.UP
                && secondPlayer.getMovement().getDirection() == Direction.DOWN
                && firstPlayer.getX() + firstPlayer.getWidth() >= secondPlayer.getX()
                && firstPlayer.getX() <= secondPlayer.getX() + secondPlayer.getWidth()
                && firstPlayer.getY() <= secondPlayer.getY() + secondPlayer.getHeight()
                                            - 0.2f* firstPlayer.getHeight()
                && firstPlayer.getY() >= secondPlayer.getY() - 0.7f * firstPlayer.getHeight();

    }

    private boolean checkDown(Player firstPlayer, Player secondPlayer) {
        return firstPlayer.getMovement().getDirection() == Direction.DOWN
                && secondPlayer.getMovement().getDirection() == Direction.UP
                && firstPlayer.getY() <= secondPlayer.getY() + secondPlayer.getHeight() + 10
                && firstPlayer.getY() >= secondPlayer.getY()
                && firstPlayer.getX() + firstPlayer.getWidth() - 10 >= secondPlayer.getX()
                && firstPlayer.getX() <= secondPlayer.getX() + secondPlayer.getWidth() - 10;
    }

    private boolean checkLeft(Player firstPlayer, Player secondPlayer) {
        return firstPlayer.getMovement().getDirection() == Direction.RIGHT
                && firstPlayer.getX() <= secondPlayer.getX() + 0.4f*firstPlayer.getWidth()
                && firstPlayer.getX() + firstPlayer.getWidth() >= secondPlayer.getX()
                && firstPlayer.getY() + firstPlayer.getHeight()*0.2f >= secondPlayer.getY()
                && firstPlayer.getY() <= secondPlayer.getY() + secondPlayer.getHeight();
    }

    private boolean checkRight(Player firstPlayer, Player secondPlayer) {
        return firstPlayer.getMovement().getDirection() == Direction.LEFT
                && firstPlayer.getX() >= secondPlayer.getX() - 0.2f*firstPlayer.getWidth()
                && firstPlayer.getX() <= secondPlayer.getX() + secondPlayer.getWidth() + 5
                && firstPlayer.getY() + firstPlayer.getHeight()*0.2f >= secondPlayer.getY()
                && firstPlayer.getY() <= secondPlayer.getY() + secondPlayer.getHeight()
                                - secondPlayer.getDistanceOverlaps();
    }

    public boolean checkValidThrow(Player firstPlayer, Player secondPlayer) {
        return checkUP(firstPlayer, secondPlayer)
                || checkDown(firstPlayer, secondPlayer)
                || checkLeft(firstPlayer, secondPlayer)
                || checkRight(firstPlayer, secondPlayer);
    }

    public void throwTo(Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.getItemHolding() == null && secondPlayer.getItemHolding() == null ||
                firstPlayer.getItemHolding()!= null && secondPlayer.getItemHolding()!= null){
            return;
        }
        if (firstPlayer.getItemHolding() != null){
            if (checkValidThrow(firstPlayer, secondPlayer)){
                throwToPlayer(firstPlayer, secondPlayer);
                firstPlayer.setValidThrow(true);
            }
            else firstPlayer.setValidThrow(false);
        }
    }
    public void throwToPlayer(Player firstPlayer, Player secondPlayer) {
        secondPlayer.setItemHolding(firstPlayer.getItemHolding());
        firstPlayer.setItemHolding(null);
    }
}
