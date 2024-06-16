package com.mygdx.game.view.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.common.constant.ItemConstant;

import java.io.*;
import java.util.*;

public class Leaderboard {
    Texture leaderboard = new Texture("otherImage/Leaderboard.png");
    Texture close = new Texture("button/game/close.png");
    Texture closePress = new Texture("button/game/closePress.png");
    File file = new File("./assets/files/LocalLeaderboard.txt");
    BitmapFont font;
    ArrayList<Map.Entry<String, String>> list;
    int originY = 663, originNameX = 300, originScoreX = 623;
    int dy = 108;
    public static String abc;

    public Leaderboard() {
        font = new BitmapFont(Gdx.files.internal("fonts/char.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f);
        list = new ArrayList<>();
        try{
            read();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isANewRecord(int minutes, int seconds){
        for(int i = 0; i < Math.max(5, list.size()); ++i){
            Map.Entry<String, String> entry = list.get(i);
            String hm[] = entry.getValue().split(":");
            int min = Integer.parseInt(hm[0]);
            int sec = Integer.parseInt(hm[1]);
            if(minutes < min || (minutes == min && seconds < sec)){
                return true;
            }
        }
        return false;
    }

    public void draw(Batch batch, boolean isLeaderboardOpen){
        if(isLeaderboardOpen){
            batch.draw(leaderboard,0, 0, 960, 960);
            try{
                print(batch);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            float x = Gdx.input.getX();
            float y = 960 - Gdx.input.getY();

            if((x >= 784 && x <= 784 + ItemConstant.ICON_WIDTH) && (y >= 816 && y <= 816 + ItemConstant.ICON_HEIGHT)){
                batch.draw(closePress, 784, 816, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
                if(Gdx.input.isTouched()){
                    ButtonMenu.isLeaderboardOpen = false;
                }
            }
            else {
                batch.draw(close, 784, 816, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            }
        }
    }

    public void print(Batch batch) throws IOException {
        read();

        int nameX = originNameX, scoreX = originScoreX, y = originY;
        for(Map.Entry<String, String> entry : list) {
            font.draw(batch, entry.getKey(), nameX, y);
            font.draw(batch, entry.getValue(), scoreX, y);
            y -= dy;
        }
    }

    public void sort(){
        list.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                String[] hm1 = o1.getValue().split(":");
                String[] hm2 = o2.getValue().split(":");

                if(hm1[0].equals(hm2[0])){
                    return hm1[1].compareTo(hm2[1]);
                }
                return hm1[0].compareTo(hm2[0]);
            }
        });
    }

    public void add(String name, String time) throws IOException {
        list.add(new AbstractMap.SimpleEntry<>(name, time));
        sort();
        save();
    }

    public void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        list = new ArrayList<>();

        String name;
        while((name = br.readLine()) != null){
            String time = br.readLine();
            list.add(new AbstractMap.SimpleEntry<>(name, time));
//            System.out.println("Name: " + name + " Time: " + time);
        }

        br.close();
    }

    public void save() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for(int i = 0; i < Math.min(5, list.size()); ++i){
            Map.Entry<String, String> entry = list.get(i);
            bw.write(entry.getKey());
            bw.write("\n");
            bw.write(entry.getValue());
            bw.write("\n");
        }

        bw.close();
    }
}
