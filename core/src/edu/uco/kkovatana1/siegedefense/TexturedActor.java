package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TexturedActor extends Actor {

    protected TextureRegion textureRegion;

    public TexturedActor(Texture texture){
        this.textureRegion = new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(textureRegion, this.getX(), this.getY());
    }
}
