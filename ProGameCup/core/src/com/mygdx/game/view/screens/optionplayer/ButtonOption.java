package com.mygdx.game.view.screens.optionplayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.draw.ui.DrawButton;
import com.mygdx.game.view.screens.maingame.multiplayer.MultiPlayer;
import com.mygdx.game.view.screens.maingame.singleplayer.SinglePlayer;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

public class ButtonOption {
    SpaceGame game;
    SpriteBatch batch;

    DrawButton drawButton;

    MainMenuScreen mainMenuScreen;

    SinglePlayer singlePlayer;

    MultiPlayer multiPlayer;

    Texture instruction, onePlayer, twoPlayer, onePress, twoPress;

    MainStory mainStory;



    public ButtonOption(SpaceGame game, MainMenuScreen mainMenuScreen, SinglePlayer singlePlayer,
                        MultiPlayer multiPlayer){
        this.game = game;
        this.batch = game.getBatch();
        this.mainMenuScreen = mainMenuScreen;
        this.singlePlayer = singlePlayer;
        this.multiPlayer = multiPlayer;
        mainStory = new MainStory(game, mainMenuScreen, singlePlayer, multiPlayer);
        drawButton = new DrawButton(game);
        initTexture();
    }

    public void initTexture() {
        instruction = new Texture("instruction/instruction.png");
        onePlayer = new Texture("instruction/one.png");
        twoPlayer = new Texture("instruction/two.png");
        onePress = new Texture("instruction/onePress.png");
        twoPress = new Texture("instruction/twoPress.png");
    }
    public void draw(){
        batch.draw(instruction, (GameConstant.WINDOW_WIDTH - instruction.getWidth())/2,
                GameConstant.WINDOW_HEIGHT - instruction.getHeight()*1.1f);
        float posY = GameConstant.WINDOW_HEIGHT - instruction.getHeight()*1.1f - 2f*onePlayer.getHeight();
        drawButton.drawButtonOption(onePlayer, onePress,
                (int)(GameConstant.WINDOW_WIDTH/2 - onePlayer.getWidth())/2, (int)posY, 1, mainStory);
        drawButton.drawButtonOption(twoPlayer, twoPress,
                (int)(3*GameConstant.WINDOW_WIDTH/2 - twoPlayer.getWidth())/2, (int)posY, 2, mainStory);
    }
}
