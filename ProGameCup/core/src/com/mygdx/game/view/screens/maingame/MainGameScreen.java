package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.controller.handleinput.HandleInput;
import com.mygdx.game.controller.item.setup.SetItem;
import com.mygdx.game.controller.player.ManagerMovement;
import com.mygdx.game.model.item.*;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.MakeAlert;
import com.mygdx.game.view.draw.ui.NewButton;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.screens.maingame.singleplayer.ManagerSingle;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    protected SpaceGame game;
    protected DrawText drawText;
    protected float stateTime;
    protected SpriteBatch batch;
    protected ArrayList<StaticItem> staticItems;
    protected ArrayList<DynamicItem> dynamicItems;
    protected MakeAlert makeAlert;
    protected NewButton newButton;
    protected SetItem setItem;
    protected MainMenuScreen mainMenuScreen;
    protected MainStory mainStory;

    protected MainEndStory mainEndStory;

    protected ManagerMovement managerMovement;

    protected HandleInput handleInput;

    protected String[] animationNames = new String[]{"WALKING_UP", "WALKING_DOWN", "WALKING_LEFT", "WALKING_RIGHT",
            "HOLDING_WALKING_UP", "HOLDING_WALKING_DOWN", "HOLDING_WALKING_LEFT", "HOLDING_WALKING_RIGHT",
            "MOPPING_FLOOR_UP", "MOPPING_FLOOR_DOWN", "MOPPING_FLOOR_LEFT", "MOPPING_FLOOR_RIGHT",
            "CLEANING_DISH"};
    protected String[] textureNames = new String[]{"IDLE_UP", "IDLE_DOWN", "IDLE_LEFT", "IDLE_RIGHT",
            "HOLDING_IDLE_UP", "HOLDING_IDLE_DOWN", "HOLDING_IDLE_LEFT", "HOLDING_IDLE_RIGHT"};


    public MainGameScreen (SpaceGame game, MainMenuScreen mainMenuScreen, MainStory mainStory){
        this.mainMenuScreen = mainMenuScreen;
        this.mainStory = mainStory;
        this.game = game;
        batch = game.getBatch();
        staticItems = new ArrayList<>();
        dynamicItems = new ArrayList<>();;
        makeAlert = new MakeAlert();
        setItem = new SetItem();
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        newButton = new NewButton(game);
    }
    @Override
    public void show() {
        dynamicItems.clear();
        staticItems.clear();
        setItem.set(dynamicItems, staticItems);
        mainEndStory = new MainEndStory(game, dynamicItems, mainStory, staticItems);
        managerMovement = new ManagerMovement();
        handleInput = new HandleInput();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setColor(1 ,1, 1, 1);
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