package com.mygdx.game.controller.handleinput;

import com.mygdx.game.controller.item.activity.MoppingFloor;
import com.mygdx.game.controller.item.activity.WashingDish;
import com.mygdx.game.controller.player.ManagerPlayer;

public class Handle {
    protected ManagerPlayer managerPlayer;

    protected MoppingFloor moppingFloor;

    protected WashingDish washingDish;

    public Handle(){
        managerPlayer = new ManagerPlayer();
        moppingFloor = new MoppingFloor();
        washingDish = new WashingDish();
    }
}
