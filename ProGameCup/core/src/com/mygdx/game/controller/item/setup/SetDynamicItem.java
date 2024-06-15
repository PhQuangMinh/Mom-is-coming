package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.model.item.DynamicItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SetDynamicItem {
    private DynamicItem getItem(String name, BufferedReader br) throws IOException {
        return new DynamicItem(name, new Texture(br.readLine()), new Texture(br.readLine()),
                Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()), Boolean.parseBoolean(br.readLine()),
                Boolean.parseBoolean(br.readLine()));
    }

    public void setDynamic(ArrayList<DynamicItem> items){
        String filePath = "assets/items/dynamic-items/dataoneplayer.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                String name = br.readLine();
                if (name == null) return;
                items.add(getItem(name, br));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
