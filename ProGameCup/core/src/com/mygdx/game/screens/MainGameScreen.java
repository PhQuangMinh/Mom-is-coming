
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Character;
import com.mygdx.game.SpaceGame;

public class MainGameScreen implements Screen {

    float speed = 120;
    Character character;
    SpaceGame game;
    Texture walk, ch;
    float x = (float) SpaceGame.WINDOW_HEIGHT /2;
    float y = (float) SpaceGame.WINDOW_WIDTH /2;
    int roll;
    float stateTime;
    SpriteBatch batch;
    Animation[] rolls;
    public MainGameScreen (SpaceGame game){
        this.game = game;
        batch = game.getBatch();
        walk = new Texture("walk.png");
        ch = new Texture("character/example.png");
        character = new Character(speed);
        character.setTexture(ch);
        roll = 0;
        rolls = new Animation[10];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(walk, 16, 20);
        rolls[0] = new Animation(0.2f, rollSpriteSheet[0]);
        rolls[1] = new Animation(0.2f, rollSpriteSheet[1]);
        rolls[2] = new Animation(0.2f, rollSpriteSheet[2]);
        rolls[3] = new Animation(0.2f, rollSpriteSheet[3]);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.113f, 0.102f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//            y += speed * Gdx.graphics.getDeltaTime();
//            roll = 3;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            y -= speed * Gdx.graphics.getDeltaTime();
//            roll = 0;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            x -= speed * Gdx.graphics.getDeltaTime();
//            roll = 1;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            x += speed * Gdx.graphics.getDeltaTime();
//            roll = 2;
//        }
//        stateTime += delta;
        batch.begin();
        character.draw(batch);
        character.update();
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
