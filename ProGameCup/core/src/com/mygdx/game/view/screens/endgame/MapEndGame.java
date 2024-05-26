package com.mygdx.game.view.screens.endgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.controller.item.setup.SetRemainItem;
import com.mygdx.game.controller.item.setup.SetStaticItem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.screens.endgame.DrawItems.Draw;
import com.mygdx.game.view.screens.endgame.DrawMom.Mom;
import com.mygdx.game.view.ui.DrawText;

import java.util.ArrayList;

public class MapEndGame implements Screen {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapObjects mapObjects;
    ArrayList<StaticItem> staticItems;
    ArrayList<DynamicItem> dynamicItems;
    SetRemainItem setRemainItem;
    SetStaticItem setStaticItem;
    SpaceGame game;
    SpriteBatch batch;
    Mom mother;
    Draw draw;
    Texture player, mom, chat;
    float stateTime;
    public MapEndGame(SpaceGame game, ArrayList<DynamicItem> dynamicItems){
        this.game = game;
        batch = game.getBatch();
        this.dynamicItems = dynamicItems;
        setStaticItem = new SetStaticItem();
        staticItems = new ArrayList<>();

        draw = new Draw();
    }
    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Texture("animations/main-char/idle-endgame.png");
        mom = new Texture("animations/mom/mom-walking.png");
        chat = new Texture("alert/note.png");

        GameConstant.mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        GameConstant.mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
        mother = new Mom(mom, dynamicItems);
        setStaticItem.setStatic(staticItems);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

        batch.begin();
        draw.draw(dynamicItems, staticItems, batch);
        batch.draw(player, GameConstant.posMapX + 150, GameConstant.posMapY + 220,
                32, 58);
        mother.draw(chat,game, batch, delta);
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
