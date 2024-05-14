package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.view.DrawText;
import com.mygdx.game.view.NewButton;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    Texture resume, pause, home, homePress, replay, replayPress, musicOn, musicOff;
    float speed = 120;
    SpaceGame game;
    DrawText drawText;
    Texture walk;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private MapObjects mapObjects;
    float stateTime;
    SpriteBatch batch;
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

    NewButton newButton;
    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("move.png");
        player = new Player(walk, GameConstant.windowHeight/2 + 50, GameConstant.windowWidth/2
                , GameConstant.playerWidth, GameConstant.playerHeight, speed);
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
        newButton = new NewButton(game);
//        playSound = PlaySound.getInstance(batch);
        drawText = new DrawText();
        createTexture();
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

        setStaticItem.setStatic(staticItems);
        setDynamicItem.setDynamic(dynamicItems);
        player.setValidThrow(true);
    }

    public void createTexture(){
        resume = new Texture("button/resume.png");
        pause = new Texture("button/pause.png");
        home = new Texture("button/home.png");
        homePress = new Texture("button/homePress.png");
        replay = new Texture("button/replay.png");
        replayPress = new Texture("button/replayPress.png");
        musicOn = new Texture("button/musicOn.png");
        musicOff = new Texture("button/musicOff.png");
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            game.setScreen(new MainMenuScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new MainMenuScreen(game));
        }

        renderer.setView(camera);
        renderer.render();

        if (player.getItemHolding()==null){
            if (player.getContainer()==null || player.getContainer().getNumber()==0)
                player.setStatusHold(1);
            else player.setStatusHold(3);
        }
        else player.setStatusHold(2);
        if(!newButton.isPause) {
            stateTime += delta;
            player.update(mapObjects, staticItems, dynamicItems);
        }
        batch.begin();
        batch.setColor(1, 1, 1, 1);
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
        newButton.drawMusicButton(musicOn, musicOff, (int)GameConstant.windowWidth - 70, 800, GameConstant.iconWidth, GameConstant.iconHeight);
        newButton.drawButton(home,homePress, (int)GameConstant.windowWidth - 125, 800, GameConstant.iconWidth, GameConstant.iconHeight, 5);
        newButton.drawButton(replay, replayPress, (int)GameConstant.windowWidth - 180, 800,GameConstant.iconWidth, GameConstant.iconHeight, 1);
        newButton.drawPauseButton(resume, pause, (int)GameConstant.windowWidth - 235, 800, GameConstant.iconWidth, GameConstant.iconHeight);

        drawText.drawClock(game, batch, stateTime, 10, 0, 360, 820, 1.2f);
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
    }
}