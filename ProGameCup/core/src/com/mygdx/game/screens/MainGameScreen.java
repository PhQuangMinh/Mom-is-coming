package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    int roll;
    private float mapWidth, mapHeight;
    private OrthogonalTiledMapRenderer renderer;
    Character character;
    private OrthographicCamera camera;
    float stateTime;
    SpriteBatch batch;

    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("move.png");
        character = new Character(walk, x, y, speed);
    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += delta;

        renderer.setView(camera);
        renderer.render();

        batch.begin();
        character.draw(batch, stateTime);
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
//package com.mygdx.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.mygdx.game.SpaceGame;
//import com.mygdx.game.model.Character;
//
//public class MainGameScreen implements Screen {
//    private final float FRAME_TIME = 1/15f;
//
//    private final float speed = 120f;
//    private float elapsedTime;
//    private Animation<TextureRegion> run;
//    private final SpaceGame game;
//
//    private Animation[] walks;
//
//    private float x = 200, y = 200;
//
//    private final SpriteBatch batch;
//    Texture pl;
//    Character player;
//    int roll;
//    public MainGameScreen(SpaceGame game){
//        this.game = game;
//        this.batch = game.getBatch();
//
//    }
//    @Override
//    public void show() {
//        pl = new Texture("character/example.png");
//
//        player = new Character();
//        float x = (float) SpaceGame.WINDOW_WIDTH / 2;
//        float y = (float) SpaceGame.WINDOW_HEIGHT / 2;
//        player.setPosition(x, y);
//        player.setTexture(pl);
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0.1f, 0.5f, 0.2f, 0.5f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//        player.draw(batch);
//        batch.end();
//
//        player.update();
//
////        TextureRegion[][] walk = TextureRegion.split(new Texture("walk.png"), 1, 1);
////        elapsedTime += delta;
////        TextureRegion currentFrame = run.getKeyFrame(elapsedTime, true);
////
////        Gdx.gl.glClearColor(0.0f, 0, 0.0f, 1f);
////        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
////
////        batch.begin();
////        batch.draw(currentFrame, 100, 100, currentFrame.getRegionWidth()/3f, currentFrame.getRegionHeight()/3f);
////        batch.end();
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
