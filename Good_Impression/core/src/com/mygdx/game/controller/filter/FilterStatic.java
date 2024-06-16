package com.mygdx.game.controller.filter;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;

import java.util.ArrayList;

public class FilterStatic {
    protected StaticItem nightStand;
    protected StaticItem bed;

    public boolean dynamicInStatic(StaticItem staticItem, DynamicItem dynamicItem){
        Rectangle rectStatic = staticItem.getBoundingRectangle();
        Rectangle rectDynamic = dynamicItem.getBoundingRectangle();
        return rectStatic.contains(rectDynamic);
    }

    public void updateDynamic(ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop,
                               StaticItem staticItem){
        ArrayList<DynamicItem> listDynamic = new ArrayList<>();
        for (DynamicItem dynamicItem : dynamicTable){
            if (dynamicInStatic(staticItem, dynamicItem)){
                listDynamic.add(dynamicItem);
            }
        }
        dynamicTable.removeAll(listDynamic);
        dynamicTop.addAll(listDynamic);
    }

    public boolean checkObscure(StaticItem item, Player player){
        return player.getX() + player.getWidth() >= item.getX()
                && player.getX() <= item.getX() + item.getWidth()
                && player.getY() >= item.getY() + item.getHeight() - item.getOverlap()
                && player.getY() <= item.getY() + item.getHeight();
    }

    public void updateNightStand(ArrayList<StaticItem> staticBottom, ArrayList<StaticItem> staticMiddle,
                                 ArrayList<StaticItem> staticTop){
        if (!staticBottom.isEmpty() && staticBottom.contains(bed)) {
            staticBottom.add(nightStand);
//            System.out.println("bottom");
        }
        else{
            if (!staticMiddle.isEmpty() && staticMiddle.contains(bed)) {
                staticMiddle.add(nightStand);
//                System.out.println("Middle");
            }
            else{
                if (!staticTop.isEmpty()) staticTop.add(nightStand);
//                System.out.println("Top");
            }
        }
    }
}
