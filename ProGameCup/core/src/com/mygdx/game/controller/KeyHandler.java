package com.mygdx.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyHandler implements InputProcessor {
    //Singleton
    public static KeyHandler keyHandler;
    private boolean up, down, left, right, shift, space, enter, esc;

    private KeyHandler() {
    }

    public static KeyHandler getInstance() {
        if (keyHandler == null) {
            keyHandler = new KeyHandler();
        }
        return keyHandler;
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SHIFT_LEFT) {
            shift = true;
        }
        if (keycode == Input.Keys.W) {
            up = true;
        }
        if (keycode == Input.Keys.S) {
            down = true;
        }
        if (keycode == Input.Keys.A) {
            left = true;
        }
        if (keycode == Input.Keys.D) {
            right = true;
        }
        if (keycode == Input.Keys.SPACE) {
            space = true;
        }
        if (keycode == Input.Keys.ENTER) {
            enter = true;
        }
        if (keycode == Input.Keys.ESCAPE) {
            esc = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SHIFT_LEFT) {
            shift = false;
        }
        if (keycode == Input.Keys.W) {
            up = false;
        }
        if (keycode == Input.Keys.S) {
            down = false;
        }
        if (keycode == Input.Keys.A) {
            left = false;
        }
        if (keycode == Input.Keys.D) {
            right = false;
        }
        if (keycode == Input.Keys.SPACE) {
            space = false;
        }
        if (keycode == Input.Keys.ENTER) {
            enter = false;
        }
        if (keycode == Input.Keys.ESCAPE) {
            esc = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isShift() {
        return shift;
    }

    public boolean isSpace() {
        return space;
    }

    public boolean isEnter() {
        return enter;
    }

    public boolean isEsc() {
        return esc;
    }
}