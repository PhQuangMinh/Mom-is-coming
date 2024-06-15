package com.mygdx.game.view.screens.endgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.MapConstant;
import com.mygdx.game.controller.MakeSize;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.model.item.StaticItem;
import com.mygdx.game.view.draw.screengame.DrawEndGame;
import com.mygdx.game.view.draw.map.DrawMap;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.screens.endgame.DrawMom.Mom;
import com.mygdx.game.view.screens.mainmenu.MainMenuScreen;
import com.mygdx.game.view.screens.mainstory.MainStory;

import java.util.ArrayList;

public class MapEndGame implements Screen {
    ArrayList<StaticItem> staticItems;
    ArrayList<DynamicItem> dynamicItems;
    SpaceGame game;
    SpriteBatch batch;
    Mom mother;
    DrawEndGame drawEndGame;
    Texture player, mom, chat;
    DrawMap drawMap;
    MainEndStory mainEndStory;
    ResultScreen resultScreen;
    DrawText drawText;
    MakeSize makeSize;
    public MapEndGame(SpaceGame game, ArrayList<DynamicItem> dynamicItems
            , ArrayList<StaticItem> staticItems, MainEndStory mainEndStory, MainMenuScreen mainMenuScreen){
        this.game = game;
        this.dynamicItems = dynamicItems;
        this.staticItems = staticItems;
        this.mainEndStory = mainEndStory;
        batch = game.getBatch();
        player = new Texture("animations/main-char1/idle-endgame.png");
        mom = new Texture("animations/mom/mom-walking.png");
        chat = new Texture("alert/note.png");
        drawMap = new DrawMap();
        drawEndGame = new DrawEndGame(game);
        drawText = new DrawText("fonts/char.fnt", Color.BLACK, mainEndStory);
        resultScreen = new ResultScreen(game, dynamicItems, mainEndStory, mainMenuScreen);
        mother = new Mom(mom, game, dynamicItems, resultScreen, drawText, drawMap);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawMap.drawMap(batch);
        drawEndGame.drawEndGame(dynamicItems, staticItems, batch);
        batch.draw(player, MapConstant.POS_MAP_Y + 150, MapConstant.POS_MAP_Y + 230,
                32, 58);
        mother.draw(chat,game, batch, delta);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
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
