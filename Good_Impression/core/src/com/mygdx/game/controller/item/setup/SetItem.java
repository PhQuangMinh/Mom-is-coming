package com.mygdx.game.controller.item.setup;

import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class SetItem {
    public void set(ArrayList<DynamicItem> dynamicItems, ArrayList<StaticItem> staticItems){
        SetStaticItem setStaticItem = new SetStaticItem();
        SetDynamicItem setDynamicItem = new SetDynamicItem();
        setStaticItem.setStatic(staticItems);
        setDynamicItem.setDynamic(dynamicItems);
    }
}
