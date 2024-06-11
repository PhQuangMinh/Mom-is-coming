package com.mygdx.game.controller.discover;

import com.mygdx.game.common.constant.Direction;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.Item;

import java.util.ArrayList;

public class DiscoverDynamic {
    private boolean checkTop(Item item, Player player) {
        return player.getMovement().getDirection() == Direction.DOWN
                && player.getY() <= item.getY() + item.getHeight() + 10
                && player.getY() >= item.getY()
                && player.getX() + player.getWidth() - 10 >= item.getX()
                && player.getX() <= item.getX() + item.getWidth() - 10;
    }

    private boolean checkBottom(Item item, Player player) {
        return player.getMovement().getDirection() == Direction.UP
                && player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() <= item.getY() + item.getHeight() - 0.2f* GameConstant.PLAYER_HEIGHT
                && player.getY() >= item.getY() - 0.7f * GameConstant.PLAYER_HEIGHT;
    }

    private boolean checkLeft(Item item, Player player) {
        return player.getMovement().getDirection() == Direction.RIGHT
                && player.getX() <= item.getX() + item.getWidth() - 0.6f*GameConstant.PLAYER_WIDTH
                && player.getX() + player.getWidth() >= item.getX()
                && player.getY() + GameConstant.PLAYER_HEIGHT*0.2f >= item.getY()
                && player.getY() <= item.getY() + item.getHeight();
    }

    private boolean checkRight(Item item, Player player) {
        return player.getMovement().getDirection() == Direction.LEFT
                && player.getX() >= item.getX() - 0.2f*GameConstant.PLAYER_WIDTH
                && player.getX() <= item.getX() + item.getWidth() + 5
                && player.getY() + GameConstant.PLAYER_HEIGHT*0.2f >= item.getY()
                && player.getY() <= item.getY() + item.getHeight()-item.getOverlap();
    }

    public boolean checkDiscover(Item item, Player player) {
        return checkTop(item, player)
                || checkBottom(item, player)
                || checkLeft(item, player)
                || checkRight(item, player);
    }

    public void resetDynamic(ArrayList<DynamicItem> items) {
        for (Item item : items) {
            item.setDiscover(false);
        }
    }
}
