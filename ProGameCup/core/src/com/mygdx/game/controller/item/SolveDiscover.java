package com.mygdx.game.controller.item;

import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.CheckCollision;
import com.mygdx.game.controller.constant.Direction;
import com.mygdx.game.model.Item;
import com.mygdx.game.model.Player;

import java.util.ArrayList;

public class SolveDiscover {
    public void discoverItems(ArrayList<Item> items, Player player) {
        resetDiscover(items);
        for (Item item : items) {
            if (checkDiscover(item, player)){
                item.setDiscover(true);
                return;
            }
        }
    }

    public void resetDiscover(ArrayList<Item> items) {
        for (Item item : items) {
            item.setDiscover(false);
        }
    }

    private boolean checkTop(Item item, Player player) {
        return player.getDirection() == Direction.DOWN
                && player.getY() <= item.getY() + item.getHeight()-5
                && player.getY() >= item.getY()
                && player.getX() + player.getWidth()>= item.getX()
                && player.getX() <= item.getX() + item.getWidth();
    }

    private boolean checkBottom(Item item, Player player) {
        return player.getDirection() == Direction.UP
                && player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() <= item.getY() + item.getHeight()
                && player.getY() >= item.getY() - 0.5f * GameConstant.playerHeight;
    }

    private boolean checkLeft(Item item, Player player) {
        return player.getDirection() == Direction.RIGHT
                && player.getX() <= item.getX() + item.getWidth()
                && player.getX() + player.getWidth() >= item.getX()
                && player.getY() + GameConstant.playerHeight*0.2f >= item.getY()
                && player.getY() <= item.getY() + item.getHeight()-item.getOverlap();
    }

    private boolean checkRight(Item item, Player player) {
        return player.getDirection() == Direction.LEFT
                && player.getX() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth() + 5
                && player.getY() + GameConstant.playerHeight*0.2f >= item.getY()
                && player.getY() <= item.getY() + item.getHeight()-item.getOverlap();
    }

    public boolean checkDiscover(Item item, Player player) {
        return checkTop(item, player)
                || checkBottom(item, player)
                || checkLeft(item, player)
                || checkRight(item, player);
    }
}
