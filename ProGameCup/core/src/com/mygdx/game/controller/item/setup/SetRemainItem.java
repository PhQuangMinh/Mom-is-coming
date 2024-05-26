package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class SetRemainItem {
    public ArrayList<DynamicItem> dynamicItems = new ArrayList<>();
    public void setRemainDynamicItem(ArrayList<DynamicItem> dynamicItems){
        this.dynamicItems = dynamicItems;
    }

    public ArrayList<DynamicItem> getDynamicItems(){
        return dynamicItems;
    }
    public void setUpItem(){
        SetDynamicItem setDynamicItem = new SetDynamicItem();
        for(DynamicItem item : dynamicItems){
            setDynamicItem.getItem(item.getName(), true);
        }
    }

}
