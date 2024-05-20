package com.mygdx.game.view.ui.button;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;

import java.util.ArrayList;

public class InitButton {
    int widthButton = GameConstant.buttonWidth;
    int heightButton = GameConstant.buttonHeight;
    int widthIcon = GameConstant.iconWidth;
    int heightIcon = GameConstant.iconHeight;
    int posX = (int)(GameConstant.windowWidth- GameConstant.buttonWidth)/2;
    SpriteBatch batch;
    SpaceGame game;
    ArrayList<Button> listButton;
    public InitButton(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
        listButton = new ArrayList<>();
    }
    public void initMenu(){
        listButton.add(new Button("menu/play", "menu/playPress"
                , posX,500, widthButton, heightButton, 1));
        listButton.add(new Button("menu/leaderboard", "menu/leaderboardPress"
                , posX,400, widthButton, heightButton, 2));
        listButton.add(new Button("menu/back", "menu/backPress"
                , posX, 300, widthButton, heightButton, 3));
        listButton.add(new Button("menu/github", "menu/githubPress"
                , 15, 15, widthIcon, heightIcon, 4));
        listButton.add(new Button("menu/howToPlay", "menu/howToPlayPress"
                , 800, 800, widthIcon, heightIcon, 5));
        listButton.add(new Button("menu/musicOn", "menu/musicOff"
                , 740, 800, widthIcon, heightIcon, 6));
    }

    public void initGame() {
        listButton.add(new Button("game/resume", "game/pause",
                (int) GameConstant.windowWidth - 70, 560, widthIcon, heightIcon, 1));
        listButton.add(new Button("game/home", "game/homePress",
                (int) GameConstant.windowWidth - 70, 680, widthIcon, heightIcon, 2));
        listButton.add(new Button("game/replay", "game/replayPress",
                (int) GameConstant.windowWidth - 70, 620, widthIcon, heightIcon, 3));
        listButton.add(new Button("game/menuBar", "game/menuBarPress",
                (int) GameConstant.windowWidth - 70, 740, widthIcon, heightIcon, 4));
        listButton.add(new Button("game/menuBar", "game/menuBarPress",
                (int) GameConstant.windowWidth - 70, 740, widthIcon, heightIcon, 5));
    }

    public void drawButtonMenu(){
        ManagerButton manager = new ManagerButton();
        manager.drawMenu(listButton, game, batch);
    }
}
