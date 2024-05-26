package com.mygdx.game.view.screens.endgame.DrawResult;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class Result {
    int result, totalScore, posX, posY;
    Texture score;
    public void drawResult(SpriteBatch batch, ArrayList<DynamicItem> dynamicItems, DrawText drawText, Texture A, Texture B, Texture C, Texture D, Texture F){
        posX = GameConstant.posScoreX;
        posY = GameConstant.posScoreY;
        result = GameConstant.dynamicItemsCount - dynamicItems.size();
        totalScore = result * 100 / GameConstant.dynamicItemsCount;
        drawText.drawStaticText(batch, "Total Score: " + totalScore + "%",550, 520, 0.5f);

        if(totalScore == 100) drawText.drawStaticText(batch, "You are the PERFECT SON. I love you, darling !!",160, 490, 0.5f);
        else drawText.drawStaticText(batch, "How ... how could I raise you this way ...?", 160, 490, 0.5f);

        drawText.drawStaticText(batch, "Hidden Items: " + result + " / " + GameConstant.dynamicItemsCount, 250, 450, 0.8f);

        if(totalScore == 0) drawText.drawStaticText(batch, "(Did you even try to hide stuff)", 250, 380, 0.5f );
        else if(totalScore == 100) drawText.drawStaticText(batch, "You have hidden everything", 250, 380, 0.5f );
        else drawText.drawStaticText(batch, "Some things haven't been hidden yet", 250, 380, 0.5f );

        if(result == GameConstant.dynamicItemsCount) score = A;
        else if(result >= 18) score = B;
        else if(result >= 10) score = C;
        else if(result >= 1) score = D;
        else score = F;

        batch.draw(score, posX, posY, GameConstant.SCORE_WIDTH, GameConstant.SCORE_WIDTH);

        drawText.drawStaticText(batch,  "ESC - Restart Game", GameConstant.WINDOW_WIDTH/2 - 80, 80, 0.5f);

    }

}
