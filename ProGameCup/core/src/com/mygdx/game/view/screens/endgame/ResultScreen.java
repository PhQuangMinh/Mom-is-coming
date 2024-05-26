package com.mygdx.game.view.screens.endgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;
import com.mygdx.game.view.screens.endgame.DrawResult.Result;
import com.mygdx.game.view.screens.maingame.MainGameScreen;

import java.util.ArrayList;

public class ResultScreen implements Screen {
    ArrayList<DynamicItem> dynamicItems;
    SpaceGame game;
    SpriteBatch batch;
    Texture endGame, A, B, C, D, F;
    Result result;
    DrawText drawText;

    public ResultScreen(SpaceGame game, ArrayList<DynamicItem> dynamicItems){
        this.game = game;
        this.batch = game.getBatch();
        this.dynamicItems = dynamicItems;

        result = new Result();
        drawText = new DrawText("fonts/char.fnt", Color.BLACK);

    }

    @Override
    public void show() {

        endGame = new Texture("endgame/endgame.png");
        A = new Texture("endgame/score/A.png");
        B = new Texture("endgame/score/B.png");
        C = new Texture("endgame/score/C.png");
        D = new Texture("endgame/score/D.png");
        F = new Texture("endgame/score/F.png");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(new MainGameScreen(game));
        batch.begin();
        batch.draw(endGame, 50, 50, GameConstant.WINDOW_WIDTH - 100, GameConstant.WINDOW_HEIGHT - 100);
        result.drawResult(batch, dynamicItems, drawText, A, B, C, D, F);
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
