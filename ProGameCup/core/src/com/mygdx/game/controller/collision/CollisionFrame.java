package com.mygdx.game.controller.collision;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.FrameStatus;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.model.Player;

public class CollisionFrame {
    public void updateFrameRight(Vector2 position, Player player){
        if (position.x < MapConstant.LOWER_LIMIT_RIGHT_X){
            if (position.y >= MapConstant.UPPER_LIMIT_CENTER_Y
                    || position.y <= MapConstant.LOWER_LIMIT_CENTER_Y){
                position.x = MapConstant.LOWER_LIMIT_RIGHT_X;
            }
            else{
                player.setFrameStatus(FrameStatus.CENTER_FRAME);
                return;
            }
        }
        if (position.x > MapConstant.UPPER_LIMIT_RIGHT_X)
            position.x = MapConstant.UPPER_LIMIT_RIGHT_X;
        if (position.y < MapConstant.LOWER_LIMIT_RIGHT_Y)
            position.y = MapConstant.LOWER_LIMIT_RIGHT_Y;
        if (position.y > MapConstant.UPPER_LIMIT_RIGHT_Y)
            position.y = MapConstant.UPPER_LIMIT_RIGHT_Y;
    }

    private void updateTopElevator(Vector2 position, Player player){
        if (position.y < MapConstant.UPPER_LIMIT_ELEVATOR_Y){
            position.y = MapConstant.UPPER_LIMIT_ELEVATOR_Y;
        }
        if (position.y > MapConstant.UPPER_LIMIT_LEFT_Y){
            position.y = MapConstant.UPPER_LIMIT_LEFT_Y;
        }
        if (position.x < MapConstant.LOWER_LIMIT_LEFT_X){
            position.x = MapConstant.LOWER_LIMIT_LEFT_X;
        }
        if (position.x > MapConstant.UPPER_LIMIT_ELEVATOR_X){
            player.setFrameStatus(FrameStatus.RIGHT_ELEVATOR);
        }
    }

    private void updateRightElevator(Vector2 position, Player player){
        if (position.y < MapConstant.LOWER_LIMIT_LEFT_Y){
            position.y = MapConstant.LOWER_LIMIT_LEFT_Y;
        }
        if (position.y > MapConstant.UPPER_LIMIT_LEFT_Y){
            position.y = MapConstant.UPPER_LIMIT_LEFT_Y;
        }
        if (position.x < MapConstant.UPPER_LIMIT_ELEVATOR_X){
            if (position.y >= MapConstant.UPPER_LIMIT_ELEVATOR_Y){
                player.setFrameStatus(FrameStatus.TOP_ELEVATOR);
                return;
            }
            else{
                position.x = MapConstant.UPPER_LIMIT_ELEVATOR_X;
            }
        }
        if (position.x > MapConstant.UPPER_LIMIT_LEFT_X){
            if (position.y < MapConstant.LOWER_LIMIT_CENTER_Y){
                position.x = MapConstant.UPPER_LIMIT_LEFT_X;
            }
            else{
                player.setFrameStatus(FrameStatus.CENTER_FRAME);
            }
        }
    }
    public void updateFrameCenter(Vector2 position, Player player){
        if (position.y < MapConstant.LOWER_LIMIT_CENTER_Y)
            position.y = MapConstant.LOWER_LIMIT_CENTER_Y;
        if (position.y > MapConstant.UPPER_LIMIT_CENTER_Y)
            position.y = MapConstant.UPPER_LIMIT_CENTER_Y;
        if (position.x < MapConstant.LOWER_LIMIT_CENTER_X){
            player.setFrameStatus(FrameStatus.RIGHT_ELEVATOR);
        }
        if (position.x > MapConstant.UPPER_LIMIT_CENTER_X){
            player.setFrameStatus(FrameStatus.RIGHT_FRAME);
        }
    }
    void updateFrame(Vector2 position, Player player){
        switch (player.getFrameStatus()) {
            case RIGHT_FRAME:
                updateFrameRight(position, player);
                break;
            case CENTER_FRAME:
                updateFrameCenter(position, player);
                break;
            case TOP_ELEVATOR:
                updateTopElevator(position, player);
                break;
            case RIGHT_ELEVATOR:
                updateRightElevator(position, player);
                break;
            default:
        }
    }
}
