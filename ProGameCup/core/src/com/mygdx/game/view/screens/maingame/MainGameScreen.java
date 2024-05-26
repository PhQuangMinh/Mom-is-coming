package com.mygdx.game.view.screens.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.draw.Draw;
import com.mygdx.game.controller.item.setup.SetItem;
import com.mygdx.game.controller.item.setup.SetRemainItem;
import com.mygdx.game.controller.player.PlayerMovement;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.*;
import com.mygdx.game.view.screens.endgame.MainEndStory;
import com.mygdx.game.view.ui.*;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    SpaceGame game;
    DrawText drawText;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapObjects mapObjects;
    float stateTime;
    SpriteBatch batch;
    ArrayList<StaticItem> staticItems;
    ArrayList<DynamicItem> dynamicItems;
    MakeAlert makeAlert;
    private final Player player;
    NewButton newButton;
    ManagerGame managerGame;
    SetItem setItem;
    SetRemainItem setRemainItem;
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
                GameConstant.windowHeight/2 + 50, GameConstant.windowHeight/2+ 50,
                GameConstant.playerWidth, GameConstant.playerHeight, 120);

        staticItems = new ArrayList<>();
        dynamicItems = new ArrayList<>();;
        makeAlert = new MakeAlert();
        setItem = new SetItem();
        managerGame = new ManagerGame(game);
        drawText = new DrawText("fonts/char.fnt", Color.ORANGE);
        newButton = new NewButton(game);
        setRemainItem = new SetRemainItem();
    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        GameConstant.mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        GameConstant.mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
        mapObjects = map.getLayers().get(3).getObjects();
        player.setValidThrow(true);
        setItem.set(dynamicItems, staticItems);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        if(!newButton.isPause) {
            stateTime += delta;
            PlayerMovement.move(player, mapObjects, staticItems, dynamicItems, stateTime);
        }
        batch.begin();
        managerGame.update(player, dynamicItems, staticItems, batch, stateTime);
        makeAlert.update(batch, stateTime, player);
        if(dynamicItems.size() == 0){
            game.setScreen(new MainEndStory(game, dynamicItems));
        }
        batch.end();

    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, GameConstant.windowWidth, GameConstant.windowHeight);

        camera.position.set(GameConstant.mapWidth/2, GameConstant.mapHeight/2, 0);

        camera.update();
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
        renderer.dispose();
        batch.dispose();
    }
}