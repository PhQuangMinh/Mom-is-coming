package com.mygdx.game.view.draw.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.item.DynamicItem;
import com.mygdx.game.view.draw.text.DrawText;

import java.util.ArrayList;

public class DrawDynamic {
    private int incY = 80;

    private String getNoteItemHolding(DynamicItem item){
        if (item.getName().equals("dish")) return "You are holding a " + item.getName() + ". Press enter to clean it in dishwashing, throw it or put it into container.";
        if (item.isClothes()) return "You are holding a " + item.getName() + ". Press enter to\nclean puddle, throw it or put it into container.";
        return "You are holding a " + item.getName() + ". Press enter to\nthrow it or put it into container.";
    }

    private void drawDynamicItemSingle(DynamicItem item, SpriteBatch batch, Player player, DrawText drawText){
        if (!item.isVisible()) return;
        Texture image;
        if (player.getItemHolding()!=null && player.getItemHolding().equals(item)){
            image = item.getImage();
            drawText.drawNoteName(batch, drawText, getNoteItemHolding(item), 0);
        }
        else
        if (item.getDiscover() && item.getChosenImage()!=null){
            image = item.getChosenImage();
            if (item.getName().equals("puddle")){
                drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". You can't take it,\nuse clothe or tissue to clean it.", 0);
            } else {
                drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". Press enter to take it.", 0);
            }
        }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    private void drawDynamicItemEndGame(DynamicItem item, SpriteBatch batch){
        Texture image;
        image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }
    public void drawDynamicEndGame(ArrayList<DynamicItem> items, SpriteBatch batch){
        for (DynamicItem item : items) {
            drawDynamicItemEndGame(item, batch);
        }
    }

    public void drawDynamicSingle(ArrayList<DynamicItem> items, SpriteBatch batch, Player player, DrawText drawText){
        for (DynamicItem item : items) {
            drawDynamicItemSingle(item, batch, player, drawText);
        }
    }

    private String getNoteItemHoldingMulti(DynamicItem item, String buttonName){
        if (item.getName().equals("dish")) return "You are holding a " + item.getName() + ". Press " + buttonName + " to clean it in dishwashing, throw it or put it into container.";
        if (item.isClothes()) return "You are holding a " + item.getName() + ". Press " + buttonName + " to\nclean puddle, throw it or put it into container.";
        return "You are holding a " + item.getName() + ". Press " + buttonName + " to\nthrow it or put it into container.";
    }

    public void drawDynamicItemMulti(Player firstPlayer, Player secondPlayer, DynamicItem item,
                                 DrawText drawText, SpriteBatch batch){
//        if (!item.isVisible()) return;
//        Texture image;
//        if (firstPlayer.getItemHolding()!=null && firstPlayer.getItemHolding().equals(item)){
//            image = item.getImage();
//            drawText.drawNoteName(batch, drawText, getNoteItemHolding(item));
//        }
//        else
//        if (item.getDiscover() && item.getChosenImage()!=null){
//            image = item.getChosenImage();
//            if (item.getName().equals("puddle")){
//                drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". You can't take it,\nuse clothe or tissue to clean it.");
//            } else {
//                drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". Press enter to take it.");
//            }
//        }
//        else image = item.getImage();
//        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
        if (!item.isVisible()) return;
        Texture image;
        if (firstPlayer.getItemHolding() != null && firstPlayer.getItemHolding().equals(item)
                || secondPlayer.getItemHolding() != null && secondPlayer.getItemHolding().equals(item)){
            image = item.getImage();
            if (firstPlayer.getItemHolding() != null && firstPlayer.getItemHolding().equals(item)){
                drawText.drawNoteName(batch, drawText, getNoteItemHoldingMulti(item, "enter"), 0);
            } else {
                drawText.drawNoteName(batch, drawText, getNoteItemHoldingMulti(item, "space"), incY);
            }
//            drawText.drawStaticText(batch, player.getItemHolding().getName(), 180, 870, 0.5f);
        }
        else
            if (item.getDiscover() && item.getChosenImage()!=null){
                if (item.getPlayerDiscover().equals(firstPlayer)){
                    image = item.getChosenImage();
                    if (item.getName().equals("puddle")){
                        drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". You can't take it,\nuse clothe or tissue to clean it.", 0);
                    } else {
                        drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". Press enter to take it.", 0);
                    }
                } else{
                    image = item.getChosenImage();
                    if (item.getName().equals("puddle")){
                        drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". You can't take it,\nuse clothe or tissue to clean it.", incY);
                    } else {
                        drawText.drawNoteName(batch, drawText, "It's a " + item.getName() + ". Press space to take it.", incY);
                    }
                }
            }
        else image = item.getImage();
        batch.draw(image, item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    public void drawDynamicMulti(Player firstPlayer, Player secondPlayer, DrawText drawText,
                                 ArrayList<DynamicItem> items, SpriteBatch batch){
        for (DynamicItem item : items) {
            drawDynamicItemMulti(firstPlayer, secondPlayer, item, drawText, batch);
        }
    }
}
