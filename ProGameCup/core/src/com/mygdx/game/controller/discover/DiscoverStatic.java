package com.mygdx.game.controller.discover;

import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.model.item.Item;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class DiscoverStatic {
    public void discoverStatic(ArrayList<StaticItem> items, Player player) {
        resetStatic(items);
        for (StaticItem item : items) {
            if (checkDiscover(item, player)){
                item.setDiscover(true);
                player.setContainer(item);
                return;
            }
        }
        player.setContainer(null);
    }

    public void resetStatic(ArrayList<StaticItem> items) {
        for (Item item : items) {
            item.setDiscover(false);
        }
    }

    private boolean checkTop(Item item, Player player) {
        return player.getDirection() == Direction.DOWN
                && player.getY() <= item.getY() + item.getHeight()-5
                && player.getY() >= item.getY()
                && player.getX() + 0.8f*player.getWidth()>= item.getX()
                && player.getX() <= item.getX() + item.getWidth();
    }

    private boolean checkBottom(Item item, Player player) {
        return player.getDirection() == Direction.UP
                && player.getX() + 0.8f*player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() <= item.getY() + item.getHeight() - item.getOverlap()
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
