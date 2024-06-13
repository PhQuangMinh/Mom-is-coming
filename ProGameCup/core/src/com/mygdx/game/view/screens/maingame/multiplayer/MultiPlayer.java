package com.mygdx.game.view.screens.maingame.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.draw.screengame.DrawMulti;
import com.mygdx.game.view.draw.ui.DrawButton;
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
        TextureAtlas textureAtlasFirst = new TextureAtlas(Gdx.files.internal("animations/Main_char_animations1.atlas"));
        firstPlayer = new Player(textureAtlasFirst, animationNames, textureNames, 500, 380,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);
        TextureAtlas textureAtlasSecond = new TextureAtlas("animations/Main_char_animations2.atlas");
        secondPlayer = new Player(textureAtlasSecond, animationNames, textureNames, 500, 380,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);
        managerMulti = new ManagerMulti();
        drawMulti = new DrawMulti(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        managerMulti.update(firstPlayer, secondPlayer, dynamicItems, staticItems);
        handleInput.handleMove(firstPlayer, secondPlayer);
        handleInput.handleActivity(firstPlayer, secondPlayer, dynamicItems, staticItems);
        drawMulti.draw(firstPlayer, secondPlayer, dynamicItems, staticItems, mainMenuScreen, this,
                batch, stateTime, delta);
        if(!DrawButton.isPause) {
            stateTime += delta;
            managerMovement.statusFirstPlayer(firstPlayer, dynamicItems, staticItems, batch);
            managerMovement.statusSecondPlayer(secondPlayer, dynamicItems, staticItems, batch);
        }
        makeAlert.update(batch, stateTime, firstPlayer);
        makeAlert.update(batch, stateTime, secondPlayer);
        batch.end();
    }

}
