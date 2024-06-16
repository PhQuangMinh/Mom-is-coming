package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.handleinput.HandleInput;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.item.setup.SetItem;
import com.mygdx.game.controller.player.ManagerMovement;
import com.mygdx.game.model.item.*;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.MakeAlert;
import com.mygdx.game.view.draw.ui.DrawButton;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.screens.endgame.MapEndGame;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    protected SpaceGame game;
    protected DrawText drawText;
    protected SpriteBatch batch;
    protected ArrayList<StaticItem> staticItems;
    protected ArrayList<DynamicItem> dynamicItems;
    protected MakeAlert makeAlert;
    protected DrawButton drawButton;
    protected SetItem setItem;
    protected MainMenuScreen mainMenuScreen;
    public static float stateTime;

    protected MainEndStory mainEndStory;

    protected ManagerMovement managerMovement;

    protected HandleInput handleInput;

    protected String[] animationNames = new String[]{"WALKING_UP", "WALKING_DOWN", "WALKING_LEFT", "WALKING_RIGHT",
            "HOLDING_WALKING_UP", "HOLDING_WALKING_DOWN", "HOLDING_WALKING_LEFT", "HOLDING_WALKING_RIGHT",
            "MOPPING_FLOOR_UP", "MOPPING_FLOOR_DOWN", "MOPPING_FLOOR_LEFT", "MOPPING_FLOOR_RIGHT",
            "CLEANING_DISH"};
    protected String[] textureNames = new String[]{"IDLE_UP", "IDLE_DOWN", "IDLE_LEFT", "IDLE_RIGHT",
            "HOLDING_IDLE_UP", "HOLDING_IDLE_DOWN", "HOLDING_IDLE_LEFT", "HOLDING_IDLE_RIGHT"};
    MapEndGame mapEndGame;
    int initTime;
    protected Texture pauseBg, blurBg;
    protected ButtonGame buttonGame;

    public MainGameScreen (SpaceGame game, MainMenuScreen mainMenuScreen, int initTime){
        this.mainMenuScreen = mainMenuScreen;
        this.game = game;
        this.initTime = initTime;
        batch = game.getBatch();
        staticItems = new ArrayList<>();
        dynamicItems = new ArrayList<>();;
        makeAlert = new MakeAlert();
        setItem = new SetItem();
        drawButton = new DrawButton(game);
        pauseBg = new Texture("otherImage/pauseBg.png");
        blurBg = new Texture("otherImage/blurBg.jpg");
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE, mainEndStory);
    }

    @Override
    public void show() {
        buttonGame = new ButtonGame(game);
        dynamicItems.clear();
        staticItems.clear();
        setItem.set(dynamicItems, staticItems);
        GameConstant.dynamicItemsCount = dynamicItems.size();
        mapEndGame = new MapEndGame(game, dynamicItems, staticItems, mainEndStory, mainMenuScreen);
        mainEndStory = new MainEndStory(game, dynamicItems, staticItems, mainMenuScreen);
        managerMovement = new ManagerMovement();
        handleInput = new HandleInput();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if(dynamicItems.isEmpty()){
            game.setScreen(mainEndStory);
        }
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}