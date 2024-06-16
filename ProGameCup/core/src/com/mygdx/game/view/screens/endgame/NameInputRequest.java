package com.mygdx.game.view.screens.endgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.view.screens.maingame.MainGameScreen;
import com.mygdx.game.view.screens.mainmenu.Leaderboard;

public class NameInputRequest {
    public static boolean nameInputDialogOpen = false;
    Stage stage;
    TextField textField;
    Texture dialog, submit, cancel;
    int BUTTON_WIDTH = 127, BUTTON_HEIGHT = 50;
    Leaderboard leaderboard;

    public NameInputRequest() {
        leaderboard = new Leaderboard();
        dialog = new Texture(Gdx.files.internal("otherImage/congratulation.png"));
        submit = new Texture(Gdx.files.internal("otherImage/submit_button.png"));
        cancel = new Texture(Gdx.files.internal("otherImage/cancel_button.png"));

        stage = new Stage(new ScreenViewport());
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont(Gdx.files.internal("fonts/char.fnt"));
        style.font.getData().setScale(0.5f);
        style.fontColor = Color.BLACK;

        textField = new TextField("Type your name here!", style);
        textField.setBounds(345, 410, 550, 50);
        textField.setDisabled(false);
        textField.setBlinkTime(0.2f);
        textField.setMaxLength(20);

        stage.addActor(textField);
        Gdx.input.setInputProcessor(stage);
    }

    private boolean isButtonClicked(int x, int y){
        if(!Gdx.input.isTouched())
            return false;

        int fx = Gdx.input.getX();
        int fy = 960 - Gdx.input.getY();
        return (fx >= x && fx <= x + BUTTON_WIDTH) && (fy >= y && fy <= y + BUTTON_HEIGHT);
    }

    public void render(Batch batch, float delta){
        batch.draw(dialog, 0, 0, 960, 960);
        batch.draw(submit, 342, 290, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(cancel, 492, 290, BUTTON_WIDTH, BUTTON_HEIGHT);

        if(isButtonClicked(342, 290)){ // submit button clicked
            int min = (int) (MainGameScreen.stateTime / 60);
            int sec = (int) (MainGameScreen.stateTime % 60);

            String name = textField.getText();
            try{
                leaderboard.add(name, MainGameScreen.dynamicItemHidden, min, sec);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            nameInputDialogOpen = false;
        }
        if(isButtonClicked(492, 290)){ // submit button clicked
            nameInputDialogOpen = false;
        }
        stage.draw();
        stage.act();
    }
}
