package com.mygdx.game.controller.filter.filtermulti;

import com.mygdx.game.controller.filter.FilterStatic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class FilterStaticMulti extends FilterStatic {

    private boolean checkBottom(Player player, StaticItem item){
        return player.getX() + player.getWidth() >= item.getX() &&
                player.getX() <= item.getX() + item.getWidth() &&
                player.getY() + player.getHeight() >= item.getY() &&
                player.getY() <= item.getY() + item.getHeight();
    }
    private int checkStatic(Player firstPlayer, Player secondPlayer, StaticItem staticItem){
        if (checkObscure(staticItem, firstPlayer) || checkObscure(staticItem, secondPlayer)){
            boolean firstObscure = checkObscure(staticItem, firstPlayer);
            boolean secondObscure = checkObscure(staticItem, secondPlayer);
            if (firstObscure &&  secondObscure){
                return 3;
            }
            else{
                if (firstObscure && !checkBottom(secondPlayer, staticItem)
                        || secondObscure && !checkBottom(firstPlayer, staticItem)){
                    return 3;
                }
                else return 2;
            }
        }
        return 1;
    }
    public void filter(Player firstPlayer, Player secondPlayer, ArrayList<StaticItem> staticItems,
                       ArrayList<StaticItem> staticTop, ArrayList<StaticItem> staticBottom,
                       ArrayList<DynamicItem> dynamicTable, ArrayList<DynamicItem> dynamicTop,
                       ArrayList<StaticItem> staticMiddle){
        for (StaticItem staticItem : staticItems) {
            if (staticItem.getName().equals("night-stand")){
                nightStand = staticItem;
                continue;
            }
            if (staticItem.getName().equals("bed")){
                bed = staticItem;
            }
            int check = checkStatic(firstPlayer, secondPlayer, staticItem);
            if (check == 3) {
                staticTop.add(staticItem);
                updateDynamic(dynamicTable, dynamicTop, staticItem);
            }
            else if (check == 2){
                staticMiddle.add(staticItem);
                updateDynamic(dynamicTable, dynamicTop, staticItem);
            }
            else staticBottom.add(staticItem);
        }
        super.updateNightStand(staticBottom, staticMiddle, staticTop);
    }
}
