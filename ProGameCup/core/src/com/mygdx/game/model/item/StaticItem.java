package com.mygdx.game.model.item;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.item.Item;

import java.util.ArrayList;

public class StaticItem extends Item {
    private Texture containItem;
    private int number;
    private ArrayList<DynamicItem> items;
    public StaticItem(String name, Texture image, Texture chosenImage, float x, float y, float width
            , float height, float overlap, Texture containItem, int number) {
        super(name, image, chosenImage, x, y, width, height, overlap);
        this.containItem = containItem;
        this.number = number;
        this.items = new ArrayList<>();
    }

    public ArrayList<DynamicItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<DynamicItem> items) {
        this.items = items;
    }
    public Texture getContainItem() {
        return containItem;
    }

    public void setContainItem(Texture containItem) {
        this.containItem = containItem;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
