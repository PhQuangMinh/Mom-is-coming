package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.*;
import com.mygdx.game.controller.item.*;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.*;
import com.mygdx.game.view.uiingame.*;

import java.util.ArrayList;

public class MainGameScreen implements Screen {
    Texture resume, pause, home, homePress, replay, replayPress, musicOn, musicOff;
    SpaceGame game;
    DrawText drawText;
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

        if(player.getStatusHold() == 4);
        else if(player.getItemHolding()==null){
            if (player.getContainer()==null || player.getContainer().getNumber()==0)
                player.setStatusHold(1);
            else player.setStatusHold(3);
        }
        else player.setStatusHold(2);

        if(!newButton.isPause) {
            stateTime += delta;
            PlayerMovement.move(player, mapObjects, staticItems, dynamicItems, stateTime);
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
    }
}