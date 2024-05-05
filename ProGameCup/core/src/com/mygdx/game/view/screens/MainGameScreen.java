package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.controller.CheckCollision;

public class MainGameScreen implements Screen {

    float speed = 120;
    SpaceGame game;
    Texture walk;
    Vector2 position, oldPosition;
    int roll;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private MapObjects mapObjects;
    float stateTime;
    SpriteBatch batch;
    Animation[] rolls;
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

        position = new Vector2(0.0f, 0.0f);
    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        mapObjects = map.getLayers().get(3).getObjects();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += delta;

        updatePosition();

        renderer.setView(camera);
        renderer.render();

        batch.begin();
        batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), position.x
                , position.y, GameConstant.playerWidth, GameConstant.playerHeight);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, GameConstant.windowWidth, GameConstant.windowHeight);

        camera.position.set(GameConstant.mapWidth/2, GameConstant.mapHeight/2, 0);
        camera.update();
    }

    private void updatePosition(){
        oldPosition = new Vector2(position.x, position.y);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed * Gdx.graphics.getDeltaTime();
            roll = 3;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed * Gdx.graphics.getDeltaTime();
            roll = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed * Gdx.graphics.getDeltaTime();
            roll = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed * Gdx.graphics.getDeltaTime();
            roll = 2;
        }

        CheckCollision checkCollision = new CheckCollision();
        position = checkCollision.updatePosition(position, oldPosition, mapObjects);
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