package com.mygdx.game.view.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.model.item.Item;

import java.io.*;
import java.util.*;

public class Leaderboard {
    Texture leaderboard;
    Texture close = new Texture("button/game/close.png");
    Texture closePress = new Texture("button/game/closePress.png");
    FileHandle file;
    BitmapFont font;
    ArrayList<Map.Entry<String, String>> list; //<tên, record>
    int mode;

    private final int originY = 548, originNameX = 250, originScoreX = 590, originTimeX = 705;
    private final int originTabWidth = 100, originTabHeight = 52, tabOriginY = 646;
    private final int tab1X = 198, tab2X = 326;
    private final int dy = 96;

    public Leaderboard() { //1: 1 player, 2: 2 players
        initPrint();
        font = new BitmapFont(Gdx.files.internal("fonts/char.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(0.45f);
        list = new ArrayList<>();
        try{
            read();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void initPrint(){
        leaderboard = new Texture("leaderboard/1PlayerLocalLeaderboard.png");
        file = Gdx.files.internal("files/1PlayerLocalLeaderboard.txt");
        mode = 1; // view 1 player leaderboard
        try{
            read();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initAdd(){
        if(GameConstant.gameType == 1)
            file = Gdx.files.internal("files/1PlayerLocalLeaderboard.txt");
        else if(GameConstant.gameType == 2)
            file = Gdx.files.internal("files/2PlayerLocalLeaderboard.txt");
    }

    private boolean checkButtonPressed(int x, int y, int width, int height){
        if(!Gdx.input.isTouched())
            return false;

        int fx = Gdx.input.getX();
        int fy = 960 - Gdx.input.getY();

        if((fx >= x && fx <= x + width) && (fy >= y && fy <= y + height))
            return true;
        return false;
    }

    private boolean checkButtonHover(int x, int y, int width, int height){
        int fx = Gdx.input.getX();
        int fy = 960 - Gdx.input.getY();

        if((fx >= x && fx <= x + width) && (fy >= y && fy <= y + height))
            return true;
        return false;
    }

    private void checkSwitchModeButtonPressed(){
        if(mode == 1){
            if(checkButtonPressed(tab2X, tabOriginY, originTabWidth, originTabHeight)){
                mode = 2;
                file = Gdx.files.internal("files/2PlayerLocalLeaderboard.txt");
                leaderboard = new Texture("leaderboard/2PlayerLocalLeaderboard.png");
                try{
                    read();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if(mode == 2){
            if(checkButtonPressed(tab1X, tabOriginY, originTabWidth, originTabHeight)){
                mode = 1;
                file = Gdx.files.internal("files/1PlayerLocalLeaderboard.txt");
                leaderboard = new Texture("leaderboard/1PlayerLocalLeaderboard.png");
                try{
                    read();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isANewRecord(int itemsHidden, int minutes, int seconds){
        initAdd();

        try{
            read();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 0; i < Math.max(5, list.size()); ++i){
            Map.Entry<String, String> entry = list.get(i);
            String hm[] = entry.getValue().split(" ");
            int item = Integer.parseInt(hm[0]);
            int min = Integer.parseInt(hm[1]);
            int sec = Integer.parseInt(hm[2]);

            if(itemsHidden > item){
                return true;
            }
            else if(itemsHidden == item){
                if(minutes < min || (minutes == min && seconds < sec)){
                    return true;
                }
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

            if(checkButtonHover(772, 800, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT)){
                batch.draw(closePress, 772, 800, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
                if(Gdx.input.isTouched()) {
                    ButtonMenu.isLeaderboardOpen = false;
                    mode = 1;
                }
            }
            else {
                batch.draw(close, 772, 800, ItemConstant.ICON_WIDTH, ItemConstant.ICON_HEIGHT);
            }

            checkSwitchModeButtonPressed();
        }
    }

    public void print(Batch batch) throws IOException {
        int y = originY;
        for(Map.Entry<String, String> entry : list) {
            String[] record = entry.getValue().split(" ");
            int item = Integer.parseInt(record[0]);
            int min  = Integer.parseInt(record[1]);
            int sec  = Integer.parseInt(record[2]);
            font.draw(batch, entry.getKey(), originNameX, y);
            font.draw(batch, String.format("%02d", item), originScoreX, y);
            font.draw(batch, String.format("%02d:%02d", min, sec), originTimeX, y);
            y -= dy;
        }
    }

    public void sort(){
        list.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                String[] record1 = o1.getValue().split(" ");
                String[] record2 = o2.getValue().split(" ");

                if(record1[0].equals(record2[0])){
                    if(record1[1].equals(record2[1])){
                        return record1[2].compareTo(record2[2]);
                    }
                    return record1[1].compareTo(record2[1]);
                }
                return record2[0].compareTo(record1[0]);
            }
        });
    }

    public void add(String name, int item, int min, int sec) throws IOException {
        initAdd();
        read();
        list.add(new AbstractMap.SimpleEntry<>(name, String.format("%02d %02d %02d", item, min, sec)));
        sort();
        save();
    }

    public void read() throws IOException {
        BufferedReader br = new BufferedReader(file.reader());
        list = new ArrayList<>();

        String name;
        while((name = br.readLine()) != null){
            String record = br.readLine();
            list.add(new AbstractMap.SimpleEntry<>(name, record));
//            System.out.println("Name: " + name + " Time: " + time);
        }

        br.close();
        sort();
    }

    public void save() throws IOException {
        BufferedWriter bw = new BufferedWriter(file.writer(false));

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
