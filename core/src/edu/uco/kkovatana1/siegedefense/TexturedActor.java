package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TexturedActor extends Actor {

    protected TextureRegion textureRegion;

    public TexturedActor(String filePath){
        this.textureRegion = new TextureRegion(GameMain.assetManager.get(filePath, Texture.class));
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(textureRegion, this.getX(), this.getY());
    }
}
