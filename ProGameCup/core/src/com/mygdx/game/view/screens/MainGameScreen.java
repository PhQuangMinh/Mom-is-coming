package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.CheckCollision;
import com.mygdx.game.controller.item.*;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.uiingame.Holding;
import com.mygdx.game.view.uiingame.MakeAlert;

import java.util.ArrayList;

public class MainGameScreen implements Screen {

    float speed = 120;
    SpaceGame game;
    Texture walk;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapObjects mapObjects;
    float stateTime;
    SpriteBatch batch;
    BitmapFont letterFont;
    SetStaticItem setStaticItem;
    SetDynamicItem setDynamicItem;
    Draw draw;
    ArrayList<StaticItem> staticItems;
    ArrayList<DynamicItem> dynamicItems;
    CheckCollision checkCollision;
    ThrowItem throwItem;
    Holding holding;
    MakeAlert makeAlert;
    float firstValue = -1;
    GetItem getItem;

    private final Player player;
    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("move.png");
        player = new Player(walk, GameConstant.windowHeight/2, GameConstant.windowWidth/2/1.2f
                , GameConstant.playerWidth, GameConstant.playerHeight, speed);
        letterFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        setStaticItem = new SetStaticItem();

        draw = new Draw();
        staticItems = new ArrayList<>();
        checkCollision = new CheckCollision();
        setDynamicItem = new SetDynamicItem();
        dynamicItems = new ArrayList<>();
        throwItem = new ThrowItem();
        holding = new Holding();
        makeAlert = new MakeAlert();
        getItem = new GetItem();
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
        letterFont.setColor(Color.ORANGE);
        letterFont.getData().setScale(0.7f);

        setStaticItem.setStatic(staticItems);
        setDynamicItem.setDynamic(dynamicItems);
        player.setValidThrow(true);
        player.setStatusHold(1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += delta;

        int minutes = 10;
        int seconds = 0;
        float countdownTime = minutes * 60 + seconds;
        float timeLeft = countdownTime - stateTime;
        if(timeLeft <= 0){
            game.setScreen(new MainMenuScreen(game));
        }
        int remainMinutes = (int)(timeLeft / 60);
        int remainSeconds = (int)(timeLeft % 60);
        stateTime += delta;

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            game.setScreen(new MainMenuScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new MainMenuScreen(game));
        }
        renderer.setView(camera);
        renderer.render();
        player.update(mapObjects, staticItems, dynamicItems);

        if (player.getItemHolding()==null){
            if (player.getContainer()==null || player.getContainer().getNumber()==0)
                player.setStatusHold(1);
            else player.setStatusHold(3);
        }
        else player.setStatusHold(2);
        batch.begin();
        getItem.takeItemStatic(player, dynamicItems);
        holding.drawHold(batch, player);
        throwItem.updatePosition(dynamicItems, staticItems, player);
        player.setOverlap(checkCollision.checkFull(staticItems, player));
        draw.draw(dynamicItems, staticItems, player, batch, stateTime);
        if (!player.isValidThrow()){
            if (firstValue == -1) firstValue = stateTime;
            makeAlert.drawAlert(batch, firstValue, stateTime, player);
            if (stateTime - firstValue > 2){
                player.setValidThrow(true);
                firstValue = -1;
            }
        }
        letterFont.draw(batch, "end game - E", 10, 35);
        letterFont.draw(batch, "stop - S", GameConstant.windowWidth - 200, 35);
        letterFont.draw(batch,String.format("%02d", remainMinutes) + ":" + String.format("%02d", remainSeconds), GameConstant.windowHeight - 150, 820 );
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, GameConstant.windowWidth, GameConstant.windowHeight);

        camera.position.set(GameConstant.mapWidth/2, GameConstant.mapHeight/2/1.2f, 0);

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
        walk.dispose();
        letterFont.dispose();
    }
}