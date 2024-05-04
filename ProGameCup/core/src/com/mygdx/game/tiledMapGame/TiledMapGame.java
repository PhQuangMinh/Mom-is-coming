package com.mygdx.game.tiledMapGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.tiledMapGame.screens.Play;

public class TiledMapGame extends Game {

    SpaceGame game;
    public TiledMapGame(){

    }

    @Override
    public void create() {
//        setScreen(new Play());
    }
    @Override
    public void dispose(){
        super.dispose();
    }
    @Override
    public void render(){
        super.render();
    }
    @Override
    public void resize(int width, int height){
        super.resize(width,height);
    }
    @Override
    public void pause(){
        super.pause();
    }
    @Override
    public void resume(){
        super.resume();
    }

}
