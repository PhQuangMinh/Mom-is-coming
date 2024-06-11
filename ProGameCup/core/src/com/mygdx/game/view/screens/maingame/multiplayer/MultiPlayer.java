package com.mygdx.game.view.screens.maingame.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.draw.screengame.DrawMulti;
import com.mygdx.game.view.draw.ui.NewButton;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

public class MultiPlayer extends MainGameScreen {

    private Player firstPlayer;

    private Player secondPlayer;

    private ManagerMulti managerMulti;

    private DrawMulti drawMulti;

    public MultiPlayer(SpaceGame game, MainMenuScreen mainMenuScreen, MainStory mainStory) {
        super(game, mainMenuScreen, mainStory);
    }

    @Override
    public void show() {
        super.show();
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("animations/Main_char_animations1.atlas"));
        firstPlayer = new Player(textureAtlas, animationNames, textureNames,
                500, 380,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);
        textureAtlas = new TextureAtlas("animations/Main_char_animations2.atlas");
        secondPlayer = new Player(textureAtlas, animationNames, textureNames,
                500, 380,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);
        managerMulti = new ManagerMulti();
        drawMulti = new DrawMulti(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        managerMulti.update(firstPlayer, secondPlayer, dynamicItems, staticItems);
        handleInput.firstPlayer(firstPlayer, dynamicItems, staticItems);
        handleInput.secondPlayer(secondPlayer, dynamicItems, staticItems);
        drawMulti.draw(firstPlayer, secondPlayer, dynamicItems, staticItems, mainMenuScreen, mainStory,
                batch, stateTime, delta);
        if(!NewButton.isPause) {
            stateTime += delta;
            managerMovement.statusFirstPlayer(firstPlayer, dynamicItems, staticItems);
            managerMovement.statusSecondPlayer(secondPlayer, dynamicItems, staticItems);
        }
        makeAlert.update(batch, stateTime, firstPlayer);
        batch.end();
    }

}
