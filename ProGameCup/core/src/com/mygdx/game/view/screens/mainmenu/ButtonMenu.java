package com.mygdx.game.view.screens.mainmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.ui.NewButton;
import com.mygdx.game.view.ui.button.Button;
import com.mygdx.game.view.ui.button.ManagerButton;

import java.util.ArrayList;

public class ButtonMenu {
    Texture play, playPress, leaderboard, leaderboardPress, back, backPress, github, githubPress, musicOn, musicOff, howToPlay, howToPlayPress;
    int widthButton = GameConstant.buttonWidth;
    int heightButton = GameConstant.buttonHeight;
    int widthIcon = GameConstant.iconWidth;
    int heightIcon = GameConstant.iconHeight;
    int posX = (int)(GameConstant.windowWidth- widthButton)/2;
    SpriteBatch batch;
    SpaceGame game;
    ArrayList<Button> listButton;

    NewButton newButton;
    public ButtonMenu(SpaceGame game) {
        this.game = game;
        batch = game.getBatch();
        listButton = new ArrayList<>();
        newButton = new NewButton(game);
        initMenu();
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
    

    public void draw(){
        newButton.drawButton(play, playPress, posX,500, widthButton, heightButton,1);
        newButton.drawMusicButton(musicOn, musicOff, 740, 800, widthIcon, heightIcon);
        newButton.drawButton(leaderboard, leaderboardPress, posX, 400, widthButton, heightButton,4);
        newButton.drawButton(back, backPress, posX,  300, widthButton, heightButton,4);
        newButton.drawButton(github, githubPress, 15,15,widthIcon, heightIcon, 6);
        newButton.drawButton(howToPlay, howToPlayPress, 800, 800, widthIcon, heightIcon, 2);
        newButton.drawHowToPlayButton(110, 300, 672,280);
    }
}
