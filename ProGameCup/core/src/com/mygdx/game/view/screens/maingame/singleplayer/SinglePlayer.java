package com.mygdx.game.view.screens.maingame.singleplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.draw.screengame.DrawSingle;
import com.mygdx.game.view.draw.ui.DrawButton;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

public class SinglePlayer extends MainGameScreen {

    private Player player;

    private DrawSingle drawSingle;

    ManagerSingle managerSingle;


    public SinglePlayer(SpaceGame game, MainMenuScreen mainMenuScreen) {
        super(game, mainMenuScreen, 180);
    }

    @Override
    public void show() {
        super.show();
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("animations/Main_char_animations1.atlas"));
        player = new Player(textureAtlas, animationNames, textureNames, 500, 500,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);
        DrawButton.isPause = false;
        drawSingle = new DrawSingle(game);
        managerSingle = new ManagerSingle();
        stateTime = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        managerSingle.update(player, dynamicItems, staticItems);
        drawSingle.draw(batch, stateTime, delta, player, dynamicItems, staticItems, mainEndStory);
        if(!DrawButton.isPause) {
            handleInput.firstPlayer(player, dynamicItems, staticItems);
            stateTime += delta;
            managerMovement.statusFirstPlayer(player, dynamicItems, staticItems, batch);
        }
        else{
            batch.setColor(1 ,1, 1, 0.6f);
            batch.draw(blurBg, MapConstant.POS_MAP_X + 239, MapConstant.POS_MAP_Y, 561, 519);

            batch.setColor(1,1,1,1);
            batch.draw(pauseBg, 300, 400, 444, 224);
            buttonGame.drawMenuBarSingle(mainMenuScreen, this);
        }
        makeAlert.update(batch, stateTime, player);
        batch.end();
        System.out.println(player.getX() + " " + player.getY());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
