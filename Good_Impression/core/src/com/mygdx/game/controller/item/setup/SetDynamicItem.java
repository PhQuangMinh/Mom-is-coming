package com.mygdx.game.controller.item.setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.common.constant.GameConstant;
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
        String filePath;
        if (GameConstant.FORMAT_PLAYER == 1) {
            filePath = "items/dynamic-items/dataoneplayer.txt";  // Không cần 'assets/' khi dùng Gdx.files.internal
        } else {
            filePath = "items/dynamic-items/datatwoplayer.txt";
        }

        FileHandle fileHandle = Gdx.files.internal(filePath);

        try (BufferedReader br = new BufferedReader(fileHandle.reader())) {  // Dùng fileHandle.reader() để lấy BufferedReader
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
