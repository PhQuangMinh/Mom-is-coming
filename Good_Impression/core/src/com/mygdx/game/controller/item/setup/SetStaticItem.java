package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.item.StaticItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SetStaticItem {
    private Texture getTexture(BufferedReader buffer) throws IOException {
        String path = buffer.readLine();
        if (path.equals("null")) return null;
        return new Texture(path);
    }
    public StaticItem getItem(String name, BufferedReader br) throws IOException {
        return new StaticItem(name, new Texture(br.readLine()), getTexture(br),
                Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()), Float.parseFloat(br.readLine()),
                Float.parseFloat(br.readLine()), getTexture(br), Integer.parseInt(br.readLine()));
    }

    public void setStatic(ArrayList<StaticItem> items){
        String filePath = "assets/items/static-items/datastatic.txt";
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
