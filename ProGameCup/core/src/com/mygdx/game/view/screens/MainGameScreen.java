package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.mygdx.game.controller.item.DrawItems;
import com.mygdx.game.controller.item.SetUpItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.DrawText;
import com.mygdx.game.view.NewButton;
import com.mygdx.game.view.music.PlaySound;

import javax.swing.plaf.basic.BasicButtonUI;
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
    SetUpItem setUpItem;
    DrawItems drawItems;
    BitmapFont letterFont;
    ArrayList<StaticItem> staticItems;

    CheckCollision checkCollision;

    private final Player player;

    NewButton newButton;
    PlaySound playSound;
    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("move.png");
        player = new Player(walk, GameConstant.windowHeight/2, GameConstant.windowWidth/2
                , GameConstant.playerWidth, GameConstant.playerHeight, speed);
        setUpItem = new SetUpItem();
        drawText = new DrawText();
        drawItems = new DrawItems();
        staticItems = new ArrayList<>();
        checkCollision = new CheckCollision();
        newButton = new NewButton(game);
        playSound = PlaySound.getInstance(batch);
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

        setUpItem.setUpItems(staticItems);
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

        if(newButton.isStopMusic) playSound.stopMusic();
        else playSound.playMusic();
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            game.setScreen(new MainMenuScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new MainMenuScreen(game));
        }

        renderer.setView(camera);
        renderer.render();
        batch.begin();
        if(!newButton.isPause) {
            stateTime += delta;
            player.update(mapObjects, staticItems);
        }
        player.setOverlap(checkCollision.checkFull(staticItems, player));
        if (player.getOverlap()){
            player.draw(batch, stateTime);
            drawItems.drawItems(staticItems, batch, player);
        }
        else{
            drawItems.drawItems(staticItems, batch, player);
            player.draw(batch, stateTime);
        }
        newButton.drawMusicButton(musicOn, musicOff, (int)GameConstant.windowWidth - 70, 800, GameConstant.iconWidth, GameConstant.iconHeight);
        newButton.drawButton(home,homePress, (int)GameConstant.windowWidth - 125, 800, GameConstant.iconWidth, GameConstant.iconHeight, 5);
        newButton.drawButton(replay, replayPress, (int)GameConstant.windowWidth - 180, 800,GameConstant.iconWidth, GameConstant.iconHeight, 1);
        newButton.drawPauseButton(resume, pause, (int)GameConstant.windowWidth - 235, 800, GameConstant.iconWidth, GameConstant.iconHeight);

        drawText.drawClock(game, batch, stateTime, 10, 0, 360, 800, 1.2f);
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
    }
}