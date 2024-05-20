package com.mygdx.game.view.ui.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.common.constant.GameConstant;

public class Button extends Sprite {
    private Texture button;
    private Texture pressedButton;
    private boolean hovered;
    private boolean pressed;
    private int function;
    public boolean isPause = false;
    public boolean isStopMusic = false;
    public static boolean isHowToPlayOpen = false;
    public static boolean isMenuBarOpen = false;

    public Button(String path, String pathPress, int x, int y, int width, int height, int function){
        this.function = function;
        button = new Texture("button/" + path + ".png");
        pressedButton = new Texture("button/" + pathPress + ".png");
        setX(x);
        setY(y);
        setSize(width, height);
    }

    public int getFunction() {
        return function;
    }
    private boolean checkHovered(){
        return Gdx.input.getX() >= getX() && Gdx.input.getX() <= getX() + getWidth()
                && GameConstant.windowHeight-Gdx.input.getY() >= getY()
                && GameConstant.windowHeight-Gdx.input.getY() <= getY() + getHeight();
    }

    public void update(){
        hovered = checkHovered();
        pressed = hovered && Gdx.input.isTouched();
    }

    public void draw(SpriteBatch batch){
        if (hovered)
            batch.draw(pressedButton, getX(), getY(), getWidth(), getHeight());
        else batch.draw(button, getX(), getY(), getWidth(), getHeight());
    }

    public Texture getButton() {
        return button;
    }

    public void setButton(Texture button) {
        this.button = button;
    }

    public Texture getPressedButton() {
        return pressedButton;
    }

    public void setPressedButton(Texture pressedButton) {
        this.pressedButton = pressedButton;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
