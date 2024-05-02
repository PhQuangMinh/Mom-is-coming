package com.mygdx.game.tiledMapGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;

public class Play implements Screen {

    private float screenWidth, screenHeight;
    private float mapWidth, mapHeight;
    private float mapPosX, mapPosY;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    SpaceGame game;
    public Play(SpaceGame game){
        this.game = game;
        show();

    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        screenWidth = SpaceGame.WINDOW_WIDTH;
        screenHeight = SpaceGame.WINDOW_HEIGHT;

        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        mapPosX = (screenWidth - mapWidth) / 2;
        mapPosY = (screenHeight - mapHeight) / 2;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.zoom = 1.2f; // Thiết lập tỉ lệ zoom mặc định
        float aspectRatio = (float) width / (float) height;
        camera.setToOrtho(false);
        camera.viewportWidth = mapWidth * camera.zoom * aspectRatio;
        camera.viewportHeight = mapHeight * camera.zoom;

        // Tính toán vị trí mới của camera sao cho bản đồ nằm ở giữa màn hình
        float cameraPosX = mapPosX + mapWidth / 2 - camera.viewportWidth / 2;
        float cameraPosY = mapPosY + mapHeight / 2 - camera.viewportHeight / 2;

        // Đặt lại vị trí của camera
        camera.position.set(cameraPosX, cameraPosY, 0);

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
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
