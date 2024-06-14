package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.model.item.DynamicItem;

import java.util.ArrayList;

public class SetDynamicItem {
    private float overlap;

    private Vector2 getPosition(String nameImage, float height) {
        overlap = 0;
        float posX = MapConstant.POS_MAP_RIGHT_X;
        float posY = MapConstant.POS_MAP_Y;
        switch (nameImage) {
            case "coca":
                return new Vector2(posX + 240, posY + 140);
            case "coca_with_no_sugar":
                return new Vector2(posX + 300 , posY + 180);
            case "blue-sock":
                return new Vector2(posX + 150, posY + 100);
            case "box_potato":
                return new Vector2(posX + 350, posY + 350);
            case "box_snack":
                return new Vector2(posX + 70, posY + 300);
            case "box_apple":
                return new Vector2(posX + 500, posY + 360);
            case "carpet":
                return new Vector2(posX + 80, posY + 110);
            case "dish_fish":
                return new Vector2(posX + 400, posY + 100);
            case "letter":
                return new Vector2(posX + 140, posY + 295);
            case "paper":
                return new Vector2(posX + 200, posY + 265);
            case "pizza":
                return new Vector2(posX + 200, posY + 200);
            case "pizza_with_meat":
                return new Vector2(posX + 370, posY + 280);
            case "box-pizza":
                return new Vector2(posX + 150, posY + 150);
            case "red-shirt":
                return new Vector2(posX + 400, posY + 200);
            case "red-sock":
                return new Vector2(posX + 280, posY + 140);
            case "trouser":
                return new Vector2(posX + 450, posY + 180);
            case "underwear":
                return new Vector2(posX + 350, posY + 50);
            case "garbage_small":
                return new Vector2(posX + 180, posY + 340);
            case "puddle":
                return new Vector2(posX + 210, posY + 290);
            case "box-chicken":
                return new Vector2(posX + 120, posY + 50);
            case "tissue":
                return new Vector2(posX + 320, posY + 380);
            case "new_coca":
                return new Vector2(posX - 100, posY + 100);
            case "dish_meat":
                return new Vector2(posX - 150, posY + 200);
            case "garbage_big":
                return new Vector2(posX - 200, posY + 220);
            case "box_salad":
                return new Vector2(posX - 90, posY + 240);
        }
        return new Vector2(posX + 300, posY + 300);//shirt
    }

    private Vector2 getSize(String image, Texture imageTexture) {
        float patio = (float) imageTexture.getHeight() /imageTexture.getWidth();
        float width = ItemConstant.DYNAMIC_SIZE, height = ItemConstant.DYNAMIC_SIZE;
        if (patio>1) height *= patio;
        else width /= patio;
        if(image.equals("puddle"))
            return new Vector2(64, 64);
        if (image.equals("coca1") || image.equals("red-sock")){
            width/=1.5f;
            height/=1.5f;
        }
        if (image.equals("box-pizza")){
            width*=1.5f;
            height*=1.5f;
        }
        return  new Vector2(width, height);
    }

    public DynamicItem getItem(String image, boolean isCross) {
        Texture imageItem = new Texture("items/dynamic-items/items/" + image + ".png");
        Texture chosenImageItem = new Texture("items/dynamic-items/pick-items/" + image + ".png");
        Vector2 size = getSize(image, imageItem);
        Vector2 position = getPosition(image, size.y);
        boolean isClothes = false;
        if(image.equals("blue-sock") || image.equals("red-sock") || image.equals("red-shirt") || image.equals("tissue") || image.equals("shirt")){
            isClothes = true;
        }
        return new DynamicItem(image, imageItem, chosenImageItem, position.x, position.y, size.x, size.y,overlap, isCross, isClothes);
    }

    public void setDynamic(ArrayList<DynamicItem> items) {
        items.add(getItem("coca", true));
        items.add(getItem("blue-sock", true));
        items.add(getItem("box_apple", false));
        items.add(getItem("box_snack", false));
        items.add(getItem("box_potato", false));
        items.add(getItem("carpet", true));
        items.add(getItem("coca_with_no_sugar", true));
        items.add(getItem("box-pizza", false));
        items.add(getItem("dish_fish", true));
        items.add(getItem("letter", true));
        items.add(getItem("paper", true));
        items.add(getItem("pizza", true));
        items.add(getItem("pizza_with_meat", true));
        items.add(getItem("red-shirt", true));
        items.add(getItem("red-sock", true));
        items.add(getItem("shirt", true));
        items.add(getItem("trouser", true));
        items.add(getItem("underwear", true));
        items.add(getItem("garbage_small", true));
        items.add(getItem("puddle", true));
        items.add(getItem("box-chicken", false));
        items.add(getItem("tissue", true));
        items.add(getItem("new_coca", true));
        items.add(getItem("dish_meat", true));
        items.add(getItem("garbage_big", true));
        items.add(getItem("box_salad", false));
    }
}
