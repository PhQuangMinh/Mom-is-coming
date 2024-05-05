
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

public class MainGameScreen implements Screen {

    float speed = 120;
    SpaceGame game;
    Texture walk;
    float x= (float) SpaceGame.WINDOW_HEIGHT /2;
    float y= (float) SpaceGame.WINDOW_WIDTH /2;
    int roll;
    private float mapWidth, mapHeight;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    float stateTime;
    SpriteBatch batch;
    Animation[] rolls;
    BitmapFont letterFont;
    private int minutes = 2;
    private int seconds = 0;
    private float countdownTime = minutes * 60 + seconds;
    private float eslapedTime = 0;
    String letter;

    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("walk.png");
        roll = 0;
        rolls = new Animation[10];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(walk, 16, 20);
        rolls[0] = new Animation(0.2f, rollSpriteSheet[0]);
        rolls[1] = new Animation(0.2f, rollSpriteSheet[1]);
        rolls[2] = new Animation(0.2f, rollSpriteSheet[2]);
        rolls[3] = new Animation(0.2f, rollSpriteSheet[3]);

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

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * Gdx.graphics.getDeltaTime();
            roll = 3;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * Gdx.graphics.getDeltaTime();
            roll = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed * Gdx.graphics.getDeltaTime();
            roll = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed * Gdx.graphics.getDeltaTime();
            roll = 2;
        }
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

        letterFont.draw(batch, "end game - E", 10, 35);
        letterFont.draw(batch, "stop - S", SpaceGame.WINDOW_WIDTH - 200, 35);
        letterFont.draw(batch,String.format("%02d", remainMinutes) + ":" + String.format("%02d", remainSeconds), SpaceGame.WINDOW_WIDTH - 150, 820 );
        batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), x, y, 40, 40);
        batch.end();
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
