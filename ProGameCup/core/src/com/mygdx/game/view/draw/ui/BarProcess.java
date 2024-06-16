package com.mygdx.game.view.draw.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.common.constant.CharacterStatus;
import com.mygdx.game.common.constant.GameConstant;
import com.mygdx.game.model.Player;

public class BarProcess {
    private static final Texture loading_bar_background = new Texture("otherImage/loading_bar_background.png");
    private static final Texture loading_bar_progress = new Texture("otherImage/loading_bar_progress.png");
    private static final TextureRegion loading_bar_start = new TextureRegion(loading_bar_progress, 0, 0, 15, 32);
    private static final TextureRegion loading_bar_body = new TextureRegion(loading_bar_progress, 15, 0, 226, 32);
    private static final TextureRegion loading_bar_end = new TextureRegion(loading_bar_progress, 241, 0, 15, 32);

    float x, y;

    float ratio = GameConstant.LOADING_BAR_RATIO;
    private void updateXY(Player player, CharacterStatus status){
        if(status == CharacterStatus.MOPPING_FLOOR){
            x = player.getItemInRange().getX() + (player.getItemInRange().getWidth() -
                    loading_bar_background.getWidth() * ratio)/2;
            y = player.getItemInRange().getY() + 70;
        }
        else{
            x = player.getContainer().getX() + (player.getContainer().getWidth() -
                    loading_bar_background.getWidth() * ratio)/2;
            y = player.getContainer().getY() + 70;
        }
    }

    public void drawBars(Player player, CharacterStatus status, Batch batch){
        updateXY(player, status);
        batch.draw(loading_bar_background, x, y, loading_bar_background.getWidth() * ratio
                , loading_bar_background.getHeight() * ratio);

        batch.draw(loading_bar_start, x, y, loading_bar_start.getRegionWidth() * ratio
                , loading_bar_start.getRegionHeight() * ratio);

        x += loading_bar_start.getRegionWidth() * ratio;
        batch.draw(loading_bar_body, x, y, loading_bar_body.getRegionWidth() * ratio * player.getItemHolding().getActionCount() / 12
                , loading_bar_start.getRegionHeight() * ratio);

        x += loading_bar_body.getRegionWidth() * ratio * player.getItemHolding().getActionCount() / 12;
        batch.draw(loading_bar_end, x, y,
                loading_bar_end.getRegionWidth() * ratio, loading_bar_end.getRegionHeight() * ratio);
    }
}
