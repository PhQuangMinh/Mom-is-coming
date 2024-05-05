package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.model.Character;

public class MainGameScreen implements Screen {

    float speed = 120;
    SpaceGame game;
    Texture walk;
    float x= (float) SpaceGame.WINDOW_HEIGHT /2;
    float y= (float) SpaceGame.WINDOW_WIDTH /2;
    private float mapWidth, mapHeight;
    private OrthogonalTiledMapRenderer renderer;
    Character character;
    private OrthographicCamera camera;
    float stateTime;
    SpriteBatch batch;

    BitmapFont letterFont;
    private int minutes = 2;
    private int seconds = 0;
    private float countdownTime = minutes * 60 + seconds;
    private float eslapedTime = 0;
    String letter;

    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("move.png");
        character = new Character(walk, x, y, speed);
        letterFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        letterFont.setColor(Color.ORANGE);
        letterFont.getData().setScale(0.7f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        eslapedTime += delta;
        float timeLeft = countdownTime - eslapedTime;
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

        batch.begin();
        character.draw(batch, stateTime);
        letterFont.draw(batch, "end game - E", 10, 35);
        letterFont.draw(batch, "stop - S", SpaceGame.WINDOW_WIDTH - 200, 35);
        letterFont.draw(batch,String.format("%02d", remainMinutes) + ":" + String.format("%02d", remainSeconds), SpaceGame.WINDOW_WIDTH - 150, 820 );
        batch.end();

        character.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, SpaceGame.WINDOW_WIDTH, SpaceGame.WINDOW_HEIGHT);

        camera.position.set(mapWidth/2, mapHeight/2, 0);

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