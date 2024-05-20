package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.controller.item.setup.SetItem;
import com.mygdx.game.controller.player.PlayerMovement;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.*;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.draw.ui.MakeAlert;
import com.mygdx.game.view.screens.Impression;
import com.mygdx.game.view.ui.*;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    SpaceGame game;
    DrawText drawText;
    float stateTime;
    SpriteBatch batch;
    ArrayList<StaticItem> staticItems;
    ArrayList<DynamicItem> dynamicItems;
    MakeAlert makeAlert;
    private final Player player;
    NewButton newButton;
    ManagerGame managerGame;
    SetItem setItem;

    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        String[] animationNames = new String[]{"WALKING_UP", "WALKING_DOWN", "WALKING_LEFT", "WALKING_RIGHT",
                "HOLDING_WALKING_UP", "HOLDING_WALKING_DOWN", "HOLDING_WALKING_LEFT", "HOLDING_WALKING_RIGHT",
                "MOPPING_FLOOR_UP", "MOPPING_FLOOR_DOWN", "MOPPING_FLOOR_LEFT", "MOPPING_FLOOR_RIGHT",
                "CLEANING_DISH"};
        String[] textureNames = new String[]{"IDLE_UP", "IDLE_DOWN", "IDLE_LEFT", "IDLE_RIGHT",
                "HOLDING_IDLE_UP", "HOLDING_IDLE_DOWN", "HOLDING_IDLE_LEFT", "HOLDING_IDLE_RIGHT"};

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("animations/Main_char_animations.atlas"));
        player = new Player(textureAtlas, animationNames, textureNames,
                500, 380,
                GameConstant.PLAYER_WIDTH, GameConstant.PLAYER_HEIGHT, 120);

        staticItems = new ArrayList<>();
        dynamicItems = new ArrayList<>();;
        makeAlert = new MakeAlert();
        setItem = new SetItem();
        managerGame = new ManagerGame(game);
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        newButton = new NewButton(game);
    }
    @Override
    public void show() {
        player.setValidThrow(true);
        setItem.set(dynamicItems, staticItems);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        managerGame.update(player, dynamicItems, staticItems, batch, stateTime);
        if(!newButton.isPause) {
            stateTime += delta;
            PlayerMovement.move(player, staticItems, dynamicItems, stateTime);
        }
        makeAlert.update(batch, stateTime, player);
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