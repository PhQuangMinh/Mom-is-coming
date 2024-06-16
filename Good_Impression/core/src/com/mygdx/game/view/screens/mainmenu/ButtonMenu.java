package com.mygdx.game.view.screens.mainmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.ItemConstant;
import com.mygdx.game.view.draw.ui.DrawButton;
import com.mygdx.game.view.screens.optionplayer.OptionPlayer;


public class ButtonMenu {
    Texture play, playPress, leaderboard, leaderboardPress, back, backPress, github, githubPress,
            musicOn, musicOff, howToPlay, howToPlayPress;
    int widthButton = ItemConstant.BUTTON_WIDTH;
    int heightButton = ItemConstant.BUTTON_HEIGHT;
    int widthIcon = ItemConstant.ICON_WIDTH;
    int heightIcon = ItemConstant.ICON_HEIGHT;
    int posX = (int)(GameConstant.WINDOW_WIDTH- widthButton)/2;
    SpriteBatch batch;
    SpaceGame game;
    public static boolean isLeaderboardOpen;
    public static boolean isHowToPlayOpen;
    Leaderboard ldb;

    DrawButton drawButton;
    public ButtonMenu(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
        drawButton = new DrawButton(game);
        initMenu();
        isLeaderboardOpen = false;
        isHowToPlayOpen = false;
        ldb = new Leaderboard();
    }

    public void initMenu() {
        play = new Texture("button/menu/play.png");
        playPress = new Texture("button/menu/playPress.png");
        leaderboard = new Texture("button/menu/leaderboard.png");
        leaderboardPress = new Texture("button/menu/leaderboardPress.png");
        back = new Texture("button/menu/back.png");
        backPress = new Texture("button/menu/backPress.png");
        github = new Texture("button/menu/github.png");
        githubPress = new Texture("button/menu/githubPress.png");
        musicOn = new Texture("button/menu/musicOn.png");
        musicOff = new Texture("button/menu/musicOff.png");
        howToPlay = new Texture("button/menu/howToPlay.png");
        howToPlayPress = new Texture("button/menu/howToPlayPress.png");
    }

    public void drawBigButton(OptionPlayer optionPlayer){
        drawButton.drawButtonMenu(play, playPress, posX,600, widthButton, heightButton,1,
                optionPlayer, ldb);
        drawButton.drawButtonMenu(howToPlay, howToPlayPress, 900, 900, widthIcon, heightIcon,
                2, optionPlayer, ldb);
        drawButton.drawButtonMenu(leaderboard, leaderboardPress, posX, 500, widthButton, heightButton,
                3, optionPlayer, ldb);
        drawButton.drawButtonMenu(back, backPress, posX,  400, widthButton, heightButton,4,
                optionPlayer, ldb);
    }

    public void drawIconButton(OptionPlayer optionPlayer){
        drawButton.drawButtonMenu(github, githubPress, 15,15, widthIcon, heightIcon, 6,
                optionPlayer, ldb);
        drawButton.drawMusicButton(musicOn, musicOff, 840, 900);
        drawButton.drawHowToPlayButton();
    }

    public void draw(OptionPlayer optionPlayer){
        drawBigButton(optionPlayer);
        drawIconButton(optionPlayer);
        ldb.draw(batch, isLeaderboardOpen);
    }
}
